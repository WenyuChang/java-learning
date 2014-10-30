package wenyu.learning.Logback;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.joran.JoranConfigurator;
import ch.qos.logback.core.joran.spi.JoranException;

public class DemoParent {
	static {
		String conf = "src/main/java/wenyu/demo/Logback/logback.xml";
		try {
			LoggerContext context = (LoggerContext) LoggerFactory.getILoggerFactory();
			JoranConfigurator configurator = new JoranConfigurator();
			configurator.setContext(context);
			context.reset();
			configurator.doConfigure(conf);
		} catch (JoranException je) {}
	}
	
	public static void log(Logger logger) {
		// Simple test
		System.out.println("================= Start to Log Message ====================");
		logger.trace("This is a trace message.");
		logger.debug("This is a debug message."); 
		logger.info("This is an info message.");
		logger.warn("This is a warn message.");
		logger.error("This is an error message.");
	}
	
	public static void logE(Logger logger, Throwable ex) {
		logger.error("This is a exception", ex);
	}
}
