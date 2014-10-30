package wenyu.learning.ClassLoader;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class CustomClassLoader extends ClassLoader {
	
    private Map<String, Class<?>> classes = new HashMap<String, Class<?>>();

    @Override
    public String toString() {
        return CustomClassLoader.class.getName();
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
    	System.out.println("Custom ClassLoader - findClass(String name)");
        if (classes.containsKey(name)) {
            return classes.get(name);
        }

        byte[] classData;
        try {
            classData = loadClassData(name);
        } catch (IOException e) {
            throw new ClassNotFoundException("Class [" + name + "] could not be found", e);
        }

        Class<?> c = defineClass(name, classData, 0, classData.length);
        resolveClass(c);
        classes.put(name, c);

        return c;
    }

    private byte[] loadClassData(String name) throws IOException {
    	System.out.println("Custom ClassLoader - loadClassData(String name)");
        BufferedInputStream in = new BufferedInputStream(ClassLoader.getSystemResourceAsStream(name.replace(".", "/") + ".class"));
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        
        int i;
        while ((i = in.read()) != -1) {
            out.write(i);
        }

        in.close();
        byte[] classData = out.toByteArray();
        out.close();

        return classData;
    }

    public static void main(String[] args) throws ClassNotFoundException,
            InstantiationException, IllegalAccessException,
            NoSuchMethodException, SecurityException, IllegalArgumentException,
            InvocationTargetException
    {
        CustomClassLoader loader = new CustomClassLoader();
        Class<?> c = loader.findClass("wenyu.demo.ClassLoader.DemoClass");
        Object o = c.newInstance();
        Method m = c.getMethod("toString");
        System.out.println(m.invoke(o));
    }

}

