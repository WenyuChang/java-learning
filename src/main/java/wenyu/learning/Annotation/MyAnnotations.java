package wenyu.learning.Annotation;

import java.lang.annotation.Annotation;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.Inherited;
import java.lang.reflect.Method;

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyRuntimePolicyAnnotation {
	int annoId();
	String passinName() default "Unassigned";
    String annoName = "MyRuntimePolicyAnnotation";
    String[] values();
}

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.CLASS)
@interface MyClassPolicyAnnotation {
	int annoId();
	String passinName() default "Unassigned";
	String annoName = "MyClassPolicyAnnotation";
}

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
@interface MySourcePolicyAnnotation {
	int annoId();
	String passinName() default "Unassigned";
	String annoName = "MySourcePolicyAnnotation";
}

@Inherited
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.SOURCE)
@interface MyInheritedAnnotation {
	int annoId();
	String passinName() default "Unassigned";
	String annoName = "MyInheritedAnnotation";
}

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyMultiAnnotation {
	MyRuntimePolicyAnnotation[] annos();
}

@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@interface MyMultiAnnotationWithValueParam {
	// if parameter name is value, it can be omit when specify values
	// See example and the difference with the above one
	MyRuntimePolicyAnnotation[] value();
}

public class MyAnnotations {
	public static void showAnnos(Class<?> clz) throws Exception {
		Method[] methods = clz.getMethods();
		for(Method mthd:methods) {
			String name = mthd.getName();
			String genericStr = mthd.toGenericString();
			String str = mthd.toString();
			Annotation[] annos = mthd.getDeclaredAnnotations();
			
			System.out.println("Method name: " + name);
			System.out.println("Method genericStr: " + genericStr);
			System.out.println("Method str: " + str);
			System.out.println("Annotation counts: " + annos.length);
			for(int i=1; i<=annos.length; i++) {
				String annoStr = annos[i-1].toString();
				String annoType = annos[i-1].annotationType().getName();
				System.out.println("    annotation" + i + ": " + annoStr);
				System.out.println("    annotation" + i + " type: " + annoType);
			}
			
			MyRuntimePolicyAnnotation anno = mthd.getAnnotation(MyRuntimePolicyAnnotation.class);
			if(anno!=null) {
				mthd.invoke(null, null);
			}

			System.out.println("=====================================================================");
		}
}
} 