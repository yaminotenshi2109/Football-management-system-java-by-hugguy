package com.example.fcms;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

public class Player implements Initializable {

    DBConnection dbConnection = new DBConnection();
    public ObservableList<String> searchlist = FXCollections.observableArrayList("Search", "ID", "Name", "Pos", "Age", "Sex", "Nation", "Medical_Condition", "Team");
    public ObservableList<String> searchrangelist = FXCollections.observableArrayList("ID", "Age", "Weight", "Height", "Rating", "Salary", "Performance_Bonus");
    public ObservableList<String> primaryposition = FXCollections.observableArrayList("", "FORWARD", "MID", "DEFENCE", "GOALKEEPER");
    public ObservableList<String> forwordpositionfocus = FXCollections.observableArrayList("RWF", "LWF", "CF");
    public ObservableList<String> midpositionfocus = FXCollections.observableArrayList("LCM", "RCM", "CM", "CDM");
    public ObservableList<String> defpositionfocus = FXCollections.observableArrayList("LB", "RB", "LCB", "RCB");
    public ObservableList<String> gkpositionfocus = FXCollections.observableArrayList("GK");
    DecimalFormat decimalFormat = new DecimalFormat("#.00");
    int id;
    String sex = "";

    private ObservableList<TableData> tableData;


    @FXML
    private TableView<TableData> pTable;

    @FXML
    private TableColumn<TableData, Integer> pID;

    @FXML
    private TableColumn<TableData, String> pName;

    @FXML
    private TableColumn<TableData, String> pPos;

    @FXML
    private TableColumn<TableData, Integer> pAge;

    @FXML
    private TableColumn<TableData, String> pSex;

    @FXML
    private TableColumn<TableData, Integer> pW;

    @FXML
    private TableColumn<TableData, Integer> pH;

    @FXML
    private TableColumn<TableData, String> pNation;

    @FXML
    private TableColumn<TableData, Double> pRating;

    @FXML
    private TableColumn<TableData, String> pCon;

    @FXML
    private Button refresh;

    @FXML
    private TextField pNameBox;

    @FXML
    private TextField pAgeBox;

    @FXML
    private TextField pWBox;

    @FXML
    private TextField pHBox;

    @FXML
    private TextField pNBox;

    @FXML
    private TextField pRatingBox;

    @FXML
    private TextField pMedConBox;

    @FXML
    private Text pIDBox;

    @FXML
    private TextField teamBox;

    @FXML
    private TextField pPerBonusBox;

    @FXML
    private TextField pSalaryBox;

    @FXML
    private RadioButton pMale;

    @FXML
    private ToggleGroup SexGrp;

    @FXML
    private TextField pDescBox;

    @FXML
    private RadioButton pFemale;

    @FXML
    private ComboBox<String> searchby;

    @FXML
    private ComboBox<String> primarypos;

    @FXML
    private ComboBox<String> posfocus;

    @FXML
    private TextField nominalsearchfiled;

    @FXML
    private TextField bisearchfield1;

    @FXML
    private TextField bisearchfield2;

    @FXML
    private Label insertUpdateFieldCheck;


    @FXML
    private ComboBox<String> searchrange;

    @FXML
    private Label searchfieldbool;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableData = FXCollections.observableArrayList();
        searchby.setItems(searchlist);
        searchrange.setItems(searchrangelist);
        searchby.getSelectionModel().selectFirst();
        searchrange.getSelectionModel().selectFirst();
        primarypos.setItems(primaryposition);
        primarypos.getSelectionModel().selectFirst();
        positionFocusSet(new ActionEvent());
        loadTableData();
        initTables();
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
    void GroundandGymMethod(ActionEvent event) throws IOException {
        Main m = new Main();
        m.changeScene("GroundAndGym.fxml");
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

    @FXML
    void positionFocusSet(ActionEvent event) {
        if (primarypos.getValue().toString().equals("FORWARD")) {
            posfocus.setItems(forwordpositionfocus);
        } else if (primarypos.getValue().toString().equals("MID")) {
            posfocus.setItems(midpositionfocus);
        } else if (primarypos.getValue().toString().equals("DEFENCE")) {
            posfocus.setItems(defpositionfocus);
        } else if (primarypos.getValue().toString().equals("GOALKEEPER")) {
            posfocus.setItems(gkpositionfocus);
        } else {
            posfocus.setItems(null);
        }
        posfocus.getSelectionModel().selectFirst();
    }

    @FXML
    void clearAllField(ActionEvent event) {
        pNameBox.setText("");
        pAgeBox.setText("");
        pWBox.setText("");
        pHBox.setText("");
        pMale.setSelected(false);
        pFemale.setSelected(false);
        pNBox.setText("");
        pRatingBox.setText("");
        pMedConBox.setText("");
        pIDBox.setText("");
        pPerBonusBox.setText("");
        pSalaryBox.setText("");
        //pReleaseDateBox.setChronology("");
        teamBox.setText("");
        pDescBox.setText("");
        insertUpdateFieldCheck.setText("");
        primarypos.setItems(primaryposition);
        primarypos.getSelectionModel().selectFirst();
        positionFocusSet(new ActionEvent());
        sex = "";
    }

    @FXML
    void searchByQuery(ActionEvent event) {
        if (nominalsearchfiled.getText().toString().equals("")) {
            searchfieldbool.setText("Search Field is Empty");
        } else {
            searchfieldbool.setText("");
            if (searchby.getValue().equals("Search")) {
                String q = nominalsearchfiled.getText().toString();
                String searchsql = "SELECT * FROM Player WHERE P_ID LIKE '%" + q + "%' OR P_Name LIKE '%" + q + "%' OR P_Pos LIKE '%" + q + "%' OR P_Age LIKE '%" + q + "%' OR P_Sex LIKE '%" + q + "%' OR P_Weight LIKE '%" + q + "%' OR P_Height LIKE '%" + q + "%' OR P_Nation LIKE '%" + q + "%' OR P_Rating LIKE '%" + q + "%' OR P_Medical_Condition LIKE '%" + q + "%' OR P_Salary LIKE '%" + q + "%'";
                searchAndPrint(searchsql);
            } else {
                String searchsql = "SELECT * FROM Player WHERE P_" + searchby.getValue() + " LIKE '%" + nominalsearchfiled.getText().toString() + "%'";
                nominalsearchfiled.setText("");
                searchAndPrint(searchsql);
            }
        }
    }

    @FXML
    void searchRangeQuery(ActionEvent event) {
        if (bisearchfield1.getText().toString().equals("") || bisearchfield2.getText().toString().equals("")) {
            searchfieldbool.setText("Search Range is not given");
        } else {
            searchfieldbool.setText("");
            String searchsql = "SELECT * FROM Player WHERE P_" + searchrange.getValue() + " BETWEEN " + bisearchfield1.getText().toString() + " AND " + bisearchfield2.getText().toString();
            bisearchfield1.setText("");
            bisearchfield2.setText("");
            searchAndPrint(searchsql);
        }

    }

    @FXML
    void refreshTable(ActionEvent event) {
        loadTableData();
        initTables();
    }
    @FXML
    void sellPlayer(ActionEvent event) throws IOException {
        if(pIDBox.getText().equals("")){
            SellWindow sellWindow=new SellWindow();
            sellWindow.setItems(pIDBox.getText(),pNameBox.getText(),pAgeBox.getText(),pNBox.getText(),posfocus.getValue());
            Main m=new Main();
            m.changeScene("SellWindow.fxml");
        }

    }
    @FXML
    void insertIntoTable(ActionEvent event) {
        //        pMale.setSelected(false);
//        pFemale.setSelected(false);
        if (pIDBox.getText().toString().equals("")) {
            pPerBonusBox.getText().toString();
            if (pMale.isSelected()) {
                sex = "M";
            } else if (pFemale.isSelected()) {
                sex = "F";
            }

                if (!primarypos.getValue().equals("")) {
                    try {
                        //pReleaseDateBox.setChronology("");
                    	//P_Contract_Year
                        String insertsql = "INSERT INTO Player(P_Name,P_Pos,P_Age,P_Sex,P_Weight,P_Height,P_Nation,P_Rating,P_Medical_Condition,P_Salary,P_Performance_Bonus,P_Description,Team_ID)" +
                            "VALUES('" + pNameBox.getText().toString() + "','" + posfocus.getValue() + "'," + pAgeBox.getText().toString() + ",'" + sex + "'," + pWBox.getText().toString() + "," + pHBox.getText().toString() + ",'" + pNBox.getText().toString() + "'," + pRatingBox.getText().toString() + ",'" + pMedConBox.getText().toString() + "'," + pSalaryBox.getText().toString() + ",5,'" + pDescBox.getText().toString() + "',(SELECT Team_ID FROM TEAM WHERE Team_Name = '" + teamBox.getText().toString() +"'))";
                    System.out.println(insertsql);
                    int row = dbConnection.getStatement().executeUpdate(insertsql);
                    refreshTable(new ActionEvent());
                    } catch (SQLException e) {
                        insertUpdateFieldCheck.setText("Some fields are not filled");
                        e.printStackTrace();
                    }
                } else {
                    insertUpdateFieldCheck.setText("Some fields are not filled");
                }
                clearAllField(new ActionEvent());


        } else {
            insertUpdateFieldCheck.setText("Data Already Present");
        }


    }

    @FXML
    void updateTable(ActionEvent event) {
        if (pIDBox.getText().toString().equals("")) {
            insertUpdateFieldCheck.setText("No data to be updated");
        } else {
            if (teamBox.getText().toString().equals("")) {
                insertUpdateFieldCheck.setText("Some fields are blank or need to be changed");
            } else {
                if (pMale.isSelected()) {
                    sex = "M";
                } else if (pFemale.isSelected()) {
                    sex = "F";
                }
                if (!primarypos.getValue().equals("")) {
                        try {
                        String updatesql = "UPDATE  Player SET P_Name='" + pNameBox.getText().toString() + "',P_Pos='" + posfocus.getValue() + "',P_Age=" + pAgeBox.getText().toString() + ",P_Sex='" + sex + "',P_Weight=" + pWBox.getText().toString() + ",P_Height=" + pHBox.getText().toString() + ",P_Nation='" + pNBox.getText().toString() + "',P_Rating=" + pRatingBox.getText().toString() + ",P_Medical_Condition='" + pMedConBox.getText().toString() + "',P_Salary=" + pSalaryBox.getText().toString() + ",P_Contract_Year=4,P_Description='" + pDescBox.getText().toString() + "',Team_ID=(SELECT Team_ID FROM TEAM WHERE Team_Name = '" + teamBox.getText().toString() + "') WHERE P_ID=" + pIDBox.getText().toString();
                        int r = dbConnection.getStatement().executeUpdate(updatesql);
                        System.out.println(updatesql);
                        insertUpdateFieldCheck.setText("Updated");
                        refreshTable(new ActionEvent());
                        } catch (SQLException e) {
                            insertUpdateFieldCheck.setText("Some fields are blank or need to be changed");
                        }

                    } else {
                        insertUpdateFieldCheck.setText("Some fields are blank or need to be changed");
                }
            }

        }

    }

    @FXML
    void deleteFromTable(ActionEvent event) {
        if (pIDBox.getText().toString().equals("")) {
            insertUpdateFieldCheck.setText("No data to be deleted");
        } else {
            try {
                String deleteSQL = "DELETE FROM Player WHERE P_ID=" + pIDBox.getText().toString();
                int r = dbConnection.getStatement().executeUpdate(deleteSQL);
                refreshTable(new ActionEvent());
                clearAllField(new ActionEvent());
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    private void searchAndPrint(String searchsql) {
        try {
            pTable.getItems().clear();
            ResultSet resultSet = dbConnection.getStatement().executeQuery(searchsql);
            while (resultSet.next()) {
                tableData.add(new TableData(resultSet.getInt("P_ID"), resultSet.getString("P_Name"), resultSet.getString("P_Pos"), resultSet.getInt("P_Age"), resultSet.getString("P_SEX"), resultSet.getInt("P_Weight"), resultSet.getInt("P_Height"), resultSet.getString("P_Nation"), resultSet.getDouble("P_Rating"), resultSet.getString("P_Medical_Condition")));
            }
            pTable.setItems(tableData);
            initTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void initTables() {
        pID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        pName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        pPos.setCellValueFactory(new PropertyValueFactory<>("Position"));
        pAge.setCellValueFactory(new PropertyValueFactory<>("Age"));
        pSex.setCellValueFactory(new PropertyValueFactory<>("Sex"));
        pW.setCellValueFactory(new PropertyValueFactory<>("Weight"));
        pH.setCellValueFactory(new PropertyValueFactory<>("Height"));
        pNation.setCellValueFactory(new PropertyValueFactory<>("Nation"));
        pRating.setCellValueFactory(new PropertyValueFactory<>("Rating"));
        pCon.setCellValueFactory(new PropertyValueFactory<>("Condition"));
    }

    private void loadTableData() {
        try {
            pTable.getItems().clear();
            String sql = "SELECT * FROM Player";
            ResultSet resultSet = dbConnection.getStatement().executeQuery(sql);
            while (resultSet.next()) {
                tableData.add(new TableData(resultSet.getInt("P_ID"), resultSet.getString("P_Name"), resultSet.getString("P_Pos"), resultSet.getInt("P_Age"), resultSet.getString("P_SEX"), resultSet.getInt("P_Weight"), resultSet.getInt("P_Height"), resultSet.getString("P_Nation"), resultSet.getDouble("P_Rating"), resultSet.getString("P_Medical_Condition")));
            }
            pTable.setItems(tableData);
            pTable.setOnMouseClicked(e -> {
                if (e.getClickCount() > 1) {
                    int index = pTable.getSelectionModel().getSelectedIndex();
                    fillBox(index);
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fillBox(int index) {
        try {
            String sql = "SELECT * FROM Player p JOIN TEAM t ON p.Team_ID = t.Team_ID AND P_ID=" + pTable.getColumns().get(0).getCellObservableValue(index).getValue();
            ResultSet resultSet = dbConnection.getStatement().executeQuery(sql);
            while (resultSet.next()) {
                pNameBox.setText(resultSet.getString("P_Name"));
                pAgeBox.setText(String.valueOf(resultSet.getInt("P_Age")));
                pWBox.setText(String.valueOf(resultSet.getInt("P_Weight")));
                pHBox.setText(String.valueOf(resultSet.getInt("P_Height")));
                if (resultSet.getString("P_SEX").equals("M")) {
                    pMale.setSelected(true);
                } else {
                    pFemale.setSelected(true);
                }
                pNBox.setText(resultSet.getString("P_Nation"));
                pRatingBox.setText(decimalFormat.format(resultSet.getDouble("P_Rating")));
                pMedConBox.setText(resultSet.getString("P_Medical_Condition"));
                pIDBox.setText(String.valueOf(resultSet.getInt("P_ID")));
                if (String.valueOf(resultSet.getDouble("P_Performance_Bonus")).equals("0.0")) {
                    pPerBonusBox.setText("0.0");
                } else {
                    pPerBonusBox.setText(decimalFormat.format(resultSet.getDouble("P_Performance_Bonus")));
                }
                pSalaryBox.setText(decimalFormat.format(resultSet.getDouble("P_Salary")));
                pDescBox.setText(resultSet.getString("P_Description"));
                //pReleaseDateBox.setChronology(resultSet.getDate("P_Release_Date"));
                teamBox.setText(resultSet.getString("Team_Name"));

                pPosition(resultSet.getString("P_Pos"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void pPosition(String position) {
        List posg = gkpositionfocus;
        List posf = forwordpositionfocus;
        List posm = midpositionfocus;
        List posd = defpositionfocus;
        if (posg.get(0).equals(position)) {
            primarypos.getSelectionModel().select(4);
        } else if (posf.get(0).equals(position) || posf.get(1).equals(position) || posf.get(2).equals(position)) {
            primarypos.getSelectionModel().select(1);
            posfocus.setItems(forwordpositionfocus);
            if (posf.get(0).equals(position)) {
                posfocus.getSelectionModel().select(0);
            } else if (posf.get(1).equals(position)) {
                posfocus.getSelectionModel().select(1);
            } else if (posf.get(2).equals(position)) {
                posfocus.getSelectionModel().select(2);
            }
        } else if (posm.get(0).equals(position) || posm.get(1).equals(position) || posm.get(2).equals(position) || posm.get(3).equals(position)) {
            primarypos.getSelectionModel().select(2);
            posfocus.setItems(midpositionfocus);
            if (posm.get(0).equals(position)) {
                posfocus.getSelectionModel().select(0);
            } else if (posm.get(1).equals(position)) {
                posfocus.getSelectionModel().select(1);
            } else if (posm.get(2).equals(position)) {
                posfocus.getSelectionModel().select(2);
            } else if (posm.get(3).equals(position)) {
                posfocus.getSelectionModel().select(3);
            }
        } else if (posd.get(0).equals(position) || posd.get(1).equals(position) || posd.get(2).equals(position) || posd.get(3).equals(position)) {
            primarypos.getSelectionModel().select(3);
            posfocus.setItems(defpositionfocus);
            if (posd.get(0).equals(position)) {
                posfocus.getSelectionModel().select(0);
            } else if (posd.get(1).equals(position)) {
                posfocus.getSelectionModel().select(1);
            } else if (posd.get(2).equals(position)) {
                posfocus.getSelectionModel().select(2);
            } else if (posd.get(3).equals(position)) {
                posfocus.getSelectionModel().select(3);
            }
        }
    }
}
