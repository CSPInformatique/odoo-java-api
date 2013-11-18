package com.cspinformatique.openerp.adapter.product;

import com.cspinformatique.openerp.adapter.EntityAdapter;
import com.cspinformatique.openerp.core.EntityParams;
import com.cspinformatique.openerp.core.OpenERPUtil;
import com.cspinformatique.openerp.core.Row;
import com.cspinformatique.openerp.entity.product.Category;

public class CategoryAdapter extends EntityAdapter<Category> {

	public CategoryAdapter(OpenERPUtil openERPUtil) {
		super(openERPUtil);
	}

	@Override
	protected String getKey() {
		return "product.category";
	}

	@Override
	protected EntityParams buildEntityParameters(Category entity) {
		throw new RuntimeException("Not supported yet.");
	}

	@Override
	protected String[] buildFieldsList() {
		return new String[]{
			"name",
			"complete_name",
			"parent_id",
			"sequence",
			"type"
		};
	}

	@Override
	protected Category transcodeRowToEntity(Row row) {
		return new Category(
			row.getID(), 
			(String)row.get("name"), 
			(String)row.get("complete_name"), 
			new Category((Integer)row.get("parent_id"), null, null, null, null, null), 
			(Integer)row.get("sequence"), 
			Category.Type.valueOf((String)row.get("type"))
		);
	}
}
