package com.cspinformatique.odoo.api.entity.product;

import java.util.List;

import com.cspinformatique.odoo.api.entity.core.Company;
import com.cspinformatique.odoo.api.entity.core.Partner;
import com.cspinformatique.odoo.api.entity.pricelist.PartnerInfo;

public class SupplierInfo {
	private Integer id;
	private Partner partner;
	private String productName;
	private String productCode;
	private Integer sequence;
	private UnitOfMeasure productUnitOfMeasure;
	private Float minimalQuantity;
	private Product product;
	private Integer delay;
	private List<PartnerInfo> pricelists;
	private Company company;
	
	public SupplierInfo(){
		
	}

	public SupplierInfo(
		Integer id, 
		Partner partner, 
		String productName,
		String productCode, 
		Integer sequence,
		UnitOfMeasure productUnitOfMeasure, 
		Float minimalQuantity,
		Product product, 
		Integer delay, 
		List<PartnerInfo> pricelists,
		Company company
	) {
		this.id = id;
		this.partner = partner;
		this.productName = productName;
		this.productCode = productCode;
		this.sequence = sequence;
		this.productUnitOfMeasure = productUnitOfMeasure;
		this.minimalQuantity = minimalQuantity;
		this.product = product;
		this.delay = delay;
		this.pricelists = pricelists;
		this.company = company;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Partner getPartner() {
		return partner;
	}

	public void setPartner(Partner partner) {
		this.partner = partner;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

	public UnitOfMeasure getProductUnitOfMeasure() {
		return productUnitOfMeasure;
	}

	public void setProductUnitOfMeasure(UnitOfMeasure productUnitOfMeasure) {
		this.productUnitOfMeasure = productUnitOfMeasure;
	}

	public Float getMinimalQuantity() {
		return minimalQuantity;
	}

	public void setMinimalQuantity(Float minimalQuantity) {
		this.minimalQuantity = minimalQuantity;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public Integer getDelay() {
		return delay;
	}

	public void setDelay(Integer delay) {
		this.delay = delay;
	}

	public List<PartnerInfo> getPricelists() {
		return pricelists;
	}

	public void setPricelists(List<PartnerInfo> pricelists) {
		this.pricelists = pricelists;
	}

	public Company getCompany() {
		return company;
	}

	public void setCompany(Company company) {
		this.company = company;
	}
}
