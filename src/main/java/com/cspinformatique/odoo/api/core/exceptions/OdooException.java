package com.cspinformatique.odoo.api.core.exceptions;

public class OdooException extends RuntimeException {
	private static final long serialVersionUID = -5360622566392311263L;

	public OdooException(){
		
	}
	
	public OdooException(Throwable cause){
		super(cause);
	}
}
