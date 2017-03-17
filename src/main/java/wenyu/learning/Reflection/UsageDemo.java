package wenyu.learning.Reflection;

import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;

public class UsageDemo {
	
	@SuppressWarnings(value = {"unchecked", "unused"})
	private static void classLoader() throws ClassNotFoundException {
		// 1. Simple load class from MyClass.class
		Class<MyClass> myClass1 = MyClass.class;
		System.out.println(myClass1);
		
		//2. Load class from is name
		String className = MyClass.class.getName();
		Class<MyClass> myClass2 = (Class<MyClass>) Class.forName(className);
		System.out.println(myClass2);
		
		//3. Local class by ClassLoader
		ClassLoader currentClassLoader = UsageDemo.class.getClassLoader();
		Class<MyClass> myClass3 = (Class<MyClass>) currentClassLoader.loadClass(className);
		System.out.println(myClass3);
	}
	
	@SuppressWarnings(value = {"unused"})
	private static void classInit() throws Exception {
		Class<MyClass> myClass = MyClass.class;
		
		// getInstance will call default constructor (no parameters)
		MyClass ins = myClass.newInstance();
		System.out.println(ins);
		
		System.out.println("===============================================================");
		
		// initilize instance with public constructor who has parameters
		Constructor<MyClass> publicConstructor = myClass.getConstructor(String.class, int.class, ArrayList.class);
		MyClass ins1 = publicConstructor.newInstance("para1", 0, new ArrayList());
		System.out.println(ins1);

		System.out.println("===============================================================");
		
		Constructor<MyClass> privateConstructor = myClass.getDeclaredConstructor(String.class, int.class, String[].class);
		privateConstructor.setAccessible(true);
		MyClass ins2 = privateConstructor.newInstance("para1", 0, new String[] {"aa", "bb", "cc"});
		System.out.println(ins2);
	}
	
	@SuppressWarnings(value = {"unchecked", "unused"})
	private static void showConstructor() {
		Class<MyClass> myClass = MyClass.class;
		
		// get all public constructors
		Constructor<MyClass>[] constructors = (Constructor<MyClass>[]) myClass.getDeclaredConstructors();
		System.out.println("There are " + constructors.length + " constructors in MyClass");
		for(Constructor<MyClass> constructor:constructors) {
			System.out.println("Name: " + constructor.getName());
			System.out.println("Modifier:" + Modifier.toString(constructor.getModifiers()));
			System.out.println("Accessible:" + constructor.isAccessible());
			System.out.print("ParemeterTypes: ");
			for(Class<?> clz : constructor.getParameterTypes()) {
				System.out.print(clz.getSimpleName() + ", ");
			}
			System.out.println();
			System.out.print("Annotations:");
			for(Annotation anno : constructor.getAnnotations()) {
				System.out.print(anno.toString() + ", ");
			}
			System.out.println();
			System.out.println("===============================================================");
		}
	}

	@SuppressWarnings(value = {"unused"})
	private static void showMethods() throws Exception {
		Class<MyClass> myClass = MyClass.class;
		
		Method[] methods = myClass.getDeclaredMethods();
		for(Method method : methods) {
			System.out.println("Name: " + method.getName());
			System.out.println("Modifier: " + Modifier.toString(method.getModifiers()));
			System.out.println("Accessible:" + method.isAccessible());
			System.out.print("Exception Types: ");
			for(Class<?> cls : method.getExceptionTypes()) {
				System.out.print(cls.getSimpleName() + ", ");
			}
			System.out.println();
			System.out.print("Parameter Types&Annotation: ");
			Class<?>[] clzs = method.getParameterTypes();
			Annotation[][] annos = method.getParameterAnnotations();
			for(int i=0; i<clzs.length; i++) {
				System.out.print(clzs[i].getSimpleName() + "(");
				for(int j=0; j<annos[i].length; j++) {
					System.out.print(annos[i][j].toString() + ", ");
				}
				System.out.print(") ");
			}
			System.out.println();
			System.out.print("Method Invoke: ");
			method.setAccessible(true);
			method.invoke(new MyClass(), "param1", "param2");
			
			System.out.println("===============================================================");
		}
	}

	@SuppressWarnings(value = {"unused"})
	private static void showMethod(String name, Class<?>... params) throws Exception {
		Class<MyClass> myClass = MyClass.class;
		
		Method method = myClass.getDeclaredMethod(name, params);
		System.out.println("Name: " + method.getName());
		System.out.println("Modifier: " + Modifier.toString(method.getModifiers()));
		System.out.println("Return Type: " + method.getReturnType().getSimpleName());
		System.out.println("Accessible:" + method.isAccessible());
		System.out.print("Exception Types: ");
		for(Class<?> cls : method.getExceptionTypes()) {
			System.out.print(cls.getSimpleName() + ", ");
		}
		System.out.println();
		System.out.print("Parameter Types&Annotation: ");
		Class<?>[] clzs = method.getParameterTypes();
		Annotation[][] annos = method.getParameterAnnotations();
		for(int i=0; i<clzs.length; i++) {
			System.out.print(clzs[i].getSimpleName() + "(");
			for(int j=0; j<annos[i].length; j++) {
				System.out.print(annos[i][j].toString() + ", ");
			}
			System.out.print(") ");
		}
		System.out.println();
		System.out.print("Method Invoke: ");
		method.setAccessible(true);
		method.invoke(new MyClass(), "param1", "param2");
	}

	@SuppressWarnings(value = {"unused"})
	private static void showField() throws Exception {
		Class<MyClass> myClass = MyClass.class;
		
		Field[] fields = myClass.getDeclaredFields();
		for(Field field : fields) {
			System.out.println("Name: " + field.getName());
			System.out.println("Modifier: " + Modifier.toString(field.getModifiers()));
			System.out.println("Type: " + field.getType().getSimpleName());
			System.out.println("Accessible:" + field.isAccessible());
			System.out.print("Annotations:");
			for(Annotation anno : field.getAnnotations()) {
				System.out.print(anno.toString() + ", ");
			}
			System.out.println();
			MyClass ins = new MyClass();
			field.setAccessible(true);
			field.set(ins, "Set from Reflection. (" + field.getName() + ")");
			System.out.println("String Value:" + field.get(ins));
			System.out.println("===============================================================");
		}
	}
	
	public static void main(String[] args) throws Exception {
//		classLoader();
		
		classInit();
		
//		showConstructor();
		
//		showMethods();
		
//		showMethod("clsMethod", String.class, String.class);
		
//		showField();
	}
}
