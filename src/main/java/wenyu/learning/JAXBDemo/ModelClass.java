package wenyu.learning.JAXBDemo;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ModelClass", propOrder = {
    "element1",
    "element2",
    "element3"
})
@XmlRootElement(name = "ModelClass")
public class ModelClass {
	
	@XmlElement(name = "element1", required = true)
	private String element1;
	@XmlElement(name = "element2", required = true)
	private int element2;
	@XmlElement(name = "element3", required = true)
	private boolean element3;
	
	public String getElement1() {
		return element1;
	}
	public void setElement1(String element1) {
		this.element1 = element1;
	}
	public int getElement2() {
		return element2;
	}
	public void setElement2(int element2) {
		this.element2 = element2;
	}
	public boolean getElement3() {
		return element3;
	}
	public void setElement3(boolean element3) {
		this.element3 = element3;
	}
}
