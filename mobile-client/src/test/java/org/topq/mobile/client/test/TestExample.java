package org.topq.mobile.client.test;

import org.junit.Ignore;
import org.junit.Test;
import org.topq.mobile.client.impl.MobileClient;
import org.topq.mobile.client.interfaces.MobileClientInterface;

/**
 * 
 * @author tal ben shabtay this class is the main test class which includes a
 *         single main method that shows the usage of the mobile client
 *         interface.
 * 
 */
public class TestExample {

	private static final String IP = "Insert the device IP";

	@Test
	@Ignore
	public void testExample() throws Exception {
		MobileClientInterface clientAPI = new MobileClient(IP, 4321);
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

}
