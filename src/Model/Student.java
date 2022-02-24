/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author MuhammedSameeh
 */
public class Student {

    /**
     * Classes Attributes
     */
    private static int st_db_id = 0;

    private int id;
    private String fullName;
    private String email;
    private String password;
    private String phoneNumber;
    private int level;
    private String GPA;

    /**
     * Getter and Setter
     *
     * @return
     */
    public static int getSt_db_id() {
        return st_db_id;
    }

    public static void setSt_db_id(int st_db_id) {
        Student.st_db_id = st_db_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getLevel() {
        return level;
    }

    public String getGPA() {
        return GPA;
    }

    public void setGPA(String GPA) {
        this.GPA = GPA;
    }

}
