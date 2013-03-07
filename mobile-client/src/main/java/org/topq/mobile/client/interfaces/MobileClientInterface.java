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
	 * will get the text of the input text view index
	 * @param index the index of the text view on the screen
	 * @return the text of the text view
	 * @throws Exception
	 */
	public String getTextView(int index) throws Exception ;
	
	/**
	 * will get the id of the text view with the input text
	 * @param text the text that is in the text view
	 * @return id of the text view
	 * @throws Exception
	 */
	public String getTextViewIndex(String text) throws Exception;
	
	/**
	 * will get a list of all the texts of the current display text views
	 * @return list of all the text views current displayed
	 * @throws Exception
	 */
	public String getCurrentTextViews() throws Exception;
	
	/**
	 * will get the text of the view by the input index
	 * @param index the index of the view in the screen
	 * @return the text of the input index text view
	 * @throws Exception
	 */
	public String getText(int index) throws Exception;
	
	/**
	 * will click on the menu item with the input text
	 * @param item the text of the item in the menu
	 * @return return status of the operation
	 * @throws Exception
	 */
	public String clickOnMenuItem(String item) throws Exception;
	
	/**
	 * will click on any view with the input index
	 * @param index the index of the view on the screen
	 * @return return status of the operation
	 * @throws Exception
	 */
	public String clickOnView(int index) throws Exception;
	
	/**
	 * will enter text in a text box by the input text and index
	 * @param index the index of the text box on the screen
	 * @param text the text to enter
	 * @return return status of the operation
	 * @throws Exception
	 */
	public String enterText(int index, String text) throws Exception;
	
	/**
	 * will click on button with the input index
	 * @param index the index of the button on the screen
	 * @return return status of the operation
	 * @throws Exception
	 */
	public String clickOnButton(int index) throws Exception;
	
	/**
	 * will click on list item with the input index
	 * @param index the index of the item in the list
	 * @return return status of the operation
	 * @throws Exception
	 */
	public String clickInList(int index) throws Exception;
	
	/**
	 * will clear a text box with the input index
	 * @param index the index of the text box to clear
	 * @return return status of the operation 
	 * @throws Exception
	 */
	public String clearEditText(int index) throws Exception;
	
	/**
	 * will click on button that includes the input text
	 * @param text the text on the button
	 * @return return status of the operation
	 * @throws Exception
	 */
	public String clickOnButtonWithText(String text) throws Exception;
	
	/**
	 * will click on the text with the input  text
	 * @param text the text to click on
	 * @return return status of the operation
	 * @throws Exception
	 */
	public String clickOnText(String text) throws Exception;

	/**
	 * simulate a click on an hardware button
	 * @param button an enum of the possible hardware buttons
	 * @return return status of the operation
	 * @throws Exception
	 */
	public String clickOnHardwereButton(HardwareButtons button) throws Exception;
	
	/**
	 * will click on the single input char
	 * @param key ascii number of the char wanted to click
	 * @return return status of the operation
	 * @throws Exception
	 */
	public String sendKey(int key) throws Exception;
	
	/**
	 * will close the connection to the server
	 * @throws Exception
	 */
	public void closeConnection() throws Exception;

}
