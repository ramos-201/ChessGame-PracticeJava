package com.andresramos.chessgamepracticejava.controllers;

import com.andresramos.chessgamepracticejava.models.ChessBoard;
import com.andresramos.chessgamepracticejava.models.pieces.*;
import com.andresramos.chessgamepracticejava.utils.enums.ColorPiece;
import com.andresramos.chessgamepracticejava.utils.enums.ColorRectangle;
import com.andresramos.chessgamepracticejava.utils.enums.PieceType;

public class ChessGameManagerController {

    private final ChessBoard chessBoard;

    public ChessGameManagerController() {
        this.chessBoard = new ChessBoard();
        placeChessPieces();
    }

    private void placeChessPieces() {
        PieceType[] pieces = {
                PieceType.ROOK,
                PieceType.KNIGHT,
                PieceType.BISHOP,
                PieceType.QUEEN,
                PieceType.KING,
                PieceType.BISHOP,
                PieceType.KNIGHT,
                PieceType.ROOK
        };
        for (int column = 0; column < pieces.length; column++) {
            PieceType pieceType = pieces[column];
            chessBoard.setPieceRectangle(getInitializePiece(PieceType.PAWN, ColorPiece.BLACK), 1, column);
            chessBoard.setPieceRectangle(getInitializePiece(pieceType, ColorPiece.BLACK), 0, column);
            chessBoard.setPieceRectangle(getInitializePiece(PieceType.PAWN, ColorPiece.WHITE), 6, column);
            chessBoard.setPieceRectangle(getInitializePiece(pieceType, ColorPiece.WHITE), 7, column);
        }
    }

    private static Piece getInitializePiece(PieceType pieceType, ColorPiece colorPiece) {
        return switch (pieceType) {
            case PAWN -> new PawnPiece(colorPiece);
            case KNIGHT -> new KnightPiece(colorPiece);
            case BISHOP -> new BishopPiece(colorPiece);
            case ROOK -> new RookPiece(colorPiece);
            case QUEEN -> new QueenPiece(colorPiece);
            case KING -> new KingPiece(colorPiece);
        };
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

    public Boolean hasPieceRectangle(int row, int column) {
        return chessBoard.hasPieceRectangle(row, column);
    }

    public Piece getPieceRectangle(int row, int column) {
        return chessBoard.getPieceRectangle(row, column);
    }
}
