package com.cspinformatique.odoo.api.adapter.product;

import com.cspinformatique.odoo.api.adapter.EntityAdapter;
import com.cspinformatique.odoo.api.core.EntityParams;
import com.cspinformatique.odoo.api.core.OdooAdapter;
import com.cspinformatique.odoo.api.core.Row;
import com.cspinformatique.odoo.api.entity.product.Product;

public class ProductAdapter extends EntityAdapter<Product> {
	private TemplateAdapter templateAdapter;
	
	public ProductAdapter(OdooAdapter odooAdapter, TemplateAdapter templateAdapter) {
		super(odooAdapter);
		
		this.templateAdapter = templateAdapter;
	}

	@Override
	protected String getKey() {
		return "product.product";
	}

	@Override
	protected EntityParams buildEntityParameters(Product product) {
		EntityParams productParams = new EntityParams();

		productParams.addParam("price", product.getPrice());
		productParams.addParam("lst_price", product.getListPrice());
		productParams.addParam("code", product.getCode());
		productParams.addParam("active", product.isActive());
		productParams.addParam("product_tmpl_id", product.getTemplate().getId());
		productParams.addParam("ean13", product.getEan13());
		
		return productParams;
	}
	
	@Override
	protected String[] buildFieldsList() {
		return new String[]{
			"price",
			"lst_price",
			"code",
			"active",
			"product_tmpl_id",
			"ean13",
		};
	}

	@Override
	protected Product transcodeRowToEntity(Row row) {
		return new Product(
			row.getID(), 
			(Double)row.get("price"), 
			(Double)row.get("lst_price"), 
			(String)row.get("code"), 
			(Boolean)row.get("active"), 
			templateAdapter.findById((Integer)((Object[])row.get("product_tmpl_id"))[0]),
			(String)row.get("ean13")
		);
	}

}
