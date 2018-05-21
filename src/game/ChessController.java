package game;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
    private int selectionRow = 99;
    private int selectionColumn = 99;
    private boolean whiteturn = true;

    @FXML GridPane gamePane;
    @FXML Label victoryMessage;
    @FXML Label playAgain;

    public void initialize(URL url, ResourceBundle rb) {
        mainClass = Main.getInstance();
        InitializeBoard();
        victoryMessage.setDisable(true);
        victoryMessage.setVisible(false);
        playAgain.setDisable(true);
        playAgain.setVisible(false);
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
        chessBoard[rowCount][columnCount].setPickOnBounds(true);

        if(piece.equals("wPawn")) {
            chessBoard[rowCount][columnCount].addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    WhitePawnPressed(r, c);
                }
            });
        }
        else if(piece.equals("bPawn")){
            chessBoard[rowCount][columnCount].addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    BlackPawnPressed(r, c);
                }
            });
        }
        else if(piece.equals("wRook")){
            chessBoard[rowCount][columnCount].addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    WhiteRookPressed(r, c);
                }
            });
        }
        else if(piece.equals("bRook")){
            chessBoard[rowCount][columnCount].addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    BlackRookPressed(r, c);
                }
            });
        }
        else if(piece.equals("wBishop")){
            chessBoard[rowCount][columnCount].addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    WhiteBishopPressed(r, c);
                }
            });
        }
        else if(piece.equals("bBishop")){
            chessBoard[rowCount][columnCount].addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    BlackBishopPressed(r, c);
                }
            });
        }
        else if(piece.equals("wQueen")){
            chessBoard[rowCount][columnCount].addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    WhiteQueenPressed(r, c);
                }
            });
        }
        else if(piece.equals("bQueen")){
            chessBoard[rowCount][columnCount].addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    BlackQueenPressed(r, c);
                }
            });
        }
        else if(piece.equals("wKnight")){
            chessBoard[rowCount][columnCount].addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    WhiteKnightPressed(r, c);
                }
            });
        }
        else if(piece.equals("bKnight")){
            chessBoard[rowCount][columnCount].addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    BlackKnightPressed(r, c);
                }
            });
        }
        else if(piece.equals("wKing")){
            chessBoard[rowCount][columnCount].addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    WhiteKingPressed(r, c);
                }
            });
        }
        else if(piece.equals("bKing")){
            chessBoard[rowCount][columnCount].addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                   BlackKingPressed(r, c);
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

    private void HighlightSelection(int r, int c){
        selectionRow = r;
        selectionColumn = c;
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
        ImageView selectionImg = new ImageView(new Image("selection.png"));
        selectionImg.setFitHeight(100);
        selectionImg.setFitWidth(100);
        selectionImg.setPickOnBounds(true);
        selectionImg.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            //When the selection is clicked again, it is deselected.
            @Override
            public void handle(MouseEvent mouseEvent) {
                RedrawBoard();
                itemSelected = false;
                selectionRow = 99;
                selectionColumn = 99;
            }
        });
        gamePane.add(selectionImg, columnCount, rowCount);
    }

    private void HighlightMove(int r, int c){
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
        ImageView selectionImg = new ImageView(new Image("selection.png"));
        selectionImg.setFitHeight(100);
        selectionImg.setFitWidth(100);
        selectionImg.setDisable(false);
        selectionImg.setPickOnBounds(true);
        selectionImg.addEventFilter(MouseEvent.MOUSE_PRESSED, new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Move(r, c);
            }
        });
        gamePane.add(selectionImg, columnCount, rowCount);
    }

    //Method to actually do a move. Called in HighlightMove method.
    private void Move(int r, int c){
        String piece = piecePositions[selectionRow][selectionColumn];
        piecePositions[selectionRow][selectionColumn] = "Empty";
        piecePositions[r][c] = piece;

        //check to see if a pawn has made it to the end and change it to a queen
        int count = 0;
        while(count < 8){
            if(piecePositions[7][count].equals("wPawn")){
                piecePositions[7][count] = "wQueen";
            }
            if(piecePositions[0][count].equals("bPawn")){
                piecePositions[0][count] = "bQueen";
            }
            count++;
        }

        RedrawBoard();
        itemSelected = false;
        selectionRow = 99;
        selectionColumn = 99;
        if(whiteturn){
            whiteturn = false;
        }
        else{
            whiteturn = true;
        }

        //check to see if a king has been killed and then declare victory
        int rowCount = 0;
        boolean whiteKingPresent = false;
        boolean blackKingPresent = false;
        while(rowCount < 8){
            int columnCount = 0;
            while(columnCount < 8){
                if(piecePositions[rowCount][columnCount].equals("wKing")){
                    whiteKingPresent = true;
                }
                else if(piecePositions[rowCount][columnCount].equals("bKing")){
                    blackKingPresent = true;
                }
                columnCount++;
            }
            rowCount++;
        }

        //If black won
        if(!whiteKingPresent){
            victoryMessage.setText("Black wins!");
            gamePane.setDisable(true);
            gamePane.setOpacity(.4);
            victoryMessage.setOpacity(1);
            playAgain.setOpacity(1);
            victoryMessage.setVisible(true);
            playAgain.setVisible(true);
            playAgain.setDisable(false);
        }

        //If white won
        else if(!blackKingPresent){
            victoryMessage.setText("White wins!");
            gamePane.setDisable(true);
            gamePane.setOpacity(.4);
            victoryMessage.setOpacity(1);
            playAgain.setOpacity(1);
            victoryMessage.setVisible(true);
            playAgain.setVisible(true);
            playAgain.setDisable(false);
        }
    }

    //Redraws the board after piece positions are changed or a piece is selected
    private void RedrawBoard(){

        //clears the pane of all images
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

        //loops again to redraw the updated pieces
        rowCount = 0;
        while(rowCount < rowMax){
            int columnCount = 0;
            while(columnCount < columnMax){
                if(!(piecePositions[rowCount][columnCount].equals("Empty"))){
                    AddPiece(rowCount, columnCount, (piecePositions[rowCount][columnCount] + ".png"));
                }
                columnCount++;
            }
            rowCount++;
        }

    }

    @FXML public void PlayAgainHovered(){
        playAgain.setUnderline(true);
    }

    @FXML public void PlayAgainExited(){
        playAgain.setUnderline(false);
    }

    @FXML public void PlayAgainClicked(){
        gamePane.setDisable(false);
        gamePane.setOpacity(1);
        playAgain.setDisable(true);
        playAgain.setUnderline(false);
        playAgain.setVisible(false);
        victoryMessage.setVisible(false);

        InitializeBoard();
        RedrawBoard();
        whiteturn = true;
    }

    //---------------------------------------Logic for chess pieces----------------------------------------------//
    //TODO Maybe figure out en passant later on.
    private void WhitePawnPressed(int r, int c){
        //if you already have a selected item
        if (itemSelected){
            return;
        }
        //if you are just now selecting the white pawn
        else if(whiteturn){
            itemSelected = true;
            selectionRow = r;
            selectionColumn = c;
            HighlightSelection(r, c);
            //if it is the first move of the pawn
            if(r == 1){
                if(piecePositions[r + 1][c].equals("Empty")){
                    HighlightMove(r + 1, c);
                    if(piecePositions[r + 2][c].equals("Empty")){
                        HighlightMove(r + 2, c);
                    }
                }
                //first move and if there are enemies
                if((c + 1 < 8) && (c - 1 >= 0)){
                    if(piecePositions[r + 1][c - 1].substring(0, 1).equals("b")){
                        HighlightMove(r + 1, c - 1);
                    }
                    if(piecePositions[r + 1][c + 1].substring(0, 1).equals("b")){
                        HighlightMove(r + 1, c + 1);
                    }
                }
                if((c + 1 == 8)){
                    if(piecePositions[r + 1][c - 1].substring(0, 1).equals("b")){
                        HighlightMove(r + 1, c - 1);
                    }
                }
                if(c - 1 == -1){
                    if(piecePositions[r + 1][c + 1].substring(0, 1).equals("b")){
                        HighlightMove(r + 1, c + 1);
                    }
                }
            }
            //if it is not the first move of the pawn
            else{
                if((r + 1 < 8) && piecePositions[r + 1][c].equals("Empty")){
                    HighlightMove(r + 1, c);
                }
                //if there is an enemy
                if((c + 1 < 8) && (c - 1 >= 0) && (r + 1 < 8)){
                    if(piecePositions[r + 1][c - 1].substring(0, 1).equals("b")){
                        HighlightMove(r + 1, c - 1);
                    }
                    if(piecePositions[r + 1][c + 1].substring(0, 1).equals("b")){
                        HighlightMove(r + 1, c + 1);
                    }
                }
                if((c - 1 == -1) && r + 1 < 8){
                    if(piecePositions[r + 1][c + 1].substring(0, 1).equals("b")){
                        HighlightMove(r + 1, c + 1);
                    }
                }
                if((c + 1 == 8) && r + 1 < 8){
                    if(piecePositions[r + 1][c - 1].substring(0, 1).equals("b")){
                        HighlightMove(r + 1, c - 1);
                    }
                }
            }
        }
        //returns if it is not white's turn
        return;
    }

    private void BlackPawnPressed(int r, int c){
        //if you already have a selected item
        if (itemSelected){
            return;
        }
        //if you are just now selecting the black pawn
        else if(!whiteturn){
            itemSelected = true;
            selectionRow = r;
            selectionColumn = c;
            HighlightSelection(r, c);
            //if it is the first move of the pawn
            if(r == 6){
                if(piecePositions[r - 1][c].equals("Empty")){
                    HighlightMove(r - 1, c);
                    if(piecePositions[r - 2][c].equals("Empty")){
                        HighlightMove(r - 2, c);
                    }
                }
                //first move and if there are enemies
                if((c + 1 < 8) && (c - 1 >= 0)){
                    if(piecePositions[r - 1][c - 1].substring(0, 1).equals("w")){
                        HighlightMove(r - 1, c - 1);
                    }
                    if(piecePositions[r - 1][c + 1].substring(0, 1).equals("w")){
                        HighlightMove(r - 1, c + 1);
                    }
                }
                if((c + 1 == 8)){
                    if(piecePositions[r - 1][c - 1].substring(0, 1).equals("w")){
                        HighlightMove(r - 1, c - 1);
                    }
                }
                if(c - 1 == -1){
                    if(piecePositions[r - 1][c + 1].substring(0, 1).equals("w")){
                        HighlightMove(r - 1, c + 1);
                    }
                }
            }
            //if it is not the first move of the pawn
            else{
                if((r - 1 >= 0) && piecePositions[r - 1][c].equals("Empty")){
                    HighlightMove(r - 1, c);
                }
                //if there is an enemy
                if((c + 1 < 8) && (c - 1 >= 0) && (r - 1 >= 0)){
                    if(piecePositions[r - 1][c - 1].substring(0, 1).equals("w")){
                        HighlightMove(r - 1, c - 1);
                    }
                    if(piecePositions[r - 1][c + 1].substring(0, 1).equals("w")){
                        HighlightMove(r - 1, c + 1);
                    }
                }
                if((c - 1 == -1) && r -1 >= 0){
                    if(piecePositions[r - 1][c + 1].substring(0, 1).equals("w")){
                        HighlightMove(r - 1, c + 1);
                    }
                }
                if((c + 1 == 8) && r - 1 >= 0){
                    if(piecePositions[r - 1][c - 1].substring(0, 1).equals("w")){
                        HighlightMove(r - 1, c - 1);
                    }
                }
            }
        }
        //returns if it is not black's turn
        return;
    }

    private void WhiteRookPressed(int r, int c){
        //if you already have an item selected return
        if (itemSelected){
            return;
        }
        //if it is black's turn, return
        if(!whiteturn){
            return;
        }
        //if you are just now selecting the white rook
        itemSelected = true;
        selectionRow = r;
        selectionColumn = c;
        HighlightSelection(r, c);
        //checks moves up
        int row = r + 1;
        while((row < 8)){
            if(piecePositions[row][c].equals("Empty")){
                HighlightMove(row, c);
            }
            else if(piecePositions[row][c].substring(0, 1).equals("b")){
                HighlightMove(row, c);
                break;
            }
            else if(piecePositions[row][c].substring(0, 1).equals("w")){
                break;
            }
            row++;
        }
        //checks moves down
        row = r - 1;
        while(row >= 0){
            if(piecePositions[row][c].equals("Empty")){
                HighlightMove(row, c);
            }
            else if(piecePositions[row][c].substring(0, 1).equals("b")){
                HighlightMove(row, c);
                break;
            }
            else if(piecePositions[row][c].substring(0, 1).equals("w")){
                break;
            }
            row--;
        }
        //checks moves right
        int column = c + 1;
        while(column < 8){
            if(piecePositions[r][column].equals("Empty")){
                HighlightMove(r, column);
            }
            else if(piecePositions[r][column].substring(0, 1).equals("b")){
                HighlightMove(r, column);
                break;
            }
            else if(piecePositions[r][column].substring(0, 1).equals("w")){
                break;
            }
            column++;
        }
        //checks moves left
        column = c - 1;
        while(column >= 0){
            if(piecePositions[r][column].equals("Empty")){
                HighlightMove(r, column);
            }
            else if(piecePositions[r][column].substring(0, 1).equals("b")){
                HighlightMove(r, column);
                break;
            }
            else if(piecePositions[r][column].substring(0, 1).equals("w")){
                break;
            }
            column--;
        }
    }

    private void BlackRookPressed(int r, int c){
        //if you already have an item selected return
        if (itemSelected){
            return;
        }
        //if it is white's turn, return
        if(whiteturn){
            return;
        }
        //if you are just now selecting the black rook
        itemSelected = true;
        selectionRow = r;
        selectionColumn = c;
        HighlightSelection(r, c);
        //checks moves up
        int row = r + 1;
        while((row < 8)){
            if(piecePositions[row][c].equals("Empty")){
                HighlightMove(row, c);
            }
            else if(piecePositions[row][c].substring(0, 1).equals("w")){
                HighlightMove(row, c);
                break;
            }
            else if(piecePositions[row][c].substring(0, 1).equals("b")){
                break;
            }
            row++;
        }
        //checks moves down
        row = r - 1;
        while(row >= 0){
            if(piecePositions[row][c].equals("Empty")){
                HighlightMove(row, c);
            }
            else if(piecePositions[row][c].substring(0, 1).equals("w")){
                HighlightMove(row, c);
                break;
            }
            else if(piecePositions[row][c].substring(0, 1).equals("b")){
                break;
            }
            row--;
        }
        //checks moves right
        int column = c + 1;
        while(column < 8){
            if(piecePositions[r][column].equals("Empty")){
                HighlightMove(r, column);
            }
            else if(piecePositions[r][column].substring(0, 1).equals("w")){
                HighlightMove(r, column);
                break;
            }
            else if(piecePositions[r][column].substring(0, 1).equals("b")){
                break;
            }
            column++;
        }
        //checks moves left
        column = c - 1;
        while(column >= 0){
            if(piecePositions[r][column].equals("Empty")){
                HighlightMove(r, column);
            }
            else if(piecePositions[r][column].substring(0, 1).equals("w")){
                HighlightMove(r, column);
                break;
            }
            else if(piecePositions[r][column].substring(0, 1).equals("b")){
                break;
            }
            column--;
        }
    }

    private void WhiteBishopPressed(int r, int c){
        //if you already have an item selected return
        if (itemSelected){
            return;
        }
        //if it is black's turn, return
        if(!whiteturn){
            return;
        }
        //if you are just now selecting the white bishop
        itemSelected = true;
        selectionRow = r;
        selectionColumn = c;
        HighlightSelection(r, c);

        //check up and left
        int row = r + 1;
        int column = c - 1;
        while((row < 8 && column >= 0)){
            if(piecePositions[row][column].equals("Empty")){
                HighlightMove(row, column);
            }
            else if(piecePositions[row][column].substring(0, 1).equals("b")){
                HighlightMove(row, column);
                break;
            }
            else if(piecePositions[row][column].substring(0, 1).equals("w")){
                break;
            }
            row++;
            column--;
        }

        //checks up and right
        row = r + 1;
        column = c + 1;
        while((row < 8 && column < 8)){
            if(piecePositions[row][column].equals("Empty")){
                HighlightMove(row, column);
            }
            else if(piecePositions[row][column].substring(0, 1).equals("b")){
                HighlightMove(row, column);
                break;
            }
            else if(piecePositions[row][column].substring(0, 1).equals("w")){
                break;
            }
            row++;
            column++;
        }

        //checks down and left
        row = r - 1;
        column = c - 1;
        while((row >= 0 && column >= 0)){
            if(piecePositions[row][column].equals("Empty")){
                HighlightMove(row, column);
            }
            else if(piecePositions[row][column].substring(0, 1).equals("b")){
                HighlightMove(row, column);
                break;
            }
            else if(piecePositions[row][column].substring(0, 1).equals("w")){
                break;
            }
            row--;
            column--;
        }

        //checks down and right
        row = r - 1;
        column = c + 1;
        while((row >= 0 && column < 8)){
            if(piecePositions[row][column].equals("Empty")){
                HighlightMove(row, column);
            }
            else if(piecePositions[row][column].substring(0, 1).equals("b")){
                HighlightMove(row, column);
                break;
            }
            else if(piecePositions[row][column].substring(0, 1).equals("w")){
                break;
            }
            row--;
            column++;
        }
    }

    private void BlackBishopPressed(int r, int c){
        //if you already have an item selected return
        if (itemSelected){
            return;
        }
        //if it is white's turn, return
        if(whiteturn){
            return;
        }
        //if you are just now selecting the black bishop
        itemSelected = true;
        selectionRow = r;
        selectionColumn = c;
        HighlightSelection(r, c);

        //check up and left
        int row = r + 1;
        int column = c - 1;
        while((row < 8 && column >= 0)){
            if(piecePositions[row][column].equals("Empty")){
                HighlightMove(row, column);
            }
            else if(piecePositions[row][column].substring(0, 1).equals("w")){
                HighlightMove(row, column);
                break;
            }
            else if(piecePositions[row][column].substring(0, 1).equals("b")){
                break;
            }
            row++;
            column--;
        }

        //checks up and right
        row = r + 1;
        column = c + 1;
        while((row < 8 && column < 8)){
            if(piecePositions[row][column].equals("Empty")){
                HighlightMove(row, column);
            }
            else if(piecePositions[row][column].substring(0, 1).equals("w")){
                HighlightMove(row, column);
                break;
            }
            else if(piecePositions[row][column].substring(0, 1).equals("b")){
                break;
            }
            row++;
            column++;
        }

        //checks down and left
        row = r - 1;
        column = c - 1;
        while((row >= 0 && column >= 0)){
            if(piecePositions[row][column].equals("Empty")){
                HighlightMove(row, column);
            }
            else if(piecePositions[row][column].substring(0, 1).equals("w")){
                HighlightMove(row, column);
                break;
            }
            else if(piecePositions[row][column].substring(0, 1).equals("b")){
                break;
            }
            row--;
            column--;
        }

        //checks down and right
        row = r - 1;
        column = c + 1;
        while((row >= 0 && column < 8)){
            if(piecePositions[row][column].equals("Empty")){
                HighlightMove(row, column);
            }
            else if(piecePositions[row][column].substring(0, 1).equals("w")){
                HighlightMove(row, column);
                break;
            }
            else if(piecePositions[row][column].substring(0, 1).equals("b")){
                break;
            }
            row--;
            column++;
        }
    }

    private void WhiteQueenPressed(int r, int c){
        //if you already have an item selected return
        if (itemSelected){
            return;
        }
        //if it is black's turn, return
        if(!whiteturn){
            return;
        }
        //if you are just now selecting the white queen
        itemSelected = true;
        selectionRow = r;
        selectionColumn = c;
        HighlightSelection(r, c);

        //check up and left
        int row = r + 1;
        int column = c - 1;
        while((row < 8 && column >= 0)){
            if(piecePositions[row][column].equals("Empty")){
                HighlightMove(row, column);
            }
            else if(piecePositions[row][column].substring(0, 1).equals("b")){
                HighlightMove(row, column);
                break;
            }
            else if(piecePositions[row][column].substring(0, 1).equals("w")){
                break;
            }
            row++;
            column--;
        }

        //checks up and right
        row = r + 1;
        column = c + 1;
        while((row < 8 && column < 8)){
            if(piecePositions[row][column].equals("Empty")){
                HighlightMove(row, column);
            }
            else if(piecePositions[row][column].substring(0, 1).equals("b")){
                HighlightMove(row, column);
                break;
            }
            else if(piecePositions[row][column].substring(0, 1).equals("w")){
                break;
            }
            row++;
            column++;
        }

        //checks down and left
        row = r - 1;
        column = c - 1;
        while((row >= 0 && column >= 0)){
            if(piecePositions[row][column].equals("Empty")){
                HighlightMove(row, column);
            }
            else if(piecePositions[row][column].substring(0, 1).equals("b")){
                HighlightMove(row, column);
                break;
            }
            else if(piecePositions[row][column].substring(0, 1).equals("w")){
                break;
            }
            row--;
            column--;
        }

        //checks down and right
        row = r - 1;
        column = c + 1;
        while((row >= 0 && column < 8)){
            if(piecePositions[row][column].equals("Empty")){
                HighlightMove(row, column);
            }
            else if(piecePositions[row][column].substring(0, 1).equals("b")){
                HighlightMove(row, column);
                break;
            }
            else if(piecePositions[row][column].substring(0, 1).equals("w")){
                break;
            }
            row--;
            column++;
        }

        //checks moves up
         row = r + 1;
        while((row < 8)){
            if(piecePositions[row][c].equals("Empty")){
                HighlightMove(row, c);
            }
            else if(piecePositions[row][c].substring(0, 1).equals("b")){
                HighlightMove(row, c);
                break;
            }
            else if(piecePositions[row][c].substring(0, 1).equals("w")){
                break;
            }
            row++;
        }
        //checks moves down
        row = r - 1;
        while(row >= 0){
            if(piecePositions[row][c].equals("Empty")){
                HighlightMove(row, c);
            }
            else if(piecePositions[row][c].substring(0, 1).equals("b")){
                HighlightMove(row, c);
                break;
            }
            else if(piecePositions[row][c].substring(0, 1).equals("w")){
                break;
            }
            row--;
        }
        //checks moves right
        column = c + 1;
        while(column < 8){
            if(piecePositions[r][column].equals("Empty")){
                HighlightMove(r, column);
            }
            else if(piecePositions[r][column].substring(0, 1).equals("b")){
                HighlightMove(r, column);
                break;
            }
            else if(piecePositions[r][column].substring(0, 1).equals("w")){
                break;
            }
            column++;
        }
        //checks moves left
        column = c - 1;
        while(column >= 0){
            if(piecePositions[r][column].equals("Empty")){
                HighlightMove(r, column);
            }
            else if(piecePositions[r][column].substring(0, 1).equals("b")){
                HighlightMove(r, column);
                break;
            }
            else if(piecePositions[r][column].substring(0, 1).equals("w")){
                break;
            }
            column--;
        }
    }

    private void BlackQueenPressed(int r, int c){
        //if you already have an item selected return
        if (itemSelected){
            return;
        }
        //if it is white's turn, return
        if(whiteturn){
            return;
        }
        //if you are just now selecting the black queen
        itemSelected = true;
        selectionRow = r;
        selectionColumn = c;
        HighlightSelection(r, c);

        //check up and left
        int row = r + 1;
        int column = c - 1;
        while((row < 8 && column >= 0)){
            if(piecePositions[row][column].equals("Empty")){
                HighlightMove(row, column);
            }
            else if(piecePositions[row][column].substring(0, 1).equals("w")){
                HighlightMove(row, column);
                break;
            }
            else if(piecePositions[row][column].substring(0, 1).equals("b")){
                break;
            }
            row++;
            column--;
        }

        //checks up and right
        row = r + 1;
        column = c + 1;
        while((row < 8 && column < 8)){
            if(piecePositions[row][column].equals("Empty")){
                HighlightMove(row, column);
            }
            else if(piecePositions[row][column].substring(0, 1).equals("w")){
                HighlightMove(row, column);
                break;
            }
            else if(piecePositions[row][column].substring(0, 1).equals("b")){
                break;
            }
            row++;
            column++;
        }

        //checks down and left
        row = r - 1;
        column = c - 1;
        while((row >= 0 && column >= 0)){
            if(piecePositions[row][column].equals("Empty")){
                HighlightMove(row, column);
            }
            else if(piecePositions[row][column].substring(0, 1).equals("w")){
                HighlightMove(row, column);
                break;
            }
            else if(piecePositions[row][column].substring(0, 1).equals("b")){
                break;
            }
            row--;
            column--;
        }

        //checks down and right
        row = r - 1;
        column = c + 1;
        while((row >= 0 && column < 8)){
            if(piecePositions[row][column].equals("Empty")){
                HighlightMove(row, column);
            }
            else if(piecePositions[row][column].substring(0, 1).equals("w")){
                HighlightMove(row, column);
                break;
            }
            else if(piecePositions[row][column].substring(0, 1).equals("b")){
                break;
            }
            row--;
            column++;
        }

        //checks moves up
        row = r + 1;
        while((row < 8)){
            if(piecePositions[row][c].equals("Empty")){
                HighlightMove(row, c);
            }
            else if(piecePositions[row][c].substring(0, 1).equals("w")){
                HighlightMove(row, c);
                break;
            }
            else if(piecePositions[row][c].substring(0, 1).equals("b")){
                break;
            }
            row++;
        }
        //checks moves down
        row = r - 1;
        while(row >= 0){
            if(piecePositions[row][c].equals("Empty")){
                HighlightMove(row, c);
            }
            else if(piecePositions[row][c].substring(0, 1).equals("w")){
                HighlightMove(row, c);
                break;
            }
            else if(piecePositions[row][c].substring(0, 1).equals("b")){
                break;
            }
            row--;
        }
        //checks moves right
        column = c + 1;
        while(column < 8){
            if(piecePositions[r][column].equals("Empty")){
                HighlightMove(r, column);
            }
            else if(piecePositions[r][column].substring(0, 1).equals("w")){
                HighlightMove(r, column);
                break;
            }
            else if(piecePositions[r][column].substring(0, 1).equals("b")){
                break;
            }
            column++;
        }
        //checks moves left
        column = c - 1;
        while(column >= 0){
            if(piecePositions[r][column].equals("Empty")){
                HighlightMove(r, column);
            }
            else if(piecePositions[r][column].substring(0, 1).equals("w")){
                HighlightMove(r, column);
                break;
            }
            else if(piecePositions[r][column].substring(0, 1).equals("b")){
                break;
            }
            column--;
        }
    }

    private void WhiteKnightPressed(int r, int c){
        //if you already have an item selected return
        if (itemSelected){
            return;
        }
        //if it is black's turn, return
        if(!whiteturn){
            return;
        }
        //if you are just now selecting the white knight
        itemSelected = true;
        selectionRow = r;
        selectionColumn = c;
        HighlightSelection(r, c);

        //checks up and far left move
        if(r + 1 < 8 && c - 2 >=0){
            if(piecePositions[r + 1][c - 2].equals("Empty") || piecePositions[r + 1][c - 2].substring(0, 1).equals("b")){
                HighlightMove(r + 1, c - 2);
            }
        }

        //checks far up and left move
        if(r + 2 < 8 && c - 1 >=0){
            if(piecePositions[r + 2][c - 1].equals("Empty") || piecePositions[r + 2][c - 1].substring(0, 1).equals("b")){
                HighlightMove(r + 2, c - 1);
            }
        }

        //checks down and far left move
        if(r - 1 >= 0 && c - 2 >=0){
            if(piecePositions[r - 1][c - 2].equals("Empty") || piecePositions[r - 1][c - 2].substring(0, 1).equals("b")){
                HighlightMove(r - 1, c - 2);
            }
        }

        //checks far down and left move
        if(r - 2 >= 0 && c - 1 >=0){
            if(piecePositions[r - 2][c - 1].equals("Empty") || piecePositions[r - 2][c - 1].substring(0, 1).equals("b")){
                HighlightMove(r - 2, c - 1);
            }
        }

        //checks up and far right move
        if(r + 1 < 8 && c + 2 < 8){
            if(piecePositions[r + 1][c + 2].equals("Empty") || piecePositions[r + 1][c + 2].substring(0, 1).equals("b")){
                HighlightMove(r + 1, c + 2);
            }
        }

        //checks far up and right move
        if(r + 2 < 8 && c + 1 < 8){
            if(piecePositions[r + 2][c + 1].equals("Empty") || piecePositions[r + 2][c + 1].substring(0, 1).equals("b")){
                HighlightMove(r + 2, c + 1);
            }
        }

        //checks down and far right move
        if(r - 1 >= 0 && c + 2 < 8){
            if(piecePositions[r - 1][c + 2].equals("Empty") || piecePositions[r - 1][c + 2].substring(0, 1).equals("b")){
                HighlightMove(r - 1, c + 2);
            }
        }

        //checks far down and right move
        if(r - 2 >= 0 && c + 1 < 8){
            if(piecePositions[r - 2][c + 1].equals("Empty") || piecePositions[r - 2][c + 1].substring(0, 1).equals("b")){
                HighlightMove(r - 2, c + 1);
            }
        }
    }

    private void BlackKnightPressed(int r, int c){
        //if you already have an item selected return
        if (itemSelected){
            return;
        }
        //if it is white's turn, return
        if(whiteturn){
            return;
        }
        //if you are just now selecting the black knight
        itemSelected = true;
        selectionRow = r;
        selectionColumn = c;
        HighlightSelection(r, c);

        //checks up and far left move
        if(r + 1 < 8 && c - 2 >=0){
            if(piecePositions[r + 1][c - 2].equals("Empty") || piecePositions[r + 1][c - 2].substring(0, 1).equals("w")){
                HighlightMove(r + 1, c - 2);
            }
        }

        //checks far up and left move
        if(r + 2 < 8 && c - 1 >=0){
            if(piecePositions[r + 2][c - 1].equals("Empty") || piecePositions[r + 2][c - 1].substring(0, 1).equals("w")){
                HighlightMove(r + 2, c - 1);
            }
        }

        //checks down and far left move
        if(r - 1 >= 0 && c - 2 >=0){
            if(piecePositions[r - 1][c - 2].equals("Empty") || piecePositions[r - 1][c - 2].substring(0, 1).equals("w")){
                HighlightMove(r - 1, c - 2);
            }
        }

        //checks far down and left move
        if(r - 2 >= 0 && c - 1 >=0){
            if(piecePositions[r - 2][c - 1].equals("Empty") || piecePositions[r - 2][c - 1].substring(0, 1).equals("w")){
                HighlightMove(r - 2, c - 1);
            }
        }

        //checks up and far right move
        if(r + 1 < 8 && c + 2 < 8){
            if(piecePositions[r + 1][c + 2].equals("Empty") || piecePositions[r + 1][c + 2].substring(0, 1).equals("w")){
                HighlightMove(r + 1, c + 2);
            }
        }

        //checks far up and right move
        if(r + 2 < 8 && c + 1 < 8){
            if(piecePositions[r + 2][c + 1].equals("Empty") || piecePositions[r + 2][c + 1].substring(0, 1).equals("w")){
                HighlightMove(r + 2, c + 1);
            }
        }

        //checks down and far right move
        if(r - 1 >= 0 && c + 2 < 8){
            if(piecePositions[r - 1][c + 2].equals("Empty") || piecePositions[r - 1][c + 2].substring(0, 1).equals("w")){
                HighlightMove(r - 1, c + 2);
            }
        }

        //checks far down and right move
        if(r - 2 >= 0 && c + 1 < 8){
            if(piecePositions[r - 2][c + 1].equals("Empty") || piecePositions[r - 2][c + 1].substring(0, 1).equals("w")){
                HighlightMove(r - 2, c + 1);
            }
        }
    }

    private void WhiteKingPressed(int r, int c){
        //if you already have an item selected return
        if (itemSelected){
            return;
        }
        //if it is black's turn, return
        if(!whiteturn){
            return;
        }
        //if you are just now selecting the white king
        itemSelected = true;
        selectionRow = r;
        selectionColumn = c;
        HighlightSelection(r, c);

        //check straight up move
        if(r + 1 < 8){
            if(piecePositions[r + 1][c].equals("Empty") || piecePositions[r + 1][c].substring(0, 1).equals("b")){
                HighlightMove(r + 1, c);
            }
        }

        //check up and left move
        if(r + 1 < 8 && c - 1 >= 0){
            if(piecePositions[r + 1][c - 1].equals("Empty") || piecePositions[r + 1][c - 1].substring(0, 1).equals("b")){
                HighlightMove(r + 1, c - 1);
            }
        }

        //check up and right move
        if(r + 1 < 8 && c + 1 < 8){
            if(piecePositions[r + 1][c + 1].equals("Empty") || piecePositions[r + 1][c + 1].substring(0, 1).equals("b")){
                HighlightMove(r + 1, c + 1);
            }
        }

        //check left move
        if(c -1 >= 0){
            if(piecePositions[r][c - 1].equals("Empty") || piecePositions[r][c - 1].substring(0, 1).equals("b")){
                HighlightMove(r, c - 1);
            }
        }

        //check right move
        if(c + 1 < 8){
            if(piecePositions[r][c + 1].equals("Empty") || piecePositions[r][c + 1].substring(0, 1).equals("b")){
                HighlightMove(r, c + 1);
            }
        }

        //check down left move
        if(r - 1 >= 0 && c - 1 >= 0){
            if(piecePositions[r - 1][c - 1].equals("Empty") || piecePositions[r - 1][c - 1].substring(0, 1).equals("b")){
                HighlightMove(r - 1, c - 1);
            }
        }

        //check straight down move
        if(r - 1 >= 0){
            if(piecePositions[r - 1][c].equals("Empty") || piecePositions[r - 1][c].substring(0, 1).equals("b")){
                HighlightMove(r - 1, c);
            }
        }

        //check down and right move
        if(r - 1 >= 0 && c + 1 < 8){
            if(piecePositions[r - 1][c + 1].equals("Empty") || piecePositions[r - 1][c + 1].substring(0, 1).equals("b")){
                HighlightMove(r - 1, c + 1);
            }
        }
    }

    private void BlackKingPressed(int r, int c){
        //if you already have an item selected return
        if (itemSelected){
            return;
        }
        //if it is white's turn, return
        if(whiteturn){
            return;
        }
        //if you are just now selecting the black king
        itemSelected = true;
        selectionRow = r;
        selectionColumn = c;
        HighlightSelection(r, c);

        //check straight up move
        if(r + 1 < 8){
            if(piecePositions[r + 1][c].equals("Empty") || piecePositions[r + 1][c].substring(0, 1).equals("w")){
                HighlightMove(r + 1, c);
            }
        }

        //check up and left move
        if(r + 1 < 8 && c - 1 >= 0){
            if(piecePositions[r + 1][c - 1].equals("Empty") || piecePositions[r + 1][c - 1].substring(0, 1).equals("w")){
                HighlightMove(r + 1, c - 1);
            }
        }

        //check up and right move
        if(r + 1 < 8 && c + 1 < 8){
            if(piecePositions[r + 1][c + 1].equals("Empty") || piecePositions[r + 1][c + 1].substring(0, 1).equals("w")){
                HighlightMove(r + 1, c + 1);
            }
        }

        //check left move
        if(c -1 >= 0){
            if(piecePositions[r][c - 1].equals("Empty") || piecePositions[r][c - 1].substring(0, 1).equals("w")){
                HighlightMove(r, c - 1);
            }
        }

        //check right move
        if(c + 1 < 8){
            if(piecePositions[r][c + 1].equals("Empty") || piecePositions[r][c + 1].substring(0, 1).equals("w")){
                HighlightMove(r, c + 1);
            }
        }

        //check down left move
        if(r - 1 >= 0 && c - 1 >= 0){
            if(piecePositions[r - 1][c - 1].equals("Empty") || piecePositions[r - 1][c - 1].substring(0, 1).equals("w")){
                HighlightMove(r - 1, c - 1);
            }
        }

        //check straight down move
        if(r - 1 >= 0){
            if(piecePositions[r - 1][c].equals("Empty") || piecePositions[r - 1][c].substring(0, 1).equals("w")){
                HighlightMove(r - 1, c);
            }
        }

        //check down and right move
        if(r - 1 >= 0 && c + 1 < 8){
            if(piecePositions[r - 1][c + 1].equals("Empty") || piecePositions[r - 1][c + 1].substring(0, 1).equals("w")){
                HighlightMove(r - 1, c + 1);
            }
        }
    }
}