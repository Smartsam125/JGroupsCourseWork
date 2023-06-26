package com.seshare.seshare;
import dashboarddesign.DashboardDesign;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

public class SeShareApp extends Application {
    Connection conn =Db.connection();
    public String email;
    private Stage primaryStage;
    private Scene loginScene;
    private Scene signUpScene;

    @Override
    public void start(Stage stage) {
       // Db database =new Db();
        //Db.connection();


        primaryStage = stage;

        // Creating labels
        Text text1 = new Text("UserName");
        Text text2 = new Text("Password");

        // Creating input fields
        TextField textField1 = new TextField();
        PasswordField textField2 = new PasswordField();

        // Creating buttons
        Button submitButton = new Button("Submit");
        Button clearButton = new Button("Clear");
        Button signUpButton = new Button("Sign Up");

        // Creating a Grid Pane
        GridPane gridPane = new GridPane();

        // Setting size for the pane
        gridPane.setMinSize(400, 200);

        // Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        // Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        // Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);

        // Arranging all the nodes in the grid
        gridPane.add(text1, 0, 0);
        gridPane.add(textField1, 1, 0);
        gridPane.add(text2, 0, 1);
        gridPane.add(textField2, 1, 1);
        gridPane.add(submitButton, 0, 2);
        gridPane.add(clearButton, 1, 2);
        gridPane.add(signUpButton, 0, 3);

        // Styling nodes
        submitButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        clearButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        signUpButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        text1.setStyle("-fx-font: normal bold 20px 'serif'");
        text2.setStyle("-fx-font: normal bold 20px 'serif'");
        gridPane.setStyle("-fx-background-color: BEIGE;");

        // Adding event handlers
        submitButton.setOnAction(e -> {
            String email = textField1.getText();
            String password = textField2.getText();
            this.email=email;
            String loginInfo =   email + password;
            try {
                PreparedStatement pst= conn.prepareStatement("select name,password from student where  name=? and password=?");
                pst.setString(1,email);
                pst.setString(2,password);
                ResultSet rs =pst.executeQuery();
                if(rs.next()){
                    //JOptionPane.showMessageDialog(null,"WelcomeSam");
                    DashboardDesign dashboard = new DashboardDesign();
                    dashboard.start(new Stage());

                    primaryStage.close();
                }else {
                    JOptionPane.showMessageDialog(null,"PleaseSignup");
                }
                //conn.close();
            } catch (Exception ex) {
                System.out.println(ex.getCause());
                //throw new RuntimeException(ex);
            }

            //System.out.println(loginInfo);
        });

        clearButton.setOnAction(e -> {
            textField1.clear();
            textField2.clear();
        });

        signUpButton.setOnAction(e -> {
            primaryStage.setScene(signUpScene);
        });

        // Creating the login scene
        loginScene = new Scene(gridPane);

        // Setting title to the Stage
        stage.setTitle("SeShare Login");

        // Setting the login scene as the initial scene
        stage.setScene(loginScene);

        // Displaying the contents of the stage
        stage.show();

        // Create the sign-up scene
        createSignUpScene();
    }

    private void createSignUpScene() {
        // Creating labels
        Text text1 = new Text("Username");
        Text text2 = new Text("New Password");

        // Creating input fields
        TextField textField1 = new TextField();
        PasswordField textField2 = new PasswordField();

        // Creating buttons
        Button signUpButton = new Button("Sign Up");

        // Creating a Grid Pane
        GridPane gridPane = new GridPane();

        // Setting size for the pane
        gridPane.setMinSize(400, 200);

        // Setting the padding
        gridPane.setPadding(new Insets(10, 10, 10, 10));

        // Setting the vertical and horizontal gaps between the columns
        gridPane.setVgap(5);
        gridPane.setHgap(5);

        // Setting the Grid alignment
        gridPane.setAlignment(Pos.CENTER);

        // Arranging all the nodes in the grid
        gridPane.add(text1, 0, 0);
        gridPane.add(textField1, 1, 0);
        gridPane.add(text2, 0, 1);
        gridPane.add(textField2, 1, 1);
        gridPane.add(signUpButton, 0, 2);

        // Styling nodes
        signUpButton.setStyle("-fx-background-color: darkslateblue; -fx-text-fill: white;");
        text1.setStyle("-fx-font: normal bold 20px 'serif'");
        text2.setStyle("-fx-font: normal bold 20px 'serif'");
        gridPane.setStyle("-fx-background-color: BEIGE;");

        // Adding event handlers
        signUpButton.setOnAction(e -> {
            String username = textField1.getText();
            String password = textField2.getText();

            String signUpInfo =  username + password;
           // System.out.println(signUpInfo);
            try{
                PreparedStatement pst = conn.prepareStatement("INSERT INTO student (name, password) VALUES (?, ?)");
                pst.setString(1,username);
                pst.setString(2,password);
                pst.executeUpdate();

            }catch (Exception r){
                System.out.println(r.getMessage());
            }
            primaryStage.setScene(loginScene);
        });

        // Creating the sign-up scene
        signUpScene = new Scene(gridPane);
    }

    public static void main(String args[]) {

        launch(args);
    }
}
