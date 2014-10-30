package wenyu.learning.GenericDemo;

/**
 * 
 * Generic Classes:
 * 
 * A generic class declaration looks like a non-generic 
 * class declaration, except that the class name is followed 
 * by a type parameter section.
 * 
 * As with generic methods, the type parameter section of a 
 * generic class can have one or more type parameters separated 
 * by commas. These classes are known as parameterized classes 
 * or parameterized types because they accept one or more 
 * parameters.
 */

public class GenericClass<T> {
	private T value;
	public void setValue(T value) {
		this.value = value;
	}
	public T getValue() {
		return value;
	}
	
	@Override
	public String toString() {
		return value.toString();
	}
}
