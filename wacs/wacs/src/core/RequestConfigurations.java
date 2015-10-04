package core;

import java.util.Properties;

import enums.RequestMethod;

public class RequestConfigurations {

	Properties properties;
	private final static String USER_AGENT = "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:40.0) Gecko/20100101 Firefox/40.1";
	
	public RequestConfigurations() {
		//set default values;
		properties = new Properties();
		properties.setProperty("protocol", "http");
		properties.setProperty("userAgent", USER_AGENT);
		properties.setProperty("method", RequestMethod.GET.toString());
		properties.setProperty("useProxy", "false");
	}
	
	public void setUserAgent(String userAgent) {
		this.properties.setProperty("userAgent", userAgent);
	}
	
	public String getUserAgent() {
		return this.properties.getProperty("userAgent");
	}
	
	public void setRequestMethod(RequestMethod method) {
		this.properties.setProperty("method", method.toString());
	}
	
	public String getRequestMethod() {
		return properties.getProperty("method");
	}
	
	public void setURL(String url) {
		
		//todo validate url
		
		
		properties.setProperty("url", url);
	}	
	
	public String getURL() {
		return properties.getProperty("url");
	}
	
	public void setProtocol(String protocol) {
		properties.setProperty("protocol", protocol);
	}
	
	//proxy properties
	public void useProxy(String useProxy) {
		properties.setProperty("useProxy", useProxy);
	}
	
	public String proxyEnabled() {
		return properties.getProperty("useProxy");
	}
	
	public void setProxyAddressAndPort(String address,String port) {
		properties.setProperty("proxyAddress", address);
		properties.setProperty("proxyPort", port);
	}
	
	public String getProxyAddress() {
		return properties.getProperty("proxyAddress");
	}
	
	public String getProxyPorts() {
		return properties.getProperty("proxyPort");
	}
	
}
