package wenyu.learning.Log4j;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerRepository;

public class FilterDemo extends DemoParent {
	protected static Logger loggerP = Logger.getLogger("wenyu.demo.Log4j");
	protected static Logger logger = Logger.getLogger("wenyu.demo.Log4j.FilterDemo");
	
	public static void loggerLevelFilter() {
		// Now set its level. Usually you do not need to set the level of
		// a logger programmatically but rather in a configuration script.
		logger.setLevel(Level.FATAL);
		log(loggerP); // This log will not be affected. Since we only set the logger's level.
		log(logger);
		
		//========================================================================================
		loggerP.setLevel(Level.ALL);
		log(loggerP);
		log(logger);
		
	}
	
	public static void hierarchyWideFilter() {
		LoggerRepository repository = logger.getLoggerRepository();
		// Now, let us disable all levels. This will turn off logging
		// entirely, i.e. nothing will ever log.
		repository.setThreshold(Level.ALL);
		// This FATAL level request will be dropped because all levels
		// are turned off.
		logger.fatal("This is a serious message but it will also be dropped.");
		loggerP.fatal("This is a serious message but it will also be dropped.");
	}
	
	public static void main(String[] args) {
		//loggerLevelFilter();
		hierarchyWideFilter();
	}

}
