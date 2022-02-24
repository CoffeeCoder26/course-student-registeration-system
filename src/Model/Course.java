/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author user
 */
public class Course {

    /**
     * Classes Attributes
     */

    private String courseId, course1Name, course2Name, course3Name, course4Name, course5Name, course6Name;
    private int numOfHours;

    /**
     * Getter and Setter
     * @return
     */
    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getCourse1Name() {
        return course1Name;
    }

    public void setCourse1Name(String course1Name) {
        this.course1Name = course1Name;
    }

    public String getCourse2Name() {
        return course2Name;
    }

    public void setCourse2Name(String course2Name) {
        this.course2Name = course2Name;
    }

    public String getCourse3Name() {
        return course3Name;
    }

    public void setCourse3Name(String course3Name) {
        this.course3Name = course3Name;
    }

    public String getCourse4Name() {
        return course4Name;
    }

    public void setCourse4Name(String course4Name) {
        this.course4Name = course4Name;
    }

    public String getCourse5Name() {
        return course5Name;
    }

    public void setCourse5Name(String course5Name) {
        this.course5Name = course5Name;
    }

    public String getCourse6Name() {
        return course6Name;
    }

    public void setCourse6Name(String course6Name) {
        this.course6Name = course6Name;
    }

    public void setNumOfHours(int numOfHours) {
        this.numOfHours = numOfHours;
    }

    public int getNumOfHours() {
        return numOfHours;
    }
}
