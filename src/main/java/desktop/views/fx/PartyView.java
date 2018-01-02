package desktop.views.fx;

import desktop.DesktopLauncher;
import desktop.net.Client;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.Map;

/**
 * Just a start button
 *
 * @author Aleksander Lasecki
 */
public class PartyView extends FXView {

    /**
     * Standard constructor
     *
     * @param stage	The JavaFX stage of this view
     */
	public PartyView(Stage stage) {
		super(stage);
	}

    /**
     * Initialize the GUI here
     */
	@Override
	protected void createWindow() {
		Button start = new Button("Start");
		start.setMinWidth(200);
		start.setOnAction(e -> Client.getInstance().startGame());
		add(start, 0, 0);
	}

	/**
	 * Callback from the client when a message is received
	 *
	 * @param msg	The message that was received
	 */
	@Override
	public void handleInput(String msg) {
		Map<String, Object> response = Client.getInstance().parser.parse(msg);
		if (response.containsKey("s_game")) {
			// Handle different games here

			GameView gv = new GameView(stage, (int) response.get("i_pcount"), (int) response.get("i_pindex"));
			Client.getInstance().changeView(gv);
			DesktopLauncher.changeRoot(stage, gv);
		} else if (response.containsKey("s_disc")) {
			Client.getInstance().disconnect();
			DesktopLauncher.changeRoot(stage, DesktopLauncher.loginView);
		}
	}
}
