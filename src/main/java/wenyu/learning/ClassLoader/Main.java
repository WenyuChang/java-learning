package wenyu.learning.ClassLoader;

public class Main {
	private void printLoaderDetail(ClassLoader clzLdr) {
		System.out.println("Class Loader - ToString: " + clzLdr.toString());
		System.out.println("Class Loader Parent - ToString: " + clzLdr.getParent().toString());
	}
	
	private void demoEntry() {
		ClassLoader clzLdr = Main.class.getClassLoader();
		printLoaderDetail(clzLdr);
		
		Class<?> clz = null;
		try {
			clz = (Class<?>) clzLdr.loadClass("wenyu.demo.ClassLoader.DemoClass");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		if(clz!=null) {
			System.out.println(clz.getName());
			ClassLoader tmpClzLdr = clz.getClassLoader();
			printLoaderDetail(tmpClzLdr);
		}
	}
	
	public static void main(String[] args) {
		new Main().demoEntry();
	}

}
