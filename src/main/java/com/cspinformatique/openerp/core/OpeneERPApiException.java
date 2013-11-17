package com.cspinformatique.openerp.core;

public class OpeneERPApiException extends RuntimeException {
	private static final long serialVersionUID = -2254704279981443142L;

	public OpeneERPApiException(String message) {
		super(message);
	}

	public OpeneERPApiException(Throwable cause) {
		super(cause);
	}

	public OpeneERPApiException(String message, Throwable cause) {
		super(message, cause);
	}
}
