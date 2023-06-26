package dashboarddesign;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class DashboardDesign extends Application {


    @Override
    public void start(Stage stage) throws IOException {
        try {
            Parent parent= FXMLLoader.load(getClass().getResource("homeView.fxml"));
            HomeView homeViewController=new HomeView();
            FXMLLoader loader=new FXMLLoader();
            loader.setController(homeViewController);

            Scene scene =new Scene(parent);

            scene.getStylesheets().add(getClass().getResource("style.css").toExternalForm());
            scene.setFill(Color.TRANSPARENT);
            stage.setScene(scene);
            //stage.initStyle(StageStyle.TRANSPARENT);
            HomeView ref=loader.getController();

            stage.show();


        }catch (Exception e){
            System.out.println(e.getCause());
        }


    }

    public static void main(String[] args) {
        launch(args);
    }
}
