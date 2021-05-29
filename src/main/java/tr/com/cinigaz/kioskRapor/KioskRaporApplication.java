package tr.com.cinigaz.kioskRapor;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@EnableAutoConfiguration   // TODO: 21.04.2021  kalkabilir
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
//@ComponentScan("tr.com.cinigaz")
@EnableJpaRepositories


public class KioskRaporApplication extends Application {

    private ConfigurableApplicationContext applicationContext;
    private static Stage stage;
    private static Scene mainForm;


    @Override
    public void init() throws ExceptionInInitializerError {
        this.applicationContext = SpringApplication.run(KioskRaporApplication.class);
    }

    @Override
    public void stop() throws Exception {
        applicationContext.close();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage priStage) throws Exception {

        stage = priStage;
        Parent telaA = FXMLLoader.load(getClass().getResource("/fxml/MainForm.fxml"));
        mainForm = new Scene(telaA);
        priStage.setScene(mainForm);
        priStage.setFullScreen(false);
        priStage.setMaximized(false);
        priStage.setTitle("Kiosk Rapor Uygulaması");
        priStage.setResizable(false);
        priStage.show();

    }


}