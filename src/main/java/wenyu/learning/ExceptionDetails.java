package wenyu.learning;

public class ExceptionDetails {
	/*
	 * Throwable is at the top off all exceptions. Underneath Throwable you have Error and Exception. 
	 * Underneath Exception you have RuntimeException.
	 * 
	 * Java has two types of exceptions - checked and unchecked. Checked exceptions are enforced by the compiler 
	 * (you have to declare them in the throws clause and catch them eventually). Unchecked exceptions are not 
	 * enforced for catching or declaring in throws clause.
	 * 
	 * Throwable exists so that there is a parent for all exception types. You should never declare that you throw 
	 * Throwable and never catch it (unless you really really really know what you are doing).
	 * 
	 * Error exists to indicate issues with the runtime environment, things that your program probably cannot recover 
	 * from, such as a badly formatted class file or the VM running out of memory. You should not catch an Error unless 
	 * you really know what you are doing.
	 * 
	 * Exception exists as the root for all non-programmer errors (see RuntimeException for the "exception" to this) , 
	 * such as a file cannot be created because the disk is full. You should not throw, throws, or catch Exception. 
	 * If you have to catch Exception make sure you know what you are doing.
	 * 
	 * RuntimeException (UncheckedException) exists to indicate all programmer error, such as going past the end of an array 
	 * or calling a method on a null object. These are things that you should fix so that they do not throw exceptions -
	 * the indicate that you, the programmer, screwed up the code. Again, you should not catch these unless you know what you 
	 * are doing.
	 */
}
