package wenyu.learning.JCA;

import java.security.Provider;
import java.security.Security;
import java.util.Enumeration;

/**
 * 
 * The term "Cryptographic Service Provider" (used interchangeably with "provider" in this document)
 * refers to a package or set of packages that supply a concrete implementation of a subset of the 
 * JDK Security API cryptography features. The Provider class is the interface to such a package or 
 * set of packages. It has methods for accessing the provider name, version number, and other information. 
 * Please note that in addition to registering implementations of cryptographic services, the Provider class 
 * can also be used to register implementations of other security services that might get defined as part 
 * of the JDK Security API or one of its extensions.
 *
 */

public class ProviderDemo {

	/**
	 * 
	 *  Static Registration
			The configuration file is located in the following location:
			        <java-home>/lib/security/java.security     [Unix]
			        <java-home>\lib\security\java.security     [Windows] 
			For each registered provider, this file should have a statement of the following form:
			        security.provider.n=masterClassName
			This declares a provider, and specifies its preference order n. The preference order is 
			the order in which providers are searched for requested algorithms (when no specific 
			provider is requested). The order is 1-based: 1 is the most preferred, followed by 2, 
			and so on. MasterClassName must specify the fully qualified name of provider's master
			class. The provider's documentation will specify its master class. This class is always 
			a subclass of the Provider class. The subclass constructor sets the values of various 
			properties that are required for the Java Cryptography API to look up the algorithms 
			or other facilities the provider implements.
		
		Dynamic Registration
			To register providers dynamically, applications call either the addProvider or insertProviderAt 
			method in the Security class. This type of registration is not persistent across VM instances, 
			and can only be done by "trusted" programs with the appropriate privilege.
	 */
	public void addProvider(Provider provider) {
		int pos = Security.addProvider(provider);
		if (pos == -1) {
			System.out.println("Failed to add provider.");
		} else {
			System.out.println(provider.getName() + " has been added at pos:" + pos);
		}
	}
	
	public void addProvider(Provider provider, int position) {
		int pos = Security.insertProviderAt(provider, position);
		if (pos == -1) {
			System.out.println("Failed to add provider.");
		} else {
			System.out.println(provider.getName() + " has been added at pos:" + pos);
		}
	}
	
	
	
	public void removeProvider(String providerName) {
		Security.removeProvider(providerName);
	}
	
	public void listCurrentProvider() {
		Provider[] providers = Security.getProviders();
		for(Provider provider:providers) {
			System.out.println("Provider Name: " + provider.getName());
			System.out.println("Provider Version: " + provider.getVersion());
			System.out.println("Provider Info: " + provider.getInfo());
			Enumeration<Object> keys = provider.keys();
			while(keys.hasMoreElements())
		    {
		        String key=(String) keys.nextElement();
		        String value = provider.get(key).toString();
		        System.out.println("Key-Value Pair: (" + key + ":" + value + ")");
		    }
			System.out.println("==============================================");
		}
	}
	
	public static void main(String[] args) {
		new ProviderDemo().listCurrentProvider();
	}

}
