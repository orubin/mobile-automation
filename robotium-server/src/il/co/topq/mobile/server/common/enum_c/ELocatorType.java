package il.co.topq.mobile.server.common.enum_c;


public enum ELocatorType {
	
	ID("ID"), NAME("NAME"), CLASS("CLASS"), CSS("CSS"), XPATH("XPATH");
	
	
	String LocatorNameStr;
	
	ELocatorType(String LocatorNameStr  ) {
		this.LocatorNameStr = LocatorNameStr;
		
	}
	

	
	private ELocatorType(ELocatorType et) {
		this.LocatorNameStr = et.LocatorNameStr;
	}
	
	public ELocatorType initELocatorType(ELocatorType et, String LocatorNameStr ){
		
		if (LocatorNameStr == ELocatorType.CSS.getLocatorNameStr()){
			et.setLocatorNameStr(ELocatorType.CSS.getLocatorNameStr());
		}
		else if (LocatorNameStr == ELocatorType.NAME.getLocatorNameStr()){
			et.setLocatorNameStr(ELocatorType.NAME.getLocatorNameStr());
		}
		else if (LocatorNameStr == ELocatorType.CLASS.getLocatorNameStr()){
			et.setLocatorNameStr(ELocatorType.CLASS.getLocatorNameStr());
		}
		else if (LocatorNameStr == ELocatorType.ID.getLocatorNameStr()){
			et.setLocatorNameStr(ELocatorType.ID.getLocatorNameStr());
		}
		else if (LocatorNameStr == ELocatorType.XPATH.getLocatorNameStr()){
			et.setLocatorNameStr(ELocatorType.XPATH.getLocatorNameStr());
		}
		
		return et;
		
	}

	public String getLocatorNameStr() {
		return LocatorNameStr;
	}

	public void setLocatorNameStr(String locatorNameStr) {
		LocatorNameStr = locatorNameStr;
	}

	

	
}