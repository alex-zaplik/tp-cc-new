package desktop.views.fx;

import desktop.DesktopLauncher;
import javafx.scene.control.Button;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

/**
 * Some handy help
 *
 * @author Aleksander Lasecki
 */
public class HelpView extends FXView {

    /**
     * The help text
     */
	private final String helpString =
			"This is some help and other stuff telling you how to play this game and so on...\n" +
			"And here is some text on a new line XD";

    /**
     * Standard constructor
     *
     * @param stage	The JavaFX stage of this view
     */
	public HelpView(Stage stage) {
		super(stage);
	}

    /**
     * Initialize the GUI here
     */
	protected void createWindow() {
		Text helpText = new Text(helpString);
		helpText.setFont(new Font("Tahoma", 16));
		helpText.setFill(Color.grayRgb(255));
		add(helpText, 0, 0);

		Button exit = new Button("Back");
		exit.setMinWidth(100);
		exit.setOnAction(event -> DesktopLauncher.changeRoot(stage, DesktopLauncher.mainView));
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
