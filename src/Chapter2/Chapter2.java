/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chapter2;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceDialog;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 *
 * @author Mahmoud_Abusaqer
 */
public class Chapter2 extends Application {

    TextArea textArea;
    Stage stage3;

    @Override
    public void start(Stage primaryStage) throws FileNotFoundException, IOException {
        //First Question
        GridPane pane = new GridPane();
        pane.setPadding(new Insets(10));
        pane.setAlignment(Pos.CENTER);
        pane.setHgap(6);
        pane.setVgap(6);
        ListView<String> itemListView = new ListView<>();
        itemListView.getItems().addAll("Black", "Blue", "Cyan", "Dark Gray", "Gray", "Green", "Red", "White", "yellow", "purple", "Pink");
        itemListView.setPrefSize(120, 145);
        itemListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        pane.add(itemListView, 0, 0);
        Button button = new Button("Copy>>>");
        pane.add(button, 1, 0);
        ListView<String> copyListView = new ListView<>();
        copyListView.setPrefSize(120, 145);
        pane.add(copyListView, 2, 0);

        button.setOnAction((event) -> {
            ObservableList<String> items = itemListView.getSelectionModel().getSelectedItems();
            if (!items.isEmpty()) {
                copyListView.getItems().addAll(items);
            } else {
                Alert a = new Alert(AlertType.NONE);
                a.setAlertType(AlertType.WARNING);
                a.setHeaderText("No item selections");
                a.setContentText("Please select an item");
                a.show();
            }
        });

        Scene scene = new Scene(pane, 350, 155);
        primaryStage.setTitle("Multiple Selection List");
        primaryStage.setScene(scene);
        primaryStage.show();

        //Second Question
        Stage stage2 = new Stage();
        FlowPane pane2 = new FlowPane();
        pane2.setPadding(new Insets(10));
        pane2.setAlignment(Pos.CENTER);
        Label titleLabel = new Label("Enter Celsius temperature:");
        TextField textField = new TextField();
        textField.setMinSize(170, 15);
        RadioButton fahrenheit = new RadioButton("Fahrenheit");
        RadioButton kelvin = new RadioButton("Kelvin");
        ToggleGroup toggleGroup = new ToggleGroup();
        fahrenheit.setToggleGroup(toggleGroup);
        kelvin.setToggleGroup(toggleGroup);
        HBox hBox2 = new HBox(10, fahrenheit, kelvin);
        Label result = new Label("");

        fahrenheit.setOnAction((event) -> {
            double convert = (Double.parseDouble(textField.getText()) * 9 / 5) + 32;
            result.setText("New Temperature in is: " + String.valueOf(convert));
        });

        kelvin.setOnAction((event) -> {
            double convert = (Integer.parseInt(textField.getText()) + 273.15);
            result.setText("New Temperature in is: " + String.valueOf(convert));
        });

        VBox vBox = new VBox(10, titleLabel, textField, hBox2, result);
        pane2.getChildren().add(vBox);
        Scene scene2 = new Scene(pane2, 350, 155);
        stage2.setTitle("Temperature Converter");
        stage2.setScene(scene2);
        stage2.show();

        //Third Question
        stage3 = new Stage();
        MenuBar menuBar = new MenuBar();
        Menu menuFile = new Menu("_File");
        MenuItem menuItemOpen = new MenuItem("_Open");
        MenuItem menuItemCloes = new MenuItem("_Cloes");
        MenuItem menuItemExit = new MenuItem("_Exit");
        menuFile.getItems().addAll(menuItemOpen, menuItemCloes, menuItemExit);

        Menu menuEdit = new Menu("_Edit");
        MenuItem menuItemFont = new MenuItem("_Font");
        MenuItem menuItemColor = new MenuItem("_Color");
        menuEdit.getItems().addAll(menuItemFont, menuItemColor);
        menuBar.getMenus().addAll(menuFile, menuEdit);
        EventHandler1 eventHandler = new EventHandler1();
        menuItemOpen.setOnAction(eventHandler);
        menuItemCloes.setOnAction(eventHandler);
        menuItemExit.setOnAction(eventHandler);
        menuItemFont.setOnAction(eventHandler);
        menuItemColor.setOnAction(eventHandler);
        textArea = new TextArea("Inital Text");
        VBox vBox2 = new VBox(menuBar, textArea);
        Scene scene3 = new Scene(vBox2, 200, 200);
        stage3.setTitle("File View");
        stage3.setScene(scene3);
        stage3.setResizable(false);
        stage3.show();

        //Fourth Question
        //For the user name and the password, they are in the password.txt file 
        //where the password was stored by using the MD5 hash 
        //The users and thier passwords
        // mahmoud mahmoud123456, ahmed ahmed123, osama osama123
        Stage stage4 = new Stage();
        GridPane pane4 = new GridPane();
        pane4.setAlignment(Pos.CENTER);
        pane4.setPadding(new Insets(15, 15, 15, 15));
        pane4.setHgap(5);
        pane4.setVgap(5);
        Label welcome = new Label("Welcome");
        welcome.setId("label-Welcome");
        welcome.setAlignment(Pos.CENTER_RIGHT);
        pane4.add(welcome, 0, 0);
        pane4.add(new Label("User Name: "), 0, 1);
        TextField name = new TextField();
        pane4.add(name, 1, 1);
        pane4.add(new Label("Password: "), 0, 2);
        PasswordField password = new PasswordField();
        pane4.add(password, 1, 2);
        Button loginButton = new Button("Sign in");
        Button exitButton = new Button("Exit");
        HBox hBox = new HBox(10, loginButton, exitButton);
        hBox.alignmentProperty().setValue(Pos.CENTER_RIGHT);
        pane4.add(hBox, 1, 3);

        String filepath = "C:\\Users\\hp\\OneDrive\\Documents\\NetBeansProjects\\JavaProgramming3Assignments\\src\\Chapter2\\password.txt";
        File file = new File(filepath);
        Scanner scanner = new Scanner(file);
        loginButton.setOnAction((ActionEvent event) -> {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.contains(name.getText()) && line.contains(md5Java(password.getText()))) {
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
                }
            }
        });

        exitButton.setOnAction((event) -> {
            stage4.close();
        });

        Scene scene4 = new Scene(pane4, 300, 300);
        scene4.getStylesheets().add("style.css");
        stage4.setTitle("Login Page");
        stage4.setScene(scene4);
        stage4.setResizable(false);
        stage4.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    public static String md5Java(String message) {
        String digest = null;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hash = md.digest(message.getBytes("UTF-8"));
            StringBuilder sb = new StringBuilder(2 * hash.length);
            for (byte b : hash) {
                sb.append(String.format("%02x", b & 0xff));
            }
            digest = sb.toString();
        } catch (UnsupportedEncodingException ex) {

        } catch (NoSuchAlgorithmException ex) {

        }
        return digest;
    }

    private class EventHandler1 implements EventHandler<ActionEvent> {

        @Override
        public void handle(ActionEvent event) {
            String menu = ((MenuItem) event.getSource()).getText();
            switch (menu) {
                case "_Open":
                    try {
                    FileChooser fc = new FileChooser();
                    fc.setTitle("Open File");
                    fc.setInitialDirectory(new File("."));
                    File file = fc.showOpenDialog(null);
                    Scanner scanner = null;
                    textArea.setText("");
                    textArea.setWrapText(true);
                    scanner = new Scanner(file);
                    while (scanner.hasNext()) {
                        textArea.appendText(scanner.nextLine());
                    }
                    textArea.setEditable(true);
                } catch (FileNotFoundException ex) {

                }
                break;
                case "_Cloes":
                    textArea.clear();
                    textArea.setEditable(false);
                    break;
                case "_Exit":
                    stage3.close();
                    break;
                case "_Font":
                    Dialog<String> dialogFont = new ChoiceDialog<>("8", "10", "12", "14", "16", "18", "20", "22", "24", "26", "30", "35", "40");
                    dialogFont.setHeaderText("Select the size of the Font from list");
                    dialogFont.setContentText("Available Sizes");
                    dialogFont.setTitle("Size Selection");
                    String font = dialogFont.showAndWait().get();
                    textArea.setStyle("-fx-font-size: " + font + "px;");
                    break;
                case "_Color":
                    Dialog<String> dialogColor = new ChoiceDialog<>("Black", "Blue", "Cyan", "Gray", "Green", "Red", "White", "yellow", "purple", "Pink");
                    dialogColor.setHeaderText("Select the color from list");
                    dialogColor.setContentText("Available Colors");
                    dialogColor.setTitle("Color Selection");
                    String color = dialogColor.showAndWait().get();
                    textArea.setStyle("-fx-text-fill: " + color + ";");
                    break;
            }
        }
    }
}
