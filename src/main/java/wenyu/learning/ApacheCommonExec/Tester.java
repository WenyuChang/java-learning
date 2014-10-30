package wenyu.learning.ApacheCommonExec;

import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;

public class Tester {
	
	private static void simpleUsage() {
		String line = "c:/test.bat";
		CommandLine cmdLine = CommandLine.parse(line);
		DefaultExecutor executor = new DefaultExecutor();
		executor.setExitValue(0); //Define the exit value of "0" to be considered as successful execution.
		try {
			int exitValue = executor.execute(cmdLine);
			System.out.println("###############################################################");
			System.out.println("Exit Value: " + exitValue);
		} catch (ExecuteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	private static void usageWithoutBlocking() {
		CommandLine cmdLine = new CommandLine("c:/test.bat");

		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();
		Executor executor = new DefaultExecutor();
		ExecuteWatchdog watchdog = new ExecuteWatchdog(1);
		executor.setExitValue(0);
		executor.setWatchdog(watchdog);
		try {
			executor.execute(cmdLine, resultHandler);
			System.out.println("###############################################################");
		} catch (ExecuteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// some time later the result handler callback was invoked so we
		// can safely request the exit value
		try {
			resultHandler.waitFor();
			int exitValue = resultHandler.getExitValue();
			System.out.println("Exit Value: " + exitValue);
			ExecuteException ex = resultHandler.getException();
			if(ex == null) {
				System.out.println("no ex");
			} else {
				System.out.println(ex);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
//		simpleUsage();
		
		usageWithoutBlocking();

	}

}
