package com.andresramos.chessgamepracticejava.ui.views;

import com.andresramos.chessgamepracticejava.controllers.ChessGameManagerController;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ChessBoardView extends GridPane {

    private final ChessGameManagerController chessGameManagerController;

    public ChessBoardView(ChessGameManagerController chessGameController) {
        this.chessGameManagerController = chessGameController;
        createChessBoard();
    }

    private void createChessBoard() {
        int sizeRowBoard = chessGameManagerController.sizeRowBoard();
        int sizeColumnBoard = chessGameManagerController.sizeColumnBoard();
        for (int row = 0; row < sizeRowBoard; row++) {
            for (int column = 0; column < sizeColumnBoard; column++) {
                Rectangle rectangle = new Rectangle(75, 75);
                Color color = ((row + column) % 2 == 0) ? Color.BLACK : Color.WHITE;
                rectangle.setFill(color);
                add(rectangle, column, row);
            }
        }
    }
}
