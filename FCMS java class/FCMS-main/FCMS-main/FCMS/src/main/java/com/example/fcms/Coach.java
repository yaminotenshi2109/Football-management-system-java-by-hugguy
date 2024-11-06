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

public class Coach implements Initializable {
    DBConnection dbConnection = new DBConnection();
    public ObservableList<String> searchlist = FXCollections.observableArrayList("Search", "ID", "Name", "Formation", "Designation", "Age", "Sex", "Medical_Condition", "Team");
    public ObservableList<String> searchrangelist = FXCollections.observableArrayList("ID", "Age", "Salary", "Performance_Bonus");
    public ObservableList<String> formations=FXCollections.observableArrayList("","4-3-3","5-3-2","4-2-4","3-4-3","4-4-2");
    public ObservableList<String> designation=FXCollections.observableArrayList("","Head","Assistant","GK Coach","Fitness");

    DecimalFormat decimalFormat = new DecimalFormat("#.00");
    int id, index;
    String sex = "",cp;
    private ObservableList<TableData> tableData;

    @FXML
    private Button LogoutButton;

    @FXML
    private TextField cNameBox;

    @FXML
    private ComboBox<String> formationBox;

    @FXML
    private TextField cAgeBox;

    @FXML
    private TextField cMedConBox;

    @FXML
    private TableView<TableData> cTable;

    @FXML
    private TableColumn<TableData, Integer> cID;

    @FXML
    private TableColumn<TableData, String> cName;

    @FXML
    private TableColumn<TableData, String> formation;

    @FXML
    private TableColumn<TableData, String> cType;

    @FXML
    private TableColumn<TableData, Integer> cAge;

    @FXML
    private TableColumn<TableData, String> cSex;

    @FXML
    private TableColumn<TableData, String> cCon;

    @FXML
    private ComboBox<String> searchby;

    @FXML
    private TextField nominalsearchfiled;

    @FXML
    private TextField bisearchfield1;

    @FXML
    private TextField bisearchfield2;

    @FXML
    private Text cIDBox;

    @FXML
    private TextField cPerBonusBox;

    @FXML
    private TextField cSalaryBox;

    @FXML
    private RadioButton cMale;

    @FXML
    private ToggleGroup SexGrp;

    @FXML
    private RadioButton cFemale;

    @FXML
    private ComboBox<String> desigBox;

    @FXML
    private TextField cDescBox;

    @FXML
    private TextField teamBox;

    @FXML
    private Button searchbyquery;

    @FXML
    private Button searchbyquery1;

    @FXML
    private ComboBox<String> searchrange;

    @FXML
    private Label insertUpdateFieldCheck;

    @FXML
    private Label searchfieldbool;

    @FXML
    private Button PlayersButton;

    @FXML
    private Button CoachButton;

    @FXML
    private Button TransferWindowButton;

    @FXML
    private Button StaffButton;


    @FXML
    void clearAllField(ActionEvent event) {
        cNameBox.setText("");
        cAgeBox.setText("");
        cMale.setSelected(false);
        cFemale.setSelected(false);
        cMedConBox.setText("");
        cIDBox.setText("");
        cPerBonusBox.setText("");
        cSalaryBox.setText("");
        //pReleaseDateBox.setChronology("");
        teamBox.setText("");
        cDescBox.setText("");
        insertUpdateFieldCheck.setText("");
        formationBox.getSelectionModel().selectFirst();
        desigBox.getSelectionModel().selectFirst();
        sex = "";
    }

    @FXML
    void deleteFromTable(ActionEvent event) {
        if (cIDBox.getText().toString().equals("")) {
            insertUpdateFieldCheck.setText("No data to be deleted");
        } else {
            try {
                String deleteSQL = "DELETE FROM Coach WHERE C_ID=" + cIDBox.getText().toString();
                System.out.println(deleteSQL);
                int r = dbConnection.getStatement().executeUpdate(deleteSQL);
                refreshTable(new ActionEvent());
                clearAllField(new ActionEvent());
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

    @FXML
    void insertIntoTable(ActionEvent event) {
        if (cIDBox.getText().toString().equals("")) {
            cPerBonusBox.getText().toString();
            if (cMale.isSelected()) {
                sex = "M";
            } else if (cFemale.isSelected()) {
                sex = "F";
            }
            try {
                //pReleaseDateBox.setChronology("");
                String teamSQL = "SELECT * FROM TEAM WHERE Team_Name = '" + teamBox.getText().toString() + "'";
                ResultSet resultSet = dbConnection.getStatement().executeQuery(teamSQL);
                if (resultSet.next() && !formationBox.getValue().equals("")) {
                    id = resultSet.getInt("Team_ID");
                    if(cPerBonusBox.getText().toString().equals("")){
                        cp="0";
                    }else{
                        cp=cPerBonusBox.getText().toString();
                    }
                    String insertsql = "INSERT INTO Coach(C_Name,C_Formation, C_Designation,C_Age,C_Sex,C_Medical_Condition,C_Salary,C_Performance_Bonus,C_Description, Team_ID)" +
                            "VALUES('" + cNameBox.getText().toString() + "','" + formationBox.getValue().toString() + "','" + desigBox.getValue().toString() + "'," + cAgeBox.getText().toString() + ",'" + sex + "','" + cMedConBox.getText().toString() + "'," + cSalaryBox.getText().toString() + ",5" + cp + ",'" + cDescBox.getText().toString() + "'," + id + ")";
                    System.out.println(insertsql);
                    int row = dbConnection.getStatement().executeUpdate(insertsql);
                    refreshTable(new ActionEvent());
                } else {
                    insertUpdateFieldCheck.setText("Not filled");
                }
                clearAllField(new ActionEvent());

            } catch (SQLException e) {
                insertUpdateFieldCheck.setText("Not filled");
                e.printStackTrace();
            }
        } else {
            insertUpdateFieldCheck.setText("Data Already Present");
        }
    }

    @FXML
    void refreshTable(ActionEvent event) {
        loadTableData();
        initTables();

    }

    @FXML
    void searchByQuery(ActionEvent event) {
        if (nominalsearchfiled.getText().toString().equals("")) {
            searchfieldbool.setText("Search Field is Empty");
        } else {
            searchfieldbool.setText("");
            if (searchby.getValue().equals("Search")) {
                String q = nominalsearchfiled.getText().toString();
                String searchsql = "SELECT * FROM Coach WHERE C_ID LIKE '%" + q + "%' OR C_Name LIKE '%" + q + "%' OR C_Formation LIKE '%" + q + "%' OR C_Designation LIKE '%" + q + "%' OR C_Age LIKE '%" + q + "%' OR C_Sex LIKE '%" + q + "%' OR C_Medical_Condition LIKE '%" + q + "%' OR C_Salary LIKE '%" + q + "%'";

                searchAndPrint(searchsql);
            } else {
                String searchsql = "SELECT * FROM Coach WHERE C_" + searchby.getValue() + " LIKE '%" + nominalsearchfiled.getText().toString() + "%'";
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
            String searchsql = "SELECT * FROM Coach WHERE C_" + searchrange.getValue() + " BETWEEN " + bisearchfield1.getText().toString() + " AND " + bisearchfield2.getText().toString();
            bisearchfield1.setText("");
            bisearchfield2.setText("");
            searchAndPrint(searchsql);
        }
    }

    private void searchAndPrint(String searchsql) {
        try {
            cTable.getItems().clear();
            ResultSet resultSet = dbConnection.getStatement().executeQuery(searchsql);
            while (resultSet.next()) {
                tableData.add(new TableData(resultSet.getInt("C_ID"), resultSet.getString("C_Name"), resultSet.getString("C_Formation"), resultSet.getString("C_Designation"), resultSet.getInt("C_Age"), resultSet.getString("C_Sex"), resultSet.getString("C_Medical_Condition")));
            }
            cTable.setItems(tableData);
            initTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    @FXML
    void updateTable(ActionEvent event) {
        if (cIDBox.getText().toString().equals("")) {
            insertUpdateFieldCheck.setText("No data to be updated");
        } else {
            if (teamBox.getText().toString().equals("")) {
                insertUpdateFieldCheck.setText("Some fields are blank or need to be changed");
            } else {
                if (cMale.isSelected()) {
                    sex = "M";
                } else if (cFemale.isSelected()) {
                    sex = "F";
                }
                try {
                    String teamSQL = "SELECT * FROM TEAM WHERE Team_Name = '" + teamBox.getText().toString() + "'";
                    ResultSet resultSet = dbConnection.getStatement().executeQuery(teamSQL);
                    if (resultSet.next()) {//|| primarypos.getValue().equals("")
                        id = resultSet.getInt("Team_ID");
                        String updatesql = "UPDATE Coach SET C_Name='" + cNameBox.getText().toString() + "',C_Formation='" + formationBox.getValue() + "', C_Designation='" + desigBox.getValue() + "',C_Age=" + Integer.parseInt(cAgeBox.getText().toString()) + ",C_Sex='" + sex + "',C_Medical_Condition='" + cMedConBox.getText().toString() + "',C_Salary=" + cSalaryBox.getText().toString() + ",C_Contract_Year=4,C_Description='" + cDescBox.getText().toString() + "',Team_ID=" + id + "WHERE C_ID=" + cIDBox.getText().toString();
                        System.out.println(updatesql);
                        int r = dbConnection.getStatement().executeUpdate(updatesql);
                        insertUpdateFieldCheck.setText("Updated");
                        refreshTable(new ActionEvent());

                    } else {
                        insertUpdateFieldCheck.setText("Some fields are blank or need to be changed");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    insertUpdateFieldCheck.setText("Some fields are blank or need to be changed");
                }
            }
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableData = FXCollections.observableArrayList();
        searchby.setItems(searchlist);
        searchrange.setItems(searchrangelist);
        formationBox.setItems(formations);
        desigBox.setItems(designation);
        searchby.getSelectionModel().selectFirst();
        searchrange.getSelectionModel().selectFirst();
        desigBox.getSelectionModel().selectFirst();
        formationBox.getSelectionModel().selectFirst();
        loadTableData();
        initTables();
    }

    private void initTables() {
        cID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        cName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        formation.setCellValueFactory(new PropertyValueFactory<>("Formation"));
        cType.setCellValueFactory(new PropertyValueFactory<>("Designation"));
        cAge.setCellValueFactory(new PropertyValueFactory<>("Age"));
        cSex.setCellValueFactory(new PropertyValueFactory<>("Sex"));
        cCon.setCellValueFactory(new PropertyValueFactory<>("Condition"));
        TableData tableData = new TableData();
    }

    private void loadTableData() {
        try {
            cTable.getItems().clear();
            String sql = "SELECT * FROM Coach";
            ResultSet resultSet = dbConnection.getStatement().executeQuery(sql);
            while (resultSet.next()) {
                tableData.add(new TableData(resultSet.getInt("C_ID"), resultSet.getString("C_Name"), resultSet.getString("C_Formation"), resultSet.getString("C_Designation"), resultSet.getInt("C_Age"), resultSet.getString("C_Sex"), resultSet.getString("C_Medical_Condition")));
                System.out.println(resultSet.getString("C_Formation") + "," + resultSet.getString("C_Designation"));
            }
            cTable.setItems(tableData);
            cTable.setOnMouseClicked(e -> {
                if (e.getClickCount() > 1) {
                    int index = cTable.getSelectionModel().getSelectedIndex();
                    fillBox(index);
                }
            });

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void fillBox(int index) {
        try {
            String sql = "SELECT * FROM Coach c JOIN TEAM t ON c.Team_ID = t.Team_ID AND C_ID=" + cTable.getColumns().get(0).getCellObservableValue(index).getValue();
            ResultSet resultSet = dbConnection.getStatement().executeQuery(sql);
            while (resultSet.next()) {
                cNameBox.setText(resultSet.getString("C_Name"));
                cAgeBox.setText(String.valueOf(resultSet.getInt("C_Age")));
                cFormation(resultSet.getString("C_Formation"));
                cdesig(resultSet.getString("C_Designation"));
                if (resultSet.getString("C_Sex").equals("M")) {
                    cMale.setSelected(true);
                } else {
                    cFemale.setSelected(true);
                }
                cMedConBox.setText(resultSet.getString("C_Medical_Condition"));
                cIDBox.setText(String.valueOf(resultSet.getInt("C_ID")));
                if (String.valueOf(resultSet.getDouble("C_Performance_Bonus")).equals("0.0")) {
                    cPerBonusBox.setText("0.0");
                } else {
                    cPerBonusBox.setText(decimalFormat.format(resultSet.getDouble("C_Performance_Bonus")));
                }
                cSalaryBox.setText(decimalFormat.format(resultSet.getDouble("C_Salary")));
                cDescBox.setText(resultSet.getString("C_Description"));
                //pReleaseDateBox.setChronology(resultSet.getDate("P_Release_Date"));
                teamBox.setText(resultSet.getString("Team_Name"));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void cdesig(String c_designation) {
        List desi=designation;
        for(int i=0;i<desi.size();i++){
            if (desi.get(i).equals(c_designation)){
                index=i;
            }
        }
        desigBox.getSelectionModel().select(index);
    }

    private void cFormation(String c_formation) {
        List form=formations;
        for(int i=0;i<form.size();i++){
            if(form.get(i).equals(c_formation)){
                index=i;
            }
        }
        formationBox.getSelectionModel().select(index);
    }


}
