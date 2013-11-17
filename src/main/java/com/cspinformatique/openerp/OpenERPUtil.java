package com.cspinformatique.openerp;

import java.util.Map;

import com.cspinformatique.openerp.core.FilterCollection;
import com.cspinformatique.openerp.core.RowCollection;

public class OpenERPUtil {
	private OpenERPAdapter openERPAdapter;
	
	public OpenERPUtil(OpenERPAdapter openERPAdapter){
		this.openERPAdapter = openERPAdapter;
	}
	
	public void createObject(Map<String, Object> parameters, String entityKey){
		openERPAdapter.createObject(parameters, entityKey);
	}
	
	public RowCollection searchObject(FilterCollection filters, String[] fields, String objectType){
		return this.openERPAdapter.searchAndReadObject(filters, fields, objectType);
	}
}
