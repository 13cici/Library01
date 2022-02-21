package Controllers;

import Utilities.AppConstrants;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.logging.Logger;

public class ScreenController {

    static Logger log = Logger.getLogger(AppConstrants.LOGGER_STRING);
    private Parent root ;
    private Stage stage;

    public void switchToSceneStart(ActionEvent event){
        try {
            root = FXMLLoader.load(getClass().getResource("/Views/StartMenu.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
            log.info("Change start menu scene successfully!");
        } catch (Exception e) {
            log.warning("Change scene problem! " + e.getMessage());
        }
    }

    public void switchToSceneLogin(ActionEvent event) {
        try {
            root = FXMLLoader.load(getClass().getResource("/Views/Login.fxml"));
            stage = (Stage)((Node)event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root, 800, 600));
            stage.show();
            log.info("Change login scene successfully!");
        } catch (Exception e){
            log.warning("Change scene problem! " + e.getMessage());
        }

    }
}
