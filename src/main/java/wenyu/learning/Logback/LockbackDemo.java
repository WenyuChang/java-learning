package wenyu.learning.Logback;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.classic.LoggerContext;

public class LockbackDemo extends DemoParent {
	public static void main(String[] args) {
		Logger logger = LoggerFactory.getLogger(LockbackDemo.class.getName());
		log(logger);
	}
}
