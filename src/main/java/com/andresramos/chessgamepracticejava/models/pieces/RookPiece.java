package com.andresramos.chessgamepracticejava.models.pieces;

import com.andresramos.chessgamepracticejava.utils.enums.ColorPiece;
import com.andresramos.chessgamepracticejava.utils.enums.PieceType;

public class RookPiece extends Piece {

    public RookPiece(ColorPiece colorPiece) {
        super(colorPiece, PieceType.ROOK);
    }
}
