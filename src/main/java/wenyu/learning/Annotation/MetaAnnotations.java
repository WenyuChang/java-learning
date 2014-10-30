package wenyu.learning.Annotation;

/**
 * Annotations itself is meta information 
 * then what is meta annotations? As you
 * have rightly guessed, it is information 
 * about annotation. When we annotate a annotation 
 * type then it is called meta annotation. 
 * For example, we say that this annotation 
 * can be used only for methods.
 */
public class MetaAnnotations {
	
	/**
	 * Annotation Types
	 * 1. Documented
		  When a annotation type is annotated with @Documented 
		  then wherever this annotation is used those elements 
		  should be documented using Javadoc tool.
	 * 2. Inherited
	      This meta annotation denotes that the annotation type 
	      can be inherited from super class. When a class is 
	      annotated with annotation of type that is annotated 
	      with Inherited, then its super class will be queried 
	      till a matching annotation is found.
	 * 3. Retention
		  This meta annotation denotes the level till which this 
		  annotation will be carried. When an annotation type is 
		  annotated with meta annotation Retention, RetentionPolicy 
		  has three possible values: (@Retention(RetentionPolicy.RUNTIME))
		  a. Class
		  	 When the annotation value is given as ‘class’ then this 
		  	 annotation will be compiled and included in the class file.
		  	 This is the default retention policy, if you do not specify 
		  	 any retention policy at all.
          b. Runtime
        	 The value name itself says, when the retention value is ‘Runtime’ 
        	 this annotation will be available in JVM at runtime. We can write 
        	 custom code using reflection package and parse the annotation. 
          c. Source
        	 This annotation will be removed at compile time and will not be 
        	 available at compiled class.
        	 If you create your own annotations for use with build tools that scan 
        	 the code, you can use this retention policy. That way the .class 
        	 files are not poluted unnecessarily. 
     * 4. Target
     	  This meta annotation says that this annotation type is applicable 
     	  for only the element (ElementType) listed. Possible values for ElementType 
     	  are, CONSTRUCTOR, FIELD, LOCAL_VARIABLE, METHOD, PACKAGE, PARAMETER, TYPE.
	 *
	 */
}
