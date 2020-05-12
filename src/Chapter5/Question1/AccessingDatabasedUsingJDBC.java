/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chapter5.Question1;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

/**
 *
 * @author Mahmoud_Abusaqer
 */
public class AccessingDatabasedUsingJDBC extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        Pane paneStudentTableView = FXMLLoader.load(getClass().getResource("StudentTableView.fxml"));
        Scene scene = new Scene(paneStudentTableView);
        primaryStage.setTitle("Accessing Databased Using JPA");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
