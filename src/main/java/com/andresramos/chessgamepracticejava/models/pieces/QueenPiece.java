package com.andresramos.chessgamepracticejava.models.pieces;

import com.andresramos.chessgamepracticejava.utils.enums.ColorPiece;
import com.andresramos.chessgamepracticejava.utils.enums.PieceType;

public class QueenPiece extends Piece {

    public QueenPiece(ColorPiece colorPiece) {
        super(colorPiece, PieceType.QUEEN);
    }
}
