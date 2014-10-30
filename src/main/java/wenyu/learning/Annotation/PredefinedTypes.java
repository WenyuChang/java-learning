package wenyu.learning.Annotation;


/**
 * The predefined annotation types defined in java.lang are: 
 * @Deprecated
 * @Override
 * @SuppressWarnings
 */
public class PredefinedTypes {
	
	
	/**
	 * @Deprecated annotation indicates that the marked
	 * element is deprecated and should no longer be used. 
	 * The compiler generates a warning whenever a program 
	 * uses a method, class, or field with the @Deprecated 
	 * annotation. When an element is deprecated, it should 
	 * also be documented using the Javadoc @deprecated tag,
	 * as shown in the following example. The use of the at
	 * sign (@) in both Javadoc comments and in annotations 
	 * is not coincidental: they are related conceptually. 
	 * Also, note that the Javadoc tag starts with a 
	 * lowercase d and the annotation starts with an 
	 * uppercase D.
	 */
	
    /**
     * @deprecated
     * explanation of why it was deprecated
     */
    @Deprecated
    public static void deprecatedMethod() { }
    
    
    
    /**
     * @Override annotation informs the compiler that the
     * element is meant to override an element declared in
     * a superclass. Overriding methods will be discussed 
     * in Interfaces and Inheritance.
     * 
     * While it is not required to use this annotation when 
     * overriding a method, it helps to prevent errors. If a 
     * method marked with @Override fails to correctly override 
     * a method in one of its superclasses, the compiler 
     * generates an error.
     */
    //@Override 
    //int overriddenMethod() { }
    
    
    
    /**
     * @SuppressWarnings annotation tells the compiler to suppress 
     * specific warnings that it would otherwise generate. In the 
     * following example, a deprecated method is used, and the 
     * compiler usually generates a warning. In this case, however, 
     * the annotation causes the warning to be suppressed.
     */
    @SuppressWarnings({"unchecked", "deprecation"})
    //@SuppressWarnings("deprecation")
    void useDeprecatedMethod() {
    	deprecatedMethod();
    }
}
