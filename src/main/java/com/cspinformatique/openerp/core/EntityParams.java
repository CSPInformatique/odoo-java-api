package com.cspinformatique.openerp.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntityParams {
	private Map<String, Object> params;
	
	public EntityParams(){
		this.params = new HashMap<String, Object>();
	}
	
	public void addParam(String key, Object param){
		this.params.put(key, this.getValue(param));
	}
	
	public Map<String, Object> getParams(){
		return this.params;
	}
	
	// Datetime format : ("yyyy-MM-dd HH:mm:ss")
	// Date format : ("yyyy-MM-dd")
	@SuppressWarnings("rawtypes")
	private Object getValue(Object value){
		Object returnValue = null;
		if(value != null){
			if(value instanceof Iterable){
				List<Object> values = new ArrayList<Object>();
				for(Object object : (Iterable)value){
					values.add(this.getValue(object));
				}
				
				returnValue = values.toArray();
			}
			
			return value.toString();
		}else{
			return returnValue;
		}
	}
}