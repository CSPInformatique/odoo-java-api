package com.cspinformatique.odoo.api.entity.product;

public class Product {
	private Integer id;
	private Double price;
	private Double listPrice;
	private String code;
	private Boolean active;
	private Template template;
	private String ean13;
	
	public Product(){
		
	}

	public Product(
		Integer id, 
		Double price, 
		Double listPrice, 
		String code,
		Boolean active, 
		Template template, 
		String ean13
	) {
		this.id = id;
		this.price = price;
		this.listPrice = listPrice;
		this.code = code;
		this.active = active;
		this.template = template;
		this.ean13 = ean13;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getListPrice() {
		return listPrice;
	}

	public void setListPrice(Double listPrice) {
		this.listPrice = listPrice;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}

	public String getEan13() {
		return ean13;
	}

	public void setEan13(String ean13) {
		this.ean13 = ean13;
	}
}
