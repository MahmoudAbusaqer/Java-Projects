/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chapter3_Part1;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Mahmoud_Abusaqer
 */
public class Question2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        File file = new File("C:\\Users\\hp\\OneDrive\\Documents\\NetBeansProjects\\JavaProgramming3Assignments\\src\\Chapter3_Part1\\Question2.txt");
        file.createNewFile();
        Scanner scanner = new Scanner(file);
        Scanner scanner2 = new Scanner(file);
        Map<String, Integer> wordsMap = new HashMap<>();
        Map<Character, Integer> lettersMap = new HashMap<>();

        //The number of occurrences of each word in the string
        String line = scanner.nextLine();
        while (line != null) {
            String[] words = line.split(" ");
            for (String checkWord : words) {
                if (wordsMap.containsKey(checkWord)) {
                    wordsMap.put(checkWord, wordsMap.get(checkWord) + 1);
                } else {
                    wordsMap.put(checkWord, 1);
                }

            }
            if (scanner.hasNextLine()) {
                line = scanner.nextLine();
            } else {
                break;
            }

        }

        System.out.println("The number of occurrences of each word in the string");
        for (Map.Entry entry : wordsMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }

        //The number of occurrences of each letter in the string
        String line2 = scanner2.next();
        while (line2 != null) {
            char[] letters = line2.toCharArray();
            for (char checkLetter : letters) {
                if (lettersMap.containsKey(checkLetter)) {
                    lettersMap.put(checkLetter, lettersMap.get(checkLetter) + 1);
                } else {
                    lettersMap.put(checkLetter, 1);
                }
            }
            if (scanner2.hasNext()) {
                line2 = scanner2.next();
            } else {
                break;
            }

        }
        System.out.println("\nThe number of occurrences of each letter in the string");
        for (Map.Entry entry : lettersMap.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}
