package clone;

/**
 * Created by jinbiao.yao on 2019/4/22.
 */
public class App {
    public static void main(String[] args) throws CloneNotSupportedException {
        Person person = new Person();
        person.setName("tom");
        person.setAge(10);
        Student student = new Student();
        student.setName("student1");
        person.setStudent(student);

        Person person1 = (Person)person.clone();
        System.out.println(person+";"+person1);

        System.out.println(person.getStudent().getName()+";"+person1.getStudent().getName());
        //Student student2 = new Student();
        //student.setName("student2");
        Student student3 = person.getStudent();
        student3.setName("修改");
        //person.setStudent();
        System.out.println(person.getStudent().getName()+";"+person1.getStudent().getName());
    }
}
