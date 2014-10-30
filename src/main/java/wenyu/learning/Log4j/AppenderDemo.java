package wenyu.learning.Log4j;

import java.io.IOException;
import java.io.OutputStreamWriter;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;
import org.apache.log4j.SimpleLayout;

public class AppenderDemo extends DemoParent {
	protected static Logger logger = Logger.getLogger(AppenderDemo.class.getSimpleName());	
	
	public static void main(String[] args) {
		logger.removeAppender("CONSOLE");
		log(logger);
		
		//=================================================================
		ConsoleAppender consoleA = new ConsoleAppender();
		consoleA.setName("ConsoleFromCode");
		consoleA.setWriter(new OutputStreamWriter(System.out));
		consoleA.setLayout(new PatternLayout("Console from Code: %m.%n"));
		logger.addAppender(consoleA);
		log(logger);
		
		//=================================================================
		try {
			FileAppender fileA = new FileAppender(new SimpleLayout(), "src/main/java/wenyu/demo/Log4j/AppenderDemo.log");
			//logger.removeAppender("ConsoleFromCode");
			logger.addAppender(fileA);
			log(logger);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
