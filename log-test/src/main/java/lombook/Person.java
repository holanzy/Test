package lombook;

import lombok.*;


//@ToString(includeFieldNames = true)
public class Person {
    @Getter
    @Setter private String name;
    private int age;
    private int sex;

    public Person() {

    }
    public void method(String name) {
        this.name = name;
    }
}
