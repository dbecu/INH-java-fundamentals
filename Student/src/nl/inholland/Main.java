package nl.inholland;

public class Main {

    public static void main(String[] args) {

        Student student = new Student();
        student.name = "Emma";
        student.age = 21;

        System.out.println("Name: " + student.name);
        System.out.println("Age: " + student.age);
    }
}
