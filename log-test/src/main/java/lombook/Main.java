package lombook;

public class Main {
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("name1");
        person.method(null);
        System.out.println("String :"+person.toString());
        System.out.println("hash:"+person.hashCode());
        Person p = new Person();
        boolean a = person.equals(p);
        System.out.println("equal:"+a);
    }
}
