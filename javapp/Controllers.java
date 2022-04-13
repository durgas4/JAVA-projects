package com.durga.javapp;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;
// implement the click events, we used controller
public class Controllers implements Initializable {
    //Initialiazble interface is implemented by controller
    @FXML // annonations
    public Label WelcomeLabel;
    @FXML
    public ChoiceBox<String> choiceBox;
    @FXML
    public TextField textField;
    @FXML
    public Button button;

    private static final String C_T_F_Text="Celsius to Fahrenheit";
    private static final String F_T_C_Text="Fahrenheit to Celsius";
    private boolean isCtoF=true;
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        choiceBox.getItems().add(F_T_C_Text);
        choiceBox.getItems().add(C_T_F_Text);
        choiceBox.setValue(C_T_F_Text);

        //------lambda used-----
        choiceBox.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue.equals(isCtoF)){ // CTOF if user selected celcius to farenheit
                isCtoF=true;
            }else{
                isCtoF=false; // user selected farenheit to celcius
            }

           //obsraalbe proerty of theitem
           //previous selectred
           //new value selected
        });

        //---------lambda used----
        button.setOnAction(event -> {
            //System.out.println("Button pressed");
            convert();
            // code for button action
        });

    }

    private void convert() {
        String input=textField.getText();
        float enteredtemp=0.0f; // make it global variable to get rid of error
        //convert string to float
        try {
            enteredtemp=Float.parseFloat(input);// "23.5" to 23.5f conversion

        }catch (Exception e){
            warnuser();
            return;
           // no code is run
            // only error for valid temperature and not 32.f
        }
        float newtemp=0.0f;
        if(isCtoF){
            newtemp=((enteredtemp)*9/5)+32;
        }else{
            newtemp=(enteredtemp-32)*5/9;
        }
        display(newtemp);
    }

    private void warnuser() {
        Alert alert=new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error!");
        alert.setContentText("Invalid temperature");
        alert.setContentText("Please enter valid temperature value");
        alert.show();

    }

    private void display(float newtemp) {
        String unit=isCtoF?"F":"C";
        //System.out.println("New Temperature " + newtemp+unit);
        Alert alert=new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Result");
        alert.setContentText("New Temperature " + newtemp+unit);
        alert.show();

    }
}
