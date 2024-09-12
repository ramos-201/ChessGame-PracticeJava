package com.andresramos.chessgamepracticejava.models;

import com.andresramos.chessgamepracticejava.models.pieces.Piece;
import com.andresramos.chessgamepracticejava.utils.enums.ColorRectangle;

public class Rectangle {
    
    private final ColorRectangle colorRectangle;
    private Piece piece;

    public Rectangle(ColorRectangle colorRectangle) {
        this.colorRectangle = colorRectangle;
    }

    public ColorRectangle getColorRectangle() {
        return colorRectangle;
    }

    public void setPiece(Piece pieceValue) {
        this.piece = pieceValue;
    }

    public Boolean hasPiece() {
        return piece != null;
    }

    public Piece getPiece() {
        return piece;
    }
}
