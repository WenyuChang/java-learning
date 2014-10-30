package wenyu.learning.MockCommand;

import java.util.ArrayList;
import java.util.List;

public enum MyCommand {
	
	//=====================================================================================================================================
	Help("help", "h") {
		@Override
		String getDescription() {
			return "Print usage and help info. To view help info of a command, use \"-command(c)\"";
		}

		@Override
		public CommandExecutor getExecutor() {
			CommandExecutor executor = new CommandExecutor() {
				public void excute() {
					if(params.get(0).paramValue==null) {
						System.out.println("All Commands:");
						System.out.println();
						for (MyCommand command: MyCommand.values()) {
							System.out.println(String.format("%s(%s): %s", command.getName(), command.getShortName(), command.getDescription()));
						}
						System.out.println();
					} else {
						String cmd = params.get(0).paramValue.toString();
						MyCommand targetCmd = null;			
						for(MyCommand command: MyCommand.values()) {
							if (command.getName().equalsIgnoreCase(cmd) || command.getShortName().equalsIgnoreCase(cmd)) {
								targetCmd = command;
								break;
							}
						}
						
						if (targetCmd == null) {
							throw new IllegalArgumentException(String.format("Invalid command \"%s\"", cmd));
						}
									
						System.out.println(String.format("%s(%s): %s", targetCmd.getName(), targetCmd.getShortName(), targetCmd.getDescription()));
						System.out.println();
						
						if (!targetCmd.getParams().isEmpty()) {
							for (CommandParameter param: targetCmd.getParams()) {				
								System.out.println(String.format("- %s(%s): %s", param.paramName, param.paraShortName, param.description));
							}
						}
						System.out.println();
					}
				}
			};
			return executor;
		}

		@Override
		List<CommandParameter> getParams() {
			params = new ArrayList<CommandParameter>();
			
			params.add(
						new CommandParameter.Builder("command", false)
										.paraShortName("c")
										.description("Command name to view help info")
										.build()
					 );
			
			return params;
		}

		private List<CommandParameter> params;
		public void setParams(List<CommandParameter> params) {
			this.params = params;
		}
		
	},
	
	//=====================================================================================================================================
	Exit("exit", "e") {
		@Override
		String getDescription() {
			return "Exit the command console.";
		}
		
		@Override
		public CommandExecutor getExecutor() {
			CommandExecutor executor = new CommandExecutor() {
				public void excute() {
					System.exit(1);
				}
			};
			
			return executor;
		}

		@Override
		List<CommandParameter> getParams() {
			return new ArrayList<CommandParameter>();
		}
		
		public void setParams(List<CommandParameter> params) { }

	},
	
	//=====================================================================================================================================
	CMD1("cmd1", "c1") {
		@Override
		String getDescription() {
			return "Print out \"This is CMD1.\"";
		}
		
		@Override
		public CommandExecutor getExecutor() {
			CommandExecutor executor = new CommandExecutor() {
				public void excute() {
					System.out.println("This is CMD1.");
					if(params!=null) {
						for(int i=0;i<params.size();i++) {
							System.out.println(params.get(i).paramName + ": " + params.get(i).paramValue);
						}
					}
					System.out.println();
				}
			};
			
			return executor;
		}

		@Override
		List<CommandParameter> getParams() {
			params = new ArrayList<CommandParameter>();
			params.add(
						new CommandParameter.Builder("param1", true)
										.paraShortName("p1")
										.description("Parameter 1")
										.build()
					 );
			
			params.add(
					new CommandParameter.Builder("param2", false)
									.paraShortName("p2")
									.defaultValue("this is parameter 2.")
									.description("Parameter 2")
									.build()
				 );
			
			return params;
		}

		private List<CommandParameter> params;
		public void setParams(List<CommandParameter> params) {
			this.params = params;
		}
	};
	
	
	// Command Names
	private final String cmdName;
	private final String cmdShortName;
	private MyCommand(String name, String shortName) {
		cmdName = name;
		cmdShortName = shortName;
	}
	public String getName() { return cmdName; }
	public String getShortName() { return cmdShortName; }
	
	// Command Methods
	abstract CommandExecutor getExecutor();
	abstract String getDescription();
	abstract void setParams(List<CommandParameter> params);
	abstract List<CommandParameter> getParams();
	
	public void validOptionalParams(List<CommandParameter> params) throws IllegalArgumentException {
		
	}
	
	public static MyCommand parse(String cmdLine) throws IllegalArgumentException {
		// Check parameters
		if(cmdLine == null || cmdLine.length()==0) {
			throw new IllegalArgumentException();
		}
		
		// Split tokens
		String[] tokens = cmdLine.split(" ");
		
		// Identity Command
		String cmdString = tokens[0];
		MyCommand command = null;
		for(MyCommand cmd: MyCommand.values()) {
			if (cmd.cmdName.equalsIgnoreCase(cmdString) || cmd.cmdShortName.equalsIgnoreCase(cmdString)) {
				command = cmd;
				break;
			}
		}
		if (command == null) {
			throw new IllegalArgumentException(String.format("Invalid command \"%s\". Use \"help\" or \"usage\" to view all commands", cmdString));
		}
		
		// Parse parameters
		List<CommandParameter> params = command.getParams();
		if (command.getParams().size() == 0) {
			return command;
		}
		for(int i=1;i<tokens.length;i++) {
			String option = tokens[i];
			if (!option.startsWith("-")) {
				throw new IllegalArgumentException(String.format("Invalid option \"%s\"", option));
			}
			option = option.substring(1);
			
			// Identity Parameter
			boolean paramMatch = false;
			for(CommandParameter param:params) {
				if (param.paramName.equalsIgnoreCase(option) || param.paraShortName.equalsIgnoreCase(option)) {
					if (++i >= tokens.length) {
						throw new IllegalArgumentException(String.format("Failed to parse value for option \"%s\"", option));
					}
					param.paramValue = tokens[i];
					paramMatch = true;
				}
			}
			if(!paramMatch) {
				throw new IllegalArgumentException(String.format("Invalid option \"%s\"", option));
			}
		}
		
		// Validate required parameters, and set default parameter values if not passed
		for (CommandParameter param: params) {
			if (param.required && param.paramValue == null) {
				throw new IllegalArgumentException(String.format("Required option \"%s\" value is not specified", param.paramName));
			}
			
			if (param.paramValue == null) {
				param.paramValue = param.defaultParaValue;
			}
		}
		
		// Valid optional params
		command.validOptionalParams(params);
		command.setParams(params);
		return command;
	}
}
