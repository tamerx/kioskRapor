package tr.com.cinigaz.kioskRapor.model;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.MenuItem;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainForm implements Initializable {


    @FXML
    MenuItem kioskAlinanForm;

    @FXML
    MenuItem kioskOdenenForm;

    @FXML
    MenuItem exitForm;

    @FXML
    ImageView imageLogo;


    @Override
    public void initialize(URL location, ResourceBundle resources) {


    }

    @FXML
    public void openKioskAlinanForm(ActionEvent actionEvent) throws IOException {

        Stage kioskAlinanStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/KioskAlinanForm.fxml"));
        Scene scene = new Scene(root);
        kioskAlinanStage.setScene(scene);
        kioskAlinanStage.setFullScreen(false);
        kioskAlinanStage.setMaximized(false);
        kioskAlinanStage.setTitle("Kiosk Rapor Uygulaması");
        kioskAlinanStage.setResizable(false);
        kioskAlinanStage.show();


    }

    @FXML
    public void openKioskOdenenForm(ActionEvent actionEvent) throws IOException {

        Stage kioskOdenenStage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/KioskOdenenForm.fxml"));
        Scene scene = new Scene(root);
        kioskOdenenStage.setScene(scene);
        kioskOdenenStage.setFullScreen(false);
        kioskOdenenStage.setMaximized(false);
        kioskOdenenStage.setTitle("Kiosk Rapor Uygulaması");
        kioskOdenenStage.setResizable(false);
        kioskOdenenStage.show();
    }

    @FXML
    public void exitForm(ActionEvent actionEvent) throws IOException {
        Platform.exit();

    }

}
