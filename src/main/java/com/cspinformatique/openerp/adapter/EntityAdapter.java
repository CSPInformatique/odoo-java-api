package com.cspinformatique.openerp.adapter;

import java.util.ArrayList;
import java.util.List;

import com.cspinformatique.openerp.core.EntityParams;
import com.cspinformatique.openerp.core.FilterCollection;
import com.cspinformatique.openerp.core.OpenERPUtil;
import com.cspinformatique.openerp.core.Row;
import com.cspinformatique.openerp.core.RowCollection;

public abstract class EntityAdapter<T> {
	private OpenERPUtil openERPUtil;
	
	public EntityAdapter(OpenERPUtil openERPUtil){
		this.openERPUtil = openERPUtil;
	}
	
	protected abstract String getKey();
	
	protected abstract EntityParams buildEntityParameters(T entity);
	
	protected abstract String[] buildFieldsList();
	
	protected abstract T transcodeRowToEntity(Row row);
	
	public Integer createEntity(T entity){
		return this.openERPUtil.createObject(this.buildEntityParameters(entity), this.getKey());
	}
	
	public T findOne(FilterCollection filters){
		RowCollection results = this.openERPUtil.searchObject(filters, this.buildFieldsList(), this.getKey());
		
		if(results.size() == 0){
			return null;
		}else if(results.size() > 1){
			throw new RuntimeException("Invalid resultset : expected 1, retreived " + results.size());
		}else{
			return this.transcodeRowToEntity(results.get(0));
		}
	}
	
	public List<T> find(FilterCollection filters){
		RowCollection results = this.openERPUtil.searchObject(filters, this.buildFieldsList(), this.getKey());
		
		ArrayList<T> entities = new ArrayList<T>();
		for(Row row : results){
			entities.add(this.transcodeRowToEntity(row));
		}
		
		return entities;
	}
}
