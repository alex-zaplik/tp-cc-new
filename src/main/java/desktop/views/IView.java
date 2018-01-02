package desktop.views;

import java.util.List;

public interface IView {
	void handleInput(String msg);

	default void resolvePending(List<String> msgList) {
		for (String msg : msgList)
			handleInput(msg);
	}
}
