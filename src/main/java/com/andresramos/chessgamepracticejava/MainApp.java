package com.andresramos.chessgamepracticejava;

import com.andresramos.chessgamepracticejava.controllers.ChessGameManagerController;
import com.andresramos.chessgamepracticejava.ui.views.ChessBoardView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainApp extends Application {

    private static final int WINDOW_WIDTH = 600;
    private static final int WINDOW_HEIGHT = 600;
    private static final String WINDOW_TITLE = "Chess Game - Practice";

    @Override
    public void start(Stage primaryStage) {
        ChessGameManagerController chessGameController = new ChessGameManagerController();
        ChessBoardView chessBoardView = new ChessBoardView(chessGameController);

        Scene scene = new Scene(chessBoardView, WINDOW_WIDTH, WINDOW_HEIGHT);
        primaryStage.setTitle(WINDOW_TITLE);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
