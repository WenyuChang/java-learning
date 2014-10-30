package wenyu.learning.MockCommand;

import java.io.PrintStream;
import java.util.Scanner;

public class MockConsole {
	
	public void consoleStart() {
		Scanner scanner = new Scanner(System.in);
		PrintStream out = System.out;
		while (true) 
		{
			out.print("wenychan>");			
			String commandLine = scanner.nextLine();
			commandLine = commandLine.trim();
			String[] commands = commandLine.split("\\|");
			
			try
			{
				for(String cmd:commands) {
					MyCommand command = MyCommand.parse(cmd.trim());
					command.getExecutor().excute();
				}
			}
			catch (IllegalArgumentException e) {
				out.println(e.getMessage());
				continue;
			}
		}
	}
}
