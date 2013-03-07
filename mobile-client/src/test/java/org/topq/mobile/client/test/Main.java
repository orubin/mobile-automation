package org.topq.mobile.client.test;

import org.topq.mobile.client.impl.MobileClient;
import org.topq.mobile.client.interfaces.MobileClientInterface;

/**
 * 
 * @author tal ben shabtay
 * this class is the main test class
 * which includes a single main method that shows the usage
 * of the mobile client interface.
 *
 */
public class Main {
	
	public static void main(String [ ] args) {
		try {
			MobileClientInterface clientAPI = MobileClient.getInstance();
			clientAPI.launch("org.topq.mobile.example.loginapp.LoginActivity");
			clientAPI.enterText(0, "tal@tal.com");
			clientAPI.enterText(1, "1234567");
			clientAPI.clickOnButtonWithText("Sign in or register");
			Thread.sleep(1000 * 5);
			clientAPI.clickOnButtonWithText("Ok");
			clientAPI.clickOnButtonWithText("Sign in or register");
			Thread.sleep(1000 * 5);
			clientAPI.clickOnButtonWithText("Ok");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);		
	}

}
