package com.andresramos.chessgamepracticejava.models.pieces;

import com.andresramos.chessgamepracticejava.utils.enums.ColorPiece;
import com.andresramos.chessgamepracticejava.utils.enums.PieceType;

public class KingPiece extends Piece {

    public KingPiece(ColorPiece colorPiece) {
        super(colorPiece, PieceType.KING);
    }
}
