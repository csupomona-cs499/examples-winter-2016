package cs499.cpp.edu.l09_data_storage;

/**
 * Created by yusun on 2/15/16.
 */
public class Student {

    private String name;
    private int id;
    private int age;
    private String major;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "Student{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", id=" + id +
                ", major='" + major + '\'' +
                '}';
    }
}
