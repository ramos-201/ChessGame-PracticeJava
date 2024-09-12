package com.andresramos.chessgamepracticejava.models.pieces;

import com.andresramos.chessgamepracticejava.utils.enums.ColorPiece;
import com.andresramos.chessgamepracticejava.utils.enums.PieceType;

public class Piece {

    private final ColorPiece colorPiece;
    private final PieceType pieceType;

    public Piece(ColorPiece colorPiece, PieceType pieceType) {
        this.colorPiece = colorPiece;
        this.pieceType = pieceType;
    }

    public ColorPiece getColor() {
        return colorPiece;
    }

    public PieceType getPieceType() {
        return pieceType;
    }
}
