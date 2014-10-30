package wenyu.learning.ApacheCommonExec;

import java.io.IOException;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecuteResultHandler;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.exec.ExecuteException;
import org.apache.commons.exec.ExecuteWatchdog;
import org.apache.commons.exec.Executor;

public class Usage {
	private static void simpleUsage() {
		String line = "ipconfig /all";
		CommandLine cmdLine = CommandLine.parse(line);
		DefaultExecutor executor = new DefaultExecutor();
		executor.setExitValue(0); //Define the exit value of "0" to be considered as successful execution.
		try {
			int exitValue = executor.execute(cmdLine);
			System.out.println("Exit Value: " + exitValue);
		} catch (ExecuteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void usageWithWatchDog() {
		// commons-exec provides a watchdog 
		// which will kill a run-away process 
		// after specified time.
		
		String line = "ipconfig";
		CommandLine cmdLine = CommandLine.parse(line);
		DefaultExecutor executor = new DefaultExecutor();
		executor.setExitValue(0);
		ExecuteWatchdog watchdog = new ExecuteWatchdog(60000);
		executor.setWatchdog(watchdog);
		try {
			int exitValue = executor.execute(cmdLine);
			System.out.println("Exit Value: " + exitValue);
		} catch (ExecuteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void usageWithCommondLineIncre() {
//		Map map = new HashMap();
//		map.put("file", new File("invoice.pdf"));
//		CommandLine cmdLine = new CommandLine("AcroRd32.exe");
//		cmdLine.addArgument("/p");
//		cmdLine.addArgument("/h");
//		cmdLine.addArgument("${file}");
//		cmdLine.setSubstitutionMap(map);
//		DefaultExecutor executor = new DefaultExecutor();
//		executor.setExitValue(1);
//		ExecuteWatchdog watchdog = new ExecuteWatchdog(60000);
//		executor.setWatchdog(watchdog);
//		int exitValue = executor.execute(cmdLine);
		
		CommandLine cmdLine = new CommandLine("ipconfig");
		cmdLine.addArgument("/all");
		DefaultExecutor executor = new DefaultExecutor();
		executor.setExitValue(0);
		ExecuteWatchdog watchdog = new ExecuteWatchdog(60000);
		executor.setWatchdog(watchdog);
		try {
			int exitValue = executor.execute(cmdLine);
			System.out.println("Exit Value: " + exitValue);
		} catch (ExecuteException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private static void usageWithoutBlocking() {
		CommandLine cmdLine = new CommandLine("ipconfig");
		cmdLine.addArgument("/all");

		DefaultExecuteResultHandler resultHandler = new DefaultExecuteResultHandler();

		ExecuteWatchdog watchdog = new ExecuteWatchdog(60*1000);
		Executor executor = new DefaultExecutor();
		executor.setExitValue(0);
		executor.setWatchdog(watchdog);
		try {
			executor.execute(cmdLine, resultHandler);
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
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		simpleUsage();
//		usageWithWatchDog();
//		usageWithCommondLineIncre();
		usageWithoutBlocking();
	}
}
