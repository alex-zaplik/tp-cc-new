package desktop.views.fx.game_view_elements;

import desktop.net.Client;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class GUIBoard extends Pane {

    private int playerIndex;

    private GUIPawn lastPawnMovedByMe;

    private Color[] colors = {
            Color.rgb(50, 63, 255),
            Color.rgb(71, 255, 76),
            Color.rgb(255, 77, 59),
            Color.rgb(206, 52, 255),
            Color.rgb(255, 173, 60),
            Color.rgb(143, 74, 44)
    };

    public int getPlayerIndex() {
        return playerIndex;
    }

    public GUIPawn getLastPawnMovedByMe() {
        return lastPawnMovedByMe;
    }

    public void setLastPawnMovedByMe(GUIPawn lastPawnMovedByMe) {
        this.lastPawnMovedByMe = lastPawnMovedByMe;
    }

    public void startPlayerTurn(){
        for(Node node: getChildren()){
            if(!(node instanceof GUIPawn)) continue;
            if(((GUIPawn) node).getColorID()==getPlayerIndex())((GUIPawn) node).enableMove();
        }
    }

    public void startEnemyTurn(){
        for(Node node: getChildren()){
            if(!(node instanceof GUIPawn)) continue;
            ((GUIPawn) node).disableMove();
        }
    }

    public void movePawn(int fromX, int fromY, int toX, int toY){
        for (Node node: getChildren()){
            if(!(node instanceof GUIPawn)) continue;
            if(((GUIPawn) node).getX()==fromX&&((GUIPawn) node).getY()==fromY){
                for (Node node2: getChildren()){
                    if(!(node2 instanceof GUIField)) continue;
                    if(((GUIField) node2).getX()==toX&&((GUIField) node2).getY()==toY){
                        ((GUIPawn) node).setXY(toX, toY);
                        ((GUIPawn) node).setCenterX(((GUIField) node2).getCenterX());
                        ((GUIPawn) node).setCenterY(((GUIField) node2).getCenterY());
                        break;
                    }
                }
                break;
            }
        }
    }

    public void sendMoveToServer(int fromX, int fromY, int toX, int toY){
        Client.getInstance().sendMove(fromX,fromY,toX,toY);
        startEnemyTurn();
    }

    public GUIBoard(int players, int playerIndex){
        super();
        this.playerIndex = playerIndex;
        setPrefSize(260,340);
        for(int x=0; x<=16; x++)
            for(int y=0; y<=16; y++){
                if(x<=12&&y<=12&&x+y>=12) {
                    getChildren().add(new GUIField(x,y));
                }
                else if(x>=4&&y>=4&&x+y<=20) getChildren().add(new GUIField(x,y));
            }
        if(players==2||players==3||players==6){ //1st color
            for(int x=4; x<=7; x++)
                for(int y=13; y<=16; y++){
                    if(x+y<=20){
                        GUIPawn pawn = new GUIPawn(this, colors[0]);
                        pawn.setCenterX(x*20+10*y+10-6*20);
                        pawn.setCenterY(y*20+10);
                        pawn.setXY(x,y);
                        pawn.setColorID(0);
                    }
                }
        }
        if(players==4||players==6){ //2nd color
            for(int x=0; x<=3; x++)
                for (int y=9; y<=12; y++){
                    if(x+y>=12){
                        GUIPawn pawn = new GUIPawn(this, colors[1]);
                        pawn.setCenterX(x*20+10*y+10-6*20);
                        pawn.setCenterY(y*20+10);
                        pawn.setXY(x,y);
                        if(players==4) pawn.setColorID(0);
                        else pawn.setColorID(1);
                    }
                }
        }
        if(players!=2){ //3rd color
            for(int x=4; x<=7; x++)
                for (int y=4; y<=7; y++){
                    if(x+y<=11){
                        GUIPawn pawn = new GUIPawn(this, colors[2]);
                        pawn.setCenterX(x*20+10*y+10-6*20);
                        pawn.setCenterY(y*20+10);
                        pawn.setXY(x,y);
                        if(players==3||players==4) pawn.setColorID(1);
                        else pawn.setColorID(2);
                    }
                }
        }
        if(players==2||players==6){ //4th color
            for(int x=9; x<=12; x++)
                for(int y=0; y<=3; y++)
                    if(x+y>=12){
                        GUIPawn pawn = new GUIPawn(this, colors[3]);
                        pawn.setCenterX(x*20+10*y+10-6*20);
                        pawn.setCenterY(y*20+10);
                        pawn.setXY(x,y);
                        if(players==2) pawn.setColorID(1);
                        else pawn.setColorID(3);
                    }
        }
        if(players!=2){ //5th color
            for(int x=13; x<=16; x++)
                for(int y=4; y<=7; y++){
                    if(x+y<=20){
                        GUIPawn pawn = new GUIPawn(this, colors[4]);
                        pawn.setCenterX(x*20+10*y+10-6*20);
                        pawn.setCenterY(y*20+10);
                        pawn.setXY(x,y);
                        if(players==3||players==4) pawn.setColorID(2);
                        else pawn.setColorID(4);
                    }
                }
        }
        if(players==4||players==6){ //6th color
            for(int x=9; x<=12; x++)
                for(int y=9; y<=12; y++){
                    if(x+y>=21){
                        GUIPawn pawn = new GUIPawn(this, colors[5]);
                        pawn.setCenterX(x*20+10*y+10-6*20);
                        pawn.setCenterY(y*20+10);
                        pawn.setXY(x,y);
                        if(players==4) pawn.setColorID(3);
                        else pawn.setColorID(5);
                    }
                }
        }
    }

}
