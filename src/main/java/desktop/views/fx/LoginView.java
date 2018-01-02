package desktop.views.fx;

import desktop.DesktopLauncher;
import desktop.net.Client;
import javafx.beans.property.StringProperty;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class LoginView extends FXView {

	private List<String> pendingMsg = new ArrayList<>();

	public LoginView(Stage stage) {
		super(stage);
	}

	@Override
	protected void createWindow() {
		HBox bAddress = new HBox();
		bAddress.setAlignment(Pos.CENTER_RIGHT);
		Label lAddress = new Label("Address:");
		lAddress.setTextFill(Color.grayRgb(255));
		bAddress.getChildren().add(lAddress);
		add(bAddress, 0, 0);

		TextField tAddress = new TextField();
		tAddress.appendText("localhost");
		add(tAddress, 1, 0);

		HBox bPort = new HBox();
		bPort.setAlignment(Pos.CENTER_RIGHT);
		Label lPort = new Label("Port:");
		lPort.setTextFill(Color.grayRgb(255));
		bPort.getChildren().add(lPort);
		add(bPort, 0, 1);

		TextField tPort = new TextField();
		tPort.appendText("4444");
		tPort.textProperty().addListener((observable, oldValue, newValue) -> {
			if (Pattern.compile("[0-9]*").matcher(newValue).matches() && newValue.length() <= 5) ((StringProperty) observable).setValue(newValue);
			else ((StringProperty) observable).setValue(oldValue);
		});
		add(tPort, 1, 1);

		Button login = new Button("Connect");
		login.setMinWidth(100);
		login.setOnAction(event -> {
			try {
				int port = Integer.parseInt(tPort.getText());
				if (port < 1 || port > 65535) throw new NumberFormatException();

				if (Client.getInstance().initConnection(this, tAddress.getText(), port)) {
					Client.getInstance().startListening();

					PartyListView partyListView = new PartyListView(stage);
					partyListView.resolvePending(pendingMsg);
					Client.getInstance().changeView(partyListView);

					DesktopLauncher.changeRoot(stage, partyListView);

					return;
				}
			} catch (NumberFormatException e) {
				displayErrorMessage("Port should be a number between 1 and 65535");
				return;
			}

			displayErrorMessage("Unable to connect to the server");
		});
		add(login, 1, 2);

		Button exit = new Button("Back");
		exit.setMinWidth(100);
		exit.setOnAction(event -> DesktopLauncher.changeRoot(stage, DesktopLauncher.mainView));
		add(exit, 1, 4);
	}

	@Override
	public void handleInput(String msg) {
		pendingMsg.add(msg);
	}
}
