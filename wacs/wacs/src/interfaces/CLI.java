package interfaces;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import core.FileManager;
import core.RequestConfigurations;
import core.RequestManager;


public class CLI {

	private static final String USAGE = "Usage: waact -u -p [-o] [-user-agent]";
	
	public static void main(String[] args) {
		String outputFile = "";
		
		RequestConfigurations configs = new RequestConfigurations();
		RequestManager manager = new RequestManager();
		Options options = new Options();
		
		options.addOption("u",true,"The target URL you want to scan. The value must contain the protocol (http or https) Example -u https://example.com");
		options.getOption("u").setRequired(true);
		
		options.addOption("p",true,"The path of file containing resources to scan. Example -p c:\files.txt");
		options.getOption("p").setRequired(true);
		
		options.addOption("o",true,"The output file path to which the results will be written.");
		
		//options.addOption("ssl",false,"Use SSL (HTTPS)");
		options.addOption("userAgent",true,"Use alternative user-agent instead of default one.");
		
		options.addOption("proxy",true,"Use a proxy to connect to the site. supply proxy address and port. Example example.com:8080.");
		
		CommandLineParser parser = new DefaultParser();
		try {
			
			CommandLine cmd = parser.parse( options, args);			

			
			//set up url
			if(cmd.hasOption("u")) {
				configs.setURL(cmd.getOptionValue("u"));
			} else {
				System.out.println("Missing URL");
				abort();
			}
			
			//set up resources file path
			if(cmd.hasOption("p")) {
				manager.loadPathsFile(cmd.getOptionValue("p"));
			} else {
				System.out.println("Missing resource file path");
				abort();
			}
			
			if(cmd.hasOption("o")) {
				outputFile = cmd.getOptionValue("o");
			} 
			
			if(cmd.hasOption("userAgent")) {
				configs.setUserAgent(cmd.getOptionValue("userAgent"));
			} 
			
			if(cmd.hasOption("proxy")) {
				configs.useProxy("true");
				String proxy = cmd.getOptionValue("proxy");
				
				if(proxy.split(":").length != 2) {
					System.out.println("Malformed proxy address.");
					abort();
				}
				
				String[] proxyConfigs = proxy.split(":");
				
				configs.setProxyAddressAndPort(proxyConfigs[0], proxyConfigs[1]);
			} 
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		run(manager,configs,outputFile);
		

	}
	
	
	
	private static void run(RequestManager manager, RequestConfigurations configs, String outputFile) {
		manager.startURLScan(configs);
		
		//if options are set to create an output results file then do it
		if(outputFile.compareTo("") != 0) {
			FileManager.createResultsFile(outputFile, manager);
		}
		
	}
	private static void abort() {
		System.out.println(USAGE);
		System.out.println("Aborting.");
		System.exit(0);
	}

}
