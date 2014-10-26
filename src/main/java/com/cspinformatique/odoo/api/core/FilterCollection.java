package com.cspinformatique.odoo.api.core;

import java.util.ArrayList;

public class FilterCollection {
	private ArrayList<Object> filters = new ArrayList<Object>();

	/**
	 * Filter operators like AND,OR,NOT. See the filter class help for more
	 * info.
	 * 
	 * @author Pieter van der Merwe
	 * 
	 */
	public enum FilterOperator {
		AND, OR, NOT
	}

	/**
	 * Adds a filter specification to the existing list of filters
	 * 
	 * @param index
	 *            Index where the specified filter should be added
	 * @param fieldName
	 *            Name of the model that should be filtered on
	 * @param comparison
	 *            For example =, !=, >, >=, <, <=, like, ilike, in, not in,
	 *            child_of, parent_left, parent_right
	 * @param value
	 *            value that will be compared to 'fieldName' using the
	 *            'comparison'
	 * @throws OdooApiException
	 */
	public void add(int index, String fieldName, String comparison, Object value)
			throws OdooApiException {
		if (fieldName == null)
			throw new OdooApiException(
					"First filter parameter is mandatory.  Please read the OpenERP help.");

		if (comparison == null)
			throw new OdooApiException(
					"Second filter parameter is mandatory.  Please read the OpenERP help.");

		Object[] filter = new Object[] { fieldName, comparison, value };
		filters.add(index, filter);
	}

	/**
	 * Adds a filter specification to the existing list of filters
	 * 
	 * @param fieldName
	 *            Name of the model that should be filtered on
	 * @param comparison
	 *            For example =, !=, >, >=, <, <=, like, ilike, in, not in,
	 *            child_of, parent_left, parent_right
	 * @param value
	 *            value that will be compared to 'fieldName' using the
	 *            'comparison'
	 * @throws OdooApiException
	 */
	public void add(String fieldName, String comparison, Object value)
			throws OdooApiException {
		add(filters.size(), fieldName, comparison, value);
	}

	/**
	 * Adds logical operators for filters
	 * 
	 * From the OpenERP code: Domain criteria can be combined using 3 logical
	 * operators than can be added between tuples: '**&**' (logical AND,
	 * default), '**|**' (logical OR), '**!**' (logical NOT). These are
	 * **prefix** operators and the arity of the '**&**' and '**|**' operator is
	 * 2, while the arity of the '**!**' is just 1. Be very careful about this
	 * when you combine them the first time.
	 * 
	 * Here is an example of searching for Partners named *ABC* from Belgium and
	 * Germany whose language is not english ::
	 * 
	 * [(
	 * 'name','=','ABC'),'!',('language.code','=','en_US'),'|',('country_id.code','=','be'),('country_id.code','=','de')
	 * )
	 * 
	 * The '&' is omitted as it is the default, and of course we could have used
	 * '!=' for the language, but what this domain really represents is::
	 * 
	 * (name is 'ABC' AND (language is NOT english) AND (country is Belgium OR
	 * Germany))
	 * 
	 * @param operator
	 */
	public void add(FilterOperator operator) {
		add(filters.size(), operator);
	}

	/**
	 * Adds logical operators for filters
	 * 
	 * From the OpenERP code: Domain criteria can be combined using 3 logical
	 * operators than can be added between tuples: '**&**' (logical AND,
	 * default), '**|**' (logical OR), '**!**' (logical NOT). These are
	 * **prefix** operators and the arity of the '**&**' and '**|**' operator is
	 * 2, while the arity of the '**!**' is just 1. Be very careful about this
	 * when you combine them the first time.
	 * 
	 * Here is an example of searching for Partners named *ABC* from Belgium and
	 * Germany whose language is not english ::
	 * 
	 * [(
	 * 'name','=','ABC'),'!',('language.code','=','en_US'),'|',('country_id.code','=','be'),('country_id.code','=','de')
	 * )
	 * 
	 * The '&' is omitted as it is the default, and of course we could have used
	 * '!=' for the language, but what this domain really represents is::
	 * 
	 * (name is 'ABC' AND (language is NOT english) AND (country is Belgium OR
	 * Germany))
	 * 
	 * @param index
	 *            Index where the specified filter should be added
	 * @param operator
	 */
	public void add(int index, FilterOperator operator) {
		switch (operator) {
		case OR:
			filters.add(index, "|");
			break;
		case NOT:
			filters.add(index, "!");
			break;
		default:
			break;
		}
	}

	/**
	 * Clears the filter from previous filter values
	 */
	public void clear() {
		filters.clear();
	}

	/**
	 * Gets the filters in a Object[] required by the XMLRPC calls to OpenERP
	 * 
	 * @return
	 */
	public Object[] getFilters() {
		return filters.toArray(new Object[filters.size()]);
	}

	/**
	 * Returns the number of filters that are configured
	 * 
	 * @return
	 */
	public int size() {
		return filters.size();
	}
}
