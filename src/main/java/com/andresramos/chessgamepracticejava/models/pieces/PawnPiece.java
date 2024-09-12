package com.andresramos.chessgamepracticejava.models.pieces;

import com.andresramos.chessgamepracticejava.utils.enums.ColorPiece;
import com.andresramos.chessgamepracticejava.utils.enums.PieceType;

public class PawnPiece extends Piece {

    public PawnPiece(ColorPiece colorPiece) {
        super(colorPiece, PieceType.PAWN);
    }
}
