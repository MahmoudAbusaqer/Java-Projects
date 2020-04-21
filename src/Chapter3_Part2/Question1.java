/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chapter3_Part2;

import java.util.Scanner;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.function.UnaryOperator;

/**
 *
 * @author Mahmoud_Abusaqer
 */
public class Question1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Scanner questionAInput = new Scanner(System.in);
        Scanner questionBInput = new Scanner(System.in);
        Scanner questionDInput = new Scanner(System.in);

        //Question a
        System.out.println("Question a:");
        Consumer<Integer> accept = value -> System.out.printf("%5d", value);
        System.out.print("Please enter an intger value:");
        int intgerInput = questionAInput.nextInt();
        accept.accept(intgerInput);
        System.out.println("");

        //Question b
        System.out.println("Question b:");
        Function<String, String> text = String::toUpperCase;
        System.out.print("Please enter a text:");
        String textInput = questionBInput.nextLine();
        System.out.println(text.apply(textInput));

        //Question c
        System.out.println("Question c:");
        Supplier<String> print = () -> "Welcome to lambdas!";
        System.out.println(print.get());

        //Question d
        System.out.println("Question d:");
        UnaryOperator<Integer> cube = number -> number * number * number;
        System.out.print("Please enter an intger value:");
        int intgerInputCube = questionDInput.nextInt();
        System.out.println(cube.apply(intgerInputCube));
    }
}
