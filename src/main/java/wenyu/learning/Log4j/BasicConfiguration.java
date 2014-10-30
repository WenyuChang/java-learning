package wenyu.learning.Log4j;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class BasicConfiguration {

	public static void main(String[] args) {
		final Logger logger = Logger.getLogger(BasicConfiguration.class);

		//Set up a simple configuration that logs on the console.
		BasicConfigurator.configure();
		logger.info("Using Basic Configuration...");
	}

}
