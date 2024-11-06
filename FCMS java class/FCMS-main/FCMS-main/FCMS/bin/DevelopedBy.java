package com.example.fcms;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

import java.io.IOException;

public class DevelopedBy {
    @FXML
    void CoachMethod(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("Coach.fxml");
    }

    @FXML
    void DevelopedByMethod(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("DevelopedBy.fxml");
    }



    @FXML
    void PlayerMethod(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("Player.fxml");
    }

    @FXML
    void StaffMethod(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("Staff.fxml");
    }

    @FXML
    void TournamentMethod(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("Tournament.fxml");
    }

    @FXML
    void TransferWindowMethod(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("TransferWindow.fxml");
    }

    public void UserLogoutMethod(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("Login.fxml");//Login Button e click korle ei method e jabe.
    }
    public void GroundandGymMethod(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("GroundAndGym.fxml");//Login Button e click korle ei method e jabe.
    }
}
