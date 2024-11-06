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


public class TransferWindow implements Initializable {



    DBConnection dbConnection = new DBConnection();
    public ObservableList<String> firstcomboarray = FXCollections.observableArrayList("Position","AGE","CURRENT_CLUB","Nationality");
    public  ObservableList<String> conditionarray= FXCollections.observableArrayList("","AGE","MVA","TRANSFERFEE");
    private ObservableList<TableData> BuytableData;

    @FXML
    private TableView<TableData> BUYTABLE;

    @FXML
    private TableColumn<TableData, Integer> TSP_ID;

    @FXML
    private TableColumn<TableData, String> TSP_NAME;

    @FXML
    private TableColumn<TableData, String> TSP_AGE;

    @FXML
    private TableColumn<TableData, String> TSP_NATIONALITY;

    @FXML
    private TableColumn<TableData, String> TSP_POSITION;

    @FXML
    private TableColumn<TableData, Double> TSP_MVA ;

    @FXML
    private TableColumn<TableData, Integer> TSP_CAPS;

    @FXML
    private TableColumn<TableData, Integer> TSP_GOALS;

    @FXML
    private TableColumn<TableData, String> TSP_CURRENT_CLUB;

    @FXML
    private TableColumn<TableData, Double> TSP_TRANSFERFEE;

    @FXML
    private ComboBox<String> firstcombo1;

    @FXML
    private ComboBox<String> RelationCombo1;
    @FXML
    private TextField Age;

    @FXML
    private TextField Caps;

    @FXML
    private Button CoachButton;

    @FXML
    private TextField Current_Club;

    @FXML
    private TextField Goals;

    @FXML
    private TextField Id;

    @FXML
    private TextField Id1;

    @FXML
    private Button LogoutButton;

    @FXML
    private TextField Market_Value;

    @FXML
    private TextField Name;

    @FXML
    private TextField Nationality;

    @FXML
    private Button PlayersButton;

    @FXML
    private TextField Position;


    @FXML
    private Button StaffButton;

    @FXML
    private TextField Transfer_Fee;


    @FXML
    private TextField firsttf_1;

    @FXML
    private TextField firsttf_2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        firstcombo1.setItems(firstcomboarray);
        RelationCombo1.setItems(conditionarray);
        firstcombo1.getSelectionModel().selectFirst();
        RelationCombo1.getSelectionModel().selectFirst();
        BuytableData = FXCollections.observableArrayList();
        loadTableData();
        initTables();
    }


    private void loadTableData() {
        try {
            BUYTABLE.getItems().clear();
            String sql = "SELECT * FROM BUYTABLE";
            ResultSet resultSet = dbConnection.getStatement().executeQuery(sql);
            while (resultSet.next()) {
                BuytableData.add(new TableData(resultSet.getInt("TSP_ID"),resultSet.getString("TSP_NAME"), resultSet.getInt("TSP_AGE"),resultSet.getString("TSP_NATIONALITY"),resultSet.getString("TSP_POSITION"),resultSet.getDouble("TSP_MVA"),resultSet.getInt("TSP_CAPS"),resultSet.getInt("TSP_GOALS"),resultSet.getString("TSP_CURRENT_CLUB"),resultSet.getDouble("TSP_TRANSFERFEE")));
            }
            BUYTABLE.setItems(BuytableData);
            BUYTABLE.setOnMouseClicked(e -> {
                if (e.getClickCount() > 1) {
                    int index = BUYTABLE.getSelectionModel().getSelectedIndex();
                    fillBox(index);
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void fillBox(int index) {
        try {
            String sql = "SELECT * FROM BUYTABLE WHERE TSP_ID = " + BUYTABLE.getColumns().get(0).getCellObservableValue(index).getValue();
            ResultSet resultSet = dbConnection.getStatement().executeQuery(sql);
            while (resultSet.next()) {
                Id.setText(String.valueOf(resultSet.getInt("TSP_ID")));
                Name.setText(String.valueOf(resultSet.getString("TSP_NAME")));
                Age.setText(String.valueOf(resultSet.getInt("TSP_AGE")));
                Nationality.setText(String.valueOf(resultSet.getString("TSP_NATIONALITY")));
                Position.setText(resultSet.getString("TSP_POSITION"));
                Market_Value.setText(String.valueOf(resultSet.getDouble("TSP_MVA")));
                Caps.setText(String.valueOf(resultSet.getString("TSP_CAPS")));
                Goals.setText(String.valueOf(resultSet.getInt("TSP_GOALS")));
                Current_Club.setText(resultSet.getString("TSP_CURRENT_CLUB"));
                Transfer_Fee.setText(String.valueOf(resultSet.getDouble("TSP_TRANSFERFEE")));
            }
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
    void DevelopedbyMethod(ActionEvent event) throws IOException {
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
    void SellTableMethod(ActionEvent event) throws IOException{
        Main m=new Main();
        m.changeScene("SellWindow.fxml");
    }

    private void initTables() {
        TSP_ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        TSP_NAME.setCellValueFactory(new PropertyValueFactory<>("Name"));
        TSP_AGE.setCellValueFactory(new PropertyValueFactory<>("Age"));
        TSP_NATIONALITY.setCellValueFactory(new PropertyValueFactory<>("Nation"));
        TSP_POSITION.setCellValueFactory(new PropertyValueFactory<>("Position"));
        TSP_MVA.setCellValueFactory(new PropertyValueFactory<>("Mva"));
        TSP_CAPS.setCellValueFactory(new PropertyValueFactory<>("Caps"));
        TSP_GOALS.setCellValueFactory(new PropertyValueFactory<>("Goals"));
        TSP_CURRENT_CLUB.setCellValueFactory(new PropertyValueFactory<>("Current_Club"));
        TSP_TRANSFERFEE.setCellValueFactory(new PropertyValueFactory<>("Transfer_FEE"));
    }

    @FXML
    void BuyPlayer(ActionEvent event) {
        int index = BUYTABLE.getSelectionModel().getSelectedIndex();
        try {
            String sql = "DELETE FROM BUYTABLE WHERE TSP_ID = " + BUYTABLE.getColumns().get(0).getCellObservableValue(index).getValue();
            System.out.println(sql);
            int W = dbConnection.getStatement().executeUpdate(sql);
            String sql3 = "INSERT INTO Player(P_Name ,P_Age, P_Sex, P_Nation, P_Pos, P_Salary ,Team_ID) \n" +
                    "VALUES( ' " + BUYTABLE.getColumns().get(1).getCellObservableValue(index).getValue() + " '," + BUYTABLE.getColumns().get(2).getCellObservableValue(index).getValue() + ", 'M' , ' " + BUYTABLE.getColumns().get(3).getCellObservableValue(index).getValue() + "' , '" + BUYTABLE.getColumns().get(4).getCellObservableValue(index).getValue() + "'," + BUYTABLE.getColumns().get(5).getCellObservableValue(index).getValue() + ", 42)";
            System.out.println(sql3);
            int R = dbConnection.getStatement().executeUpdate(sql3);
            loadTableData() ;
            initTables();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    void Search(ActionEvent event){
        if(RelationCombo1.getValue().toString().equals("")){
            String sql= "SELECT * FROM BUYTABLE WHERE TSP_"+firstcombo1.getValue().toString()+" LIKE '%"+Id1.getText().toString()+"%'";
            searchAndPrint(sql);
        }else {
            String sql="SELECT * FROM BUYTABLE WHERE TSP_"+firstcombo1.getValue().toString()+" LIKE '%"+Id1.getText().toString()+"%' AND ( TSP_"+RelationCombo1.getValue()+" BETWEEN "+firsttf_1.getText().toString()+" AND "+firsttf_2.getText().toString()+" )";
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
            BUYTABLE.getItems().clear();
            ResultSet resultSet = dbConnection.getStatement().executeQuery(searchsql);
            while (resultSet.next()) {
                BuytableData.add(new TableData(resultSet.getInt("TSP_ID"),resultSet.getString("TSP_NAME"), resultSet.getInt("TSP_AGE"),resultSet.getString("TSP_NATIONALITY"),resultSet.getString("TSP_POSITION"),resultSet.getDouble("TSP_MVA"),resultSet.getInt("TSP_CAPS"),resultSet.getInt("TSP_GOALS"),resultSet.getString("TSP_CURRENT_CLUB"),resultSet.getDouble("TSP_TRANSFERFEE")));
            }
            BUYTABLE.setItems(BuytableData);
            initTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
