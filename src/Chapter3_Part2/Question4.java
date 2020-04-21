/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chapter3_Part2;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

/**
 *
 * @author Mahmoud_Abusaqer
 */
public class Question4 extends Application {
//This is the Options Page and the Add Student Page only

    Stage stage4;
    ListView listView;
    Button add;
    RadioButton a;
    RadioButton b;
    RadioButton c;
    RadioButton d;
    RadioButton e;
    TextField id;
    TextField name;
    TextField major;
    TextField grade;
    Label average;

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, IOException {
        //The Options Page scene
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
            //The Add Student Page scene
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

            addStudentPane.add(new Label("Sort: "), 0, 6, 1, 2);
            a = new RadioButton("By Name");
            b = new RadioButton("By Grade");
            c = new RadioButton("Filterd");
            d = new RadioButton("Average");
            e = new RadioButton("By Major");
            ToggleGroup toggleGroup = new ToggleGroup();
            a.setToggleGroup(toggleGroup);
            b.setToggleGroup(toggleGroup);
            c.setToggleGroup(toggleGroup);
            d.setToggleGroup(toggleGroup);
            e.setToggleGroup(toggleGroup);
            HBox hBox3 = new HBox(10, a, b);
            addStudentPane.add(hBox3, 1, 6);
            HBox hBox4 = new HBox(10, c, d, e);
            addStudentPane.add(hBox4, 1, 7);

            listView = new ListView<>();
            listView.setPrefSize(370, 300);
            addStudentPane.add(listView, 2, 1, 7, 7);
            average = new Label("");
            addStudentPane.add(average, 1, 8);
            //Add Student button & sort event handling
            EventHandler2 eventHandler = new EventHandler2();
            add.setOnAction(eventHandler);
            a.setOnAction(eventHandler);
            b.setOnAction(eventHandler);
            c.setOnAction(eventHandler);
            d.setOnAction(eventHandler);
            e.setOnAction(eventHandler);
            //Reset button event handling
            reset.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    id.setText("");
                    name.setText("");
                    major.setText("");
                    grade.setText("");
                }
            });
            //Exit button event handling
            exit.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    stage4.close();
                }
            });
            Scene addStudentScene = new Scene(addStudentPane, 700, 570);
            addStudentScene.getStylesheets().add("style.css");
            stage4.setTitle("Add Student Page");
            stage4.setScene(addStudentScene);
        }
    }

    private class EventHandler2 implements EventHandler<ActionEvent> {

        ArrayList<Student> list = new ArrayList<>();
        Student student;

        @Override
        public void handle(ActionEvent event) {
            if (event.getSource() == add) {
                //Add Student in ListView and in the ArrayList
                if (!id.getText().equals("") && name.getText() != null && major.getText() != null && grade.getText() != null) {
                    int studentId = Integer.parseInt(id.getText());
                    String studentName = name.getText();
                    String studentMajor = major.getText();
                    double studentGrade = Double.parseDouble(grade.getText());
                    student = new Student(studentId, studentName, studentMajor, studentGrade);
                    list.add(student);
                    listView.getItems().addAll(student);
                }
            }

            if (event.getSource() == a) {
                //Sort Students by name
                listView.getItems().setAll(list.stream()
                        .sorted(Comparator.comparing(Student::getName))
                        .collect(Collectors.toList()));

            } else if (event.getSource() == b) {
                //Map each Student to its name and grade and Sort Students by grade in descending order
                listView.getItems().setAll(list.stream()
                        .sorted(Comparator.comparing(Student::getGrade).reversed())
                        .map(s -> s.getName() + "       " + s.getGrade())
                        .collect(Collectors.toList()));

            } else if (event.getSource() == c) {
                //Map each Student to its name and grade, filter the Students by thier grads from 80 to 90
                //and Sort Students by grade in descending order
                listView.getItems().setAll(list.stream()
                        .filter(s -> (s.getGrade() >= 80) && (s.getGrade() < 90))
                        .sorted(Comparator.comparing(Student::getGrade).reversed())
                        .map(s -> s.getName() + "       " + s.getGrade())
                        .collect(Collectors.toList()));

            } else if (event.getSource() == d) {
                //The Average for all Students by thier grades
                double avg = list.stream()
                        .mapToDouble((Student s) -> s.getGrade())
                        .average().getAsDouble();
                average.setText("Average:" + avg);

            } else if (event.getSource() == e) {
                //Group Students by major
                listView.getItems().clear();
                list.stream()
                        .collect(Collectors.groupingBy(Student::getMajor))
                        .forEach((std, maj) -> {
                            listView.getItems().add(std + ": ");
                            maj.forEach(x -> listView.getItems().add(x));
                        });
            }

            id.setText("");
            name.setText("");
            major.setText("");
            grade.setText("");

        }
    }
}
