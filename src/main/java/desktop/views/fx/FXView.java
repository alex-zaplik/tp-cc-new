package desktop.views.fx;

import desktop.views.IView;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * Abstract class representing a standard for a view in this app
 */
public abstract class FXView extends GridPane implements IView {

	/**
	 * The JavaFX stage of this view
	 */
	Stage stage;

	/**
	 * Standard constructor
	 *
	 * @param stage	The JavaFX stage of this view
	 */
	FXView(Stage stage) {
		this.stage = stage;

		setPadding(new Insets(25, 25, 25, 25));
		setVgap(10);
		setHgap(10);
		setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0), CornerRadii.EMPTY, Insets.EMPTY)));

		createWindow();
	}

	/**
	 * Displaying an error message as an {@link Alert}
	 *
	 * @param msg	The message to be displayed
	 */
	void displayErrorMessage(String msg) {
		Alert alert = new Alert(Alert.AlertType.ERROR);
		alert.setTitle("Error");
		alert.setHeaderText("Something went wrong");
		alert.setContentText(msg);

		alert.showAndWait();
	}

	/**
	 * Initialize the GUI here
	 */
	protected abstract void createWindow();
}
