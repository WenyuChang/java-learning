package wenyu.learning.JAXBDemo;

import java.io.File;
import javax.xml.bind.JAXB;

/*
 * Java Architecture for XML Binding (JAXB) allows Java developers to map Java classes to XML representations
 */

public class MainDemo {

	private <T> void marshal(T model) {	 
		File file = new File("src/main/java/wenyu/demo/JAXBDemo/JAXBModelDemo.xml");
		JAXB.marshal(model, file);
	}
	
	private <T> void unmarshal() {
		File file = new File("src/main/java/wenyu/demo/JAXBDemo/JAXBModelDemo.xml");
		ModelClass model = JAXB.unmarshal(file, ModelClass.class);
//		System.out.println("element1:" + model.getElement1());
//		System.out.println("element2:" + model.getElement2());
//		System.out.println("element3:" + model.getElement3());
	}
	
	public void demoEntry() {
		ModelClass model = new ModelClass();
		model.setElement1("element1");
		model.setElement2(0);
		model.setElement3(true);
		
		// marshal from ModelClass to JAXBModelDemo.xml
		marshal(model);
		
		// unmarshal from JAXBModelDemo.xml to ModelClass and print out value
		unmarshal();
	}
	
	public static void main(String[] args) {
		new MainDemo().demoEntry();
	}

}
