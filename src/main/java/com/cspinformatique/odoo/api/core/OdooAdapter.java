package com.cspinformatique.odoo.api.core;

import org.apache.xmlrpc.XmlRpcException;










import com.cspinformatique.odoo.api.core.xmlrpc.OdooXmlRpcTemplate;

import java.util.Map;
import java.util.Map.Entry;

public class OdooAdapter {
	private OdooSession openERPSession;
	private OdooConfiguration odooConfiguration;
	
	private boolean sessionStarted = false;
	
	public OdooAdapter(
		OdooConfiguration odooConfiguration
	){
		this.odooConfiguration = odooConfiguration;
		
		this.openERPSession =	new OdooSession(
									new OdooXmlRpcTemplate(this.odooConfiguration.getHost(), this.odooConfiguration.getPort()), 
									this.odooConfiguration.getDatabase(),
									this.odooConfiguration.getUser(), 
									this.odooConfiguration.getPassword()
								);
	}
	
	public int createObject(Map<String, Object> parameters, String objectType){
		this.startSession();
		
		ObjectAdapter objectAdapter = openERPSession.getObjectAdapter(objectType);
		
		Row row = this.getRowForCreation(objectAdapter, parameters);
		
		this.loadParams(parameters, row);
		
		objectAdapter.createObject(row);
		
		return row.getID();	
	}
	
	private void loadParams(Map<String, Object> parameters, Row row){
		for(Entry<String, Object> entry : parameters.entrySet()){
			row.put(entry.getKey(), entry.getValue());
		}		
	}
	
	private Row getRowForCreation(ObjectAdapter objectAdapter, Map<String, Object> parameters){
		int i = 0;
		String[] fields = new String[parameters.size()];
		for(String key : parameters.keySet()){
			fields[i] = key;
			++i;
		}
		
		return objectAdapter.getNewRow(fields);
	}
	
	public RowCollection searchAndReadObject(FilterCollection filters, String[] fields, String objectType){
		try{
			this.startSession();
		
			ObjectAdapter objectAdapter = this.openERPSession.getObjectAdapter(objectType);
			
			return objectAdapter.searchAndReadObject(filters, fields);
		}catch(XmlRpcException xmlRpcEx){
			throw new RuntimeException(xmlRpcEx);
		}
	}
	
	private void startSession(){
		if(!this.sessionStarted){
			this.sessionStarted = true;
			this.openERPSession.startSession();
		}
	}
}
