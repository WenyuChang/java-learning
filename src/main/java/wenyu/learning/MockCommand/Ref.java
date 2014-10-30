package wenyu.learning.MockCommand;

public class Ref<T> {
	private T value;

	public Ref() {
		
	}
	
    public Ref(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    @Override
    public String toString() {
    	if (value == null) {
    		return super.toString();
    	}
    	
        return value.toString();
    }

    @Override
    public boolean equals(Object obj) {
    	if (value == null) {
    		return obj == null;
    	}
    	
        return value.equals(obj);
    }
}
