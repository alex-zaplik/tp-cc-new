package desktop;

import desktop.views.fx.HelpView;
import desktop.views.fx.LoginView;
import desktop.views.fx.MainView;
import desktop.views.fx.PartyView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class DesktopLauncher extends Application {

	public static MainView mainView;
	public static HelpView helpView;
	public static LoginView loginView;
	// public static PartyListView partyListView;
	public static PartyView partyView;

	private static Stage lastStage = null;

	public static void changeRoot(Stage stage, Pane root) {
		if (stage != null)
			lastStage = stage;

		if (lastStage == null)
			return;

		lastStage.hide();
		lastStage.getScene().setRoot(root);
		lastStage.show();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		mainView = new MainView(primaryStage);
		helpView = new HelpView(primaryStage);
		loginView = new LoginView(primaryStage);
		// partyListView = new PartyListView(primaryStage);
		partyView = new PartyView(primaryStage);

		primaryStage.setTitle("mojeChineseCheckers");
		primaryStage.setScene(new Scene(DesktopLauncher.mainView));
		primaryStage.show();
	}

	public static void main (String[] args) {
		launch(args);
	}
}
