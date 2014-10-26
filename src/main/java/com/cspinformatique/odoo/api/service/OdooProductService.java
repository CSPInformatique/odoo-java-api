package com.cspinformatique.odoo.api.service;

import com.cspinformatique.odoo.api.entity.product.Product;

public interface OdooProductService {

	public Product findProduct(String ean13);
}
