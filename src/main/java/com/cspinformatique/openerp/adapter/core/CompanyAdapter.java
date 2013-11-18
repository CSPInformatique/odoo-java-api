package com.cspinformatique.openerp.adapter.core;

import com.cspinformatique.openerp.adapter.EntityAdapter;
import com.cspinformatique.openerp.core.EntityParams;
import com.cspinformatique.openerp.core.OpenERPUtil;
import com.cspinformatique.openerp.core.Row;
import com.cspinformatique.openerp.entity.core.Company;

public class CompanyAdapter extends EntityAdapter<Company> {

	public CompanyAdapter(OpenERPUtil openERPUtil) {
		super(openERPUtil);
	}

	@Override
	protected String getKey() {
		return "res.company";
	}

	@Override
	protected EntityParams buildEntityParameters(Company entity) {
		throw new RuntimeException("Not suppoerted yet.");
	}

	@Override
	protected String[] buildFieldsList() {
		return new String[]{"name"};
	}

	@Override
	protected Company transcodeRowToEntity(Row row) {
		return new Company(row.getID(), (String)row.get("name"));
	}
}
