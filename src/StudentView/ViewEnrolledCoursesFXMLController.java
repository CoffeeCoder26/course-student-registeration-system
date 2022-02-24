/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package StudentView;

import Controller.DatabaseConnection;
import Model.Course;
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
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class ViewEnrolledCoursesFXMLController implements Initializable {
    
    int id ;

    Statement statement;
    ResultSet resultSet;

    @FXML
    private TableColumn<Course, String> idCol;
    @FXML
    private TableColumn<Course, String> course1Col;
    @FXML
    private TableColumn<Course, String> course2Col;
    @FXML
    private TableColumn<Course, String> course3Col;
    @FXML
    private TableColumn<Course, String> course4Col;
    @FXML
    private TableColumn<Course, String> course5Col;
    @FXML
    private TableColumn<Course, String> course6Col;
    @FXML
    private TableColumn<Course, Integer> hoursCol;
    @FXML
    private TableView<Course> CoursesTabel;
    @FXML
    private Button BackButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        showRegistratedCourses();
    }

    /**
     * getCoursesData Method to get courses from database
     * @return 
     */
    public ObservableList<Course> getCoursesData() {
        //Hint
        Student s = new Student();
      
        ObservableList<Course> course = FXCollections.observableArrayList();
        try {
            statement = DatabaseConnection.getConnection().createStatement();
            String sqlQuery = "SELECT Course_Id, Course1 , Course2,Course3,Course4,Course5,Course6,Course_Hours FROM courses WHERE student_db_id = '"+Student.getSt_db_id()+"'";
            resultSet = statement.executeQuery(sqlQuery);
            while (resultSet.next()) {
                Course c = new Course();
                c.setCourseId(resultSet.getString(1));
                c.setCourse1Name(resultSet.getString(2));
                c.setCourse2Name(resultSet.getString(3));
                c.setCourse3Name(resultSet.getString(4));
                c.setCourse4Name(resultSet.getString(5));
                c.setCourse5Name(resultSet.getString(6));
                c.setCourse6Name(resultSet.getString(7));
                c.setNumOfHours(resultSet.getInt(8));

                course.add(c);

            }

        } catch (SQLException e) {
            e.toString();
        }

        return course;
    }

    /**
     * showRegistratedCourses Method for get all courses shown in the table
     */
    public void showRegistratedCourses() {

        ObservableList<Course> courseList = getCoursesData();
    
        idCol.setCellValueFactory(new PropertyValueFactory<Course, String>("CourseId"));
        course1Col.setCellValueFactory(new PropertyValueFactory<Course, String>("course1Name"));
        course2Col.setCellValueFactory(new PropertyValueFactory<Course, String>("course2Name"));
        course3Col.setCellValueFactory(new PropertyValueFactory<Course, String>("course3Name"));
        course4Col.setCellValueFactory(new PropertyValueFactory<Course, String>("course4Name"));
        course5Col.setCellValueFactory(new PropertyValueFactory<Course, String>("course5Name"));
        course6Col.setCellValueFactory(new PropertyValueFactory<Course, String>("course6Name"));
        hoursCol.setCellValueFactory(new PropertyValueFactory<Course, Integer>("numOfHours"));
        
        CoursesTabel.setItems(courseList);

    }

    @FXML
    private void handelButtonAction(ActionEvent event) throws IOException {
        
        if(event.getSource() == BackButton){
            Parent root = FXMLLoader.load(getClass().getResource("/StudentView/StudentSectionFXML.fxml"));
            Stage window = (Stage) BackButton.getScene().getWindow();
            window.setScene(new Scene(root));
        }
    }


}
