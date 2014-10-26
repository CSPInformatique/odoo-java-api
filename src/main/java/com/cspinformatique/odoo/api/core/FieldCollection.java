package com.cspinformatique.odoo.api.core;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class FieldCollection extends ArrayList<Field> {
	 /**
	 * 
	 */
	private static final long serialVersionUID = -6365161627257600308L;

	/**
     * Sorts the field entries in this field collection by Name
     */
    public void SortByName(){
      Collections.sort(this,new FieldByNameComparator());
    }
    
    private class FieldByNameComparator implements Comparator<Field> { 

      @Override
      public int compare(Field arg0, Field arg1) {
        return arg0.getName().compareTo(arg1.getName());
      }
      
    }
}
