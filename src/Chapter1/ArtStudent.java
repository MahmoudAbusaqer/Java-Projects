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
public class ArtStudent extends Student {

    public ArtStudent(int id, String name, String major, int mid, int report, int finalExam) {
        this.id = id;
        this.name = name;
        this.major = major;
        this.grade = mid * 0.40 + report * 0.10 + finalExam * 0.50;
    }

}
