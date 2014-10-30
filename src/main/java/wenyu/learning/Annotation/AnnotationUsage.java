package wenyu.learning.Annotation;


public class AnnotationUsage {

	@MyInheritedAnnotation(annoId = 0)
	@MyRuntimePolicyAnnotation(annoId=1, values={"aa", "bb", "cc"})
	@MyClassPolicyAnnotation(annoId=2)
	@MySourcePolicyAnnotation(annoId=3)
	@MyMultiAnnotation(annos = {
			@MyRuntimePolicyAnnotation(annoId = 5, values = {"aa", "bb"}),
			@MyRuntimePolicyAnnotation(annoId = 6, values = {"cc", "dd"})
			})
	@MyMultiAnnotationWithValueParam({
		@MyRuntimePolicyAnnotation(annoId = 7, values = {"aa", "bb"}),
		@MyRuntimePolicyAnnotation(annoId = 8, values = {"cc", "dd"})
		})
	public static void methodAnno() {
		// only the runtime policy annotation will be accessed when run showAnnos
		System.out.println("Method with Runtime Policy Annotation invoke...");
	}
	
	public static void methodWithoutAnno() {
		System.out.println("Method without Annotation invoke...");
	}
	
	public static void main(String[] args) throws Exception {
		MyAnnotations.showAnnos(AnnotationUsage.class);
	}
}
