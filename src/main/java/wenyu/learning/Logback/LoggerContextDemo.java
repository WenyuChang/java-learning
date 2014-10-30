package wenyu.learning.Logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;

public class LoggerContextDemo extends DemoParent {
	public static Logger logger = LoggerFactory.getLogger(LoggerContextDemo.class.getName());
	
	private void stopContext() {
		LoggerContext lc = (LoggerContext) LoggerFactory.getILoggerFactory();
		lc.stop();
		log(logger); // no log will be appended.
	}
	
	public void demoEntry() {
		stopContext();
	}
	
	public static void main(String[] args) {
		new LoggerContextDemo().demoEntry();
	}

}
