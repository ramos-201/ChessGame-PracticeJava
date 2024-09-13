package com.andresramos.chessgamepracticejava.models;

import com.andresramos.chessgamepracticejava.models.pieces.Piece;
import com.andresramos.chessgamepracticejava.utils.enums.ColorRectangle;

public class ChessBoard {

    private static final int SIZE_ROW = 8;
    private static final int SIZE_COLUMN = 8;
    private final Rectangle[][] rectangles;

    public ChessBoard() {
        this.rectangles = new Rectangle[SIZE_ROW][SIZE_COLUMN];
        createRectangles();
    }

    private void createRectangles() {
        for (int row = 0; row < SIZE_ROW; row++) {
            for (int column = 0; column < SIZE_COLUMN; column++) {
                ColorRectangle colorRectangle = ((row + column) % 2 == 0) ? ColorRectangle.BLACK : ColorRectangle.WHITE;
                rectangles[row][column] = new Rectangle(colorRectangle);
            }
        }
    }

    public ColorRectangle getColorRectangle(int row, int column) {
        return getRectangle(row, column).getColorRectangle();
    }

    public Piece getPieceRectangle(int row, int column) {
        return getRectangle(row, column).getPiece();
    }

    public void setPieceRectangle(Piece initializePiece, int row, int column) {
        getRectangle(row, column).setPiece(initializePiece);
    }

    public Boolean hasPieceRectangle(int row, int column) {
        return getRectangle(row, column).hasPiece();
    }

    public void removePiece(int startRectangleRow, int startRectangleColumn) {
        getRectangle(startRectangleRow, startRectangleColumn).setPiece(null);
    }

    private Rectangle getRectangle(int row, int column) {
        // TODO: no exist rectangle?
        return rectangles[row][column];
    }

    public int sizeRow() {
        return SIZE_ROW;
    }

    public int sizeColumn() {
        return SIZE_COLUMN;
    }
}
