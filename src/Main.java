import Utilities.AppConstrants;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class Main extends Application {

    static Logger log = Logger.getLogger(AppConstrants.LOGGER_STRING);

    @Override
    public void start(Stage primaryStage) throws Exception{
        FileHandler fileHandler = null;
        try {
            fileHandler = new FileHandler("ApplicationLogs.log");
            log.addHandler(fileHandler);
            SimpleFormatter formatter = new SimpleFormatter();
            fileHandler.setFormatter(formatter);
            log.info("Logger initialised!");
        } catch (Exception e){
            e.printStackTrace();
        }

        try{
            Parent root = FXMLLoader.load(getClass().getResource("Views/Login.fxml"));
            primaryStage.setTitle("Library");
            primaryStage.setScene(new Scene(root, 800, 600));
            primaryStage.show();
        } catch (Exception e ){
            log.warning("Problem with initial screen! " + e.getMessage());
        }

        final FileHandler finalFileHandler = fileHandler;
        primaryStage.setOnCloseRequest(windowEvent -> {
            //Closing the only log file in application
            finalFileHandler.close();
        });
    }


    public static void main(String[] args) {
        log.info("Application Start!");
        launch(args);
        log.info("Application End!");
    }
}
