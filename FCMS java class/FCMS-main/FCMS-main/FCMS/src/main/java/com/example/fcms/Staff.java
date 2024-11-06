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
import java.util.ResourceBundle;

public class Staff implements Initializable {
    DBConnection dbConnection = new DBConnection();
    public ObservableList<String> searchlist = FXCollections.observableArrayList("Search", "ID", "Name", "Type", "Age", "Sex", "Medical_Condition", "Team");
    public ObservableList<String> searchrangelist = FXCollections.observableArrayList("ID", "Age", "Salary");

    DecimalFormat decimalFormat = new DecimalFormat("#.00");

    String sex;
    private ObservableList<TableData> tableData;

    @FXML
    private Button LogoutButton;

    @FXML
    private TextField sNameBox;

    @FXML
    private TextField sAgeBox;

    @FXML
    private TextField sMedConBox;

    @FXML
    private TableView<TableData> sTable;

    @FXML
    private TableColumn<TableData, Integer> sID;

    @FXML
    private TableColumn<TableData, String> sName;

    @FXML
    private TableColumn<TableData, String> sType;

    @FXML
    private TableColumn<TableData, Integer> sAge;

    @FXML
    private TableColumn<TableData, String> sSex;

    @FXML
    private TableColumn<TableData, String> sCon;

    @FXML
    private ComboBox<String> searchby;

    @FXML
    private TextField nominalsearchfiled;


    @FXML
    private TextField bisearchfield1;

    @FXML
    private TextField bisearchfield2;

    @FXML
    private Button refresh;

    @FXML
    private Text sIDBox;


    @FXML
    private TextField sSalaryBox;

    @FXML
    private RadioButton sMale;

    @FXML
    private RadioButton sFemale;

    @FXML
    private TextField sTypeBox;

    @FXML
    private ComboBox<String> searchrange;

    @FXML
    private Button searchbyquery;

    @FXML
    private Button searchrangequery;


    @FXML
    private TextField teamBox;

    @FXML
    private Button PlayersButton;

    @FXML
    private Button CoachButton;

    @FXML
    private Button TransferWindowButton;

    @FXML
    private Button StaffButton;

    @FXML
    private Label insertUpdateFieldCheck;

    @FXML
    private Label searchfieldbool;

    @FXML
    void deleteFromTable(ActionEvent event) {
        if (sIDBox.getText().toString().equals("")) {
            insertUpdateFieldCheck.setText("No data to be deleted");
        } else {
            try {
                String deleteSQL = "DELETE FROM Staff WHERE S_ID=" + sIDBox.getText().toString();
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
        if (sIDBox.getText().toString().equals("")) {
            if (sMale.isSelected()) {
                sex = "M";
            } else if (sFemale.isSelected()) {
                sex = "F";
            }
            try {
                //pReleaseDateBox.setChronology("");
                String insertsql = "INSERT INTO Staff(S_Name,S_Type,S_Age,S_Sex,S_Medical_Condition,S_Salary,S_Contract_Year,S_Release_Date,S_InLeave)" +
                        "VALUES('" + sNameBox.getText().toString() + "','" + sTypeBox.getText().toString() + "'," + sAgeBox.getText().toString() + ",'" + sex + "','" + sMedConBox.getText().toString() + "'," + sSalaryBox.getText().toString() + ",5,'2026-05-28',(SELECT Team_ID FROM TEAM WHERE Team_Name = '" + teamBox.getText().toString() + "'))";
                System.out.println(insertsql);
                int row = dbConnection.getStatement().executeUpdate(insertsql);
                refreshTable(new ActionEvent());
            } catch (SQLException e) {
                insertUpdateFieldCheck.setText("Some fields are not filled");
                e.printStackTrace();
            }

            clearAllField(new ActionEvent());


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
                String searchsql = "SELECT * FROM Staff WHERE S_ID LIKE '%" + q + "%' OR S_Name LIKE '%" + q + "%' OR S_Type LIKE '%" + q + "%' OR S_Age LIKE '%" + q + "%' OR S_Sex LIKE '%" + q + "%' OR S_Medical_Condition LIKE '%" + q + "%' OR S_Salary LIKE '%" + q + "%'";
                searchAndPrint(searchsql);
            } else {
                String searchsql = "SELECT * FROM Staff WHERE S_" + searchby.getValue() + " LIKE '%" + nominalsearchfiled.getText().toString() + "%'";
                System.out.println(searchsql);
                nominalsearchfiled.setText("");
                searchAndPrint(searchsql);
            }
        }
    }

    private void searchAndPrint(String searchsql) {
        try {
            sTable.getItems().clear();
            ResultSet resultSet = dbConnection.getStatement().executeQuery(searchsql);
            System.out.println(searchsql);
            while (resultSet.next()) {
                tableData.add(new TableData(resultSet.getInt("S_ID"), resultSet.getString("S_Name"), resultSet.getString("S_Type"), resultSet.getInt("S_Age"), resultSet.getString("S_Sex"), resultSet.getString("S_Medical_Condition")));
            }
            sTable.setItems(tableData);
            initTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void searchRangeQuery(ActionEvent event) {

    }

    @FXML
    void updateTable(ActionEvent event) {
        if (sIDBox.getText().toString().equals("")) {
            insertUpdateFieldCheck.setText("No data to be updated");
        } else {
            if (teamBox.getText().toString().equals("")) {
                insertUpdateFieldCheck.setText("Some fields are blank or need to be changed");
            } else {
                if (sMale.isSelected()) {
                    sex = "M";
                } else if (sFemale.isSelected()) {
                    sex = "F";
                }
                try {
                    String updatesql = "UPDATE  Player SET S_Name='" + sNameBox.getText().toString() + "',S_Type='" + sTypeBox.getText().toString() + "',S_Age=" + sAgeBox.getText().toString() + ",S_Sex='" + sex + "',S_Medical_Condition='" + sMedConBox.getText().toString() + "',S_Salary=" + sSalaryBox.getText().toString() + ",Team_ID=(SELECT Team_ID FROM TEAM WHERE Team_Name = '" + teamBox.getText().toString() + "') WHERE S_ID=" + sIDBox.getText().toString();
                    int r = dbConnection.getStatement().executeUpdate(updatesql);
                    System.out.println(updatesql);
                    insertUpdateFieldCheck.setText("Updated");
                    refreshTable(new ActionEvent());
                } catch (SQLException e) {
                    insertUpdateFieldCheck.setText("Some fields are blank or need to be changed");
                }
            }

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tableData = FXCollections.observableArrayList();
        searchby.setItems(searchlist);
        searchrange.setItems(searchrangelist);
        searchby.getSelectionModel().selectFirst();
        searchrange.getSelectionModel().selectFirst();
        loadTableData();
        initTables();
    }

    private void initTables() {
        sID.setCellValueFactory(new PropertyValueFactory<>("ID"));
        sName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        sType.setCellValueFactory(new PropertyValueFactory<>("Type"));
        sAge.setCellValueFactory(new PropertyValueFactory<>("Age"));
        sSex.setCellValueFactory(new PropertyValueFactory<>("Sex"));
        sCon.setCellValueFactory(new PropertyValueFactory<>("Condition"));
    }

    private void loadTableData() {
        try {
            sTable.getItems().clear();
            String sql = "SELECT * FROM Staff";
            ResultSet resultSet = dbConnection.getStatement().executeQuery(sql);
            while (resultSet.next()) {
                tableData.add(new TableData(resultSet.getInt("S_ID"), resultSet.getString("S_Name"), resultSet.getString("S_Type"), resultSet.getInt("S_Age"), resultSet.getString("S_Sex"), resultSet.getString("S_Medical_Condition")));
            }
            sTable.setItems(tableData);
            sTable.setOnMouseClicked(e -> {
                if (e.getClickCount() > 1) {
                    int index = sTable.getSelectionModel().getSelectedIndex();
                    System.out.println(index);
                    fillBox(index);
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

    public void GroundandGymMethod(ActionEvent event) throws IOException {
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


    private void fillBox(int index) {
        try {
            String sql = "SELECT * FROM Staff s JOIN TEAM t ON s.Team_ID = t.Team_ID AND s_ID=" + sTable.getColumns().get(0).getCellObservableValue(index).getValue();
            ResultSet resultSet = dbConnection.getStatement().executeQuery(sql);
            boolean i = true;
            while (resultSet.next()) {
                sNameBox.setText(resultSet.getString("S_Name"));
                sAgeBox.setText(String.valueOf(resultSet.getInt("S_Age")));
                if (resultSet.getString("S_SEX").equals("M")) {
                    sMale.setSelected(true);
                } else {
                    sFemale.setSelected(true);
                }
                sMedConBox.setText(resultSet.getString("S_Medical_Condition"));
                sIDBox.setText(String.valueOf(resultSet.getInt("S_ID")));
                sTypeBox.setText(resultSet.getString("S_Type"));
                sSalaryBox.setText(decimalFormat.format(resultSet.getDouble("S_Salary")));
                //pReleaseDateBox.setChronology(resultSet.getDate("P_Release_Date"));
                teamBox.setText(resultSet.getString("Team_Name"));
                i = false;
            }
            if (i) {
                String sql1 = "SELECT * FROM Staff WHERE s_ID=" + sTable.getColumns().get(0).getCellObservableValue(index).getValue();
                ResultSet resultSet1 = dbConnection.getStatement().executeQuery(sql1);
                while (resultSet1.next()) {
                    sNameBox.setText(resultSet1.getString("S_Name"));
                    sAgeBox.setText(String.valueOf(resultSet1.getInt("S_Age")));
                    if (resultSet1.getString("S_SEX").equals("M")) {
                        sMale.setSelected(true);
                    } else {
                        sFemale.setSelected(true);
                    }
                    sMedConBox.setText(resultSet1.getString("S_Medical_Condition"));
                    sIDBox.setText(String.valueOf(resultSet1.getInt("S_ID")));
                    sTypeBox.setText(resultSet1.getString("S_Type"));
                    sSalaryBox.setText(decimalFormat.format(resultSet1.getDouble("S_Salary")));
                    //pReleaseDateBox.setChronology(resultSet.getDate("P_Release_Date"));
                    teamBox.setText("");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void clearAllField(ActionEvent event) {
        sNameBox.setText("");
        sAgeBox.setText("");
        sMale.setSelected(false);
        sFemale.setSelected(false);
        sMedConBox.setText("");
        sIDBox.setText("");
        sSalaryBox.setText("");
        sTypeBox.setText("");
        //sReleaseDateBox.setChronology("");
        teamBox.setText("");
        insertUpdateFieldCheck.setText("");
        sex = "";
    }

}
