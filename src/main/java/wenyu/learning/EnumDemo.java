package wenyu.learning;

enum MyEnum {
	enum1("enum1", 1) {
		@Override
		void abstractPrint() {
			System.out.println("(" + this.name() + ") This is in the abstractPrint method...");
		}
	},
	enum2("enum2", 2) {
		@Override
		void abstractPrint() {
			System.out.println("(" + this.name() + ") This is in the abstractPrint method...");
		}
	};
	
	private String arg1;
	private int arg2;
	
	public void printMyEnum() {
		System.out.println("name: " + this.name());
		System.out.println("Arg1" + arg1);
		System.out.println("Arg2" + arg2);
	}
	
	abstract void abstractPrint();
	
	private MyEnum(String arg1, int arg2) {
		this.arg1 = arg1;
		this.arg2 = arg2;
	}
}

public class EnumDemo {
	
	public static void main(String[] args) {
		System.out.println("*** " + MyEnum.enum1);
		
		//================================================
		System.out.println("valueof()...");
		MyEnum tmpEnum = MyEnum.valueOf("enum1");
		System.out.println(tmpEnum.name());
		
		//================================================
		System.out.println("values()...");
		for(MyEnum myenum:MyEnum.values()) {
			System.out.println(myenum.name());
		}
	}
}
