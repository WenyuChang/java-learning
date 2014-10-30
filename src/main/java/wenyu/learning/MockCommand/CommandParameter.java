package wenyu.learning.MockCommand;

public class CommandParameter {
	public String paramName;
	public String paraShortName;
	public Object defaultParaValue;
	public Object paramValue;
	public String description;
	public boolean required;
	
	public static class Builder {
		private CommandParameter param;
		
		public Builder(String paramName, boolean required) {
			param = new CommandParameter();
			param.paramName = paramName;
			param.required = required;
		}
		
		public Builder paraShortName(String name) {
			param.paraShortName = name;
			return this;
		}
		
		public Builder defaultValue(Object value) {
			param.defaultParaValue = value;
			return this;
		}
		
		public Builder description(String desc) {
			param.description = desc;
			return this;
		}
		
		public CommandParameter build() {
			return param;
		}
	}
}
