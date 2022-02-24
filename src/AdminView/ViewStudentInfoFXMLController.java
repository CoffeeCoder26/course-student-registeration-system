/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminView;

import Controller.DatabaseConnection;
import Model.Student;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ViewStudentInfoFXMLController implements Initializable {

    @FXML
    private TableColumn<Student, Integer> idCol;
    @FXML
    private TableColumn<Student, String> FullNameCol;
    @FXML
    private TableColumn<Student, String> EmailCol;
    @FXML
    private TableColumn<Student, String> PassCol;
    @FXML
    private TableColumn<Student, String> ContactCol;
    @FXML
    private TableColumn<Student, Integer> LevelCol;
    @FXML
    private TableColumn<Student, String> GPACOl;
    @FXML
    private TableView<Student> StudentTabel;
    @FXML
    private Button SearchButton;
    @FXML
    private AnchorPane StudentTable;
    @FXML
    private TextField SearchField;
    @FXML
    private Button BackButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ShowStudentData();
    }

    @FXML
    private void handelButtonAction(ActionEvent event) throws IOException {

        if (event.getSource() == SearchButton) {
            if (SearchField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Enter Student Id");
            } else {
                if (checkInput(SearchField.getText())) {
                    this.StudentTabel.setItems(searchStudent(Integer.parseInt(SearchField.getText())));
                } else {
                    JOptionPane.showMessageDialog(null, "Please Enter Vaild Id");
                }

            }
        }
        if (event.getSource() == BackButton) {
            Parent root = root = FXMLLoader.load(getClass().getResource("AdminSectionFXML.fxml"));
            Stage window = (Stage) BackButton.getScene().getWindow();
            window.setScene(new Scene(root));
        }
    }

    Statement statement;
    ResultSet resultSet;

    /**
     * Get all data about student from database
     *
     * @return
     */
    public ObservableList<Student> getStudentData() {

        // listview
        ObservableList<Student> student = FXCollections.observableArrayList();
        try {
            statement = DatabaseConnection.getConnection().createStatement();
            String sqlQuery = "select * from student";
            resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                Student studentdata = new Student();
                studentdata.setId(resultSet.getInt(1));
                studentdata.setFullName(resultSet.getString(2));
                studentdata.setEmail(resultSet.getString(3));
                studentdata.setPassword(resultSet.getString(4));
                studentdata.setPhoneNumber(resultSet.getString(5));
                studentdata.setLevel(resultSet.getInt(6));
                studentdata.setGPA(resultSet.getString(7));

                student.add(studentdata);

            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error In Method get Student Data");
        }

        return student;
    }

    /**
     * Show method used to show data in table
     */
    public void ShowStudentData() {

        ObservableList<Student> studentList = getStudentData();
        idCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("id"));
        FullNameCol.setCellValueFactory(new PropertyValueFactory<Student, String>("fullName"));
        EmailCol.setCellValueFactory(new PropertyValueFactory<Student, String>("email"));
        PassCol.setCellValueFactory(new PropertyValueFactory<Student, String>("password"));
        ContactCol.setCellValueFactory(new PropertyValueFactory<Student, String>("phoneNumber"));
        LevelCol.setCellValueFactory(new PropertyValueFactory<Student, Integer>("level"));
        GPACOl.setCellValueFactory(new PropertyValueFactory<Student, String>("GPA"));

        StudentTabel.setItems(studentList);
    }

    /**
     * Search For All Students
     *
     * @param id
     * @return
     */
    public ObservableList<Student> searchStudent(int id) {
        //Hint
        ObservableList<Student> students = FXCollections.observableArrayList();
        try {
            statement = DatabaseConnection.getConnection().createStatement();
            String sqlQuery = "select * from student where student_db_id =" + id;
            resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                JOptionPane.showMessageDialog(null, "Student Found.");
                Student s = new Student();
                s.setId(resultSet.getInt(1));
                s.setFullName(resultSet.getString(2));
                s.setEmail(resultSet.getString(3));
                s.setPassword(resultSet.getString(4));
                s.setPhoneNumber(resultSet.getString(5));
                s.setLevel(resultSet.getInt(6));
                s.setGPA(resultSet.getString(7));

                students.add(s);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Student Not Found.");
        }
        return students;

    }

    private boolean checkInput(String s) {
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) < '0' || s.charAt(i) > '9') {
                return false;
            }
        }

        return true;
    }

}
