/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chapter3_Part1;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Mahmoud_Abusaqer
 */
public class Question1 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Random random = new Random();
            int x = random.nextInt(101);
            list.add(x);
        }

        list.stream().sorted();

        Operation<Integer> sumOper = (number) -> {
            int sum = 0;
            for (int i = 0; i < number; i++) {
                sum += list.get(i);
            }
            return sum;
        };
        Operation2<Double> avgOper = (sum) -> {
            double avg = 0;
            avg = sum / list.size();
            return avg;
        };
        int sum = sumOper.length(list.size());
        System.out.println("Sum: " + sum);
        System.out.println("Average: " + avgOper.average((double) sum));
    }

    private interface Operation<T> {

        T length(T x);
    }

    private interface Operation2<T> {

        T average(T y);
    }
}
