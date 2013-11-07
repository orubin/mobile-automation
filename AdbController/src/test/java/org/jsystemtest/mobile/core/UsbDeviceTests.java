package org.jsystemtest.mobile.core;

import org.jsystemtest.mobile.core.device.USBDevice;
import org.junit.Test;

public class UsbDeviceTests {

	private static final String SERIAL = "4d0090140a6340d9";

	@Test
	public void testForceStop() throws ConnectionException, Exception {
		USBDevice device = AdbController.getInstance().waitForDeviceToConnect(SERIAL);
		device.forceStop("org.topq.mobile.example.loginapp");

	}

}
