package wenyu.learning.WebServiceDemo;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.ParameterStyle;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

/**
 * @WebService
 *    name: <wsdl:portType>
 *    targetNamespace
 *    serviceName: <wsdl:service>
 *    wsdlLocation
 *    endpointInterface
 *    portName: <wsdl:port>
 */

/**
 * @SOAPBinding
 *    style: Style.DOCUMENT/Style.RPC (default: DOCUMENT)
 *    use: Use.LITERAL/Use.ENCODED (default: LITERAL)
 *    parameterStyle: ParameterStyle.WRAPPED/ParameterStyle.BARE (default: WRAPPED)
 */

/** 
 * @WebMethod
 *    operationName: <wsdl:operation>
 *    action: <soap:operation>
 *	  exclude: true/false (default: false)
 */

@WebService(name="SoapServicePort", serviceName="SoapService", portName="SoapServicePort")
@SOAPBinding(style=Style.DOCUMENT, use=Use.LITERAL, parameterStyle=ParameterStyle.WRAPPED)
public class SOAPServiceImpl implements ISOAPService {
	@WebMethod (operationName="SimpleExecuteOp", action="SimpleExecuteAction", exclude=false)
	public void simpleExecute() {
		System.out.println("=== In the Simple Execute Method ===");
		
	}
	
	@WebMethod (operationName="ExecuteModelOp", action="ExecuteModelAction", exclude=true)
	public String modelExecution(String model) {
		System.out.println("=== In the Simple Execute Method ===");
		System.out.println("    Pass-in model is: " + model.toString());
		return model;
	}
	
	@WebMethod (exclude=true)
	public void otherMethod() {
		System.out.println("=== In the Other Method ===");
	}
}
