package cs499.cpp.edu.l08_firebase_demo;

/**
 * Created by yusun on 2/8/16.
 */
public class Student {

    private String name;
    private int age;
    private String course;

    public Student() {

    }

    public Student(String name, int age, String course) {
        this.age = age;
        this.course = course;
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
