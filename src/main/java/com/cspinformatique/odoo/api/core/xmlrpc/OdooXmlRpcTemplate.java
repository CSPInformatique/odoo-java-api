package com.cspinformatique.odoo.api.core.xmlrpc;

import org.apache.xmlrpc.XmlRpcException;

import com.cspinformatique.odoo.api.core.exceptions.OdooException;
import com.cspinformatique.odoo.api.core.xmlrpc.OdooXmlRpcClient.RPCProtocol;

public class OdooXmlRpcTemplate {
	private OdooXmlRpcClient.RPCProtocol protocol;
	private String host;
	private int port;

	public OdooXmlRpcTemplate(String host, int port) {
		this(RPCProtocol.RPC_HTTP, host, port);
	}

	public OdooXmlRpcTemplate(OdooXmlRpcClient.RPCProtocol protocol,
			String host, int port) {
		this.protocol = protocol;
		this.host = host;
		this.port = port;
	}

	@SuppressWarnings("unchecked")
	public <Result> Result executeCall(OdooXmlRpcClient.RPCServices rpcService,
			String methodName, Object[] parameters, Class<Result> result) {
		try {
			return (Result) new OdooXmlRpcClient(protocol, host, port,
					rpcService).execute(methodName, parameters);
		} catch (XmlRpcException xmlRpcEx) {
			throw new OdooException(xmlRpcEx);
		}
	}
}
