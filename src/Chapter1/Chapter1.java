/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chapter1;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;

/**
 *
 * @author Mahmoud_Abusaqer
 */
public class Chapter1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Student[] students = {new ItStudent(1, "Ahmad", "CS", 80, 88, 85),
            new ItStudent(2, "Mahmoud", "SD", 75, 90, 92),
            new ItStudent(3, "Omar", "CS", 94, 78, 90),
            new ArtStudent(4, "Abood", "Art", 71, 80, 95),
            new ArtStudent(5, "Osama", "Art", 89, 80, 88),
            new ArtStudent(6, "Haitam", "Art", 81, 60, 84)};
        Arrays.sort(students, new Student() {
        });

        File file = new File("C:\\Users\\hp\\OneDrive\\Documents\\NetBeansProjects\\JavaProgramming3Assignments\\src\\Chapter1\\EmployeeApp.txt");
        file.createNewFile();
        FileWriter crunchifyWriter;
        crunchifyWriter = new FileWriter(file.getAbsoluteFile(), false);
        BufferedWriter bufferWriter = new BufferedWriter(crunchifyWriter);
        for (int i = 0; i < students.length; i++) {
            bufferWriter.write(students[i].toString() + "\n");
        }
        bufferWriter.close();
    }
}
