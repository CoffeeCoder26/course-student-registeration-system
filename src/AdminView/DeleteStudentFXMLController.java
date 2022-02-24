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
import java.sql.PreparedStatement;
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
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class DeleteStudentFXMLController implements Initializable {

    Statement statement;
    ResultSet resultSet;
    PreparedStatement preparedStatement;

    @FXML
    private TextField DeleteTxtField;
    @FXML
    private Button DeleteButton;
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
    private TableColumn<Student, String> GPACol;
    @FXML
    private TableView<Student> StudentTabel;
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
        if (event.getSource() == DeleteButton) {
            if (DeleteTxtField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Enter Valid Id");
            } else {
                if (checkInput(DeleteTxtField.getText())) {
                    deleteStudent(Integer.parseInt(DeleteTxtField.getText()));
                    ShowStudentData();
                } else {
                    JOptionPane.showMessageDialog(null, "Enter Valid Id");
                }

            }

        }

        if (event.getSource() == BackButton) {
            Parent root = FXMLLoader.load(getClass().getResource("AdminSectionFXML.fxml"));
            Stage window = (Stage) BackButton.getScene().getWindow();
            window.setScene(new Scene(root));
        }
    }

    /**
     * first we will delete student , and just deleting student , his courses
     *
     * @param id
     */
    public void deleteStudent(int id) {

        try {
            String deleteCourseQuery = "delete from courses where student_db_id = '" + id + "'";
            statement = DatabaseConnection.getConnection().createStatement();
            statement.executeUpdate(deleteCourseQuery);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Student Hasn't Enrolled Courses yet");
        }

        /**
         * delete the Student itself search for student id in the db
         */
        try {
            String searchQuery = "select student_db_id from student where student_db_id = '" + id + "'";
            statement = DatabaseConnection.getConnection().createStatement();
            resultSet = statement.executeQuery(searchQuery);

            if (resultSet.next()) {
                JOptionPane.showMessageDialog(null, "Student Deleted.");
                String sqlQuery = "delete from student where student_db_id = '" + id + "'";
                statement = DatabaseConnection.getConnection().createStatement();
                statement.executeUpdate(sqlQuery);

            } else {
                JOptionPane.showMessageDialog(null, "Student With That Id Not Found, Please Enter a Vaild Id .....");
            }

        } catch (SQLException e) {

            JOptionPane.showMessageDialog(null, "Error In Delete Method .....");

        }
    }

    public ObservableList<Student> getStudentData() {

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
            JOptionPane.showMessageDialog(null, "Error In Method getStudentData");
        }

        return student;
    }

    /**
     * *
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
        GPACol.setCellValueFactory(new PropertyValueFactory<Student, String>("GPA"));

        StudentTabel.setItems(studentList);
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
