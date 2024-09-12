package com.andresramos.chessgamepracticejava.ui.views;

import com.andresramos.chessgamepracticejava.controllers.ChessGameManagerController;
import com.andresramos.chessgamepracticejava.models.pieces.Piece;
import com.andresramos.chessgamepracticejava.utils.enums.ColorPiece;
import com.andresramos.chessgamepracticejava.utils.enums.ColorRectangle;
import com.andresramos.chessgamepracticejava.utils.enums.PieceType;
import javafx.geometry.Pos;
import javafx.scene.Node;
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
    private Rectangle selectedRectangle;
    private Rectangle highlightedRectangle;
    private Piece selectedPiece;

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
                    StackPane pieceInRect = placePieceInRectangle(row, column, rectangle);
                    add(pieceInRect, column, row);
                }
            }
        }
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

    private StackPane placePieceInRectangle(int row, int column, Rectangle rectangle) {
        Piece piece = chessGameManagerController.getPieceRectangle(row, column);
        ColorPiece colorPiece = piece.getColor();
        PieceType pieceType = piece.getPieceType();
        StackPane stackPanePiece = getStackPaneForPieceCreated(colorPiece, pieceType);
        ColorPiece currentGameColor = chessGameManagerController.getCurrentGameColorPiece();
        if (currentGameColor == colorPiece) {
            stackPanePiece.setOnMouseClicked(_ -> ClickOnThePieceToMove(row, column, rectangle, pieceType, colorPiece, piece));
        }
        return stackPanePiece;
    }

    private void ClickOnThePieceToMove(int row, int column, Rectangle rectangle, PieceType pieceType, ColorPiece colorPiece, Piece piece) {
        if (selectedRectangle!= null) {
            selectedRectangle.setFill(getColorForRectangle(
                    GridPane.getRowIndex(selectedRectangle),
                    GridPane.getColumnIndex(selectedRectangle)
            ));
        }
        selectedRectangle = rectangle;
        rectangle.setFill(Color.GREEN);

        if (highlightedRectangle != null) {
            highlightedRectangle.setFill(getColorForRectangle(
                    GridPane.getRowIndex(highlightedRectangle),
                    GridPane.getColumnIndex(highlightedRectangle)
            ));
        }

        for (Node node : getChildren()) {
            if (node instanceof Rectangle nextRectangle) {
                int rectangleRow = GridPane.getRowIndex(nextRectangle);
                int rectangleColumn = GridPane.getColumnIndex(nextRectangle);

                // Logic PAWN select
                if (pieceType == PieceType.PAWN) {
                    int nextRectangleRow = (colorPiece == ColorPiece.WHITE) ? rectangleRow + 1 : rectangleRow - 1;
                    if (nextRectangleRow == row && rectangleColumn == column) {
                        nextRectangle.setFill(Color.YELLOW);
                        highlightedRectangle = nextRectangle;
                        selectedPiece = piece;
                        nextRectangle.setOnMouseClicked(_ -> MovePiece(rectangleRow, rectangleColumn, row, column));
                    }
                }
            }
        }
    }

    private void MovePiece(int nextRectangleRow, int nextRectangleColumn, int startRectangleRow, int startRectangleColumn) {
        chessGameManagerController.movePiece(selectedPiece, nextRectangleRow, nextRectangleColumn, startRectangleRow, startRectangleColumn);
        chessGameManagerController.changeCurrentGameColorPiece();
        updateBoard();
    }

    private void updateBoard() {
        getChildren().clear();
        createChessBoard();
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
}
