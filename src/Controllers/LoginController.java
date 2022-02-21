package Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.IOException;

public class LoginController {

    @FXML
    private TextField username;

    @FXML
    private Button btnLogin;

    @FXML
    private PasswordField password;

    @FXML
    private Label infoLabel;

    @FXML
    void onLoginClicked(ActionEvent event){
        if(username.getText().equalsIgnoreCase("admin") && password.getText().equalsIgnoreCase("123")){
            ScreenController sc = new ScreenController();
            sc.switchToSceneStart(event);
        } else {
            infoLabel.setText("Wrong login information, password or username");
        }
    }



}
