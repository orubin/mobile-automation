package il.co.topq.mobile.client.impl;

import il.co.topq.mobile.client.interfaces.MobileClientInterface;
import il.co.topq.mobile.common.client.enums.HardwareButtons;
import il.co.topq.mobile.common.datamodel.CommandRequest;
import il.co.topq.mobile.common.datamodel.CommandResponse;
import il.co.topq.mobile.common.server.utils.JsonParser;
import il.co.topq.mobile.tcp.impl.TcpClient;

import java.io.File;
import java.io.FileOutputStream;

import net.iharder.Base64;

import org.apache.log4j.Logger;

/**
 * this is the implementation of the commands that the robotium server will interpret in to methods
 * 
 * @author tal ben shabtay , limor bortman
 * 
 */
public class MobileClient implements MobileClientInterface {

	private static Logger logger = Logger.getLogger(MobileClient.class);

	private TcpClient tcpClient;
	private int serverPort;
	private String serverHost;

	public enum Attribute {
		TEXT, ID;
	}

	/**
	 * 
	 * @param serverHost
	 *            The ip or host name of the mobile device or emulator
	 * @param serverPort
	 *            The port in which the server is listens to
	 */
	public MobileClient(String serverHost, int serverPort) {
		try {
			this.serverPort = serverPort;
			this.serverHost = serverHost;
			this.tcpClient = new TcpClient(this.serverHost, this.serverPort);
		} catch (Exception e) {
			logger.error("Exception in constructor !!", e);
		}
	}

	/**
	 * send data wrapper of the input command and text into a proper command object
	 * 
	 * @param command
	 *            the string command to execute
	 * @param params
	 *            the parameters to send along side of the command
	 * @throws Exception
	 */
	public CommandResponse sendData(String command, String... params) throws Exception {
		CommandResponse result = null;
		try {
			result = sendDataAndGetJSonObj(new CommandRequest(command, params));
		} catch (Exception e) {
			logger.error("Failed to send / receive data", e);
			throw e;
		}
		return result;
	}

	/**
	 * will send the command object to the server and wait for response
	 * 
	 * @param request
	 *            a command request object
	 * @return a command response of object the input command request
	 * @throws Exception
	 */
	private CommandResponse sendDataAndGetJSonObj(CommandRequest request) throws Exception {
		String jsonRequest = JsonParser.toJson(request);
		logger.info("Sending command: " + jsonRequest);
		CommandResponse result = null;
		logger.info("Send Data to " + this.serverHost + ':' + this.serverPort);

		try {
			String resultStr = null;
			if ((resultStr = this.tcpClient.sendData(jsonRequest)) == null) {
				throw new Exception("No data recvied from server! please check server log!");
			}
			result = JsonParser.fromJson(resultStr, CommandResponse.class);
		} catch (Exception e) {
			logger.error("Failed to send / receive data", e);
			throw e;
		}
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#launch(java.lang.String)
	 */

	@Override
	public CommandResponse launch(String launcherActivityClass) throws Exception {
		return sendData("launch", launcherActivityClass);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#getTextView(int)
	 */
	@Override
	public CommandResponse getTextView(int index) throws Exception {
		return sendData("getTextView", Integer.toString(index));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#getTextViewIndex(java.lang.String)
	 */
	@Override
	public CommandResponse getTextViewIndex(String text) throws Exception {
		return sendData("getTextViewIndex", text);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#getCurrentTextViews()
	 */
	@Override
	public CommandResponse getCurrentTextViews() throws Exception {
		return sendData("getCurrentTextViews", "a");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#getText(int)
	 */
	@Override
	public CommandResponse getText(int index) throws Exception {
		return sendData("getText", Integer.toString(index));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#clickOnMenuItem(java.lang.String)
	 */
	@Override
	public CommandResponse clickOnMenuItem(String item) throws Exception {
		return sendData("clickOnMenuItem", item);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#clickOnView(int)
	 */
	@Override
	public CommandResponse clickOnView(int index) throws Exception {
		return sendData("clickOnView", Integer.toString(index));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#verifyViewExistsByDescription(java.lang.String,
	 * boolean, boolean, boolean, float, float)
	 */
	@Override
	public CommandResponse verifyViewExistsByDescription(String description, boolean click, boolean startsWith, boolean clickInSpecificPosition, float x, float y) throws Exception {
		return sendData("verifyViewExistsByDescription", description, Boolean.toString(click), Boolean.toString(startsWith), Boolean.toString(clickInSpecificPosition), Float.toString(x),
				Float.toString(y));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#enterText(int, java.lang.String)
	 */
	@Override
	public CommandResponse enterText(int index, String text) throws Exception {
		return sendData("enterText", Integer.toString(index), text);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#clickOnButton(int)
	 */
	@Override
	public CommandResponse clickOnButton(int index) throws Exception {
		return sendData("clickOnButton", Integer.toString(index));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#clearEditText(int)
	 */
	@Override
	public CommandResponse clearEditText(int index) throws Exception {
		return sendData("clearEditText", Integer.toString(index));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#clickOnButtonWithText(java.lang.String)
	 */
	@Override
	public CommandResponse clickOnButtonWithText(String text) throws Exception {
		return sendData("clickOnButtonWithText", text);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#clickOnText(java.lang.String)
	 */
	@Override
	public CommandResponse clickOnText(String text) throws Exception {
		return sendData("clickOnText", text);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#waitForActivity(java.lang.String, int)
	 */
	@Override
	public CommandResponse waitForActivity(String activityName, int timeout) throws Exception {
		return sendData("waitForActivity", activityName, Integer.toString(timeout));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#sendKey(int)
	 */
	@Override
	public CommandResponse sendKey(int key) throws Exception {
		return sendData("sendKey", Integer.toString(key));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * il.co.topq.mobile.client.impl.Delme#clickOnHardwareButton(il.co.topq.mobile.common.client
	 * .enums.HardwareButtons)
	 */
	@Override
	public CommandResponse clickOnHardwareButton(HardwareButtons button) throws Exception {
		return sendData("clickOnHardware", button.name());
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#closeConnection()
	 */
	@Override
	public void closeConnection() throws Exception {
		sendData("exit");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#getViews()
	 */
	@Override
	public CommandResponse getViews() throws Exception {
		return sendData("getViews");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#swipeLeft()
	 */
	@Override
	public CommandResponse swipeLeft() throws Exception {
		return sendData("swipeLeft");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#swipeRight()
	 */
	@Override
	public CommandResponse swipeRight() throws Exception {
		return sendData("swipeRight");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#clickOnImageButton(int)
	 */
	@Override
	public CommandResponse clickOnImageButton(int index) throws Exception {
		return sendData("clickOnImageButton", Integer.toString(index));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#clickOnImage(int)
	 */
	@Override
	public CommandResponse clickOnImage(int index) throws Exception {
		return sendData("clickOnImage", Integer.toString(index));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#scrollDown()
	 */
	@Override
	public CommandResponse scrollDown() throws Exception {
		return sendData("scrollDown", new String[] {});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#isTextVisible(java.lang.String)
	 */
	@Override
	public CommandResponse isTextVisible(String text) throws Exception {
		return sendData("isTextVisible", new String[] { text });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#scrollDownUntilTextIsVisible(java.lang.String)
	 */
	@Override
	public CommandResponse scrollDownUntilTextIsVisible(String text) throws Exception {
		return sendData("scrollDownUntilTextIsVisible", new String[] { text });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#getCurrentActivity()
	 */
	@Override
	public CommandResponse getCurrentActivity() throws Exception {
		return sendData("getCurrentActivity", new String[] {});
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#clickOnActionBarItem(int)
	 */
	@Override
	public CommandResponse clickOnActionBarItem(int index) throws Exception {
		return sendData("clickOnActionBarItem", new String[] { Integer.toString(index) });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#clickOnScreen(float, float, boolean)
	 */
	@Override
	public CommandResponse clickOnScreen(float x, float y, boolean relative) throws Exception {
		return sendData("clickOnScreen", new String[] { Float.toString(x), Float.toString(y), (relative ? "relative" : "absolute") });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#drag(float, float, float, float, int, boolean)
	 */
	@Override
	public CommandResponse drag(float fromX, float toX, float fromY, float toY, int steps, boolean relative) throws Exception {
		return sendData("drag", new String[] { Float.toString(fromX), Float.toString(toX), Float.toString(fromY), Float.toString(toY), Integer.toString(steps), (relative ? "relative" : "absolute") });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#setOrientation(int)
	 */
	@Override
	public CommandResponse setOrientation(int orientation) throws Exception {
		if (orientation == 0) {
			return sendData("setLandscapeOrientation");
		} else if (orientation == 1) {
			return sendData("setPortraitOrientation");
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#scrollToEdge(il.co.topq.mobile.client.interfaces.
	 * MobileClientInterface.EDGE)
	 */
	@Override
	public CommandResponse scrollToEdge(EDGE edge) throws Exception {
		return sendData("scrollToEdge", new String[] { (edge == EDGE.TOP ? "top" : "bottom") });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#takeScreenshot()
	 */
	@Override
	public File takeScreenshot() throws Exception {
		CommandResponse response = sendData("takeScreenshot");
		File tempFile = null;
		if (response.isSucceeded()) {
			byte[] body = Base64.decode(response.getResponse());

			tempFile = File.createTempFile("screenshot", ".jpg");
			FileOutputStream imageOutFile = null;
			try {
				imageOutFile = new FileOutputStream(tempFile);
				imageOutFile.write(body);
			} finally {
				if (imageOutFile != null) {
					imageOutFile.close();
				}
			}
		}

		return tempFile;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#clickInList(int)
	 */
	@Override
	public CommandResponse clickInList(int index) throws Exception {
		return sendData("clickInList", Integer.toString(index));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#clickInList(int, int)
	 */
	@Override
	public CommandResponse clickInList(int index1, int index2) throws Exception {
		return sendData("clickInList", Integer.toString(index1), Integer.toString(index2));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#getAllVisibleIds()
	 */
	@Override
	public CommandResponse getAllVisibleIds() throws Exception {
		return sendData("getAllVisibleIds");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#click(java.lang.String)
	 */
	@Override
	public CommandResponse click(String expression) throws Exception {
		return sendData("click", expression);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#isViewVisibleByViewId(int)
	 */
	@Override
	public CommandResponse isViewVisibleByViewId(int viewId) throws Exception {
		return sendData("isViewVisibleByViewId", String.valueOf(viewId));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#isEditTextVisibleById(int)
	 */
	@Override
	public CommandResponse isEditTextVisibleById(int editTextId) throws Exception {
		return sendData("isViewVisibleByViewId", String.valueOf(editTextId));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#getCurrentActivityName()
	 */
	@Override
	public CommandResponse getCurrentActivityName() throws Exception {
		return sendData("getCurrentActivityName");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#closeActivity(java.lang.String)
	 */
	@Override
	public CommandResponse closeActivity(String activityName) throws Exception {
		return sendData("closeActivity", activityName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#isViewVisibleByViewName(java.lang.String)
	 */
	@Override
	public CommandResponse isViewVisibleByViewName(String viewName) throws Exception {
		return sendData("isViewVisibleByViewName", viewName);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#clickInControlByIndex(java.lang.String, int)
	 */
	@Override
	public CommandResponse clickInControlByIndex(String controlName, int indexToClickOn) throws Exception {
		return sendData("clickInControlByIndex", controlName, Integer.toString(indexToClickOn));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#clickOnMenuItemById(int)
	 */
	@Override
	public CommandResponse clickOnMenuItemById(int id) throws Exception {
		return sendData("clickOnMenuItemById", Integer.toString(id));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#closeApplication()
	 */
	@Override
	public void closeApplication() throws Exception {
		sendData("closeApplication");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#clickOnViewByIndex(int)
	 */
	@Override
	public CommandResponse clickOnViewByIndex(int index) throws Exception {
		return sendData("clickOnViewByIndex", Integer.toString(index));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#ScrollLeft()
	 */
	@Override
	public CommandResponse ScrollLeft() throws Exception {
		return sendData("scrollLeft");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#ScrollRight()
	 */
	@Override
	public CommandResponse ScrollRight() throws Exception {
		return sendData("scrollRight");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#ScrollUP()
	 */
	@Override
	public CommandResponse ScrollUP() throws Exception {
		return sendData("scrollUp");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#clickOnRadioButtonByIndex(int)
	 */
	@Override
	public CommandResponse clickOnRadioButtonByIndex(int radioButtonIndex) throws Exception {
		return sendData("clickOnRadioButton", Integer.toString(radioButtonIndex));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#clickOnImageByIndex(int)
	 */
	@Override
	public CommandResponse clickOnImageByIndex(int imageIndex) throws Exception {
		return sendData("clickOnImage", Integer.toString(imageIndex));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#clickOnActionBarHomeButton()
	 */
	@Override
	public CommandResponse clickOnActionBarHomeButton() throws Exception {
		return sendData("clickOnActionBarHomeButton");
	}

	@Override
	public CommandResponse showMenuOptions() throws Exception {
		return sendData("showMenuOptions");
	}

	@Override
	public byte[] pull(String fileName) throws Exception {
		// TODO: Handle this one!!!
		// JSONObject jsonObj = sendDataAndGetJSonObj("pull", fileName);
		// logger.info("command pull receved" + jsonObj);
		// return ((jsonObj.getString("file"))).getBytes("UTF-16LE");
		return null;
	}

	@Override
	public CommandResponse push(byte[] data, String newlocalFileName) throws Exception {
		return sendData("createFileInServer", newlocalFileName, Base64.encodeBytes(data, Base64.URL_SAFE), "true");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#setPreferanceCompleteRideCounter()
	 */
	@Override
	public CommandResponse setPreferanceCompleteRideCounter() throws Exception {
		return sendData("setPreferanceCompleteRideCounter");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see il.co.topq.mobile.client.impl.Delme#launch()
	 */
	@Override
	public CommandResponse launch() throws Exception {
		return sendData("launch");
	}

	public CommandResponse isButtonVisible(Attribute attribute, String value) throws Exception {
		return sendData("isButtonVisible", attribute.name(), value);
	}

	public CommandResponse setText(String text) throws Exception {
		return sendData("setText", text);
	}

}
