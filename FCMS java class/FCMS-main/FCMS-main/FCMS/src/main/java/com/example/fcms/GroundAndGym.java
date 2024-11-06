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

public class GroundAndGym implements Initializable {
    DBConnection dbConnection = new DBConnection();
    public ObservableList<String> merchendisesearch = FXCollections.observableArrayList("Search", "No", "Name", "Type", "Capacity", "Description");
    public ObservableList<String> relationcombo= FXCollections.observableArrayList("=","<>",">=","<=");
    private ObservableList<TableData> GroundtableData;

    @FXML
    private TableView<TableData> GROUNDTABLE;

    @FXML
    private TableColumn<TableData, Integer> GG_No;

    @FXML
    private TableColumn<TableData, String> GG_Name;

    @FXML
    private TableColumn<TableData, String> GG_Type;

    @FXML
    private TableColumn<TableData, String> GG_Description;

    @FXML
    private TableColumn<TableData, Integer> GG_Capacity;

    @FXML
    private TableColumn<TableData, Integer> S_ID;

    //@FXML
    //private TableColumn<TableData, Integer> Pr_TotalPr_Stock;

    @FXML
    private TextField Product_ID;

    @FXML
    private TextField Product_Name;

    @FXML
    private TextField Product_Type;

    @FXML
    private TextField Product_Description;

    @FXML
    private TextField Product_Rate;

    @FXML
    private TextField Product_Quantity;

    @FXML
    private TextField Product_Sold;

    @FXML
    private TextField Product_Predictedsell;

    @FXML
    private TextField Product_PerCost;

    @FXML
    private ComboBox<String>mersearchrow1;

    @FXML
    private TextField firsttf_1;

    @FXML
    private ComboBox<String> RelationCombo1;

    @FXML
    private Label GroundNo;

    @FXML
    private TextField Ground_Type;

    @FXML
    private TextField firsttf_2;

    @FXML
    private TextField Ground_Name;

    @FXML
    private TextField Ground_SiD;

    @FXML
    private TextField Ground_Capacity;

    @FXML
    private TextField Ground_Des;

    @FXML
    private Button PlayersButton;

    @FXML
    private Button CoachButton;

    @FXML
    private Button TransferWindowButton;

    @FXML
    private Button StaffButton;

    @FXML
    private Button Insert;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        GroundtableData = FXCollections.observableArrayList();
        loadTableData();
        initTables();

    }

    private void loadTableData() {
        try {
            GROUNDTABLE.getItems().clear();
            String sql = "SELECT * FROM GroundandGym";
            ResultSet resultSet = dbConnection.getStatement().executeQuery(sql);
            while (resultSet.next()) {

                GroundtableData.add(new TableData(resultSet.getInt("GG_No"),resultSet.getString("GG_Name"), resultSet.getString("GG_Type"),resultSet.getString("GG_Description"),resultSet.getInt("GG_Capacity"),resultSet.getInt("S_ID")));
            }
            GROUNDTABLE.setItems(GroundtableData);
            GROUNDTABLE.setOnMouseClicked(e -> {
                if (e.getClickCount() > 1) {
                    int index = GROUNDTABLE.getSelectionModel().getSelectedIndex();
                    fillBox(index);
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fillBox(int index) {
        try {
            String sql = "select * from GroundandGym gg join Staff s on s.S_ID=gg.S_ID AND gg.GG_No=" + GROUNDTABLE.getColumns().get(0).getCellObservableValue(index).getValue();
            ResultSet resultSet = dbConnection.getStatement().executeQuery(sql);
            while (resultSet.next()) {
                GroundNo.setText(String.valueOf(resultSet.getInt("GG_No")));
                Ground_Name.setText(resultSet.getString("GG_Name"));
                Ground_Type.setText(resultSet.getString("GG_Type"));
                Ground_Des.setText(resultSet.getString("GG_Description"));
                Ground_Capacity.setText(resultSet.getString("GG_Capacity"));
                Ground_SiD.setText(resultSet.getString("S_Name"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void insertIntoTable(ActionEvent event) {
        try {
            String insertSQL = "INSERT INTO GroundandGym(GG_Name,GG_Type,GG_Capacity,GG_Description,S_ID)" +
                    "VALUES('"+ Ground_Name.getText().toString() + "','" + Ground_Type.getText().toString() + "','" + Ground_Capacity.getText().toString() + "','" + Ground_Des.getText().toString() + "', " + Ground_SiD.getText().toString() + ")" ;
            System.out.println(insertSQL);
            int row = dbConnection.getStatement().executeUpdate(insertSQL);
            clearAllfields(new ActionEvent());
            refreshTable(new ActionEvent());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void clearAllfields(ActionEvent event) {
        Ground_Type.setText("");
        Ground_Name.setText("");
        Ground_Capacity.setText("");
        Ground_Des.setText("");
        GG_No.setText("");
        Ground_SiD.setText("");
    }

    @FXML
    void refreshTable(ActionEvent event) {
        loadTableData();
        initTables();
    }


    private void initTables() {
        GG_No.setCellValueFactory(new PropertyValueFactory<>("ID"));
        GG_Name.setCellValueFactory(new PropertyValueFactory<>("Name"));
        GG_Type.setCellValueFactory(new PropertyValueFactory<>("Type"));
        GG_Description.setCellValueFactory(new PropertyValueFactory<>("Description"));
        GG_Capacity.setCellValueFactory(new PropertyValueFactory<>("Capacity"));
        S_ID.setCellValueFactory(new PropertyValueFactory<>("S_ID"));
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



}
