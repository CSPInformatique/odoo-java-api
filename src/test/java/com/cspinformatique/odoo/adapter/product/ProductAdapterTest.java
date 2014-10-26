package com.cspinformatique.odoo.adapter.product;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.cspinformatique.odoo.api.adapter.company.CompanyAdapter;
import com.cspinformatique.odoo.api.adapter.product.CategoryAdapter;
import com.cspinformatique.odoo.api.adapter.product.ProductAdapter;
import com.cspinformatique.odoo.api.adapter.product.TemplateAdapter;
import com.cspinformatique.odoo.api.adapter.product.UnitOfMeasureAdapter;
import com.cspinformatique.odoo.api.entity.core.Company;
import com.cspinformatique.odoo.api.entity.product.Category;
import com.cspinformatique.odoo.api.entity.product.Product;
import com.cspinformatique.odoo.api.entity.product.Template;
import com.cspinformatique.odoo.api.entity.product.UnitOfMeasure;
import com.cspinformatique.odoo.api.entity.product.Template.CostMethod;
import com.cspinformatique.odoo.config.TestConfig;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = TestConfig.class)
public class ProductAdapterTest {
	@Autowired
	private CategoryAdapter categoryAdapter;

	@Autowired
	private CompanyAdapter companyAdapter;

	@Autowired
	private TemplateAdapter templateAdapter;

	@Autowired
	private ProductAdapter productAdapter;

	@Autowired
	private UnitOfMeasureAdapter unitOfMeasureAdapter;

	@Test
	public void createProductTest() {
		Template template = this.getTestTemplate();
		template.setId(templateAdapter.createEntity(template));

		Assert.assertNotEquals(Integer.valueOf(0), template.getId());

		int productId = productAdapter.createEntity(new Product(0, 10d, 9d,
				"AAA", true, template, null));

		Assert.assertTrue(0 != productId);
	}

	@Test
	public void findProduct() {
		Product product = productAdapter.findByField("name", "Banane");
		Assert.assertNotNull(product);
	}

	public Template getTestTemplate() {
		UnitOfMeasure unitOfMeasure = this.unitOfMeasureAdapter.findById(1);
		Category category = this.categoryAdapter.findById(1);
		Company company = this.companyAdapter
				.findbyName("La Dimension Fantastique");

		return new Template(0, // Integer id
				"Produit de test", // String name
				"Description d'un produit de test", // String description
				null, // String descriptionPurchase
				null, // String descriptionSale
				null, // Type type
				category, // Category category
				null, // Double listPrice
				null, // Double standardPrice
				null, // Float volume
				null, // Float weight
				null, // Float weightNet
				CostMethod.standard, // CostMethod costMethod
				null, // Float warranty
				null, // Boolean saleOk
				null, // State state
				unitOfMeasure, // UnitOfMeasure unitOfMeasure
				unitOfMeasure, // UnitOfMeasure unitOfMeasurePurchase
				null, // Float unitOfSaleCoefficient
				null, // MeasureType measureType
				company // Company company
		);
	}
}
