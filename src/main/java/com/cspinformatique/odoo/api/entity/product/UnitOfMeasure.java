package com.cspinformatique.odoo.api.entity.product;

public class UnitOfMeasure {
	public enum Type{bigger, reference, smaller}
	
	private Integer id;
	private String name;
	private UnitOfMeasureCategory category;
	private Double factor;
	private Double rounding;
	private Boolean active;
	private Type type;
	
	public UnitOfMeasure(){
		
	}

	public UnitOfMeasure(
		Integer id, 
		String name,
		UnitOfMeasureCategory category, 
		Double factor, 
		Double rounding,
		Boolean active, 
		Type type
	) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.factor = factor;
		this.rounding = rounding;
		this.active = active;
		this.type = type;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public UnitOfMeasureCategory getCategory() {
		return category;
	}

	public void setCategory(UnitOfMeasureCategory category) {
		this.category = category;
	}

	public Double getFactor() {
		return factor;
	}

	public void setFactor(Double factor) {
		this.factor = factor;
	}

	public Double getRounding() {
		return rounding;
	}

	public void setRounding(Double rounding) {
		this.rounding = rounding;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}
