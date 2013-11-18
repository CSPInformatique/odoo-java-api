package com.cspinformatique.openerp.entity.product;

public class Category {
	public enum Type{view, normal}
	
	private Integer id;
	private String name;
	private String completeName;
	private Category parent;
	private Integer sequence;
	private Type type;
	
	public Category(){
		
	}

	public Category(
		Integer id, 
		String name, 
		String completeName,
		Category parentId, 
		Integer sequence, 
		Type type
	) {
		this.id = id;
		this.name = name;
		this.completeName = completeName;
		this.parent = parentId;
		this.sequence = sequence;
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

	public String getCompleteName() {
		return completeName;
	}

	public void setCompleteName(String completeName) {
		this.completeName = completeName;
	}

	public Category getParent() {
		return parent;
	}

	public void setParent(Category parent) {
		this.parent = parent;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}
}
