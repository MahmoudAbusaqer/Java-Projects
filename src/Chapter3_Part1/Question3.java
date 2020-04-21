/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chapter3_Part1;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author Mahmoud_Abusaqer
 */
public class Question3 extends Application {

    Stage stage4;
    ListView<Student> listView;
    Button add;
    TextField id;
    TextField name;
    TextField major;
    TextField grade;

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, IOException {
        stage4 = new Stage();
        GridPane pane5 = new GridPane();
        pane5.setAlignment(Pos.CENTER);
        pane5.setPadding(new Insets(15, 15, 15, 15));
        pane5.setHgap(5);
        pane5.setVgap(15);
        Button addStudent = new Button("Add Student");
        pane5.add(addStudent, 0, 0);
        Button viewStudent = new Button("View Student");
        pane5.add(viewStudent, 0, 1);
        Scene scene5 = new Scene(pane5, 300, 300);
        scene5.getStylesheets().add("style.css");
        stage4.setTitle("Options Page");
        stage4.setScene(scene5);

        //Add Student Scene
        EventHandler1 eventHandler = new EventHandler1();
        addStudent.setOnAction(eventHandler);
        stage4.setResizable(false);
        stage4.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    private class EventHandler1 implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            GridPane addStudentPane = new GridPane();
            addStudentPane.setAlignment(Pos.CENTER);
            addStudentPane.setPadding(new Insets(15, 15, 15, 15));
            addStudentPane.setHgap(5);
            addStudentPane.setVgap(15);
            Label welcome = new Label("Student Data");
            welcome.setId("label-Welcome");
            welcome.setAlignment(Pos.CENTER);
            HBox hBox = new HBox(10, welcome);
            addStudentPane.add(hBox, 0, 0, 2, 1);
            addStudentPane.add(new Label("ID: "), 0, 1);
            id = new TextField();
            addStudentPane.add(id, 1, 1);

            addStudentPane.add(new Label("Name: "), 0, 2);
            name = new TextField();
            addStudentPane.add(name, 1, 2);

            addStudentPane.add(new Label("Major: "), 0, 3);
            major = new TextField();
            addStudentPane.add(major, 1, 3);

            addStudentPane.add(new Label("Grade: "), 0, 4);
            grade = new TextField();
            addStudentPane.add(grade, 1, 4);

            add = new Button("Add");
            Button reset = new Button("Reset");
            Button exit = new Button("Exit");
            HBox hBox2 = new HBox(10, add, reset, exit);
            addStudentPane.add(hBox2, 1, 5);
            listView = new ListView<>();
            listView.setPrefSize(370, 300);
            addStudentPane.add(listView, 2, 1, 7, 7);
            EventHandler2 eventHandler = new EventHandler2();
            add.setOnAction(eventHandler);
            exit.setOnAction(new EventHandler<ActionEvent>() {

                @Override
                public void handle(ActionEvent event) {
                    stage4.close();
                }
            });
            Scene addStudentScene = new Scene(addStudentPane, 700, 570);
            addStudentScene.getStylesheets().add("style.css");
            stage4.setTitle("Options Page");
            stage4.setScene(addStudentScene);
        }
    }

    private class EventHandler2 implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == add) {
                if (!id.getText().equals("") && name.getText() != null && major.getText() != null && grade.getText() != null) {
                    int studentId = Integer.parseInt(id.getText());
                    String studentName = name.getText();
                    String studentMajor = major.getText();
                    double studentGrade = Double.parseDouble(grade.getText());
                    Student student = new Student(studentId, studentName, studentMajor, studentGrade);
                    List<Student> list = new ArrayList<>(Arrays.asList(student));
                    listView.getItems().addAll(list);
                }
            }
            id.setText("");
            name.setText("");
            major.setText("");
            grade.setText("");
            listView.getItems().setAll(listView.getItems().stream().sorted(Comparator.comparingDouble(Student::getGrade).reversed())
                    .collect(Collectors.toList())
            );

        }

    }
}
