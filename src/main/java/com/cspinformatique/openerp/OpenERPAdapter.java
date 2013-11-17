package com.cspinformatique.openerp;

import org.apache.xmlrpc.XmlRpcException;

import com.cspinformatique.openerp.core.FilterCollection;
import com.cspinformatique.openerp.core.ObjectAdapter;
import com.cspinformatique.openerp.core.Row;
import com.cspinformatique.openerp.core.RowCollection;
import com.cspinformatique.openerp.core.Session;

import java.util.Map;
import java.util.Map.Entry;

public class OpenERPAdapter {
	private Session openERPSession;
	private OpenERPConfig openERPConfig;
	
	private boolean sessionStarted = false;
	
	public OpenERPAdapter(
		OpenERPConfig openERPConfig
	){
		this.openERPConfig = openERPConfig;
		
		this.openERPSession =	new Session(
									this.openERPConfig.getHost(), 
									this.openERPConfig.getPort(), 
									this.openERPConfig.getDatabase(),
									this.openERPConfig.getUser(), 
									this.openERPConfig.getPassword()
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
