package core;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

public class FileManager {

	public static ArrayList<String> parseFile(String filePath) {
		ArrayList<String> requestPaths = new ArrayList<String>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		       requestPaths.add(line);
		    }
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return requestPaths;
	}
	
	public static void createResultsFile(String file, RequestManager manager) {
		PrintWriter writer;
		try {
			System.out.println("Writing file " + file);
			writer = new PrintWriter(file, "UTF-8");
			
			//files 200
			writer.println("Files found:");
			for(RequestResponsePair pair : manager.getResultsByCode(200) ) {
				writer.println(pair.getHttpRequest().getPath() + " : " + pair.getHttpResponse().getResponseCode());
			}
		
			writer.println();
			writer.println();
			writer.println("Access Denied:");
			for(RequestResponsePair pair : manager.getResultsByCode(301) ) {
				writer.println(pair.getHttpRequest().getPath() + " : " + pair.getHttpResponse().getResponseCode());
			}
			
			writer.println();
			writer.println();
			writer.println("Server Error:");
			for(RequestResponsePair pair : manager.getResultsByCode(500) ) {
				writer.println(pair.getHttpRequest().getPath() + " : " + pair.getHttpResponse().getResponseCode());
			}
			
			writer.println();
			writer.println();
			writer.println("not found:");
			for(RequestResponsePair pair : manager.getResultsByCode(404) ) {
				writer.println(pair.getHttpRequest().getPath() + " : " + pair.getHttpResponse().getResponseCode());
			}

			
			writer.close();
			
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
