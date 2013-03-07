package org.topq.mobile.client.impl;

import org.apache.log4j.Logger;
import org.topq.mobile.client.interfaces.MobileClientInterface;
import org.topq.mobile.common.client.enums.HardwareButtons;
import org.topq.mobile.common.datamodel.CommandRequest;
import org.topq.mobile.common.datamodel.CommandResponse;
import org.topq.mobile.common.server.consts.TcpConsts;
import org.topq.mobile.common.server.utils.JsonParser;
import org.topq.mobile.tcp.impl.TcpClient;

/**
 * this is the implementation of the commands that
 * the robotium server will interpret in to methods 
 * @author tal ben shabtay , limor bortman
 *
 */
public class MobileClient implements MobileClientInterface {

	private static Logger logger = Logger.getLogger(MobileClient.class);

	private TcpClient tcpClient;
	private int serverPort;
	private String serverHost;
	
	/**
	 * this CTOR will init a tcp client object with the input params 
	 * @param serverHost the server ip
	 * @param serverPort the server port
	 */
	private MobileClient(String serverHost,int serverPort) {
		try {
			this.serverPort = serverPort;
			this.serverHost = serverHost;
			this.tcpClient = new TcpClient(this.serverHost, this.serverPort);
		}
		catch(Exception e) {
			logger.error("Exception in constructor !!", e);
		}
	}
	
	/**
	 * will get a mobile client interface with a connection to the default server parameters
	 * as localhost:4321 server
	 * @return an interface of the mobile client
	 */
	public static MobileClientInterface getInstance(){
		return new MobileClient(TcpConsts.SERVER_DEFAULT_HOSTNAME,TcpConsts.SERVER_DEFAULT_PORT);
	}
	
	/**
	 * will get a mobile client interface with a connection to the input server parameters
	 * @param serverHost the server ip
	 * @param serverPort the server port
	 * @return an interface of the mobile client
	 */
	public static MobileClientInterface getInstance(String serverHost,int serverPort){
		return new MobileClient(serverHost,serverPort);
	}


	/**
	 * send data wrapper of the input command and text into a proper command object 
	 * @param command the string command to execute
	 * @param params the parameters to send along side of the command
	 * @throws Exception
	 */
	private String sendData(String command, String... params) throws Exception {
		String resultValue;
		try {
			CommandResponse result = sendDataAndGetJSonObj(new CommandRequest(command,params));
			resultValue = result.getResponse();
			if (!result.isSucceeded()) {
				logger.error(result);
			} 
			else {
				logger.info(result);
			}
		} catch (Exception e) {
			logger.error("Failed to send / receive data", e);
			throw e;
		}
		return resultValue;
	}
	
	/**
	 * will send the command object to the server and wait for response
	 * @param request a command request object
	 * @return a command response of object the input command request
	 * @throws Exception
	 */
	private CommandResponse sendDataAndGetJSonObj(CommandRequest request) throws Exception {
		String jsonRequest = JsonParser.toJson(request);
		logger.info("Sending command: " + jsonRequest);
		CommandResponse result = null;
		logger.info("Send Data to " + this.serverHost+':'+this.serverPort);

		try {
			String resultStr = null;
			if ((resultStr = this.tcpClient.sendData(jsonRequest)) == null) {
				throw new Exception("No data recvied from server! pleas check server log!");
			}
			result = JsonParser.fromJson(resultStr, CommandResponse.class);
		} 
		catch (Exception e) {
			logger.error("Failed to send / receive data", e);
			throw e;
		}
		return result;
	}

	/**
	 * NOTE ! THIS METHOD MUST BE THE FIRST COMMAND BEFORE ANY OTHER COMMAND
	 * the launch method will launch the instrumentation of the application
	 * @param launcherActivityClass the full class name with the package of the main launcher activity
	 * @return return status of the operation
	 * @throws Exception
	 */
	public String launch(String launcherActivityClass) throws Exception {
		return sendData("launch",launcherActivityClass);
	}
	
	/**
	 * will get the text of the input text view index
	 * @param index the index of the text view on the screen
	 * @return the text of the text view
	 * @throws Exception
	 */
	public String getTextView(int index) throws Exception {
		return sendData("getTextView", Integer.toString(index));
	}

	/**
	 * will get the id of the text view with the input text
	 * @param text the text that is in the text view
	 * @return id of the text view
	 * @throws Exception
	 */
	public String getTextViewIndex(String text) throws Exception {
		return sendData("getTextViewIndex", text);
	}

	/**
	 * will get a list of all the texts of the current display text views
	 * @return list of all the text views current displayed
	 * @throws Exception
	 */
	public String getCurrentTextViews() throws Exception {
		return sendData("getCurrentTextViews", "a");
	}

	/**
	 * will get the text of the view by the input index
	 * @param index the index of the view in the screen
	 * @return the text of the input index text view
	 * @throws Exception
	 */
	public String getText(int index) throws Exception {
		return sendData("getText", Integer.toString(index));
	}

	/**
	 * will click on the menu item with the input text
	 * @param item the text of the item in the menu
	 * @return return status of the operation
	 * @throws Exception
	 */
	public String clickOnMenuItem(String item) throws Exception {
		return sendData("clickOnMenuItem", item);
	}

	/**
	 * will click on any view with the input index
	 * @param index the index of the view on the screen
	 * @return return status of the operation
	 * @throws Exception
	 */
	public String clickOnView(int index) throws Exception {
		return sendData("clickOnView", Integer.toString(index));
	}

	/**
	 * will enter text in a text box by the input text and index
	 * @param index the index of the text box on the screen
	 * @param text the text to enter
	 * @return return status of the operation
	 * @throws Exception
	 */
	public String enterText(int index, String text) throws Exception {
		return sendData("enterText", Integer.toString(index), text);
	}

	/**
	 * will click on button with the input index
	 * @param index the index of the button on the screen
	 * @return return status of the operation
	 * @throws Exception
	 */
	public String clickOnButton(int index) throws Exception {
		return sendData("clickOnButton", Integer.toString(index));
	}

	/**
	 * will click on list item with the input index
	 * @param index the index of the item in the list
	 * @return return status of the operation
	 * @throws Exception
	 */
	public String clickInList(int index) throws Exception {
		return sendData("clickInList", Integer.toString(index));
	}

	/**
	 * will clear a text box with the input index
	 * @param index the index of the text box to clear
	 * @return return status of the operation 
	 * @throws Exception
	 */
	public String clearEditText(int index) throws Exception {
		return sendData("clearEditText", Integer.toString(index));
	}

	/**
	 * will click on button that includes the input text
	 * @param text the text on the button
	 * @return return status of the operation
	 * @throws Exception
	 */
	public String clickOnButtonWithText(String text) throws Exception {
		return sendData("clickOnButtonWithText", text);
	}

	/**
	 * will click on the text with the input  text
	 * @param text the text to click on
	 * @return return status of the operation
	 * @throws Exception
	 */
	public String clickOnText(String text) throws Exception {
		return sendData("clickOnText", text);
	}

	/**
	 * will click on the single input char
	 * @param key ascii number of the char wanted to click
	 * @return return status of the operation
	 * @throws Exception
	 */
	public String sendKey(int key) throws Exception {
		return sendData("sendKey", Integer.toString(key));
	}

	/**
	 * simulate a click on an hardware button
	 * @param button an enum of the possible hardware buttons
	 * @return return status of the operation
	 * @throws Exception
	 */
	public String clickOnHardwereButton(HardwareButtons button) throws Exception {
		return sendData("clickOnHardware", button.name());
	}

	/**
	 * will close the connection to the server
	 * @throws Exception
	 */
	public void closeConnection() throws Exception {
		sendData("exit");
	}
	
//	public void closeActivity() throws Exception {
//		sendData("closeActivity");
//	}
//
//	public String isViewVisible(String viewName) throws Exception {
//		return sendData("isViewVisible", viewName);
//	}
//
//	public String clickInControlByIndex(String controlName, int indexToClickOn) throws Exception {
//		return sendData("clickInControlByIndex", controlName, Integer.toString(indexToClickOn));
//	}
//
//	public String isViewVisibleByViewName(String viewName) throws Exception {
//		return sendData("isViewVisibleByViewName", viewName);
//	}
//
//	public String isViewVisibleByViewId(int viewId) throws Exception {
//		return sendData("isViewVisibleByViewId", String.valueOf(viewId));
//	}
//	public String isButtonVisible(Attribute attribute, String value) throws Exception {
//		return sendData("isButtonVisible", attribute.name(), value);
//	}
	
}
