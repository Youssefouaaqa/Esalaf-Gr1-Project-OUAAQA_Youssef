package com.example.esalaf;

import Models.Admin.Admin;
import Models.Admin.AdminDAO;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController {
    @FXML
    private TextField input_Email;

    @FXML
    private PasswordField input_Password;
    @FXML
    private Label LoginMessageLabel;

    @FXML
    public void onSaveButtonClick(ActionEvent event) throws SQLException , IOException{
        //verification de l'existence du Admin qui a le meme email et password entrer
        String Email = input_Email.getText();
        String Password = input_Password.getText();
        AdminDAO AdminModels = new AdminDAO();

        if (Email.isEmpty() || Password.isEmpty()){
            LoginMessageLabel.setText("Veuillez remplir tout les champs !!");

        }
        else {
            Admin AdminCheck = AdminModels.getAdminByEmailAndPassword(Email , Password);
            if (AdminCheck == null){
                LoginMessageLabel.setText("L'email ou password sont incorrect !!");

            } else if (AdminCheck.getEmail().equals(Email) && AdminCheck.getPassword().equals(Password)){
                Parent root = FXMLLoader.load(getClass().getResource("Dashboard.fxml"));
                Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.setTitle("Tableau de bord Esalaf");
                stage.show();
            }
        }
    }

    @FXML
    protected void onCreateCompteClick(ActionEvent event) throws IOException {
        //switch scene to register view
        Parent root = FXMLLoader.load(getClass().getResource("Register-view.fxml"));
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.show();



    }
}