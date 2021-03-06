package com.nik;

import com.nik.Box.ConfirmBox;
import com.nik.Box.DisplayViewBox;
import com.nik.Box.UserBoxes;
import com.nik.Model.Account;
import com.nik.Model.AccountsList;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class Main extends Application {
    private static Stage mainWindow;
    private static AccountsList myList;

    public static void main(String[] args) {
        myList = new AccountsList();
        myList.addAccount("mike_polanksy32", "myPassword", "Mike", "Jenkins", 57, 1, true);

        launch(args);
    }

    public void start(Stage window){
        mainWindow = window;
        // Icon image
        Image image = new Image("/com/nik/Photos/awesome_deep_space.jpg");
        window.getIcons().addAll(image);
        // TODO: 1. Make main GUI -- Possible objects: ComboBox, ChoiceBox, CheckBox, TableView, ListView
        /* MAIN scene */

        /* Objects */
        // TOP objects
        Button quitButton = new Button("Quit");
        Button aboutButton = new Button("About");

        // CENTER objects
        Label usernameLabel = new Label("Username:");
        Label passwordLabel = new Label("Password:");

        TextField usernameField = new TextField();
        usernameField.setPromptText("Enter your username");
        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Enter your password");

        Button submitButton = new Button("Login");

        Label errorLabel = new Label("");

        // BOTTOM objects
        Label authorLabel = new Label("Author Nik");
        Label copyrightLabel = new Label("Copyright @ 2018 Bell Labz. All rights reserved.");

        /* Events */
//      TODO: 2. Add events
        // TOP events
        aboutButton.setOnAction(event -> {
            DisplayViewBox.display("About Page", "Author", "Nik Fernandez; 20 years old.\nHas a novice level of experience with various programming languages.", "Description",
                    "This program was created as one of many series of tests on my knowledge of JavaFX", "Revisions",
                    "02/04/2018: Created application. Added main windows and functionality (i.e., main window, confirm window, about window, quit functionality, added stylesheet).");
        });
        quitButton.setOnAction(event -> closeOperation());
        // CENTER events
        submitButton.setOnAction(event -> {
            // TODO: Open window for login page
            Account checkerAccount = myList.findByName(usernameField.getText());
            if(checkerAccount != null){
                if(checkerAccount.getPassword().equals(passwordField.getText())){ // checks if the account's associated password is equal to the password entered
                    errorLabel.setStyle("-fx-text-fill: green");
                    errorLabel.setText("SUCCESS: Login successful");

                    // Clear username and password fields
                    usernameField.clear();
                    passwordField.clear();

                    // Open logged in window
                    UserBoxes.displayHome(checkerAccount);
                }else{
                    errorLabel.setStyle("-fx-text-fill: red");
                    errorLabel.setText("ERROR: Wrong password");
                }
            }else{
                errorLabel.setStyle("-fx-text-fill: red");
                errorLabel.setText("ERROR: No matching username found");
            }
        });
        // WINDOW events
        mainWindow.setOnCloseRequest(event -> {
            event.consume(); // stop the window from closing
            closeOperation();
        });

        // layout
        // TOP layout
        HBox topLayout = new HBox(20);
        topLayout.setAlignment(Pos.TOP_LEFT);
        topLayout.getChildren().addAll(quitButton, aboutButton);
        // CENTER Layout
        GridPane.setConstraints(usernameLabel, 0, 0);
        GridPane.setConstraints(usernameField, 1, 0);
        GridPane.setConstraints(passwordLabel, 0, 1);
        GridPane.setConstraints(passwordField, 1, 1);
        GridPane.setConstraints(submitButton, 0, 2);
        GridPane.setConstraints(errorLabel, 0, 3);
        GridPane centerLayout = new GridPane();
        centerLayout.setAlignment(Pos.CENTER);
        centerLayout.setVgap(30);
        centerLayout.setHgap(30);
        centerLayout.getChildren().setAll(usernameLabel, usernameField, passwordLabel, passwordField, submitButton, errorLabel);
        // LEFT layout
        // BOTTOM layout
        HBox bottomLayout = new HBox(20);
        bottomLayout.getChildren().addAll(authorLabel, copyrightLabel);
        // MAIN layout
        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(topLayout);
        mainLayout.setCenter(centerLayout);
        mainLayout.setBottom(bottomLayout);

        // SCENE
        Scene mainScene = new Scene(mainLayout, 600, 400);
        // STYLESHEET
        mainScene.getStylesheets().add("/com/nik/main.css");

        // window options
        mainWindow.setTitle("TechNet's System v1.2");
        mainWindow.setScene(mainScene);
        mainWindow.show();
    }
    private static void closeOperation(){
        boolean answer = ConfirmBox.display("Are you sure?", "Are you sure you want to quit?");
        if(answer)
            mainWindow.close(); // close the window
    }
}
