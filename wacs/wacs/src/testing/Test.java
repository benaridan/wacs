package testing;

import java.util.ArrayList;

import core.FileManager;
import core.HTTPConnection;
import core.RequestConfigurations;
import core.RequestManager;
import core.RequestResponsePair;
import core.TrustAllX509TrustManager;
import enums.RequestMethod;
import java.net.URL;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Test {

	public static void main(String[] args) throws Exception {
		
		RequestManager manager = new RequestManager();
		
		
		manager.loadPathsFile("c:\\users\\idan\\desktop\\resources.txt");
		
		RequestConfigurations configs = new RequestConfigurations();
		configs.setURL("https://localhost/");

		manager.startURLScan(configs);
		
		
		System.out.println("Printing results:");
		ArrayList<RequestResponsePair> oks = manager.getResultsByCode(404);
		RequestManager.printResults(oks);
		
		FileManager.createResultsFile("test2.txt", manager);
		

		
		
		
		
		
		
	}

}
