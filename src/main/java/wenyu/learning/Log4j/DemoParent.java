package wenyu.learning.Log4j;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

public class DemoParent {
	static {
		String conf = "src/main/java/wenyu/demo/Log4j/log4j.properties"; // For normal configuration
		//conf = "src/main/java/wenyu/demo/Log4j/log4j.xml"; // For XML configuration
		
		if(conf.endsWith("properties")) {
			PropertyConfigurator.configure(conf);
		} else if(conf.endsWith("xml")) {
			DOMConfigurator.configure(conf);
		} else {
			System.out.println("Failed to read configuration.");
			System.exit(1);
		}
	}
	
	public static void log(Logger logger) {
		// Simple test
		logger.trace("This is a trace message.");
		logger.debug("This is a debug message."); 
		logger.info("This is an info message.");
		logger.warn("This is a warn message.");
		logger.error("This is an error message.");
		logger.fatal("This is an fatal message.");
		logger.log(Level.DEBUG, "This is a message written by log method.");
		System.out.println("=====================================================");
	}
	
	public static void logE(Logger logger, Throwable ex) {
		logger.error("This is a exception", ex);
	}
}
