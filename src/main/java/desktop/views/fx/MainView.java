package desktop.views.fx;

import desktop.DesktopLauncher;
import javafx.application.Platform;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * The main menu
 *
 * @author Aleksander Lasecki
 */
public class MainView extends FXView {

    /**
     * Standard constructor
     *
     * @param stage	The JavaFX stage of this view
     */
	public MainView(Stage stage) {
		super(stage);
	}

    /**
     * Initialize the GUI here
     */
	protected void createWindow() {
		Image logo = new Image("/logo.png");
        ImageView logoView = new ImageView(logo);
		add(logoView, 0, 0, 1, 4);

		Button start = new Button("Start");
		start.setMinWidth(100);
		start.setOnAction(event -> DesktopLauncher.changeRoot(stage, DesktopLauncher.loginView));
		add(start, 1, 0);

		Button help = new Button("Help");
		help.setMinWidth(100);
		help.setOnAction(event -> DesktopLauncher.changeRoot(stage, DesktopLauncher.helpView));
		add(help, 1, 1);

		Button exit = new Button("Exit");
		exit.setMinWidth(100);
		exit.setOnAction(event -> Platform.exit());
		add(exit, 1, 2);
	}

	/**
	 * Callback from the client when a message is received
	 *
	 * @param msg	The message that was received
	 */
	@Override
	public void handleInput(String msg) {

	}
}
