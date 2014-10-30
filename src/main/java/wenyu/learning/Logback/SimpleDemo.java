package wenyu.learning.Logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.core.util.StatusPrinter;


public class SimpleDemo {
	/**
	 * Reasons to prefer logback over log4j: 
	 * http://logback.qos.ch/reasonsToSwitch.html
	 */
	
	private static void simpleDemo() {
		// print internal state
	    LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
	    StatusPrinter.print(lc);
		
	    /*
	     * By virtue of logback's default configuration policy, 
	     * when no default configuration file is found, logback 
	     * will add a ConsoleAppender to the root logger.
	     * 
	     * 1. Logback tries to find a file called logback.groovy in the classpath.
	     * 2. If no such file is found, logback tries to find a file called logback-test.xml in the classpath.
	     * 3. If no such file is found, it checks for the file logback.xml in the classpath.
	     * 4. If neither file is found, logback configures itself automatically using the BasicConfigurator.
	     */
		Logger logger = LoggerFactory.getLogger(SimpleDemo.class.getName());
		logger.info("Simple Demo Info Message.");
	}
	
	private static void rootLogger() {
		Logger rootLogger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		rootLogger.info("Root logger Info Message.");
	}
	
	
	private static void betterLoggerWay() {
		Logger logger = LoggerFactory.getLogger(Logger.ROOT_LOGGER_NAME);
		
		String entry = new String("test");
		/*
		 * The following two lines will yield the exact same output. 
		 * However, in case of a disabled logging statement, the 
		 * second variant will outperform the first variant by a factor
		 * of at least 30.
		 */
		logger.debug("The new entry is "+entry+".");
		logger.debug("The new entry is {}.", entry);
		
		/*
		 * Or another way is use isDebugEnalbed
		 */
		if(logger.isDebugEnabled()) { 
			logger.debug("The new entry is "+entry+".");
		}
	}
	public static void main(String[] args) {
		simpleDemo();
		rootLogger();
		betterLoggerWay();
	}
}
