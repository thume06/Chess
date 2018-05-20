package game;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;

import java.net.URL;
import java.util.ResourceBundle;

public class ChessController implements Initializable {
    private Main mainClass;

    private ImageView[][] chessBoard = new ImageView[8][8]; //this array stores the images of each piece in the correct location
    private String[][] piecePositions = new String[8][8]; //this array stores the name of the piece in the correct location

    private final int rowMax = 8;
    private final int rowMin = 0;
    private final int columnMax = 8;
    private final int columnMin = 0;

    private boolean itemSelected = false;

    @FXML GridPane gamePane;

    public void initialize(URL url, ResourceBundle rb) {
        mainClass = Main.getInstance();
        InitializeBoard();
        System.out.println(piecePositions[0][0]);
    }

    //Creates the board and places all of the pieces. Called in initialize method.
    private void InitializeBoard(){
        //Loop sets all spaces to be empty (once pieces are added those spaces will be overwritten)
        int spaceRowCount = 0;
        while(spaceRowCount < rowMax){
            int spaceColumnCount = 0;
            while(spaceColumnCount < columnMax){
                piecePositions[spaceRowCount][spaceColumnCount] = "Empty";
                spaceColumnCount++;
            }
            spaceRowCount++;
        }


        //Loop creates the checker pattern on the board
        int rowCount = 0;
        while(rowCount < rowMax){
            int columnCount = 0;
            while(columnCount < columnMax){
                //if it is an even numbered row (remember starting at 0)
                if((rowCount == 0) || (rowCount%2 == 0)){
                    if(columnCount ==0 || columnCount%2 == 0){
                        AddChecker(rowCount, columnCount);

                    }
                }
                else{
                    if(!(columnCount == 0) && !(columnCount%2 == 0)){
                        AddChecker(rowCount, columnCount);
                    }
                }
                columnCount++;
            }
            rowCount++;
        }

        //Add white pieces
        AddPiece(0, 0, "wRook.png");
        AddPiece(0, 1, "wKnight.png");
        AddPiece(0, 2, "wBishop.png");
        AddPiece(0, 3, "wQueen.png");
        AddPiece(0, 4, "wKing.png");
        AddPiece(0, 5, "wBishop.png");
        AddPiece(0, 6, "wKnight.png");
        AddPiece(0, 7, "wRook.png");
        int count = 0;
        while(count < 8){
            AddPiece(1, count, "wPawn.png");
            count++;
        }

        //Add black pieces
        AddPiece(7, 0, "bRook.png");
        AddPiece(7, 1, "bKnight.png");
        AddPiece(7, 2, "bBishop.png");
        AddPiece(7, 3, "bQueen.png");
        AddPiece(7, 4, "bKing.png");
        AddPiece(7, 5, "bBishop.png");
        AddPiece(7, 6, "bKnight.png");
        AddPiece(7, 7, "bRook.png");

        count = 0;
        while(count < 8){
            AddPiece(6, count, "bPawn.png");
            count++;
        }

    }

    //Method used to make adding a piece simple. Just input row, column, and the image.
    private void AddPiece(int r, int c, String url){
        int rowCount = r;
        int columnCount = c;
        String piece = url.substring(0, url.length() - 4);

        piecePositions[rowCount][columnCount] = piece;

        chessBoard[rowCount][columnCount] = new ImageView(new Image(url));
        chessBoard[rowCount][columnCount].setLayoutX(100 * columnCount);
        chessBoard[rowCount][columnCount].setLayoutY(100 * rowCount);
        chessBoard[rowCount][columnCount].setFitWidth(100);
        chessBoard[rowCount][columnCount].setFitHeight(100);

        if(piece.equals("wPawn")) {
            chessBoard[rowCount][columnCount].addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    WhitePawnPressed(r, c);
                }
            });
        }

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

    private void AddChecker(int r, int c){
        int rowCount = 0;
        int columnCount = c;
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
        gamePane.add(new ImageView(new Image("blackSquare.png")), columnCount, rowCount);
    }

    public void WhitePawnPressed(int r, int c){
        if(itemSelected){ return; }
        itemSelected = true;
        chessBoard[r][c] = new ImageView(new Image("wPawnSelected.png"));
        RedrawBoard(r, c, "Selection");
    }

    public void RedrawBoard(int r, int c, String x){
        boolean selection;
        String selectionImg = "";

        if(x.equals("Selection")){
            selection = true;
            selectionImg = (piecePositions[r][c] + "Selected.png");
        }
        else{
            selection = false;
        }

        gamePane.getChildren().clear();

        //redraws the black and white squares
        int rowCount = 0;
        while(rowCount < rowMax){
            int columnCount = 0;
            while(columnCount < columnMax){
                //if it is an even numbered row (remember starting at 0)
                if((rowCount == 0) || (rowCount%2 == 0)){
                    if(columnCount ==0 || columnCount%2 == 0){
                        AddChecker(rowCount, columnCount);
                    }
                }
                else{
                    if(!(columnCount == 0) && !(columnCount%2 == 0)){
                        AddChecker(rowCount, columnCount);
                    }
                }
                columnCount++;
            }
            rowCount++;
        }

        //loops again to redraw the pieces
        rowCount = 0;
        while(rowCount < rowMax){
            int columnCount = 0;
            while(columnCount < columnMax){
                if(selection && rowCount == r && columnCount == c){
                    AddPiece(rowCount, columnCount, selectionImg);
                }
                else if(!(piecePositions[rowCount][columnCount].equals("Empty"))){
                    AddPiece(rowCount, columnCount, (piecePositions[rowCount][columnCount] + ".png"));
                }
                columnCount++;
            }
            rowCount++;
        }

    }
}