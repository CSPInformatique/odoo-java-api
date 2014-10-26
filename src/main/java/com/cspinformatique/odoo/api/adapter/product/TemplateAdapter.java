package com.cspinformatique.odoo.api.adapter.product;

import com.cspinformatique.odoo.api.adapter.EntityAdapter;
import com.cspinformatique.odoo.api.adapter.company.CompanyAdapter;
import com.cspinformatique.odoo.api.core.EntityParams;
import com.cspinformatique.odoo.api.core.OdooAdapter;
import com.cspinformatique.odoo.api.core.Row;
import com.cspinformatique.odoo.api.entity.product.Template;
import com.cspinformatique.odoo.api.entity.product.Template.CostMethod;
import com.cspinformatique.odoo.api.entity.product.Template.MeasureType;

public class TemplateAdapter extends EntityAdapter<Template>{	
	private CategoryAdapter categoryAdapter;
	CompanyAdapter companyAdapter;
	private UnitOfMeasureAdapter unitOfMeasureAdapter;
	
	public TemplateAdapter(OdooAdapter odooAdapter, CategoryAdapter categoryAdapter, CompanyAdapter companyAdapter, UnitOfMeasureAdapter unitOfMeasureAdapter){
		super(odooAdapter);
		
		this.categoryAdapter = categoryAdapter;
		this.companyAdapter = companyAdapter;
		this.unitOfMeasureAdapter = unitOfMeasureAdapter;
	}
	
	public EntityParams buildEntityParameters(Template template){		
		EntityParams templateParams = new EntityParams();
		
		templateParams.addParam("name", template.getName());
		templateParams.addParam("description", template.getDescription());
		templateParams.addParam("description_purchase", template.getDescriptionPurchase());
		templateParams.addParam("description_sale", template.getDescriptionSale());
		templateParams.addParam("categ_id", template.getCategory().getId());
		templateParams.addParam("list_price", template.getListPrice());
		templateParams.addParam("standard_price", template.getStandardPrice());
		templateParams.addParam("volume", template.getVolume());
		templateParams.addParam("weight", template.getWeight());
		templateParams.addParam("weight_net", template.getWeightNet());
		templateParams.addParam("cost_method", template.getCostMethod());
		templateParams.addParam("warranty", template.getWarranty());
		templateParams.addParam("sale_ok", template.getSaleOk());
		templateParams.addParam("uom_id", template.getUnitOfMeasure().getId());
		templateParams.addParam("uom_po_id", template.getUnitOfMeasurePurchase().getId());
		templateParams.addParam("uos_coeff", template.getUnitOfSaleCoefficient());
		templateParams.addParam("mes_type", template.getMeasureType());
		templateParams.addParam("company_id", template.getCompany().getId());
		
		return templateParams;
	}
	
	protected String[] buildFieldsList(){
		return new String[]{
			"name",
			"description",
			"descriptionPurchase",
			"descriptionSale",
			"categ_id",
			"list_price",
			"standard_price",
			"volume",
			"weight",
			"weight_net",
			"cost_method",
			"warranty",
			"sale_ok",
			"uom_id",
			"uom_po_id",
			"uos_coeff",
			"mes_type",
			"company_id"
		};
	}
	
	protected String getKey(){
		return "product.template";
	}
	
	protected Template transcodeRowToEntity(Row row){
		Template template = new Template();
		
		template.setId(row.getID());
		template.setName((String)row.get("name"));
		template.setDescription((String)row.get("description"));
		template.setDescriptionPurchase((String)row.get("descriptionPurchase"));
		template.setDescriptionSale((String)row.get("descriptionSale"));
		template.setCategory(categoryAdapter.findById(((Integer)((Object[])row.get("categ_id"))[0])));
		template.setListPrice((Double)row.get("list_price"));
		template.setStandardPrice((Double)row.get("standard_price"));
		template.setVolume((Double)row.get("volume"));
		template.setWeight((Double)row.get("weight"));
		template.setWeightNet((Double)row.get("weight_net"));
		template.setCostMethod(CostMethod.valueOf((String)row.get("cost_method")));
		template.setWarranty((Double)row.get("warranty"));
		template.setSaleOk((Boolean)row.get("sale_ok"));
		template.setUnitOfMeasure(unitOfMeasureAdapter.findById((Integer)((Object[])row.get("uom_id"))[0]));
		template.setUnitOfMeasurePurchase(unitOfMeasureAdapter.findById((Integer)((Object[])row.get("uom_po_id"))[0]));
		template.setUnitOfSaleCoefficient((Double)row.get("uos_coeff"));
		template.setMeasureType(MeasureType.valueOf((String)row.get("mes_type")));
		template.setCompany(companyAdapter.findById((Integer)((Object[])row.get("company_id"))[0]));
		
		return template;
	}
}
