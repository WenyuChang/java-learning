package wenyu.learning.Log4j;

import org.apache.log4j.Logger;

public class SimpleDemo extends DemoParent {
	protected static Logger logger = Logger.getLogger(SimpleDemo.class.getSimpleName());
	
	public static void main(String[] args) {
		log(logger);
	}
}
