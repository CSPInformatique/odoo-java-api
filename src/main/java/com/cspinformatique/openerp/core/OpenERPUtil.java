package com.cspinformatique.openerp.core;

public class OpenERPUtil {
	private OpenERPAdapter openERPAdapter;
	
	public OpenERPUtil(OpenERPAdapter openERPAdapter){
		this.openERPAdapter = openERPAdapter;
	}
	
	public Integer createObject(EntityParams entityParams, String entityKey){
		return openERPAdapter.createObject(entityParams.getParams(), entityKey);
	}
	
	public RowCollection searchObject(FilterCollection filters, String[] fields, String objectType){
		return this.openERPAdapter.searchAndReadObject(filters, fields, objectType);
	}
}
