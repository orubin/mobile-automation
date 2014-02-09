package il.co.topq.mobile.server.interfaces;

import com.robotium.solo.Solo;

/**
 * 
 * @author limor bortman
 *	interface for solo provider
 */
public interface ISoloProvider {
	

	public Solo getSolo();
	public String getLastExecutorID();
	public Solo syncActivity();
	
}
