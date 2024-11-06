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
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import javafx.fxml.Initializable;

public class Tournament implements Initializable {
    DBConnection dbConnection = new DBConnection();
    public ObservableList<String> Tournamentsearch = FXCollections.observableArrayList("Date","Match_Type","Match_Area","Opponent","Stadium","Result");
    public ObservableList<String> results=FXCollections.observableArrayList("Won","Loss","Draw");
    private ObservableList<TableData> TournamentTable;
    String matchA;
int index;
    @FXML
    private TableView<TableData> TOURNAMNETTABLE;

    @FXML
    private TableColumn<TableData, Integer> Tour_ID;

    @FXML
    private TableColumn<TableData, String> Tour_Match_Type ;

    @FXML
    private TableColumn<TableData, String> Tour_Stadium;

    @FXML
    private TableColumn<TableData, String> Tour_Match_Area;

    @FXML
    private TableColumn<TableData, String> Tour_Date;

    @FXML
    private TableColumn<TableData, String> Tour_Opponent;

    @FXML
    private TableColumn<TableData, String> Tour_Result;

    @FXML
    private TableColumn<TableData, String> Tour_Match_Fee;

    @FXML
    private TableColumn<TableData, Integer> Tour_Team_ID;

    @FXML
    private RadioButton home;

    @FXML
    private ToggleGroup ma;

    @FXML
    private RadioButton away;
    @FXML
    private ComboBox<String> searchcriteria;

    @FXML
    private TextField stadium;

    @FXML
    private TextField opponent;

    @FXML
    private TextField matchFee;

    @FXML
    private TextField searchkey;

    @FXML
    private DatePicker date1;

    @FXML
    private DatePicker date2;

    @FXML
    private DatePicker da;

    @FXML
    private ComboBox<String> resultbox;

    @FXML
    private TextField teamId;

    @FXML
    private TextField matchType;


    @FXML
    private TextField matchdate;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        TournamentTable = FXCollections.observableArrayList();
        searchcriteria.setItems(Tournamentsearch);
        searchcriteria.getSelectionModel().selectFirst();
        resultbox.setItems(results);
        loadTableData();
        initTables();

    }

    private void loadTableData() {
        try {
            //BUYTABLE.getItems().clear();
            TOURNAMNETTABLE.getItems().clear();
            String sql = "SELECT * FROM Tournament t JOIN Team te ON t.Team_ID=te.Team_ID ";
            ResultSet resultSet = dbConnection.getStatement().executeQuery(sql);
            while (resultSet.next()) {
                TournamentTable.add(new TableData(resultSet.getInt("Tm_ID"),resultSet.getString("Tm_Match_Type"), resultSet.getString("Tm_Stadium"),resultSet.getString("Tm_Match_Area"),resultSet.getString("Tm_Date"),resultSet.getString("Tm_Opponent"),resultSet.getString("Tm_Result"),resultSet.getFloat("Tm_Match_Fee"),resultSet.getString("Team_Name")));
            }
            TOURNAMNETTABLE.setItems(TournamentTable);
            TOURNAMNETTABLE.setOnMouseClicked(e -> {
                if (e.getClickCount() > 1) {
                    int index = TOURNAMNETTABLE.getSelectionModel().getSelectedIndex();
                    fillBox(index);
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fillBox(int index) {
        try {
            String sql = "SELECT * FROM Tournament tr JOIN TEAM t ON tr.Team_ID = t.Team_ID AND Tm_ID=" + TOURNAMNETTABLE.getColumns().get(0).getCellObservableValue(index).getValue();
            ResultSet resultSet = dbConnection.getStatement().executeQuery(sql);
            while (resultSet.next()) {
                stadium.setText(resultSet.getString("Tm_Stadium"));
                opponent.setText(resultSet.getString("Tm_Opponent"));
                matchFee.setText(String.valueOf(resultSet.getDouble("Tm_Match_Fee")));
                teamId.setText(resultSet.getString("Team_Name"));
                matchType.setText(resultSet.getString("Tm_Match_Type"));
                matchdate.setText(resultSet.getString("Tm_Date"));
                resultboxresult(resultSet.getString("Tm_Result"));
                if (resultSet.getString("Tm_Match_Area").equals("Home")) {
                    home.setSelected(true);
                } else {
                    away.setSelected(true);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void resultboxresult(String result) {
        List res=results;
        System.out.println(result);
        for(int i=0;i<res.size();i++){
            if(res.get(i).equals(result)){
                index=i;
            }
        }
        resultbox.getSelectionModel().select(index);
    }



    private void initTables() {
        Tour_ID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        Tour_Match_Type.setCellValueFactory(new PropertyValueFactory<>("Match_Type"));
        Tour_Stadium.setCellValueFactory(new PropertyValueFactory<>("Stadium"));
        Tour_Match_Area.setCellValueFactory(new PropertyValueFactory<>("Match_Area"));
        Tour_Date.setCellValueFactory(new PropertyValueFactory<>("Date"));
        Tour_Opponent.setCellValueFactory(new PropertyValueFactory<>("Opponent"));
        Tour_Result.setCellValueFactory(new PropertyValueFactory<>("Result"));
        Tour_Match_Fee.setCellValueFactory(new PropertyValueFactory<>("Match_Fee"));
        Tour_Team_ID.setCellValueFactory(new PropertyValueFactory<>("Team_ID"));
    }

    @FXML
    void UserLogoutMethod(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("Login.fxml");
    }

    @FXML
    void PlayerMethod(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("Player.fxml");
    }

    @FXML
    void CoachMethod(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("Coach.fxml");
    }

    @FXML
    void TransferWindowMethod(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("TransferWindow.fxml");
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
    void DevelopedbyMethod(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("DevelopedBy.fxml");
    }
    public void GroundandGymMethod(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("GroundAndGym.fxml");
    }

    @FXML
    void search(ActionEvent event){
        if(searchcriteria.getValue().toString().equals("Date")){
            String date = date1.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String datee2= date2.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            String sql="SELECT * FROM Tournament WHERE Tm_Date BETWEEN '"+date+"' AND'"+datee2+"'";
            searchAndPrint(sql);
        }else{
            String sql= "SELECT * FROM Tournament WHERE Tm_"+searchcriteria.getValue().toString()+" LIKE '%"+searchkey.getText().toString()+"%'";
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
            System.out.println(searchsql);
            TOURNAMNETTABLE.getItems().clear();
            ResultSet resultSet = dbConnection.getStatement().executeQuery(searchsql);
            while (resultSet.next()) {
                TournamentTable.add(new TableData(resultSet.getInt("Tm_ID"),resultSet.getString("Tm_Match_Type"), resultSet.getString("Tm_Stadium"),resultSet.getString("Tm_Match_Area"),resultSet.getString("Tm_Date"),resultSet.getString("Tm_Opponent"),resultSet.getString("Tm_Result"),resultSet.getFloat("Tm_Match_Fee"),resultSet.getString("Team_ID")));
            }

            TOURNAMNETTABLE.setItems(TournamentTable);
            initTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @FXML
    void insertTour(ActionEvent event){
        try {
            //pReleaseDateBox.setChronology("");
            if(home.isSelected()){
                matchA=home.getText();
            }else{
                matchA=away.getText();
            }
            String insertsql = "INSERT INTO Tournament(Tm_Match_Type, Tm_Stadium, Tm_Match_Area, Tm_Date, Tm_Opponent, Tm_Result, Tm_Match_Fee,Team_ID)" +
                    "VALUES('" + matchType.getText() + "','" + stadium.getText() + "','" + matchA + "','" + da.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "','" + opponent.getText() + "','" + resultbox.getValue() + "'," + matchFee.getText() + ",(SELECT Team_ID FROM TEAM WHERE Team_Name = '" + teamId.getText().toString() +"'))";
            System.out.println(insertsql);
            int row = dbConnection.getStatement().executeUpdate(insertsql);
            clearAllField(new ActionEvent());
            refreshTable(new ActionEvent());
        } catch (SQLException e) {

            e.printStackTrace();
        }
    }
    @FXML
    void clearAllField(ActionEvent event){
        matchdate.setText("");
        matchFee.setText("");
        matchType.setText("");
        home.setSelected(false);
        away.setSelected(false);
        stadium.setText("");
        resultbox.setItems(results);
        opponent.setText("");
        matchdate.setText("");
        teamId.setText("");
    }
}

