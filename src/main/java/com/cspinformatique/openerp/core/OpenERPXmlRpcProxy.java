package com.cspinformatique.openerp.core;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class OpenERPXmlRpcProxy extends XmlRpcClient {
	/**
	 * Enum for the main RPC services that OpenERP expose
	 * 
	 * @author Pieter van der Merwe
	 */
	public enum RPCServices {
		RPC_COMMON, RPC_OBJECT, RPC_DATABASE
	}

	/**
	 * Enum for the RPC protocol used to connect to OpenERP
	 * 
	 * @author Pieter van der Merwe
	 */
	public enum RPCProtocol {
		RPC_HTTP, RPC_HTTPS
	}

	private final String RPC_COMMON_URL = "/xmlrpc/common";
	private final String RPC_OBJECT_URL = "/xmlrpc/object";
	private final String RPC_DATABASE_URL = "/xmlrpc/db";

	/**
	 * Proxy object to handle calls to and from the OpenERP server
	 * 
	 * @param protocol
	 *            Protocol to use when connecting to the RPC service ex.
	 *            http/https
	 * @param host
	 *            Host name or IP address where the OpenERP server is hosted
	 * @param port
	 *            XML-RPC port number to connect to. Typically 8069.
	 * @param service
	 *            OpenERP webservice to call (db/common etc)
	 */
	public OpenERPXmlRpcProxy(RPCProtocol protocol, String host, int port,
			RPCServices service) {
		super();

		String URL = "";

		switch (service) {
		case RPC_COMMON:
			URL = this.RPC_COMMON_URL;
			break;
		case RPC_OBJECT:
			URL = this.RPC_OBJECT_URL;
			break;
		case RPC_DATABASE:
			URL = this.RPC_DATABASE_URL;
			break;
		}

		String protocol_str = "";
		switch (protocol) {
		case RPC_HTTP:
			protocol_str = "http";
			break;

		default:
			protocol_str = "https";
			break;
		}

		XmlRpcClientConfigImpl xmlrpcConfigLogin = new XmlRpcClientConfigImpl();

		// OpenERP does not support extensions
		xmlrpcConfigLogin.setEnabledForExtensions(false);

		// The URL is hardcoded and can not be malformed
		try {
			xmlrpcConfigLogin.setServerURL(new URL(protocol_str, host, port,
					URL));
		} catch (MalformedURLException e) {
		}

		this.setConfig(xmlrpcConfigLogin);
	}

	/**
	 * Proxy object to handle calls to and from the OpenERP server. Uses the
	 * http protocol to connect.
	 * 
	 * @param host
	 *            Host name or IP address where the OpenERP server is hosted
	 * @param port
	 *            XML-RPC port number to connect to. Typically 8069.
	 * @param service
	 *            OpenERP webservice to call (db/common etc)
	 */
	public OpenERPXmlRpcProxy(String host, int port, RPCServices service) {
		this(RPCProtocol.RPC_HTTP, host, port, service);
	}

	/***
	 * Get a list of databases available on a specific host and port
	 * 
	 * @param protocol
	 *            Protocol to use when connecting to the RPC service ex.
	 *            http/https
	 * @param host
	 *            Host name or IP address where the OpenERP server is hosted
	 * @param port
	 *            XML-RPC port number to connect to
	 * @return A list of databases available for the OpenERP instance
	 * @throws XmlRpcException
	 */
	public static ArrayList<String> getDatabaseList(RPCProtocol protocol,
			String host, int port) throws XmlRpcException {
		OpenERPXmlRpcProxy client = new OpenERPXmlRpcProxy(protocol, host,
				port, RPCServices.RPC_DATABASE);

		// Retrieve databases
		Object[] result = (Object[]) client.execute("list", new Object[] {});

		ArrayList<String> finalResults = new ArrayList<String>();
		for (Object res : result)
			finalResults.add((String) res);

		return finalResults;
	}

	/***
	 * Get a list of databases available on a specific host and port with the
	 * http protocol.
	 * 
	 * @param host
	 *            Host name or IP address where the OpenERP server is hosted
	 * @param port
	 *            XML-RPC port number to connect to
	 * @return A list of databases available for the OpenERP instance
	 * @throws XmlRpcException
	 */
	public static ArrayList<String> getDatabaseList(String host, int port)
			throws XmlRpcException {
		return getDatabaseList(RPCProtocol.RPC_HTTP, host, port);
	}

	/***
	 * Returns the OpenERP server version. For example 7.0-20130216-002451 or
	 * 6.1-1
	 * 
	 * @param host
	 *            Host name or IP address where the OpenERP server is hosted
	 * @param port
	 *            XML-RPC port number to connect to
	 * @return The version number as a String
	 * @throws XmlRpcException
	 */
	public static Version getServerVersion(String host, int port) {
		return getServerVersion(RPCProtocol.RPC_HTTP, host, port);
	}

	/***
	 * Returns the OpenERP server version. For example 7.0-20130216-002451 or
	 * 6.1-1
	 * 
	 * @param protocol
	 *            Protocol to use when connecting to the RPC service ex.
	 *            http/https
	 * @param host
	 *            Host name or IP address where the OpenERP server is hosted
	 * @param port
	 *            XML-RPC port number to connect to
	 * @return The version number as a String
	 * @throws XmlRpcException
	 */
	public static Version getServerVersion(RPCProtocol protocol, String host, int port) {
		try{
			OpenERPXmlRpcProxy client = new OpenERPXmlRpcProxy(protocol, host, port, RPCServices.RPC_DATABASE);
	
			return new Version(client.execute("server_version", new Object[] {}).toString());
		}catch(XmlRpcException xmlRpcEx){
			throw new RuntimeException(xmlRpcEx);
		}
	}
}
