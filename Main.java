package com.company;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application
{
    private Controller controller;

    @Override
    // Mandatory to override Start method
    // Init & Stop are optional to override

    public void start(Stage primaryStage) throws Exception // Stage is outermost container of the app
    {
        System.out.println("Application Started");

        // Loader connects MyMain with FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        GridPane rootGridPane = loader.load(); // Loads rootNode as GridPane

        controller = loader.getController();
        controller.createPlayground();

        MenuBar menuBar = createMenu();

        menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
        // Matches the menuBar width with primaryStage width

        Pane menuPane = (Pane) rootGridPane.getChildren().get(0);
        menuPane.getChildren().add(menuBar);

        Scene scene = new Scene(rootGridPane);

        // Setting the scene
        primaryStage.setScene(scene);
        primaryStage.setTitle("Connect 4");
        primaryStage.setResizable(false); // If you want to prevent the user from resizing the Stage
        primaryStage.show();
    }

    private MenuBar createMenu()
    {
        //File Menu
        Menu fileMenu = new Menu("File");

        MenuItem newGame = new MenuItem("New Game");
        newGame.setOnAction(event -> controller.resetGame()); // Replaced with Lambda

        MenuItem resetGame = new MenuItem("Reset Game");
        resetGame.setOnAction(event -> controller.resetGame()); // Replaced with Lambda

        SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
        // allows for a horizontal Separator to be embedded within it

        MenuItem exitGame = new MenuItem("Exit Game");
        exitGame.setOnAction(event -> exitGame());

        fileMenu.getItems().addAll(newGame, resetGame, separatorMenuItem, exitGame);

        //Help Menu
        Menu helpMenu = new Menu("Help");

        MenuItem aboutGame = new MenuItem("About Connect 4");
        aboutGame.setOnAction(event -> aboutConnect4());
        SeparatorMenuItem separatorItem = new SeparatorMenuItem();
        MenuItem aboutMe = new MenuItem("About Me");
        aboutMe.setOnAction(event -> aboutMe());

        helpMenu.getItems().addAll(aboutGame, separatorItem, aboutMe);

        // Menu Bar
        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu, helpMenu); // Adding Menus to Menu Bar

        return menuBar;
    }

    private void aboutMe()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About The Developer");
        alert.setHeaderText("Durga Sadasivuni");
        alert.setContentText("Hey! I am Durga Sadasivuni." +
                "For me coding is fun" +
                "Experienced web developer, worked on various frontend and backend techonlogies");
        alert.show(); // To display the About section
    }

    private void aboutConnect4()
    {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("About Connect 4 Game");
        alert.setHeaderText("How To Play ?");
        alert.setContentText("The Connect 4 Game Rules are easy to understand." +
                "Connect the dots in either direction" +
                ",can be vertically, horizontally or in diagonally" +
                ". If you connect four in a sequence, poof! you won the game" +
                ".");

        alert.show();
    }

    private void exitGame()
    {
        Platform.exit(); // Closes the Virtual Machine
        System.exit(0); // Closes the Application
    }

    public static void main(String[] args) // Optional
    {
        launch(args);
    }
}