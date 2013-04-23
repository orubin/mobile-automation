package org.topq.mobile.server.interfaces;

import org.topq.mobile.server.interfaces.IDataCallback;
import org.topq.mobile.server.interfaces.IInstrumentationLauncher;

interface IExecutorService {

	void registerExecutor(String executorID,IDataCallback executor);
	void registerInstrumenationLauncher(IInstrumentationLauncher iInstrumentationLauncher);
	void startServerCommunication(int serverPort);
	String getLastExecutorID();
}