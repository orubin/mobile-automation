package il.co.topq.mobile.server.interfaces;

import il.co.topq.mobile.server.interfaces.IDataCallback;
import il.co.topq.mobile.server.interfaces.IInstrumentationLauncher;

interface IExecutorService {

	void registerExecutor(String executorID,IDataCallback executor);
	void registerInstrumenationLauncher(IInstrumentationLauncher iInstrumentationLauncher);
	void startServerCommunication(int serverPort);
	String getLastExecutorID();
}