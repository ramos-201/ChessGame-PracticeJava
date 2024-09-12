package com.andresramos.chessgamepracticejava.ui.views;

import com.andresramos.chessgamepracticejava.controllers.ChessGameManagerController;
import com.andresramos.chessgamepracticejava.models.pieces.Piece;
import com.andresramos.chessgamepracticejava.utils.enums.ColorPiece;
import com.andresramos.chessgamepracticejava.utils.enums.ColorRectangle;
import com.andresramos.chessgamepracticejava.utils.enums.PieceType;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

public class ChessBoardView extends GridPane {

    private static final int SIZE_RECTANGLE = 75;
    private static final int PIECE_RADIUS = 30;
    private final ChessGameManagerController chessGameManagerController;

    public ChessBoardView(ChessGameManagerController chessGameController) {
        this.chessGameManagerController = chessGameController;
        createChessBoard();
    }

    private void createChessBoard() {
        int sizeRowBoard = chessGameManagerController.sizeRowBoard();
        int sizeColumnBoard = chessGameManagerController.sizeColumnBoard();

        for (int row = 0; row < sizeRowBoard; row++) {
            for (int column = 0; column < sizeColumnBoard; column++) {
                Rectangle rectangle = getRectangleForChessBoard(row, column);
                add(rectangle, column, row);

                Boolean hasPiece = chessGameManagerController.hasPieceRectangle(row, column);
                if (Boolean.TRUE.equals(hasPiece)) {
                    StackPane pieceInRect = placePieceInRectangle(row, column);
                    add(pieceInRect, column, row);
                }
            }
        }
    }

    private StackPane placePieceInRectangle(int row, int column) {
        Piece piece = chessGameManagerController.getPieceRectangle(row, column);
        ColorPiece colorPiece = piece.getColor();
        PieceType pieceType = piece.getPieceType();
        return getStackPaneForPieceCreated(colorPiece, pieceType);
    }

    private StackPane getStackPaneForPieceCreated(ColorPiece colorPiece, PieceType pieceType) {
        Circle drawingPiece = new Circle(PIECE_RADIUS);
        drawingPiece.setFill(colorPiece == ColorPiece.WHITE ? Color.WHITE : Color.BLACK);
        Text textPieceType = new Text(getPieceTypeLabel(pieceType));
        textPieceType.setFill(colorPiece == ColorPiece.WHITE ? Color.BLACK : Color.WHITE);
        StackPane stack = new StackPane(drawingPiece, textPieceType);
        stack.setAlignment(Pos.CENTER);
        return stack;
    }

    private String getPieceTypeLabel(PieceType pieceType) {
        return switch (pieceType) {
            case ROOK -> "ROOK";
            case KNIGHT -> "KNIGHT";
            case QUEEN -> "QUEEN";
            case BISHOP -> "BISHOP";
            case KING -> "KING";
            case PAWN -> "PAWN";
        };
    }

    private Rectangle getRectangleForChessBoard(int row, int column) {
        Rectangle rectangle = new Rectangle(SIZE_RECTANGLE, SIZE_RECTANGLE);
        Color colorForRectangle = getColorForRectangle(row, column);
        rectangle.setFill(colorForRectangle);
        return rectangle;
    }

    private Color getColorForRectangle(int row, int column) {
        ColorRectangle colorRectangle = chessGameManagerController.getColorRectangle(row, column);
        return switch (colorRectangle) {
            case BLACK -> Color.BEIGE;
            case WHITE -> Color.SADDLEBROWN;
        };
    }
}
