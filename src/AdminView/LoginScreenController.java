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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javax.swing.JOptionPane;

/**
 *
 * @author user
 */
public class LoginScreenController implements Initializable {

    /**
     * Database Classes
     */
    PreparedStatement preparedStatement;
    Statement statement;
    ResultSet resultSet;

    @FXML
    private PasswordField passTxtField;
    @FXML
    private Button loginBtn;
    @FXML
    private Button signupBtn;
    @FXML
    private TextField emailTxtField;
    @FXML
    private Label StatusLabel;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handelButtonAction(ActionEvent event) throws IOException, SQLException {

        if (event.getSource() == loginBtn) {
            checkLogin();
        }

    }

    /**
     * Method For Loading Admin Section
     *
     * @throws SQLException
     * @throws IOException
     */
    public void loadAdminSection() throws SQLException, IOException {

        Parent root = FXMLLoader.load(getClass().getResource("AdminSectionFXML.fxml"));
        Stage window = (Stage) loginBtn.getScene().getWindow();
        window.setScene(new Scene(root));
        window.setResizable(false);

    }

    /**
     * Method For Loading Student Section
     *
     * @throws SQLException
     * @throws IOException
     */
    public void loadStudentSection() throws SQLException, IOException {

        Parent root = FXMLLoader.load(getClass().getResource("/StudentView/StudentSectionFXML.fxml"));
        Stage window = (Stage) loginBtn.getScene().getWindow();
        window.setScene(new Scene(root));

    }

    /**
     * Method For Checking Login (Student Or Admin)
     *
     * @throws SQLException
     * @throws IOException
     */
    public void checkLogin() throws SQLException, IOException {
        Student s = new Student();
        String email = emailTxtField.getText().toString();
        String password = passTxtField.getText().toString();
        
        
        if (!emailTxtField.getText().isEmpty() || !passTxtField.getText().isEmpty()) {
            if (emailTxtField.getText().equals("admin") && passTxtField.getText().equals("admin")) {
                loadAdminSection();
            } else {
                statement = DatabaseConnection.getConnection().createStatement();
                resultSet = statement.executeQuery("select *  from student where Student_Email = '" + email + "'  and Password ='" + password + "' ");

                if (resultSet.next()) {
                    Student.setSt_db_id(resultSet.getInt(1));
                    loadStudentSection();
                } else {

                    JOptionPane.showMessageDialog(null, "Enter Valid Email Or Password");
                }
            }
        } else {
            JOptionPane.showMessageDialog(null, "Email Or Password Can't Be Empty");

        }
    }
}
