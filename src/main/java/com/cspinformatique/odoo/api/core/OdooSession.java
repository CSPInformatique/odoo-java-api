package com.cspinformatique.odoo.api.core;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.apache.xmlrpc.XmlRpcException;

import com.cspinformatique.odoo.api.core.xmlrpc.OdooXmlRpcTemplate;
import com.cspinformatique.odoo.api.core.xmlrpc.OdooXmlRpcClient.RPCServices;

public class OdooSession {
	private String databaseName;
	private String userName;
	private String password;
	
	private int userID;
	private Context context = new Context();
	private static boolean connecting = false;
	
	private OdooXmlRpcTemplate odooXmlRpcTemplate;

	public OdooSession(OdooXmlRpcTemplate odooXmlRpcTemplate, String databaseName, String userName, String password) {
		this.odooXmlRpcTemplate = odooXmlRpcTemplate; 
		this.databaseName = databaseName;
		this.userName = userName;
		this.password = password;		
	}
	
	public boolean createDatabase(
			String database, 
			String demo, 
			String lang, 
			String password
		){
		return this.odooXmlRpcTemplate.executeCall(RPCServices.RPC_COMMON, "create_database", new Object[]{database, demo, lang, password}, Boolean.class);
	}
	
	public void dropDatabase(String database, String password){
		
	}

	/**
	 * Returns an initialized OdooCommand object for ease of reference. A
	 * OdooCommand provides basic calls to the server
	 * 
	 * @return
	 */
	public OdooCommand getOpenERPCommand() {
		return new OdooCommand(this);
	}

	/**
	 * Returns an initialized ObjectAdapter object for ease of reference. A
	 * ObjectAdapter object does type conversions and error checking before
	 * making a call to the server
	 * 
	 * @return
	 */
	public ObjectAdapter getObjectAdapter(String objectName){
		return new ObjectAdapter(this, objectName);
	}

	/***
	 * Starts a session on the OpenERP server and saves the UserID for use in
	 * later calls
	 * 
	 * @throws Exception
	 */
	public void startSession() {

		try {
			// 21/07/2012 - Database listing may not be enabled
			// (--no-database-list or list_db=false).
			// Only provides additional information in any case.
			List<String> dbList = this.getDatabaseList();
			
			if (dbList.indexOf(databaseName) < 0) {
				StringBuffer dbListBuff = new StringBuffer();
				for (String dbName : dbList)
					dbListBuff.append(dbName
							+ System.getProperty("line.separator"));

				throw new Exception(
						"Error while connecting to OpenERP.  Database ["
								+ databaseName + "] "
								+ " was not found in the following list: "
								+ System.getProperty("line.separator")
								+ System.getProperty("line.separator")
								+ dbListBuff.toString());
			}
		} catch (Exception e) {

		}

		// Synchronize all threads to login. If you login with the same user at
		// the same time you get concurrency
		// errors in the OpenERP server (for example by running a multi threaded
		// ETL process like Kettle).
		OdooSession.startConnecting();

		Object id = null;
		try {
			id = odooXmlRpcTemplate.executeCall(RPCServices.RPC_COMMON, "login", new Object[] { databaseName,
					userName, password }, Object.class);
		} catch (ClassCastException c) {
			// General exception is only thrown if the database doesn't exist.
			// Incorrect username and password will return an id of 0.
			// Incorrect server parameters (servername/port) will not be caught
			// here
			throw new RuntimeException("Database " + databaseName + " does not exist");
		} finally {
			OdooSession.connecting = false;
		}

		if (id instanceof Integer)
			userID = (Integer) id;
		else
			throw new RuntimeException(
					"Incorrect username and/or password.  Login Failed.");

		this.context.clear();
		@SuppressWarnings("unchecked")
		HashMap<String, Object> openerpContext = (HashMap<String, Object>) this
				.executeCommand("res.users", "context_get", new Object[] {});
		this.context.putAll(openerpContext);

		// Standard behavior is web/gui clients.
		this.context.setActiveTest(true);

	}

	private synchronized static void startConnecting() {
		while (OdooSession.connecting) {
			try {
				Thread.sleep(100);
			} catch (Exception e) {
			}
		}
		OdooSession.connecting = true;
	}

	/***
	 * Get a list of databases available on a specific host and port
	 * 
	 * @param host
	 *            Host name or IP address where the OpenERP server is hosted
	 * @param port
	 *            XML-RPC port number to connect to. Typically 8069.
	 * @return A list of databases available for the OpenERP instance
	 * @throws XmlRpcException
	 */
	public List<String> getDatabaseList()
			throws XmlRpcException {
		return Arrays.asList(odooXmlRpcTemplate.executeCall(RPCServices.RPC_DATABASE, "list",  new Object[]{}, String[].class));
	}

	/**
	 * Executes any command on the server linked to the /xmlrpc/object service.
	 * All parameters are prepended by: "databaseName,userID,password"
	 * 
	 * @param objectName
	 *            Object or model name to execute the command on
	 * @param commandName
	 *            Command name to execute
	 * @param parameters
	 *            List of parameters for the command. For easy of use, consider
	 *            the OdooCommand object or ObjectAdapter
	 * @return The result of the call
	 * @throws XmlRpcException
	 */
	public Object executeCommand(
		final String objectName,
		final String commandName, 
		final Object[] parameters
	)  {
		Object[] connectionParams = new Object[] { databaseName, userID,
				password, objectName, commandName };

		// Combine the connection parameters and command parameters
		Object[] params = new Object[connectionParams.length
				+ (parameters == null ? 0 : parameters.length)];
		System.arraycopy(connectionParams, 0, params, 0,
				connectionParams.length);

		if (parameters != null && parameters.length > 0)
			System.arraycopy(parameters, 0, params, connectionParams.length,
					parameters.length);

		return odooXmlRpcTemplate.executeCall(RPCServices.RPC_OBJECT, "execute", params, Object.class);
	}

	/**
	 * Returns the OpenERP server version for this session
	 * 
	 * @return
	 * @throws XmlRpcException
	 */
	public Version getServerVersion() {
		return new Version(this.odooXmlRpcTemplate.executeCall(RPCServices.RPC_DATABASE, "server_version", new Object[] {}, Object.class).toString());
	}

	/**
	 * Retrieves the context object for the session to set properties on
	 * 
	 * @return
	 */
	public Context getContext() {
		return context;
	}
}
