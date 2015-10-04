package core;

import java.util.ArrayList;
import java.util.LinkedList;

public class RequestManager {

	LinkedList<RequestResponsePair> requestResponsePairs;


	public RequestManager() {
		requestResponsePairs = new LinkedList<RequestResponsePair>();
		TrustAllX509TrustManager.setVerifyAllCertificates();
	}

	public void loadPathsFile(String filePath) {
		ArrayList<String> paths = FileManager.parseFile(filePath);

		if(paths.size() > 0) {

			//set request and response pair for each path from file
			for(String path : paths) {
				HTTPRequest request= new HTTPRequest();
				HTTPResponse response = new HTTPResponse();

				request.setPath(path);
				response.setPath(path);

				//generate new pair and add to list
				RequestResponsePair pair = new RequestResponsePair();
				pair.setHttpRequest(request);
				pair.setHttpResponse(response);

				requestResponsePairs.add(pair);

			}
		}

		System.out.println("Succesfully loaded " + requestResponsePairs.size() + " paths");

	}

	public void startURLScan(RequestConfigurations requestConfigurations) {

		//iterate over all pairs and set a new connections
		for(RequestResponsePair pair : requestResponsePairs) {
			HTTPConnection connection = new HTTPConnection(requestConfigurations, pair);
			connection.runRequest();
		}

	}

	/*
	 * Given a response code returns all responses matching this code
	 */
	public ArrayList<RequestResponsePair> getResultsByCode(int code) {
		ArrayList<RequestResponsePair> results = new ArrayList<RequestResponsePair>();

		for(RequestResponsePair pair : requestResponsePairs) {
			if(pair.getHttpResponse().getResponseCode() == code) {
				results.add(pair);
			}
		}

		return results;
	}

	public static String printResults(ArrayList<RequestResponsePair> pairs) {
		StringBuilder results = new StringBuilder();
		for(RequestResponsePair pair : pairs) {
			results.append(pair.getHttpRequest().getPath() + " : " + pair.getHttpResponse().getResponseCode() + System.lineSeparator());
		}		

		System.out.println(results.toString());
		return results.toString();
	}

}
