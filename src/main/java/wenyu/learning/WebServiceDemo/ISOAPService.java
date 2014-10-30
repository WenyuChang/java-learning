package wenyu.learning.WebServiceDemo;

import javax.jws.WebService;

/**
 * @author wenychan
 *
 */
@WebService
public interface ISOAPService {
	/**
	 * Simple execute method
	 * which will just print sth
	 */
	public void simpleExecute();
	/**
	 * Passed in a model and then excute model
	 * @param model
	 * @return model toString
	 */
	public String modelExecution(String model);
	
	/**
	 * This method will not include into wsdl by setting exclude to true
	 */
	public void otherMethod();
}
