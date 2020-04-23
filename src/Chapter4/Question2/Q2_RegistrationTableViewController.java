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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;

/**
 * FXML Controller class
 *
 * @author Mahmoud_Abusaqer
 */
public class Q2_RegistrationTableViewController implements Initializable {

    @FXML
    private TextField textFiledStudentId;
    @FXML
    private TextField textFiledCourseId;
    @FXML
    private TextField textFiledSemester;
    @FXML
    private TableColumn<Student, Integer> tcStudentId;
    @FXML
    private TableColumn<Student, Integer> tcCourseId;
    @FXML
    private TableColumn<Student, String> tcSemester;
    @FXML
    private Button addButton;
    @FXML
    private Button studentPage;
    @FXML
    private TableView<?> registrationTableView;
    @FXML
    private FlowPane rootPane;
    
    Statement statement;

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
        tcStudentId.setCellValueFactory(new PropertyValueFactory("studentId"));
        tcCourseId.setCellValueFactory(new PropertyValueFactory("courseId"));
        tcSemester.setCellValueFactory(new PropertyValueFactory("semester"));
//        studentTableView.getSelectionModel().selectedItemProperty().addListener(listener -> selectStudent());
    }

    @FXML
    private void addButtonHandle(ActionEvent event) throws SQLException {
        Integer studentId = Integer.parseInt(textFiledStudentId.getText());
        Integer courseId = Integer.parseInt(textFiledCourseId.getText());
        String semester = textFiledSemester.getText();
        String sql = "insert into registration values(" + studentId + ", " + courseId + ", '" + semester + "')";
        this.statement.executeUpdate(sql);
        clearFields();
//        showStudents();
    }

    @FXML
    private void studentPageHandle(ActionEvent event) throws IOException {
        FlowPane flowPane = FXMLLoader.load(getClass().getResource("Q2_StudentTableView.fxml"));
        rootPane.getChildren().setAll(flowPane);
    }

//    private void showStudents() throws SQLException {
//        ResultSet resultSet = this.statement.executeQuery("select * from registration");
//        registrationTableView.getItems().clear();
//        while (resultSet.next()) {
//            Student student = new Student();
//            student.setId(resultSet.getInt("id"));
//            student.setName(resultSet.getString("name"));
//            student.setMajor(resultSet.getString("major"));
//            registrationTableView.getItems().add(resultSet.);
//
//        }
//    }
//
//    private void selectStudent() {
//        Student student = registrationTableView.getSelectionModel().getSelectedItem();
//        if (student != null) {
//            textFiledStudentId.setText(String.valueOf(student.getId()));
//            textFiledSemester.setText(student.getName());
//            textFiledCourseId.setText(String.valueOf(student.getGrade()));
//        }
//
//    }

    private void clearFields() {
        textFiledStudentId.setText("");
        textFiledCourseId.setText("");
        textFiledSemester.setText("");
    }
}
