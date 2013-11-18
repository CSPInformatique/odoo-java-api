package com.cspinformatique.openerp.adapter.product;

import com.cspinformatique.openerp.adapter.EntityAdapter;
import com.cspinformatique.openerp.core.EntityParams;
import com.cspinformatique.openerp.core.OpenERPUtil;
import com.cspinformatique.openerp.core.Row;
import com.cspinformatique.openerp.entity.product.Product;
import com.cspinformatique.openerp.entity.product.Template;

public class ProductAdapter extends EntityAdapter<Product> {

	public ProductAdapter(OpenERPUtil openERPUtil) {
		super(openERPUtil);
	}

	@Override
	protected String getKey() {
		return "product.product";
	}

	@Override
	protected EntityParams buildEntityParameters(Product entity) {
		throw new RuntimeException("Not supported yet.");
	}

	/*
	private Integer id;
	private Double price;
	private Double listPrice;
	private String code;
	private Boolean active;
	private Template template;
	private String ean13;(non-Javadoc)
	 * @see com.cspinformatique.openerp.adapter.EntityAdapter#buildFieldsList()
	 */
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
			(String)row.get("active"), 
			(Boolean)row.get("active"), 
			new Template(
				(Integer)row.get("product_tmpl_id"), 
				null, 
				null, 
				null, 
				null, 
				null, 
				null, 
				null, 
				null, 
				null, 
				null, 
				null, 
				null, 
				null, 
				null, 
				null, 
				null, 
				null, 
				null, 
				null, 
				null, 
				null
			), 
			(String)row.get("ean13")
		);
	}

}
