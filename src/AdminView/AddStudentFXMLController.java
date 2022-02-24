/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminView;

import Controller.DatabaseConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AddStudentFXMLController implements Initializable {

    @FXML
    private TextField FullNameTxtField;
    @FXML
    private TextField EmailTxtField;
    @FXML
    private TextField PassField;
    @FXML
    private TextField ContactField;
    @FXML
    private ComboBox<String> LevelTxtField;
    @FXML
    private Button AddStudentButton;
    @FXML
    private TextField GPAField;
    @FXML
    private Label StatusLabel;

    private String[] levels = {"1", "2", "3", "4"};
    @FXML
    private Button BackButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //  Adding Values of Levels LevelTextField
        ObservableList<String> Level = FXCollections.observableArrayList("1", "2", "3", "4");
        LevelTxtField.setItems(Level);

    }

    @FXML
    private void handelButtonAction(ActionEvent event) throws SQLException, IOException {
        if (event.getSource() == AddStudentButton) {
            addStudent();
        }

        if (event.getSource() == BackButton) {
            Parent root = root = FXMLLoader.load(getClass().getResource("AdminSectionFXML.fxml"));
            Stage window = (Stage) BackButton.getScene().getWindow();
            window.setScene(new Scene(root));
        }
    }

    Statement statement;
    Connection connection;

    //Add Student Method
    public void addStudent() throws SQLException {
        try {
            if (FullNameTxtField.getText().isEmpty() || EmailTxtField.getText().isEmpty() || PassField.getText().isEmpty() || ContactField.getText().isEmpty() || LevelTxtField.getValue() == null || GPAField.getText().isEmpty()) {

                JOptionPane.showMessageDialog(null, "Pleaes Fill All Fields");

            } else {
                statement = DatabaseConnection.getConnection().createStatement();
                String mySqlQuery = "insert into student (Student_FullName , Student_Email , Password , Contact,Level,GPA) values "
                        + "('" + FullNameTxtField.getText() + "' "
                        + ",'" + EmailTxtField.getText() + "' , "
                        + "'" + PassField.getText() + "' , "
                        + "'" + ContactField.getText() + "' , "
                        + "'" + LevelTxtField.getValue() + "',"
                        + "'" + GPAField.getText() + "')";

                statement.executeUpdate(mySqlQuery);

                Parent root = FXMLLoader.load(getClass().getResource("/AdminView/ViewStudentInfoFXML.fxml"));
                Stage window = (Stage) AddStudentButton.getScene().getWindow();
                window.setScene(new Scene(root));
                window.setTitle("Student Data");

            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Failed To Add Student");
        }
    }

}
