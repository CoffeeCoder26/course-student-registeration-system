/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudentView;

import Controller.DatabaseConnection;
import Model.Student;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class CoursesFXMLController implements Initializable {

    Statement statement;
    ResultSet resultSet;

    Student s = new Student();

    @FXML
    private ComboBox<String> course1;
    @FXML
    private ComboBox<String> course2;
    @FXML
    private ComboBox<String> course3;
    @FXML
    private ComboBox<String> course4;
    @FXML
    private ComboBox<String> course5;
    @FXML
    private ComboBox<String> course6;
    @FXML
    private Label EnrollLabel;
    @FXML
    private Button EnrollButton;
    @FXML
    private Button BackButton;
    @FXML
    private ComboBox<String> LevelComBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            // TODO
            checkLevel();
        } catch (SQLException ex) {
            Logger.getLogger(CoursesFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            ObservableList<String> Levels = FXCollections.observableArrayList("1", "2", "3", "4");
            LevelComBox.setItems(Levels);

            checkGPA();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.toString());

        }

    }

    @FXML
    private void handelButtonAction(ActionEvent event) throws IOException, SQLException {
        checkLevel();
        if (event.getSource() == BackButton) {

            Parent root = FXMLLoader.load(getClass().getResource("/StudentView/StudentSectionFXML.fxml"));
            Stage window = (Stage) BackButton.getScene().getWindow();
            window.setScene(new Scene(root));

        }
    }

    @FXML
    private void enrollButtonAction(ActionEvent event) throws SQLException, IOException {

        if (event.getSource() == EnrollButton) {
            enrollCourses();
            JOptionPane.showMessageDialog(null, "Congrats..You Have Enrolled.");

            Parent root = FXMLLoader.load(getClass().getResource("/StudentView/StudentSectionFXML.fxml"));
            Stage window = (Stage) EnrollButton.getScene().getWindow();
            window.setScene(new Scene(root));
        }
    }

    public void enrollCourses() {

        try {
            statement = DatabaseConnection.getConnection().createStatement();

            String mySqlQuery = "insert into Courses (Course_Id ,student_db_id, Course1 , Course2, Course3 , Course4, Course5,Course6,Course_Hours) values ('" + "CS50" + "' ,'" + Student.getSt_db_id() + "','" + course1.getValue() + "' ,'" + course2.getValue() + "','" + course3.getValue() + "','" + course4.getValue() + "','" + course5.getValue() + "','" + course6.getValue() + "','" + 3 + "')";
            statement.executeUpdate(mySqlQuery);

        } catch (SQLException ex) {
            System.out.println(ex.toString());
        }

    }

    public void checkLevel() throws SQLException {
        if (LevelComBox.getSelectionModel().getSelectedItem() == "1") {
            ObservableList<String> coursesNameLevel1 = FXCollections.observableArrayList("Intro To CS", "Math 1", "Discrete Mathematic", "Human Rights", "Programming 1", "Techinical Writing");
            course1.setItems(coursesNameLevel1);
            course2.setItems(coursesNameLevel1);
            course3.setItems(coursesNameLevel1);
            course4.setItems(coursesNameLevel1);
            course5.setItems(coursesNameLevel1);
            course6.setItems(coursesNameLevel1);

        }

        if (LevelComBox.getSelectionModel().getSelectedItem() == "2") {

            ObservableList<String> coursesNameLevel2 = FXCollections.observableArrayList("Network", "Web", "Data Structure, Algorithm", "CO", "Java", "Multimedia");
            course1.setItems(coursesNameLevel2);
            course2.setItems(coursesNameLevel2);
            course3.setItems(coursesNameLevel2);
            course4.setItems(coursesNameLevel2);
            course5.setItems(coursesNameLevel2);
            course6.setItems(coursesNameLevel2);
        }
        if (LevelComBox.getSelectionModel().getSelectedItem() == "3") {

            ObservableList<String> coursesNameLevel3 = FXCollections.observableArrayList("OS-1", "OR", "Assembly", "Statistics", "Java Swing", "Systems Analysis");

            course1.setItems(coursesNameLevel3);
            course2.setItems(coursesNameLevel3);
            course3.setItems(coursesNameLevel3);
            course4.setItems(coursesNameLevel3);
            course5.setItems(coursesNameLevel3);
            course6.setItems(coursesNameLevel3);

        }

        if (LevelComBox.getSelectionModel().getSelectedItem() == "4") {

            ObservableList<String> coursesNameLevel4 = FXCollections.observableArrayList("Ai", "OS-2", "SWE", "Cloud Computing", "JavaFx", "Database");

            course1.setItems(coursesNameLevel4);
            course2.setItems(coursesNameLevel4);
            course3.setItems(coursesNameLevel4);
            course4.setItems(coursesNameLevel4);
            course5.setItems(coursesNameLevel4);
            course6.setItems(coursesNameLevel4);

        }
    }

    /**
     * Method CheckGPA is used For Student that how many Courses he will Enroll
     *
     * @throws SQLException
     */
    public void checkGPA() throws SQLException {

        statement = DatabaseConnection.getConnection().createStatement();
        String sqlQuery = "select GPA from student where student_db_id = '" + Student.getSt_db_id() + "'";
        resultSet = statement.executeQuery(sqlQuery);

        if (resultSet.next()) {
            double studentGPA = resultSet.getBigDecimal(1).doubleValue();

            if (studentGPA >= 2.0 && studentGPA <= 4.0) {
                course1.setDisable(false);
                course2.setDisable(false);
                course3.setDisable(false);
                course4.setDisable(false);
                course5.setDisable(false);
                course6.setDisable(false);
            } else {
                course5.setDisable(true);
                course6.setDisable(true);
            }

        }

    }
}
