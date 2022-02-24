/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudentView;

import AdminView.AdminSectionFXMLController;
import Controller.DatabaseConnection;
import Model.Student;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class StudentSectionFXMLController implements Initializable {

    @FXML
    private Button EnrollCourseButton;
    @FXML
    private Button ViewEnrolledCourseButton;
    @FXML
    private Button BackButton;
    @FXML
    private Label StatusLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handelButtonAction(ActionEvent event) throws IOException, SQLException {
        if (event.getSource() == EnrollCourseButton) {
            Statement statement = DatabaseConnection.getConnection().createStatement();
            String qu = "select * from courses where student_db_id = " + Student.getSt_db_id();
            ResultSet resultSet = statement.executeQuery(qu);

            if (!resultSet.next()) {
                loadCoursesSection();
              
            }else{
               JOptionPane.showMessageDialog(null ,"You have Enrolled Once.");
            }   
        }
        
        
        if (event.getSource() == ViewEnrolledCourseButton) {
            loadEnrolledCourses();
        }

        if (event.getSource() == BackButton) {
            Parent root = FXMLLoader.load(getClass().getResource("/AdminView/LoginScreen.fxml"));
            Stage window = (Stage) BackButton.getScene().getWindow();
            window.setScene(new Scene(root));
        }
    }

    
    /**
     * loadEnrolledCourses Method for loading Courses Sections
     * @throws IOException 
     */
    public void loadCoursesSection() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/StudentView/CoursesFXML.fxml"));
        Stage window = (Stage) EnrollCourseButton.getScene().getWindow();
        window.setScene(new Scene(root));

        window.setTitle("Courses Section");
    }

    
    /**
     * loadEnrolledCourses Method for loading Enrolled Courses
     * @throws IOException 
     */
    public void loadEnrolledCourses() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/StudentView/ViewEnrolledCoursesFXML.fxml"));
        Stage window = (Stage) ViewEnrolledCourseButton.getScene().getWindow();
        window.setScene(new Scene(root));

        window.setTitle("Enrolled Courses ");

    }
}
