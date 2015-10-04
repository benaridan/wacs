package core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;



//Represents a single HTTP connection (request and response)
public class HTTPConnection {

	private RequestConfigurations configurations; // the configurations regarding the HTTP request
	private RequestResponsePair requestResponsePair;

	public HTTPConnection(RequestConfigurations configurations,RequestResponsePair pair) {
		this.configurations = configurations;
		this.requestResponsePair = pair;

	}

	public void runRequest() {
		String urlString = configurations.getURL() + requestResponsePair.getHttpRequest().getPath();		


		try {
			URL url = new URL(urlString);
			Proxy proxy = null;
			HttpURLConnection connection = null;

			//check if should use proxy
			if(configurations.proxyEnabled().compareTo("true") == 0) {
				//use proxy
				int port = Integer.parseInt(configurations.getProxyPorts());
				proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(configurations.getProxyAddress(), port));	
				connection = (HttpURLConnection)url.openConnection(proxy); //creates a URL connection. request is not sent yet here
			} else {
				//dont use proxy
				connection = (HttpURLConnection)url.openConnection(); 
			}

			//set connection properties
			connection.setRequestMethod("GET");
			connection.setRequestProperty("User-Agent", configurations.getUserAgent());


			connection.connect();
			int code  = connection.getResponseCode();

			requestResponsePair.getHttpResponse().setResponseCode(code); //sets the response code to the httpResponseObject

			//check if we get a response
			if(code == -1) {				
				throw new IOException();
			}

			//we got a response. now check which code is it
			if(code == 200) {
				System.out.println(requestResponsePair.getHttpRequest().getPath() +". Found. code " + code);
				readResponse(connection);
				return;
			}

			if(code >= 400 && code <= 499) {
				System.out.println(requestResponsePair.getHttpRequest().getPath() +". Not found. code " + code);
				return;
			}

			if(code >= 300 && code <= 399) {
				System.out.println(requestResponsePair.getHttpRequest().getPath() +". Access Denied. code " + code);
				return;
			}

			if(code >= 500 && code <= 599) {
				System.out.println(requestResponsePair.getHttpRequest().getPath() +". Server Error. code " + code);
				return;
			}		



		}			
		catch(MalformedURLException e) {
			e.printStackTrace();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 

	}

	private void readResponse(HttpURLConnection connection) {
		//read response
		BufferedReader in;
		try {
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));			

			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();

			//save result
			//System.out.println(response.toString());
			requestResponsePair.getHttpRequest().setResponse(response.toString());

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	


}
