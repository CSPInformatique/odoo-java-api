package com.cspinformatique.openerp.adapter.product;

import com.cspinformatique.openerp.adapter.EntityAdapter;
import com.cspinformatique.openerp.core.EntityParams;
import com.cspinformatique.openerp.core.OpenERPUtil;
import com.cspinformatique.openerp.core.Row;
import com.cspinformatique.openerp.entity.core.Company;
import com.cspinformatique.openerp.entity.product.Category;
import com.cspinformatique.openerp.entity.product.Template;
import com.cspinformatique.openerp.entity.product.Template.CostMethod;
import com.cspinformatique.openerp.entity.product.Template.MeasureType;
import com.cspinformatique.openerp.entity.product.UnitOfMeasure;

public class TemplateAdapter extends EntityAdapter<Template>{	
	
	public TemplateAdapter(OpenERPUtil openERPUtil){
		super(openERPUtil);
	}
	
	public EntityParams buildEntityParameters(Template template){		
		EntityParams templateParams = new EntityParams();
	
		templateParams.addParam("name", template.getName());
		templateParams.addParam("description", template.getDescription());
		templateParams.addParam("descriptionPurchase", template.getDescriptionPurchase());
		templateParams.addParam("descriptionSale", template.getDescriptionSale());
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
		templateParams.addParam("uos_id", template.getUnitOfMeasureSale().getId());
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
			"uos_id",
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
		template.setCategory(new Category(((Integer)row.get("categ_id")), null, null, null, null, null));
		template.setListPrice((Double)row.get("list_price"));
		template.setStandardPrice((Double)row.get("standard_price"));
		template.setVolume((Float)row.get("volume"));
		template.setWeight((Float)row.get("weight"));
		template.setWeightNet((Float)row.get("weight_net"));
		template.setCostMethod(CostMethod.valueOf((String)row.get("cost_method")));
		template.setWarranty((Float)row.get("warranty"));
		template.setSaleOk((Boolean)row.get("sale_ok"));
		template.setUnitOfMeasure(new UnitOfMeasure((Integer)row.get("uom_id"), null, null, null, null, null, null));
		template.setUnitOfMeasurePurchase(new UnitOfMeasure((Integer)row.get("uom_po_id"), null, null, null, null, null, null));
		template.setUnitOfMeasureSale(new UnitOfMeasure((Integer)row.get("uos_id"), null, null, null, null, null, null));
		template.setUnitOfSaleCoefficient((Float)row.get("uos_coeff"));
		template.setMeasureType(MeasureType.valueOf((String)row.get("mes_type")));
		template.setCompany(new Company((Integer)row.get("company_id"), null));
		
		return template;
	}
}
