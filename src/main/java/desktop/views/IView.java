package desktop.views;

import java.util.List;

/**
 * Interface gluing all views
 *
 * @author Aleksander Lasecki
 */
public interface IView {
	/**
	 * Callback from the client when a message is received
	 *
	 * @param msg	The message that was received
	 */
	void handleInput(String msg);

	/**
	 * Calling handleInput on all message in msgList
	 *
	 * @param msgList	List of messages to handle
	 */
	default void resolvePending(List<String> msgList) {
		for (String msg : msgList)
			handleInput(msg);
	}
}
