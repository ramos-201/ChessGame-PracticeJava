package com.andresramos.chessgamepracticejava.ui.views;

import com.andresramos.chessgamepracticejava.controllers.ChessGameManagerController;
import com.andresramos.chessgamepracticejava.utils.enums.ColorRectangle;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class ChessBoardView extends GridPane {

    private static final int SIZE_RECTANGLE = 75;
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
                Rectangle rectangle = getRectangleForChessBoard(row, column);
                add(rectangle, column, row);
            }
        }
    }

    private Rectangle getRectangleForChessBoard(int row, int column) {
        Rectangle rectangle = new Rectangle(SIZE_RECTANGLE, SIZE_RECTANGLE);
        Color colorForRectangle = getColorForRectangle(row, column);
        rectangle.setFill(colorForRectangle);
        return rectangle;
    }

    private Color getColorForRectangle(int row, int column) {
        ColorRectangle colorRectangle = chessGameManagerController.getColorRectangle(row, column);
        return switch (colorRectangle) {
            case BLACK -> Color.BEIGE;
            case WHITE -> Color.SADDLEBROWN;
        };
    }
}
