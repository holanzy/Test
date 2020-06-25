package reflect;

import java.io.Serializable;
import java.net.URL;

/**
 * Created by jinbiao.yao on 2019/3/25.
 */
public class Person implements Serializable {

    private String name;
    private int age;
    private Student student;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    Person() {
    }

    Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public void getUrl() {
        URL url = this.getClass().getClassLoader().getResource("/reflect.Person");
        //System.out.println(url.getPath());

        System.out.println(this.getClass().getResource("reflect/Person"));
        System.out.println(this.getClass().getResource("/"));
        System.out.println(this.getClass().getResource("Person.class"));
        System.out.println(this.getClass().getResource("/Person.class"));

    }
}