package com.nik.Box;

import com.nik.Model.Account;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/** A class that provides a few different windows for when a user is logged in */
public class UserBoxes {
    /** A window for viewing the Home of someone's login session -- takes in the current user's account */
    public static void displayHome(Account currentAccount){
        Stage window = new Stage();
        // Icon image
        Image image = new Image("/com/nik/Photos/awesome_deep_space.jpg");
        window.getIcons().addAll(image);
        window.setTitle("Account Home: " + currentAccount.getUsername());

        /* Scene */
//      Objects
//      TOP Objects
        Button logoutButton = new Button("Logout of " + currentAccount.getUsername());
        Button settingsButton = new Button("Settings for " + currentAccount.getUsername());
//      CENTER objects


//      Events
        logoutButton.setOnAction(event -> {
            boolean answer = ConfirmBox.display("Logout", "Are you sure you want to logout?");
            if(answer)
                window.close(); // close the window
        });
        settingsButton.setOnAction(event -> {
            // Open settings window
        });

//      Layout
//      TOP layout
        HBox topLayout = new HBox();
        topLayout.getChildren().setAll(logoutButton, settingsButton);
//      MAIN layout
        BorderPane mainLayout = new BorderPane();
        mainLayout.setTop(topLayout);

//      Scene options
        Scene mainScene = new Scene(mainLayout, 600, 400);
        mainScene.getStylesheets().add("/com/nik/main.css");

        // Window options
        window.setScene(mainScene);
        window.initModality(Modality.APPLICATION_MODAL);
        window.showAndWait();
    }
}
