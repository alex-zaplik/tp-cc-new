package desktop;

import desktop.views.fx.HelpView;
import desktop.views.fx.LoginView;
import desktop.views.fx.MainView;
import desktop.views.fx.PartyView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 * The main class of the client
 *
 * @author Aleksander Lasecki
 */
public class DesktopLauncher extends Application {

	/**
	 * MainView object used throughout the entire run time
	 */
	public static MainView mainView;
	/**
	 * HelpView object used throughout the entire run time
	 */
	public static HelpView helpView;
	/**
	 * LoginView object used throughout the entire run time
	 */
	public static LoginView loginView;
	// public static PartyListView partyListView;
	/**
	 * PartyView object used throughout the entire run time
	 */
	public static PartyView partyView;

	/**
	 * Last stage used by changeRoot
	 */
	private static Stage lastStage = null;

	/**
	 * Changing the root of the main stage
	 *
	 * @param stage	The stage to change the root of
	 * @param root	The new root
	 */
	public static void changeRoot(Stage stage, Pane root) {
		if (stage != null)
			lastStage = stage;

		if (lastStage == null)
			return;

		lastStage.hide();
		lastStage.getScene().setRoot(root);
		lastStage.show();
	}

	/**
	 * Initialization of the app
	 *
	 * @param primaryStage	JavaFX main stage
	 */
	@Override
	public void start(Stage primaryStage) {
		mainView = new MainView(primaryStage);
		helpView = new HelpView(primaryStage);
		loginView = new LoginView(primaryStage);
		// partyListView = new PartyListView(primaryStage);
		partyView = new PartyView(primaryStage);

		primaryStage.setTitle("mojeChineseCheckers");
		primaryStage.setScene(new Scene(DesktopLauncher.mainView));
		primaryStage.show();
	}

	/**
	 * The main function
	 *
	 * @param args	Ignored
	 */
	public static void main (String[] args) {
		launch(args);
	}
}
