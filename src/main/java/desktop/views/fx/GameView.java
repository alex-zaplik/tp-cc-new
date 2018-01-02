package desktop.views.fx;

import desktop.DesktopLauncher;
import desktop.net.Client;
import desktop.views.IView;
import desktop.views.fx.game_view_elements.GUIBoard;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Map;

public class GameView extends GridPane implements IView {

    private Stage stage;

    private int playerCount;
    private int playerIndex;

    private boolean isMoving = false;
    private GUIBoard board;

    public GameView(Stage stage, int playerCount, int playerIndex) {
        this.stage = stage;

        setPadding(new Insets(25, 25, 25, 25));
        setVgap(10);
        setHgap(10);
        setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0), CornerRadii.EMPTY, Insets.EMPTY)));

        this.playerCount = playerCount;
        this.playerIndex = playerIndex;

        createWindow();
    }

    private void createWindow() {
        board = new GUIBoard(playerCount, playerIndex);
        if (isMoving) board.startPlayerTurn();

        add(board,0,0);

        Button skip = new Button("Skip move");
        skip.setOnAction(event -> {
            Client.getInstance().skipMove();
            board.setLastPawnMovedByMe(null);
            board.startEnemyTurn();
        });
        add(skip, 1, 0);

        Client.getInstance().sendDone(true);
    }

    private void displayGameOverMessage(String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Game over");
        alert.setHeaderText("Game over");
        alert.setContentText(msg);

        alert.showAndWait();
    }

    private void displayYourMoveMessage() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Your move");
        alert.setHeaderText("Your move");
        alert.setContentText("Make a move or skip");

        alert.showAndWait();
    }

    @Override
    public void handleInput(String msg) {
        Map<String, Object> response = Client.getInstance().parser.parse(msg);
        if (response.containsKey("s_move")) {
            displayYourMoveMessage();

            if (board != null) {
                if(board.getLastPawnMovedByMe()==null) board.startPlayerTurn();
                else board.getLastPawnMovedByMe().enableMove();
            } else {
                isMoving = true;
            }
        } else if (response.containsKey("b_valid")) {
                board.getLastPawnMovedByMe().confirmMove((boolean) response.get("b_valid"));
                if((boolean) response.get("b_valid")){
                    board.startEnemyTurn();
                    if(!((boolean) response.get("b_jump"))) board.setLastPawnMovedByMe(null);
                }
        } else if (response.containsKey("i_action")) {
            board.movePawn((int) response.get("i_fx"), (int) response.get("i_fy"), (int) response.get("i_tx"), (int) response.get("i_ty"));
        } else if (response.containsKey("s_disc")) {
            Client.getInstance().disconnect();
            DesktopLauncher.changeRoot(stage, DesktopLauncher.loginView);
        } else if (response.containsKey("b_won")) {
            displayGameOverMessage(((boolean) response.get("b_won")) ? "You won!" : "You lost :<");
        }
    }
}
