/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminView;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author user
 */
public class AdminSectionFXMLController implements Initializable {

    
    /**
     * Controller Classes
     */
    ViewStudentInfoFXMLController viewStudentInfoFXMLController;
    UpdateStudentInfoFXMLController updateStudentInfoFXMLController;
    DeleteStudentFXMLController deleteStudentFXMLController;

    @FXML
    private Button AddStudentButton;
    @FXML
    private Button ViewStudentInfoButton;
    @FXML
    private Button UpdateStudentButton;
    @FXML
    private Button DeleteStudentButton;
    @FXML
    private Button LogoutButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @FXML
    private void handelButtonAction(ActionEvent event) throws IOException {

        if (event.getSource() == AddStudentButton) {
            Parent root = FXMLLoader.load(getClass().getResource("AddStudentFXML.fxml"));
            Stage window = (Stage) AddStudentButton.getScene().getWindow();
            window.setScene(new Scene(root));

        }

        if (event.getSource() == ViewStudentInfoButton) {
            Parent root = FXMLLoader.load(getClass().getResource("/AdminView/ViewStudentInfoFXML.fxml"));
            Stage window = (Stage) ViewStudentInfoButton.getScene().getWindow();
            window.setScene(new Scene(root));
            window.setTitle("Student Data");
            

        }

        if (event.getSource() == UpdateStudentButton) {

            Parent root = FXMLLoader.load(getClass().getResource("UpdateStudentInfoFXML.fxml"));
            Stage window = (Stage) UpdateStudentButton.getScene().getWindow();
            window.setScene(new Scene(root));
            window.setTitle("Update Student Data");
           

        }

        if (event.getSource() == DeleteStudentButton) {
            Parent root = FXMLLoader.load(getClass().getResource("DeleteStudentFXML.fxml"));
            Stage window = (Stage) DeleteStudentButton.getScene().getWindow();
            window.setScene(new Scene(root));
           
        }

        if (event.getSource() == LogoutButton) {
            Parent root = root = FXMLLoader.load(getClass().getResource("LoginScreen.fxml"));
            Stage window = (Stage) LogoutButton.getScene().getWindow();
            window.setScene(new Scene(root));
        }
    }

}
