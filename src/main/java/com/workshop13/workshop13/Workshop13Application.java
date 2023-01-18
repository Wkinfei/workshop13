package com.workshop13.workshop13;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import static com.workshop13.workshop13.util.Contacts.*;



@SpringBootApplication
public class Workshop13Application {
	// private static final Logger logger = LoggerFactory.getLogger(Workshop13Application.class);

	public static void main(String[] args) {
		// SpringApplication app = new SpringApplication(Workshop13Application.class);
		// DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);
		// List<String> opsVal = appArgs.getOptionValues("dataDir");
		// System.out.println(opsVal);
		// if (opsVal != null) {
		// 	logger.info("" + (String) opsVal.get(0));
		// 	setupDirectory((String) opsVal.get(0));
		// } else {
		// 	logger.warn("No data directory was provided");
		// 	System.exit(1);
		// }
		// app.run(args);
		// 
		// 
		// 
	// TODO: remove after testing
	String[] myArgs = {"--dataDir", "/opt/tmp/data"}; 
	args = myArgs;

	// Check exactly two arguments
	if(args.length != 2) System.exit(-1);

	// Check arg[0] is exactly "--dataDir"
	if(!args[0].equals("--dataDir")) System.exit(-1);

	// Try to create directories
	if(!setupDirectory(args[1])) System.exit(-1);


	SpringApplication app = new SpringApplication(Workshop13Application.class);
	app.run(args[0]+"="+args[1]);
	
		
	}

}
