package com.andresramos.chessgamepracticejava;

import com.andresramos.chessgamepracticejava.controllers.ChessGameController;
import com.andresramos.chessgamepracticejava.ui.views.ChessBoardView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    private static final int WINDOW_WIDTH_SIZE = 600;
    private static final int WINDOW_HEIGHT_SIZE = 600;
    private static final String MAIN_WINDOW_TITLE = "CHESS GAME";

    @Override
    public void start(Stage primaryStage) {
        ChessGameController chessGameController = new ChessGameController();
        ChessBoardView chessBoardView = new ChessBoardView(chessGameController);

        Scene scene = new Scene(chessBoardView, WINDOW_WIDTH_SIZE, WINDOW_HEIGHT_SIZE);
        primaryStage.setTitle(MAIN_WINDOW_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
