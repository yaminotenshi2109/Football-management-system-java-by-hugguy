package com.example.fcms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;


public class SellWindow implements Initializable {

    DBConnection dbConnection = new DBConnection();
    public ObservableList<String> firstcomboarray = FXCollections.observableArrayList("Pos","AGE","CURRENT_CLUB","Nation");
    public  ObservableList<String> conditionarray= FXCollections.observableArrayList("","AGE","MVA","TRANSFERFEE");

    private ObservableList<TableData> SelltableData;

    @FXML
    private TableView<TableData> SELLTABLE;

    @FXML
    private TableColumn<TableData, Integer> Sell_ID;

    @FXML
    private TableColumn<TableData, String> Sell_NAME;

    @FXML
    private TableColumn<TableData, String> Sell_AGE;

    @FXML
    private TableColumn<TableData, String> Sell_NATIONALITY;

    @FXML
    private TableColumn<TableData, String> Sell_POSITION;

    @FXML
    private TableColumn<TableData, Double> Sell_MVA ;

    @FXML
    private TableColumn<TableData, Integer> Sell_CAPS;

    @FXML
    private TableColumn<TableData, Integer> Sell_GOALS;

    @FXML
    private TableColumn<TableData, Double> Sell_TRANSFERFEE;

    @FXML
    private ComboBox<String> firstcombo1;

    @FXML
    private ComboBox<String> RelationCombo1;
    @FXML
    private TextField firsttf_1;

    @FXML
    private TextField Id1;
    @FXML
    private TextField firsttf_2;
    @FXML
    private TextField Id;

    @FXML
    private TextField Age;

    @FXML
    private TextField Transfer_Fee;

    @FXML
    private TextField Name;

    @FXML
    private TextField Goals;

    @FXML
    private TextField Caps;

    @FXML
    private TextField Market_Value;

    @FXML
    private TextField Position;

    @FXML
    private TextField Nationality;
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        SelltableData = FXCollections.observableArrayList();
        firstcombo1.setItems(firstcomboarray);
        RelationCombo1.setItems(conditionarray);
        firstcombo1.getSelectionModel().selectFirst();
        RelationCombo1.getSelectionModel().selectFirst();
        loadTableData();
        initTables();
    }


    private void loadTableData() {
        try {
            SELLTABLE.getItems().clear();
            String sql = "SELECT SELL_ID, P_name, P_Age, P_Nation, P_Pos, SELL_MVA, SELL_CAPS, SELL_GOAL , SELL_TRANSFERFEE" +
                    "   FROM Player P JOIN SELL_TABLE S" +
                    "   ON P.P_ID = S.SELL_ID" +
                    "   ORDER BY S.SELL_ID";
            ResultSet resultSet = dbConnection.getStatement().executeQuery(sql);
            while (resultSet.next()) {
                SelltableData.add(new TableData(resultSet.getInt("Sell_ID"),resultSet.getString("P_name"), resultSet.getInt("P_Age"),resultSet.getString("P_Nation"),resultSet.getString("P_Pos"),resultSet.getDouble("Sell_MVA"),resultSet.getInt("Sell_CAPS"),resultSet.getInt("Sell_GOAL"),resultSet.getDouble("Sell_TRANSFERFEE")));
            }
            SELLTABLE.setItems(SelltableData);
            SELLTABLE.setOnMouseClicked(e -> {
                if (e.getClickCount() > 1) {
                    int index = SELLTABLE.getSelectionModel().getSelectedIndex();
                    /*fillBox(index); */
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

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
        m.changeScene("Login.fxml");
    }
    public void GroundandGymMethod(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("GroundAndGym.fxml");
    }

    @FXML
    void searchRangeQuery(ActionEvent event) throws IOException{

    }
    private void initTables() {
        Sell_ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        Sell_NAME.setCellValueFactory(new PropertyValueFactory<>("Name"));
        Sell_AGE.setCellValueFactory(new PropertyValueFactory<>("Age"));
        Sell_NATIONALITY.setCellValueFactory(new PropertyValueFactory<>("Nation"));
        Sell_POSITION.setCellValueFactory(new PropertyValueFactory<>("Position"));
        Sell_MVA.setCellValueFactory(new PropertyValueFactory<>("Mva"));
        Sell_CAPS.setCellValueFactory(new PropertyValueFactory<>("Caps"));
        Sell_GOALS.setCellValueFactory(new PropertyValueFactory<>("Goals"));
        Sell_TRANSFERFEE.setCellValueFactory(new PropertyValueFactory<>("Transfer_FEE"));
    }

    @FXML
    void SellPlayer(ActionEvent event) {
        int index = SELLTABLE.getSelectionModel().getSelectedIndex();
        try {
            String sql = "DELETE FROM SELL_TABLE WHERE SELL_ID = " + SELLTABLE.getColumns().get(0).getCellObservableValue(index).getValue();
            System.out.println(sql);
            int W = dbConnection.getStatement().executeUpdate(sql);
            String sql4 = "DELETE FROM Player WHERE  P_ID = " +   SELLTABLE.getColumns().get(0).getCellObservableValue(index).getValue();
            int R = dbConnection.getStatement().executeUpdate(sql4);
            loadTableData();
            initTables();
        } catch (SQLException ex){
            ex.printStackTrace();
        }
    }

    @FXML
    void Search(ActionEvent event){
        if(RelationCombo1.getValue().toString().equals("")){
            String sql= "SELECT * FROM Player P JOIN SELL_TABLE S\n" +
                    "\t   ON P.P_ID = S.SELL_ID WHERE P_"+firstcombo1.getValue().toString()+" LIKE '%"+Id1.getText().toString()+"%'";
            searchAndPrint(sql);
        }else {
            String sql="SELECT * FROM Player P JOIN SELL_TABLE S\n" +
                    "\t   ON P.P_ID = S.SELL_ID WHERE P_"+firstcombo1.getValue().toString()+" LIKE '%"+Id1.getText().toString()+"%' AND ( SELL_"+RelationCombo1.getValue()+" BETWEEN "+firsttf_1.getText().toString()+" AND "+firsttf_2.getText().toString()+" )";
            System.out.println(sql);
            searchAndPrint(sql);
        }

    }
    @FXML
    void refreshTable(ActionEvent event) {
        loadTableData();
        initTables();
    }

    private void searchAndPrint(String searchsql) {
        try {
            SELLTABLE.getItems().clear();
            ResultSet resultSet = dbConnection.getStatement().executeQuery(searchsql);
            while (resultSet.next()) {
                SelltableData.add(new TableData(resultSet.getInt("Sell_ID"),resultSet.getString("P_name"), resultSet.getInt("P_Age"),resultSet.getString("P_Nation"),resultSet.getString("P_Pos"),resultSet.getDouble("Sell_MVA"),resultSet.getInt("Sell_CAPS"),resultSet.getInt("Sell_GOAL"),resultSet.getDouble("Sell_TRANSFERFEE")));
            }
            SELLTABLE.setItems(SelltableData);
            initTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setItems(String text, String text1, String text2, String text3, String value) {
        Id.setText(text);
        Name.setText(text1);
        Age.setText(text2);
        Nationality.setText(text3);
        Position.setText(value);
        Position.setText(value);
//        initTables();
//        loadTableData();
    }
}
