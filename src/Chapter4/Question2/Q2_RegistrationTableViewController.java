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
    private TableColumn<Registration, Integer> tcStudentId;
    @FXML
    private TableColumn<Registration, Integer> tcCourseId;
    @FXML
    private TableColumn<Registration, String> tcSemester;
    @FXML
    private Button addButton;
    @FXML
    private Button studentPage;
    @FXML
    private TableView<Registration> registrationTableView;
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
        if (rootPane.isVisible()) {
            try {
                showStudents();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    private void addButtonHandle(ActionEvent event) throws SQLException {
        Integer studentId = Integer.parseInt(textFiledStudentId.getText());
        Integer courseId = Integer.parseInt(textFiledCourseId.getText());
        String semester = textFiledSemester.getText();
        String sql = "insert into registration values(" + studentId + ", " + courseId + ", '" + semester + "')";
        this.statement.executeUpdate(sql);
        clearFields();
        showStudents();
    }

    @FXML
    private void studentPageHandle(ActionEvent event) throws IOException {
        FlowPane flowPane = FXMLLoader.load(getClass().getResource("Q2_StudentTableView.fxml"));
        rootPane.getChildren().setAll(flowPane);
    }

    private void showStudents() throws SQLException {
        ResultSet resultSet = this.statement.executeQuery("select * from registration");
        registrationTableView.getItems().clear();
        while (resultSet.next()) {
            Registration registration = new Registration();
            registration.setStudentId(resultSet.getInt("studentId"));
            registration.setCourseId(resultSet.getInt("courseId"));
            registration.setSemester(resultSet.getString("semester"));
            registrationTableView.getItems().add(registration);
        }
    }

    private void clearFields() {
        textFiledStudentId.setText("");
        textFiledCourseId.setText("");
        textFiledSemester.setText("");
    }
}
