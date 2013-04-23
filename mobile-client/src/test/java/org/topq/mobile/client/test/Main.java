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
	
	public static final boolean RUN_MULTI_APPLICATION_TEST = true;
	
	public static void main(String [ ] args) {
		if (RUN_MULTI_APPLICATION_TEST) {
			testMultiApplications();
		}
		else {
			testMainApplication();
		}
	}
	
	public static void testMainApplication() {
		try {
			MobileClientInterface clientAPI = MobileClient.getInstance();
			clientAPI.setExecutorID("LogingAPP");
			clientAPI.launch("org.topq.mobile.example.loginapp.LoginActivity","org.topq.mobile.server.impl.RobotiumExecutor");
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
	
	public static void testMultiApplications() {
		try {
			MobileClientInterface clientAPI = MobileClient.getInstance();
			clientAPI.setExecutorID("DictionaryAPP");
			clientAPI.launch("com.example.android.searchabledict.SearchableDictionary","org.topq.mobile.server.impl.RobotiumExecutor2");
			clientAPI.clickOnActionBarItem(0x7f090004);
			Thread.sleep(2*1000);
			clientAPI.enterText(0, "do");
			Thread.sleep(2*1000);
			clientAPI.clickInList(0);
			Thread.sleep(2*1000);
			clientAPI.getText(1);
			clientAPI.setExecutorID("LogingAPP");
			clientAPI.launch("org.topq.mobile.example.loginapp.LoginActivity","org.topq.mobile.server.impl.RobotiumExecutor");
			clientAPI.enterText(0, "tal@tal.com");
			clientAPI.enterText(1, "1234567");
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		System.exit(0);	
	}

}
