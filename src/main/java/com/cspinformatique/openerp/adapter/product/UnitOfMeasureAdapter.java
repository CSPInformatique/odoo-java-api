package com.cspinformatique.openerp.adapter.product;

import com.cspinformatique.openerp.adapter.EntityAdapter;
import com.cspinformatique.openerp.core.EntityParams;
import com.cspinformatique.openerp.core.OpenERPUtil;
import com.cspinformatique.openerp.core.Row;
import com.cspinformatique.openerp.entity.product.UnitOfMeasure;
import com.cspinformatique.openerp.entity.product.UnitOfMeasureCategory;

public class UnitOfMeasureAdapter extends EntityAdapter<UnitOfMeasure> {

	public UnitOfMeasureAdapter(OpenERPUtil openERPUtil) {
		super(openERPUtil);
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
		return new UnitOfMeasure(
			row.getID(), 
			(String)row.get("name"), 
			new UnitOfMeasureCategory((Integer)row.get("category_id"), null), 
			(Float)row.get("factor"), 
			(Float)row.get("rounding"), 
			(Boolean)row.get("active"), 
			UnitOfMeasure.Type.valueOf((String)row.get("uom_type"))
		);
	}
}
