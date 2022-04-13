package com.durga.javapp;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Optional;
public class Main extends Application {
    public static void main(String[] args) {
        System.out.println("main");// not part of javafx life cycle
        launch(args);
	// write your code here
    }

    @Override// starting of application
    public void init() throws Exception {
        super.init();
        System.out.println("inititalize application");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println("Start of application");
        FXMLLoader loader = new FXMLLoader(getClass().getResource("app_layout.fxml"));
        VBox rootNode = loader.load();
        Scene scene = new Scene(rootNode);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Temperature Converter");
        //primaryStage.resizableProperty(false);
        primaryStage.show();
        MenuBar menubar=CreateMenu();
        rootNode.getChildren().add(0,menubar); // menubar before welcome tag

    }
    private MenuBar CreateMenu(){
        //file menu
        Menu filemenu =new Menu("Menu");
        MenuItem newitem=new MenuItem("New");

        //----------with lambda function------------

        newitem.setOnAction(event -> System.out.println("New item clicked"));
        SeparatorMenuItem separator=new SeparatorMenuItem();
        MenuItem quititem=new MenuItem("Quit");


        //----------with lambda function------------
        quititem.setOnAction(event -> {
            //System.out.println("Exit the application");
            // can use shut down
            Platform.exit();
            //System.exit(0);//closes virtual machine
        });
        filemenu.getItems().addAll(newitem,separator,quititem);
        //help menu
        Menu helpmenu =new Menu("Help");
        MenuBar menubar=new MenuBar();
        MenuItem helpitem=new MenuItem("Help");
        MenuItem moreitem=new MenuItem("About");


        // --------------without lambda function-------------------------
        moreitem.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                aboutapp();
            }
        });

        helpmenu.getItems().addAll(helpitem,moreitem);
        menubar.getMenus().addAll(filemenu,helpmenu);
        return menubar;
        //
    }
    public static void aboutapp(){
        // class of Alert
        // variable name - alert
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        // alert type
        alert.setTitle("My first desktop app");
        alert.setHeaderText("Learning javaFx");//substitle
        alert.setContentText("Learner");// description

        //-------- customize button type---------------
        ButtonType yesbtn=new ButtonType("yes");
        ButtonType nobtn=new ButtonType("No");
        alert.getButtonTypes().setAll(yesbtn,nobtn);

        //------handle event for button---
        Optional<ButtonType> clickbtn=alert.showAndWait();
        if(clickbtn.isPresent()&&clickbtn.get() == yesbtn){
            System.out.println("Yes pressed");
        }
        else{
            System.out.println("No pressed");
        }

        alert.show();
    }
    @Override
    public void stop() throws Exception {
        System.out.println("stop application");
        super.stop();
    }
}
// life cyclemethods
//init -  not to override compulsorily-init90
//start method-stage.show(), must override
//stop method - need not to override - stop()