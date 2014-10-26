package com.cspinformatique.odoo.api.core;

public class OdooApiException extends RuntimeException {
	private static final long serialVersionUID = -2254704279981443142L;

	public OdooApiException(String message) {
		super(message);
	}

	public OdooApiException(Throwable cause) {
		super(cause);
	}

	public OdooApiException(String message, Throwable cause) {
		super(message, cause);
	}
}
