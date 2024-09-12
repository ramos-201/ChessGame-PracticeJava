package com.andresramos.chessgamepracticejava.models.pieces;

import com.andresramos.chessgamepracticejava.utils.enums.ColorPiece;
import com.andresramos.chessgamepracticejava.utils.enums.PieceType;

public class KnightPiece extends Piece {

    public KnightPiece(ColorPiece colorPiece) {
        super(colorPiece, PieceType.KNIGHT);
    }
}
