/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chapter4.Question2;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;

/**
 * FXML Controller class
 *
 * @author Mahmoud_Abusaqer
 */
public class Q2_StudentTableViewController implements Initializable {

    @FXML
    private TextField textFiledId;
    @FXML
    private TextField textFiledName;
    @FXML
    private TextField textFiledMajor;
    @FXML
    private TextField textFiledGrade;
    @FXML
    private TableColumn<Student, Integer> tcId;
    @FXML
    private TableColumn<Student, String> tcName;
    @FXML
    private TableColumn<Student, String> tcMajor;
    @FXML
    private TableColumn<Student, Double> tcGrade;
    @FXML
    private Button addButton;
    @FXML
    private Button editButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button registrationPage;
    @FXML
    private TableView<Student> studentTableView;
    @FXML
    private FlowPane rootPane;

    Statement statement;
    @FXML
    private TextArea textArea;
    @FXML
    private Button aButton;
    @FXML
    private Button bButton;
    @FXML
    private Button cButton;
    @FXML
    private Button dButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection
                    = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/student?serverTimezone=UTC",
                            "root", "");
            this.statement = connection.createStatement();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        tcId.setCellValueFactory(new PropertyValueFactory("id"));
        tcName.setCellValueFactory(new PropertyValueFactory("name"));
        tcMajor.setCellValueFactory(new PropertyValueFactory("major"));
        tcGrade.setCellValueFactory(new PropertyValueFactory("grade"));
        studentTableView.getSelectionModel().selectedItemProperty().addListener(listener -> selectStudent());
        if (rootPane.isVisible()) {
            try {
                showStudents();
            } catch (SQLException ex) {
                Logger.getLogger(Q2_StudentTableViewController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    @FXML
    private void addButtonHandle(ActionEvent event) throws SQLException {
        Integer id = Integer.parseInt(textFiledId.getText());
        String name = textFiledName.getText();
        String major = textFiledMajor.getText();
        Double grade = Double.parseDouble(textFiledGrade.getText());
        String sql = "insert into student_table values(" + id + ", '" + name + "', '" + major + "', " + grade + ")";
        this.statement.executeUpdate(sql);
        clearFields();
        showStudents();
    }

    @FXML
    private void editButtonHandle(ActionEvent event) throws SQLException {
        Integer id = Integer.parseInt(textFiledId.getText());
        String name = textFiledName.getText();
        String major = textFiledMajor.getText();
        Double grade = Double.parseDouble(textFiledGrade.getText());
        String sql = "UPDATE student_table SET name='" + name + "', major='" + major + "', grade=" + grade + " WHERE id=" + id;
        this.statement.executeUpdate(sql);
        showStudents();
        clearFields();
    }

    @FXML
    private void deleteButtonHandle(ActionEvent event) throws SQLException {
        Integer id = Integer.parseInt(textFiledId.getText());
        String sql = "DELETE FROM student_table WHERE id=" + id;
        this.statement.executeUpdate(sql);
        showStudents();
        clearFields();
    }

    @FXML
    public void registrationPageHandle(ActionEvent event) throws IOException {
        FlowPane flowPane = FXMLLoader.load(getClass().getResource("Q2_RegistrationTableView.fxml"));
        rootPane.getChildren().setAll(flowPane);
    }

    private void showStudents() throws SQLException {
        ResultSet resultSet = this.statement.executeQuery("select * from student_table");
        studentTableView.getItems().clear();
        while (resultSet.next()) {
            Student student = new Student();
            student.setId(resultSet.getInt("id"));
            student.setName(resultSet.getString("name"));
            student.setMajor(resultSet.getString("major"));
            student.setGrade(resultSet.getDouble("grade"));
            studentTableView.getItems().add(student);

        }
    }

    private void selectStudent() {
        Student student = studentTableView.getSelectionModel().getSelectedItem();
        if (student != null) {
            textFiledId.setText(String.valueOf(student.getId()));
            textFiledName.setText(student.getName());
            textFiledMajor.setText(student.getMajor());
            textFiledGrade.setText(String.valueOf(student.getGrade()));
        }

    }

    private void clearFields() {
        textFiledId.setText("");
        textFiledName.setText("");
        textFiledMajor.setText("");
        textFiledGrade.setText("");
    }

    @FXML
    private void aButtonHandle(ActionEvent event) throws SQLException {
        textArea.clear();
        ResultSet resultSet = this.statement.executeQuery("SELECT * FROM student_table WHERE major='Software Engineering'");
        studentTableView.getItems().clear();
        while (resultSet.next()) {
            Student student = new Student();
            student.setId(resultSet.getInt("id"));
            student.setName(resultSet.getString("name"));
            student.setMajor(resultSet.getString("major"));
            student.setGrade(resultSet.getDouble("grade"));
            studentTableView.getItems().add(student);
        }
        textArea.appendText("SELECT * FROM student_table WHERE major='Software Engineering'");
    }

    @FXML
    private void bButtonHandle(ActionEvent event) throws SQLException {
        textArea.clear();
        ResultSet resultSet = this.statement.executeQuery("SELECT * FROM student_table WHERE grade>=90");
        studentTableView.getItems().clear();
        while (resultSet.next()) {
            Student student = new Student();
            student.setId(resultSet.getInt("id"));
            student.setName(resultSet.getString("name"));
            student.setMajor(resultSet.getString("major"));
            student.setGrade(resultSet.getDouble("grade"));
            studentTableView.getItems().add(student);
        }
        textArea.appendText("SELECT * FROM student_table WHERE grade>=90");
    }

    @FXML
    private void cButtonHandle(ActionEvent event) throws SQLException {
        textArea.clear();
        ResultSet resultSet = this.statement.executeQuery("SELECT * FROM student_table WHERE grade>=60 ORDER BY name");
        studentTableView.getItems().clear();
        while (resultSet.next()) {
            Student student = new Student();
            student.setId(resultSet.getInt("id"));
            student.setName(resultSet.getString("name"));
            student.setMajor(resultSet.getString("major"));
            student.setGrade(resultSet.getDouble("grade"));
            studentTableView.getItems().add(student);
        }
        textArea.appendText("SELECT * FROM student_table WHERE grade>=60 ORDER BY name");
    }

    @FXML
    private void dButtonHandle(ActionEvent event) throws SQLException {
        textArea.clear();

        int updateGrade = this.statement.executeUpdate("UPDATE student_table "
                + "SET grade = grade+3 WHERE major='Computer Science' AND grade<70 ");

        ResultSet resultSet = this.statement.executeQuery("SELECT * FROM student_table "
                + "WHERE major='Computer Science' AND grade<73");
        studentTableView.getItems().clear();

        while (resultSet.next()) {
            Student student = new Student();
            student.setId(resultSet.getInt("id"));
            student.setName(resultSet.getString("name"));
            student.setMajor(resultSet.getString("major"));
            student.setGrade(resultSet.getDouble("grade"));
            studentTableView.getItems().add(student);
        }
        textArea.appendText("UPDATE student_table SET grade = grade+3 WHERE major='Computer Science' AND grade<70 ");
    }

}
