package com.cspinformatique.odoo.api.core;

public class OdooConfiguration {
	private String host; 
	private int port;
	private String database; 
	private String user;
	private String password;
	private String masterPassword;
	private String companyName;
	
	public OdooConfiguration(){
		
	}
	
	public OdooConfiguration(
		String host, 
		int port, 
		String database, 
		String user, 
		String password,
		String masterPassword,
		String companyName
	){
		this.host = host;
		this.port = port;
		this.database = database;
		this.user = user;
		this.password = password;
		this.masterPassword = masterPassword;
		this.companyName = companyName;
	}

	public String getHost() {
		return host;
	}

	public int getPort() {
		return port;
	}

	public String getDatabase() {
		return database;
	}

	public String getUser() {
		return user;
	}

	public String getPassword() {
		return password;
	}

	public String getMasterPassword() {
		return masterPassword;
	}

	public String getCompanyName() {
		return companyName;
	}
}
