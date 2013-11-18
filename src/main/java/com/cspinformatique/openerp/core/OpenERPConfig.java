package com.cspinformatique.openerp.core;

//import javax.servlet.ServletContext;

public class OpenERPConfig {
	private String host; 
	private int port;
	private String database; 
	private String user;
	private String password;
	private String masterPassword;
	
	public OpenERPConfig(){
		
	}
	
	public OpenERPConfig(
		String host, 
		int port, 
		String database, 
		String user, 
		String password,
		String masterPassword
	){
		this.host = host;
		this.port = port;
		this.database = database;
		this.user = user;
		this.password = password;
		this.masterPassword = masterPassword;
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
}
