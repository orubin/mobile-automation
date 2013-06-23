package org.topq.mobile.client.test;

import org.junit.Before;
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

	private static final String IP = "127.0.0.1";
	private MobileClientInterface mobile;
	
	@Before
	public void setUp(){
		mobile = new MobileClient(IP, 4321);
	}
	
	@Test
	@Ignore
	public void testExample() throws Exception {
		mobile.launch("org.topq.mobile.example.loginapp.LoginActivity");
		mobile.enterText(0, "tal@tal.com");
		mobile.enterText(1, "1234567");
		mobile.clickOnButtonWithText("Sign in or register");
		Thread.sleep(1000 * 5);
		mobile.clickOnButtonWithText("Ok");
		mobile.clickOnButtonWithText("Sign in or register");
		Thread.sleep(1000 * 5);
		mobile.clickOnButtonWithText("Ok");
	}
	
	@Test
	@Ignore
	public void testClickByExpression() throws Exception {
		mobile.launch("org.topq.mobile.example.loginapp.LoginActivity");
		mobile.enterText(0, "tal@tal.com");
		mobile.enterText(1, "1234567");
		mobile.click("//Button[@id='2131165190']");
		Thread.sleep(1000 * 5);
		mobile.click("//Button[@text='Ok']");
		
	}


}
