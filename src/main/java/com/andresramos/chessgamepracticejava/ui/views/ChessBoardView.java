package com.andresramos.chessgamepracticejava.ui.views;

import com.andresramos.chessgamepracticejava.controllers.ChessGameController;
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

    private final ChessGameController chessGameController;
    private Rectangle selectedRectangle;
    private Rectangle highlightedRectangle;

    public ChessBoardView(ChessGameController chessGameController) {
        this.chessGameController = chessGameController;
        initializeChessBoard();
    }

    private void initializeChessBoard() {
        final int SIZE_ROW_BOARD = chessGameController.sizeRowBoard();
        final int SIZE_COLUMN_BOARD = chessGameController.sizeColumnBoard();

        for (int row = 0; row < SIZE_ROW_BOARD; row++) {
            for (int column = 0; column < SIZE_COLUMN_BOARD; column++) {
                StackPane stackPane = createTile(row, column);
                add(stackPane, column, row);
            }
        }
    }

    private StackPane createTile(int row, int column) {
        StackPane stackPane = new StackPane();
        Rectangle rectangle = createRectangleForChessBoard(row, column);
        stackPane.getChildren().add(rectangle);

        Boolean hasPiece = chessGameController.hasPieceRectangle(row, column);
        if (Boolean.TRUE.equals(hasPiece)) {
            StackPane pieceInRect = placePieceInRectangle(row, column, rectangle);
            stackPane.getChildren().add(pieceInRect);
        }
        return stackPane;
    }

    private Rectangle createRectangleForChessBoard(int row, int column) {
        final int SIZE_RECTANGLE = 75;
        Rectangle rectangle = new Rectangle(SIZE_RECTANGLE, SIZE_RECTANGLE);
        Color colorForRectangle = getColorForRectangle(row, column);
        rectangle.setFill(colorForRectangle);
        GridPane.setRowIndex(rectangle, row);
        GridPane.setColumnIndex(rectangle, column);
        return rectangle;
    }

    private Color getColorForRectangle(int row, int column) {
        ColorRectangle colorRectangle = chessGameController.getColorRectangle(row, column);
        return switch (colorRectangle) {
            case BLACK -> Color.BEIGE;
            case WHITE -> Color.SADDLEBROWN;
        };
    }

    private StackPane placePieceInRectangle(int row, int column, Rectangle rectangle) {
        Piece piece = chessGameController.getPieceRectangle(row, column);
        ColorPiece colorPiece = piece.getColor();
        PieceType pieceType = piece.getPieceType();
        StackPane stackPanePiece = getStackPaneForPieceCreated(colorPiece, pieceType);
        GridPane.setRowIndex(stackPanePiece, row);
        GridPane.setColumnIndex(stackPanePiece, column);
        ColorPiece currentGameColor = chessGameController.getCurrentGameColorPiece();
        if (currentGameColor == colorPiece) {
            stackPanePiece.setOnMouseClicked(_ -> clickOnThePieceToMove(row, column, rectangle, pieceType, colorPiece, piece));
        }
        return stackPanePiece;
    }

    private StackPane getStackPaneForPieceCreated(ColorPiece colorPiece, PieceType pieceType) {
        final int PIECE_RADIUS = 30;
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

    private void clickOnThePieceToMove(int row, int column, Rectangle rectangle, PieceType pieceType, ColorPiece colorPiece, Piece piece) {
        changeColorForSelectRectangle(rectangle);
        resetHighlightedRectangle(highlightedRectangle);

        if (pieceType == PieceType.PAWN) {
            actionForwardMovement(row, column, colorPiece, piece);
            actionRightDiagonalMovement(colorPiece, row, column + 1, column, piece);
            actionLeftDiagonalMovement(colorPiece, row, column - 1, column, piece);
        }
    }

    private void actionLeftDiagonalMovement(ColorPiece colorPiece, int row, int column, int column1, Piece piece) {
        int nextNodeRow = (colorPiece == ColorPiece.WHITE) ? row - 1 : row + 1;
        int leftDiagonalColumn = column;
        Node nodeAtLeftDiagonalStackPane = getChildren().stream()
                .filter(node -> GridPane.getRowIndex(node) == nextNodeRow && GridPane.getColumnIndex(node) == leftDiagonalColumn)
                .findFirst()
                .orElse(null);
        if (nodeAtLeftDiagonalStackPane instanceof StackPane stackPanePiece) {
            Rectangle nextRectangle = (Rectangle) stackPanePiece.getChildren().getFirst();
            Boolean hasPieceNextRectangle = chessGameController.hasPieceRectangle(nextNodeRow, leftDiagonalColumn);
            if (Boolean.TRUE.equals(hasPieceNextRectangle)) {
                ColorPiece nextPieceColor = chessGameController.getPieceRectangle(nextNodeRow, leftDiagonalColumn).getColor();
                if (nextPieceColor != colorPiece) {
                    nextRectangle.setFill(Color.YELLOW);
                    nextRectangle.setStroke(Color.BLACK);
                    highlightedRectangle = nextRectangle;
                    stackPanePiece.setOnMouseClicked(_ -> movePiece(nextNodeRow, leftDiagonalColumn, row, column1, piece));
                }
            }
        }
    }

    private void actionRightDiagonalMovement(ColorPiece colorPiece, int row, int column, int column1, Piece piece) {
        int nextNodeRow = (colorPiece == ColorPiece.WHITE) ? row - 1 : row + 1;
        int rightDiagonalColumn = column;
        Node nodeAtRightDiagonalStackPane = getChildren().stream()
                .filter(node -> GridPane.getRowIndex(node) == nextNodeRow && GridPane.getColumnIndex(node) == rightDiagonalColumn)
                .findFirst()
                .orElse(null);

        if (nodeAtRightDiagonalStackPane instanceof StackPane stackPanePiece) {
            Rectangle nextRectangle = (Rectangle) stackPanePiece.getChildren().getFirst();
            Boolean hasPieceNextRectangle = chessGameController.hasPieceRectangle(nextNodeRow, rightDiagonalColumn);
            if (Boolean.TRUE.equals(hasPieceNextRectangle)) {
                ColorPiece nextPieceColor = chessGameController.getPieceRectangle(nextNodeRow, rightDiagonalColumn).getColor();
                if (nextPieceColor != colorPiece) {
                    nextRectangle.setFill(Color.YELLOW);
                    nextRectangle.setStroke(Color.BLACK);
                    highlightedRectangle = nextRectangle;
                    stackPanePiece.setOnMouseClicked(_ -> movePiece(nextNodeRow, rightDiagonalColumn, row, column1, piece));
                }
            }
        }
    }

    private void actionForwardMovement(int row, int column, ColorPiece colorPiece, Piece piece) {
        int nextNodeRow = (colorPiece == ColorPiece.WHITE) ? row - 1 : row + 1;
        Node nodeAtPositionStackPane = getChildren().stream()
                .filter(node -> GridPane.getRowIndex(node) == nextNodeRow && GridPane.getColumnIndex(node) == column)
                .findFirst()
                .orElse(null);
        if (nodeAtPositionStackPane instanceof StackPane stackPane1) {
            Boolean hasPieceNextRectangle = chessGameController.hasPieceRectangle(nextNodeRow, column);
            if (Boolean.FALSE.equals(hasPieceNextRectangle)) {
                Rectangle nextRectangle = (Rectangle) stackPane1.getChildren().getFirst();
                nextRectangle.setFill(Color.YELLOW);
                highlightedRectangle = nextRectangle;
                stackPane1.setOnMouseClicked(_ -> movePiece(nextNodeRow, column, row, column, piece));
            }
        }
    }

    private void resetHighlightedRectangle(Rectangle highlightedRectangle) {
        if (highlightedRectangle != null) {
            Integer rowIndex = GridPane.getRowIndex(highlightedRectangle);
            Integer columnIndex = GridPane.getColumnIndex(highlightedRectangle);
            int row = (rowIndex != null) ? rowIndex : 0;
            int column = (columnIndex != null) ? columnIndex : 0;
            highlightedRectangle.setFill(getColorForRectangle(row, column));
        }
    }

    private void changeColorForSelectRectangle(Rectangle rectangle) {
        resetHighlightedRectangle(selectedRectangle);
        selectedRectangle = rectangle;
        rectangle.setFill(Color.GREEN);
    }

    private void movePiece(int nextRectangleRow, int nextRectangleColumn, int startRectangleRow, int startRectangleColumn, Piece piece) {
        chessGameController.movePiece(piece, nextRectangleRow, nextRectangleColumn, startRectangleRow, startRectangleColumn);
        chessGameController.changeCurrentGameColorPiece();
        updateBoard();
    }

    private void updateBoard() {
        getChildren().clear();
        initializeChessBoard();
    }
}
