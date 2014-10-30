package wenyu.learning.WebServiceDemo;

import javax.xml.ws.Endpoint;

public class MockSOAPServer{

	private Thread codeFirstServiceServer = new Thread() {
		@Override
		public void run() {
			System.out.println("============== Starting Server ==============");
			String address = "http://localhost:8080/ISOAPService";
			ISOAPService service = new SOAPServiceImpl();
			Endpoint.publish(address, service);
		}
	};
	
	public static void main(String[] args) throws Exception {
		MockSOAPServer servers = new MockSOAPServer();
		
		servers.codeFirstServiceServer.setDaemon(false);
		servers.codeFirstServiceServer.start();
		servers.codeFirstServiceServer.join();
	}

}
