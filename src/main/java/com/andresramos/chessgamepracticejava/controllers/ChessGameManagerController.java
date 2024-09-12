package com.andresramos.chessgamepracticejava.controllers;

import com.andresramos.chessgamepracticejava.models.ChessBoard;
import com.andresramos.chessgamepracticejava.utils.enums.ColorRectangle;

public class ChessGameManagerController {

    private final ChessBoard chessBoard;

    public ChessGameManagerController() {
        this.chessBoard = new ChessBoard();
    }

    public int sizeRowBoard() {
        return chessBoard.sizeRow();
    }

    public int sizeColumnBoard() {
        return chessBoard.sizeColumn();
    }

    public ColorRectangle getColorRectangle(int row, int column) {
        return chessBoard.getColor(row, column);
    }
}
