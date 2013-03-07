package org.topq.mobile.client.interfaces;

import org.topq.mobile.common.client.enums.HardwareButtons;

/**
 * 
 * @author Bortman Limor,Tal ben shabtay
 * this interface is the API that will be exposed to the clients.
 * the features in this API has the ability to operate functions
 * upon an android application
 *
 */
public interface MobileClientInterface {
	
	/**
	 * NOTE ! THIS METHOD MUST BE THE FIRST COMMAND BEFORE ANY OTHER COMMAND
	 * the launch method will launch the instrumentation of the application
	 * @param launcherActivityClass the full class name with the package of the main launcher activity
	 * @return return status of the operation
	 * @throws Exception
	 */
	public String launch(String launcherActivityClass) throws Exception;
	
	/**
	 * 
	 * @param index
	 * @return
	 * @throws Exception
	 */
	public String getTextView(int index) throws Exception ;
	
	public String getTextViewIndex(String text) throws Exception;
	
	public String getCurrentTextViews() throws Exception;
	
	public String getText(int index) throws Exception;
	
	public String clickOnMenuItem(String item) throws Exception;
	
	public String clickOnView(int index) throws Exception;
	
	public String enterText(int index, String text) throws Exception;
	
	public String clickOnButton(int index) throws Exception;
	
	public String clickInList(int index) throws Exception;
	
	public String clearEditText(int index) throws Exception;
	
	public String clickOnButtonWithText(String text) throws Exception;
	
	public String clickOnText(String text) throws Exception;

//	public String goBack() throws Exception;
	
	public String clickOnHardwereButton(HardwareButtons button) throws Exception;
	
	public String sendKey(int key) throws Exception;
	
	public void closeConnection() throws Exception;

}
