package desktop.views.fx.game_view_elements;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class GUIPawn extends Circle {

    private GUIBoard board;
    private int colorID;
    private int x;
    private int y;
    private boolean movable = false;

    public boolean isMovable() {
        return movable;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setXY(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void confirmMove(boolean valid) {
        for (int i = 0; i < board.getChildren().size(); i++) {
            if (board.getChildren().get(i) instanceof GUIField) {
                GUIField field = (GUIField) board.getChildren().get(i);
                if (valid) {
                    if (field.getCenterY() == getCenterY() && field.getCenterX() == getCenterX()) {
                        setXY(field.getX(), field.getY());
                        break;
                    }
                } else {
                    if (field.getX() == getX() && field.getY() == getY()) {
                        setCenterX(field.getCenterX());
                        setCenterY(field.getCenterY());
                        board.setLastPawnMovedByMe(null);
                        displayInvalidMove();
                        break;
                    }
                }
            }
        }
    }

    private void displayInvalidMove() {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Oops");
        alert.setHeaderText("Invalid move");
        alert.setContentText("Your move was invalid. Please try again");

        alert.showAndWait();
    }

    public void setColorID(int colorID) {
        this.colorID = colorID;
    }

    public int getColorID() {
        return colorID;
    }

    public void enableMove() {
        movable=true;
    }

    public void disableMove() {
        movable=false;
    }

    GUIPawn(GUIBoard board, Paint fill) {

        super(8, fill);
        this.board = board;
        setOnMouseEntered((MouseEvent mouseEvent) -> {
            if (isMovable()) {
                //on mouse enter effects:
                setRadius(getRadius() + 1);
                setEffect(new DropShadow());
            }
        });
        //removing changes for the object
        setOnMouseExited(mouseEvent1 -> {
            if (getColorID()==board.getPlayerIndex()) {
                setRadius(8);
                setEffect(null);
            }
        });

        EventHandler<MouseEvent> pawnMover = mouseEvent -> {
            double previousCenterX = getCenterX();
            double previousCenterY = getCenterY();
            double startingMouseX = mouseEvent.getSceneX();
            double startingMouseY = mouseEvent.getSceneY();
            EventHandler<MouseEvent> mover = mouseEvent1 -> {
                if (isMovable()) {
                    double deltaX = mouseEvent1.getSceneX() - startingMouseX;
                    double deltaY = mouseEvent1.getSceneY() - startingMouseY;
                    setCenterX(previousCenterX + deltaX);
                    setCenterY(previousCenterY + deltaY);
                }
            };
            setOnMouseDragged(mover);
            setOnMouseReleased(mouseDragEvent -> {
                if (isMovable()) {
                    boolean found = false;
                    for (int i = 0; i < board.getChildren().size(); i++) {
                        if (!(board.getChildren().get(i) instanceof GUIField)) continue;
                        GUIField field = (GUIField) board.getChildren().get(i);
                        double distance = Math.sqrt(
                                (field.getCenterX() - getCenterX()) * (field.getCenterX() - getCenterX()) +
                                        (field.getCenterY() - getCenterY()) * (field.getCenterY() - getCenterY()));
                        if (distance < field.getRadius()) {
                            found = true;
                            board.sendMoveToServer(getX(), getY(), field.getX(), field.getY());
                            board.setLastPawnMovedByMe(this);
                            setCenterY(field.getCenterY());
                            setCenterX(field.getCenterX());
                            break;
                        }
                    }
                    if (!found) {
                        setCenterX(previousCenterX);
                        setCenterY(previousCenterY);
                    }
                }
            });
        };

        setOnMousePressed(pawnMover);

        board.getChildren().add(this);
    }
}
