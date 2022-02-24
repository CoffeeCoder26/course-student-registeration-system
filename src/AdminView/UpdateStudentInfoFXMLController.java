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
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
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
public class UpdateStudentInfoFXMLController implements Initializable {

    Statement statement;
    ResultSet resultSet;
    PreparedStatement preparedStatement;

    @FXML
    private Button UpdateButton;
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
    private TextField GPAField;
    @FXML
    private TableView<Student> StudentTabel;
    @FXML
    private TextField idField;
    @FXML
    private TextField FullNameField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField PassFeild;
    @FXML
    private TextField ContactField;
    @FXML
    private Label Statuslabel;
    @FXML
    private ChoiceBox<String> LevelChkBox;

    private String[] levels = {"1", "2", "3", "4"};
    @FXML
    private Button BackButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LevelChkBox.getItems().addAll(levels);
        ShowStudentData();
    }

    @FXML
    private void handelButtonAction(ActionEvent event) throws IOException {
        if (event.getSource() == UpdateButton) {
            if (idField.getText().isEmpty() || FullNameField.getText().isEmpty() || emailField.getText().isEmpty() || PassFeild.getText().isEmpty() || ContactField.getText().isEmpty() || LevelChkBox.getValue().isEmpty() || GPAField.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Please Enter All Fields");
            } else {
                updateStudent();
                ShowStudentData();
                JOptionPane.showMessageDialog(null, "Updated Successfully");
            }
        }
        if (event.getSource() == BackButton) {
            Parent root = root = FXMLLoader.load(getClass().getResource("AdminSectionFXML.fxml"));
            Stage window = (Stage) BackButton.getScene().getWindow();
            window.setScene(new Scene(root));
        }
    }

    /**
     * method to get Student data from database
     *
     * @return
     */
    public ObservableList<Student> getStudentData() {
        //Hint
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
     * Show method used to show data in tabel
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

    /**
     * *
     * Update Student
     */
    public void updateStudent() {
        try {

            String sqlQuery = "update student set Student_FullName = ? , Student_Email = ? , Password = ? , Contact = ? , Level = ? ,GPA = ? Where student_db_id = ?";

            int id = Integer.parseInt(idField.getText());
            String name = FullNameField.getText();
            String email = emailField.getText();
            String pass = PassFeild.getText();
            String contact = ContactField.getText();
            int level = Integer.parseInt(LevelChkBox.getValue());
            String gpa = GPAField.getText();

            preparedStatement = DatabaseConnection.getConnection().prepareStatement(sqlQuery);

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, pass);
            preparedStatement.setString(4, contact);
            preparedStatement.setInt(5, level);
            preparedStatement.setString(6, gpa);
            preparedStatement.setInt(7, id);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error In Update Method .....");
            // System.out.println(e.toString());
        }

    }

}
