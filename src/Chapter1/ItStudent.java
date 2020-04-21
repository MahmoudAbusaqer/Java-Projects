/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Chapter1;

/**
 *
 * @author Mahmoud_Abusaqer
 */
public class ItStudent extends Student {

    public ItStudent(int id, String name, String major, int mid, int project, int finalExam) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.grade = mid * 0.30 + project * 0.30 + finalExam * 0.40;
    }
}
