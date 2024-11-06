package com.example.fcms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.sql.*;
import java.sql.Connection;

public class Login {

    public Login() {

    }

    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private Button LogInButton;
    @FXML
    private Label wronglogin;

    public void UserLoginMethod(ActionEvent event) throws IOException {
        checkLogin();  
    }

    private void checkLogin() throws IOException {
        Main m = new Main();

        if (username.getText().isEmpty() && password.getText().isEmpty()) {
            wronglogin.setText("Please enter credentials");
        } else {
            try {
                DBConnection dbConnection = new DBConnection();
                String sql = "SELECT * FROM DBLogin WHERE UserName = '" + username.getText().toString() + "'";
                ResultSet resultSet = dbConnection.getStatement().executeQuery(sql);
                if (resultSet.next()) {
                    if (password.getText().toString().equals(resultSet.getString("UserPassword"))) {
                        wronglogin.setText("Success!!");
                        m.changeScene("Menu.fxml");
                    } else {
                        wronglogin.setText("Wrong username or password...");
                    }
                }
                else{
                    wronglogin.setText("Wrong username or password...");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

}

