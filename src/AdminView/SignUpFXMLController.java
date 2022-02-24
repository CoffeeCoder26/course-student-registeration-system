/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AdminView;

import Controller.DatabaseConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;

/**
 * FXML Controller class
 *
 * @author user
 */
public class SignUpFXMLController implements Initializable {

    @FXML
    private TextField FullNameTxtField;
    @FXML
    private TextField EmailTxtField;
    @FXML
    private TextField PassField;
    @FXML
    private TextField ContactField;
    @FXML
    private Label StatusLabel;
    @FXML
    private TextField idField;
    @FXML
    private ChoiceBox<String> LevelChcBox;
    @FXML
    private Button signupButton;

    /**
     * Initializes the controller class.
     */
    private String[] levels = {"1", "2", "3", "4"};

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        LevelChcBox.getItems().addAll(levels);
    }

    @FXML
    private void handelButtonAction(ActionEvent event) throws SQLException {
        if (event.getSource() == signupButton) {
            registerStudent();
            StatusLabel.setText("Successfully Registeration ..");

        }
    }

    Statement statement;
    Connection connection;

    /**
     * Add Student Method
     */
    public void registerStudent() throws SQLException {
        try {
            statement = DatabaseConnection.getConnection().createStatement();
            String mySqlQuery = "insert into student (Student_Id , Student_FullName , Student_Email , Password , Student_Contact,Student_Level) values "
                    + "('" + idField.getText() + "', '" + FullNameTxtField.getText() + "' "
                    + ",'" + EmailTxtField.getText() + "' , "
                    + "'" + PassField.getText() + "' , "
                    + "'" + ContactField.getText() + "' , "
                    + "'" + LevelChcBox.getValue() + "')";

            statement.executeUpdate(mySqlQuery);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error In Insert Method .....");
            System.out.println(ex.toString());
        }
    }
}
