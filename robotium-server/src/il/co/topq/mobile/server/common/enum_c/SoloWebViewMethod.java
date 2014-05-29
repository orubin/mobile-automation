package il.co.topq.mobile.server.common.enum_c;


public enum SoloWebViewMethod {
	
	ClickOnWebElement("ClickOnWebElement"), waitForWebElement("waitForWebElement"), enterTextInWebElement("enterTextInWebElement"), clearTextInWebElement("clearTextInWebElement");
	
	
	String methodeName;
	
	SoloWebViewMethod(String methodName  ) {
		this.methodeName = methodName;
		
	}
	

	
	private SoloWebViewMethod(SoloWebViewMethod et) {
		this.methodeName = et.methodeName;
	}
	
	public SoloWebViewMethod initMethodName(SoloWebViewMethod et, String LocatorNameStr ){
		
		if (LocatorNameStr == SoloWebViewMethod.ClickOnWebElement.getMethodName()){
			et.setMethodName(SoloWebViewMethod.ClickOnWebElement.getMethodName());
		}
		else if (LocatorNameStr == SoloWebViewMethod.waitForWebElement.getMethodName()){
			et.setMethodName(SoloWebViewMethod.waitForWebElement.getMethodName());
		}
		else if (LocatorNameStr == SoloWebViewMethod.enterTextInWebElement.getMethodName()){
			et.setMethodName(SoloWebViewMethod.enterTextInWebElement.getMethodName());
		}
		else if (LocatorNameStr == SoloWebViewMethod.clearTextInWebElement.getMethodName()){
			et.setMethodName(SoloWebViewMethod.clearTextInWebElement.getMethodName());
		}
		else if (LocatorNameStr == SoloWebViewMethod.clearTextInWebElement.getMethodName()){
			et.setMethodName(SoloWebViewMethod.clearTextInWebElement.getMethodName());
		}
		else if (LocatorNameStr == SoloWebViewMethod.clearTextInWebElement.getMethodName()){
			et.setMethodName(SoloWebViewMethod.clearTextInWebElement.getMethodName());
		}
		
		return et;
		
	}

	public String getMethodName() {
		return methodeName;
	}

	public void setMethodName(String locatorNameStr) {
		methodeName = locatorNameStr;
	}

	

	
}