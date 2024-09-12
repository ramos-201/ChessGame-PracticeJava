package com.andresramos.chessgamepracticejava.models.pieces;

import com.andresramos.chessgamepracticejava.utils.enums.ColorPiece;
import com.andresramos.chessgamepracticejava.utils.enums.PieceType;

public class BishopPiece extends Piece {

    public BishopPiece(ColorPiece colorPiece) {
        super(colorPiece, PieceType.BISHOP);
    }
}
