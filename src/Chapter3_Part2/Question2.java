/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chapter3_Part2;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 *
 * @author Mahmoud_Abusaqer
 */
public class Question2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        Pattern pattern = Pattern.compile("");
        Files.lines(Paths.get("C:\\Users\\hp\\OneDrive\\Documents\\NetBeansProjects\\JavaProgramming3Assignments\\src\\Chapter3_Part2\\Question2.txt"))
                .flatMap(line -> pattern.splitAsStream(line))
                .filter(s -> !s.equals(" "))
                .collect(Collectors.groupingBy(String::toString, Collectors.counting()))
                .forEach((character, count) -> System.out.println(character + " : " + count));
    }

}
