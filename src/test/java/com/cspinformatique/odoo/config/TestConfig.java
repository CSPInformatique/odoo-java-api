package com.cspinformatique.odoo.config;

import javax.annotation.Resource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.cspinformatique.odoo.api.adapter.company.CompanyAdapter;
import com.cspinformatique.odoo.api.adapter.product.CategoryAdapter;
import com.cspinformatique.odoo.api.adapter.product.ProductAdapter;
import com.cspinformatique.odoo.api.adapter.product.TemplateAdapter;
import com.cspinformatique.odoo.api.adapter.product.UnitOfMeasureAdapter;
import com.cspinformatique.odoo.api.core.OdooAdapter;
import com.cspinformatique.odoo.api.core.OdooConfiguration;

@Configuration
@PropertySource("classpath:config/odoo.properties")
public class TestConfig {
	@Resource
	private Environment env;

	public @Bean CategoryAdapter categoryAdapter() {
		return new CategoryAdapter(odooAdapter());
	}

	public @Bean CompanyAdapter companyAdater() {
		return new CompanyAdapter(odooAdapter());
	}

	public @Bean OdooConfiguration odooConfiguration() {
		return new OdooConfiguration(env.getRequiredProperty("odoo.host"),
				env.getRequiredProperty("odoo.port", Integer.class),
				env.getRequiredProperty("odoo.database"),
				env.getRequiredProperty("odoo.user"),
				env.getRequiredProperty("odoo.password"),
				env.getRequiredProperty("odoo.masterPassword"),
				env.getRequiredProperty("odoo.companyName"));
	}

	public @Bean OdooAdapter odooAdapter() {
		return new OdooAdapter(odooConfiguration());
	}

	public @Bean ProductAdapter productAdapter() {
		return new ProductAdapter(odooAdapter(), templateAdapter());
	}

	public @Bean UnitOfMeasureAdapter unitOfMeasureAdapter() {
		return new UnitOfMeasureAdapter(odooAdapter());
	}

	public @Bean TemplateAdapter templateAdapter() {
		return new TemplateAdapter(odooAdapter(), categoryAdapter(),
				companyAdater(), unitOfMeasureAdapter());
	}
}