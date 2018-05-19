package game;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ChessController implements Initializable {
    private Main mainClass;

    private ImageView[][] chessBoard = new ImageView[8][8];

    private final int rowMax = 8;
    private final int rowMin = 0;
    private final int columnMax = 8;
    private final int columnMin = 0;

    @FXML GridPane gamePane;

    public void initialize(URL url, ResourceBundle rb) {
        mainClass = Main.getInstance();
        InitializeBoard();
    }

    //Creates the board and places all of the pieces. Called in initialize method.
    private void InitializeBoard(){
        int rowCount = 0;

        //Loop creates the checker pattern on the board
        while(rowCount < rowMax){
            int columnCount = 0;
            while(columnCount < columnMax){
                //if it is an even numbered row (remember starting at 0)
                if((rowCount == 0) || (rowCount%2 == 0)){
                    if(columnCount ==0 || columnCount%2 == 0){
                        AddImage(rowCount, columnCount, "blackSquare.png");
                    }
                }
                else{
                    if(!(columnCount == 0) && !(columnCount%2 == 0)){
                        AddImage(rowCount, columnCount, "blackSquare.png");
                    }
                }
                columnCount++;
            }
            rowCount++;
        }

        //Add white pieces
        AddImage(0, 0, "wRook.png");
        AddImage(0, 1, "wKnight.png");
        AddImage(0, 2, "wBishop.png");
        AddImage(0, 3, "wQueen.png");
        AddImage(0, 4, "wKing.png");
        AddImage(0, 5, "wBishop.png");
        AddImage(0, 6, "wKnight.png");
        AddImage(0, 7, "wRook.png");
        int count = 0;
        while(count < 8){
            AddImage(1, count, "wPawn.png");
            count++;
        }

        //Add black pieces
        AddImage(7, 0, "bRook.png");
        AddImage(7, 1, "bKnight.png");
        AddImage(7, 2, "bBishop.png");
        AddImage(7, 3, "bQueen.png");
        AddImage(7, 4, "bKing.png");
        AddImage(7, 5, "bBishop.png");
        AddImage(7, 6, "bKnight.png");
        AddImage(7, 7, "bRook.png");

        count = 0;
        while(count < 8){
            AddImage(6, count, "bPawn.png");
            count++;
        }

    }

    //Method used to make adding an image simple. Just input row, column, and the image.
    private void AddImage(int r, int c, String url){
        int rowCount = r;
        int columnCount = c;

        chessBoard[rowCount][columnCount] = new ImageView(new Image(url));
        chessBoard[rowCount][columnCount].setLayoutX(100 * columnCount);
        chessBoard[rowCount][columnCount].setLayoutY(100 * rowCount);
        chessBoard[rowCount][columnCount].setFitWidth(100);
        chessBoard[rowCount][columnCount].setFitHeight(100);

        if(r == 0){
            rowCount = 7;
        }
        else if(r == 1){
            rowCount = 6;
        }
        else if(r == 2){
            rowCount = 5;
        }
        else if(r == 3){
            rowCount = 4;
        }
        else if(r == 4){
            rowCount = 3;
        }
        else if(r == 5){
            rowCount = 2;
        }
        else if(r == 6){
            rowCount = 1;
        }
        else if(r == 7){
            rowCount = 0;
        }
        gamePane.add(chessBoard[r][c], columnCount, rowCount);

    }
}