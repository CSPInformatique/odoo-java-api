package com.cspinformatique.odoo.api.adapter.product;

import com.cspinformatique.odoo.api.adapter.EntityAdapter;
import com.cspinformatique.odoo.api.core.EntityParams;
import com.cspinformatique.odoo.api.core.OdooAdapter;
import com.cspinformatique.odoo.api.core.Row;
import com.cspinformatique.odoo.api.entity.product.UnitOfMeasure;
import com.cspinformatique.odoo.api.entity.product.UnitOfMeasureCategory;

public class UnitOfMeasureAdapter extends EntityAdapter<UnitOfMeasure> {

	public UnitOfMeasureAdapter(OdooAdapter odooAdapter) {
		super(odooAdapter);
	}

	@Override
	protected String getKey() {
		return "product.uom";
	}

	@Override
	protected EntityParams buildEntityParameters(UnitOfMeasure entity) {
		throw new RuntimeException("Not supported yet");
	}

	@Override
	protected String[] buildFieldsList() {
		return new String[]{
			"name",
			"category_id",
			"factor",
			"rounding",
			"active",
			"uom_type",
		};
	}

	@Override
	protected UnitOfMeasure transcodeRowToEntity(Row row) {
		Object[] category = (Object[])row.get("category_id");
		return new UnitOfMeasure(
			row.getID(), 
			(String)row.get("name"), 
			new UnitOfMeasureCategory((Integer)category[0], (String)category[1]), 
			(Double)row.get("factor"), 
			(Double)row.get("rounding"), 
			(Boolean)row.get("active"), 
			UnitOfMeasure.Type.valueOf((String)row.get("uom_type"))
		);
	}
}
