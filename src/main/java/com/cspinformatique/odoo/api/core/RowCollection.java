package com.cspinformatique.odoo.api.core;

import java.util.ArrayList;
import java.util.HashMap;

public class RowCollection extends ArrayList<Row> {
	private static final long serialVersionUID = 7223016661380166975L;

	public RowCollection(){
    }

    @SuppressWarnings("unchecked")
    public RowCollection(Object [] openERPResultSet, FieldCollection fields) throws OdooApiException{
            for (int i = 0; i < openERPResultSet.length; i++){
                    Row row = new Row((HashMap<String, Object>) openERPResultSet[i], fields);
                    this.add(row);
            }
    }

    @Override
    public void add(int index, Row element) {
            super.add(index, element);
    }

    @Override
    public boolean add(Row e) {
            return super.add(e);
    }
}
