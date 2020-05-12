/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chapter5.Question2;

import Chapter5.Question1.Course;
import Chapter5.Question1.Registration;
import Chapter5.Question1.Student;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * FXML Controller class
 *
 * @author Mahmoud_Abusaqer
 */
public class Q2_StudentTableViewController implements Initializable {

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TextField textFiledStudentId;
    @FXML
    private TextField textFiledName;
    @FXML
    private TextField textFiledMajor;
    @FXML
    private TextField textFiledGrade;
    @FXML
    private TextField textFiledCourseId;
    @FXML
    private TextField textFiledSemester;
    @FXML
    private TableView<Student> studentTableView;
    @FXML
    private TableColumn<Student, Integer> tcStudentId;
    @FXML
    private TableColumn<Student, String> tcStudentName;
    @FXML
    private TableColumn<Student, String> tcMajor;
    @FXML
    private TableColumn<Student, Double> tcGrade;
    @FXML
    private TableView<Registration> registrationTableView;
    @FXML
    private TableColumn<Registration, Integer> tcStudentIdR;
    @FXML
    private TableColumn<Registration, Integer> tcCourseId;
    @FXML
    private TableColumn<Registration, String> tcSemester;
    @FXML
    private Button addStudentButton;
    @FXML
    private Button editStudentButton;
    @FXML
    private Button deleteButton;
    @FXML
    private Button addRegistration;
    @FXML
    private TextArea textArea;
    @FXML
    private Button softwareEngineeringButton;
    @FXML
    private Button excellentGradeButton;
    @FXML
    private Button allPassOrderNameButton;
    @FXML
    private Button CSandGradeButton;

    private EntityManagerFactory emf;
    private Student student;
    private Course course;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tcStudentId.setCellValueFactory(new PropertyValueFactory("id"));
        tcStudentName.setCellValueFactory(new PropertyValueFactory("name"));
        tcMajor.setCellValueFactory(new PropertyValueFactory("major"));
        tcGrade.setCellValueFactory(new PropertyValueFactory("grade"));
        tcStudentIdR.setCellValueFactory(new PropertyValueFactory("studentId"));
        tcCourseId.setCellValueFactory(new PropertyValueFactory("courseId"));
        tcSemester.setCellValueFactory(new PropertyValueFactory("semester"));
        this.emf = Persistence.createEntityManagerFactory("JavaProgramming3AssignmentsPU");
        studentTableView.getSelectionModel().selectedItemProperty().addListener(listener -> selectStudent());
        if (rootPane.isVisible()) {
            showStudents();
            showRegistration();
        }
    }

    @FXML
    private void textFiledStudentIdHandle(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
        student = (Student) em.createNamedQuery("Student.findById")
                .setParameter("id", Integer.parseInt(textFiledStudentId.getText())).getSingleResult();
        em.close();
    }

    @FXML
    private void textFiledCourseIdHandle(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
        course = (Course) em.createNamedQuery("Course.findById")
                .setParameter("id", Integer.parseInt(textFiledCourseId.getText())).getSingleResult();
        em.close();
    }

    @FXML
    private void addButtonHandle(ActionEvent event) {
        Student student = new Student();
        student.setId(Integer.parseInt(textFiledStudentId.getText()));
        student.setName(textFiledName.getText());
        student.setMajor(textFiledMajor.getText());
        student.setGrade(Double.parseDouble(textFiledGrade.getText()));
        EntityManager em = this.emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(student);
        em.getTransaction().commit();
        em.close();
        showStudents();
        clearFields();
    }

    @FXML
    private void editButtonHandle(ActionEvent event) {
        EntityManager em = this.emf.createEntityManager();
        Student student = (Student) em.createNamedQuery("Student.findById")
                .setParameter("id", Integer.parseInt(textFiledStudentId.getText()))
                .getSingleResult();
        student.setName(textFiledName.getText());
        student.setMajor(textFiledMajor.getText());
        student.setGrade(Double.parseDouble(textFiledGrade.getText()));
        em.getTransaction().begin();
        em.merge(student);
        em.getTransaction().commit();
        em.close();
        showStudents();
        clearFields();
    }

    @FXML
    private void deleteButtonHandle(ActionEvent event) {
        EntityManager em = this.emf.createEntityManager();
        Student student = (Student) em.createNamedQuery("Student.findById")
                .setParameter("id", Integer.parseInt(textFiledStudentId.getText()))
                .getSingleResult();
        student.setName(textFiledName.getText());
        student.setMajor(textFiledMajor.getText());
        student.setGrade(Double.parseDouble(textFiledGrade.getText()));
        em.getTransaction().begin();
        em.remove(student);
        em.getTransaction().commit();
        em.close();
        showStudents();
        clearFields();
    }

    @FXML
    private void addRegistrationHandle(ActionEvent event) {
        Registration registration = new Registration();
        registration.setStudentId(student);
        registration.setCourseId(course);
        registration.setSemester(textFiledSemester.getText());
        EntityManager em = this.emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(registration);
        em.getTransaction().commit();
        em.close();
        showRegistration();
        clearFields();
    }

    @FXML
    private void softwareEngineeringButtonHandle(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
        List<Student> students = em.createNamedQuery("Student.softwareEngineering")
                .setParameter("major", "Software Engineering").getResultList();
        studentTableView.getItems().setAll(students);
        em.close();
        textArea.clear();
        textArea.appendText("SELECT s \n"
                + "FROM Student s\n"
                + "WHERE s.major = :major\n"
                + ":major = Software Engineering");
    }

    @FXML
    private void excellentGradeButtonHandle(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
        List<Student> students = em.createNamedQuery("Student.excellentGrade")
                .setParameter("grade", 90).getResultList();
        studentTableView.getItems().setAll(students);
        em.close();
        textArea.clear();
        textArea.appendText("SELECT s \n"
                + "FROM Student s\n"
                + "WHERE s.grade >= :grade\n"
                + ":grade = 90");
    }

    @FXML
    private void allPassOrderNameButtonHandle(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
        List<Student> students = em.createNamedQuery("Student.allPassOrderName")
                .setParameter("grade", 60).getResultList();
        studentTableView.getItems().setAll(students);
        em.close();
        textArea.clear();
        textArea.appendText("SELECT s \n"
                + "FROM Student s\n"
                + "WHERE s.grade >= :grade\n"
                + "ORDER BY s.name\n"
                + ":grade = 60");
    }

    @FXML
    private void CSandGradeButtonHandle(ActionEvent event) {
        EntityManager em = emf.createEntityManager();
        List<Student> students = em.createNamedQuery("Student.CSandGrade")
                .setParameter("major", "Computer Science").setParameter("grade", 70.0).getResultList();

        em.getTransaction().begin();
        students.stream().map((student) -> {
            student.setGrade(student.getGrade() + 3);
            return student;})
                .forEachOrdered((student) -> {
                    em.merge(student);
                });

        em.getTransaction().commit();
        studentTableView.getItems().setAll(students);
        em.close();
        textArea.clear();
        textArea.appendText("SELECT s \n"
                + "FROM Student s\n"
                + "WHERE s.major = :major\n"
                + "AND s.grade < :grade\n"
                + ":major = Computer Science\n"
                + ":grade = 70");
    }

    private void showStudents() {
        EntityManager em = emf.createEntityManager();
        List<Student> students = em.createNamedQuery("Student.findAll").getResultList();
        studentTableView.getItems().setAll(students);
        em.close();
    }

    private void showRegistration() {
        EntityManager em = emf.createEntityManager();
        List<Registration> registrations = em.createNamedQuery("Registration.findAll").getResultList();
        registrationTableView.getItems().setAll(registrations);
        em.close();
    }

    private void selectStudent() {
        Student student = studentTableView.getSelectionModel().getSelectedItem();
        if (student != null) {
            textFiledStudentId.setText(String.valueOf(student.getId()));
            textFiledName.setText(student.getName());
            textFiledMajor.setText(student.getMajor());
            textFiledGrade.setText(String.valueOf(student.getGrade()));
        }
    }

    private void clearFields() {
        textFiledStudentId.setText("");
        textFiledName.setText("");
        textFiledMajor.setText("");
        textFiledGrade.setText("");
        textFiledCourseId.setText("");
        textFiledSemester.setText("");

    }
}
