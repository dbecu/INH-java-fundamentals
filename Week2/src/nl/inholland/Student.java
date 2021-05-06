package nl.inholland;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Student extends User{

    private final Scanner scanner = new Scanner(System.in);

    private int[] grades;

    public int[] getGrades() {
        return grades;
    }

    public void setGrades(int[] grades) {
        this.grades = grades;
    }

    private String group;

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public Student() {}

    public Student(String username, String password, String firstName, String lastName, Date birthdate, int age, String group) {
        super(username, password, firstName, lastName, birthdate, age);

        this.group = group;
        this.grades = new int[]{0, 0, 0, 0};
    }

    public Student(String username, String password, String firstName, String lastName, Date birthdate, int age, String group, int[] grades)
    {
        this(username, password, firstName, lastName, birthdate, age, group);
        this.grades = grades;
    }


    public void showStudentScreen(ArrayList<User> collectionOfUsers)
    {

        System.out.print("S. Display Students  |  T. Display Teacher  |  X. Exit \n Please, enter a choice: ");

        char userChoice = scanner.next().charAt(0);

        switch (userChoice)
        {
            case 'S':
                showAllStudents(collectionOfUsers);
                break;
            case 'T':
                showAllTeachers(collectionOfUsers);
                break;
            case 'X':
                System.out.println("Leaving the program now...");
                System.exit(0);
                break;
            default:
                System.out.println("Please choose a correct option");
                break;
        }
        showStudentScreen(collectionOfUsers);
    }

    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

    public void showAllStudents(ArrayList<User> listOfUsers)
    {
        System.out.printf("%-10s %-20s %-20s %-20s %-20s %-20s \n", "Id", "FirstName", "LastName", "Birthdate", "Age", "Group");
        System.out.printf("%-10s %-20s %-20s %-20s %-20s %-20s \n", "**", "*********", "********", "*********", "***", "*****");

        for(int i = 0; i < listOfUsers.size(); i++)
        {
            if (listOfUsers.get(i).getClass() == Student.class)
            {
                Student student = (Student) listOfUsers.get(i);

                System.out.printf("%-10d %-20s %-20s %-20s %-20s %-20s \n",
                        (i+1),
                        student.getFirstName(),
                        student.getLastName(),
                        dateFormat.format(student.getBirthdate()),
                        student.getAge(),
                        student.getGroup());
            }

        }
    }

    public void showAllTeachers(ArrayList<User> listOfUsers) {
        System.out.printf("%-10s %-20s %-20s %-20s %-20s \n", "Id", "FirstName", "LastName", "Birthdate", "Age");
        System.out.printf("%-10s %-20s %-20s %-20s %-20s \n", "**", "*********", "********", "*********", "***");

        for (int i = 0; i < listOfUsers.size(); i++) {
            if (listOfUsers.get(i).getClass() == Teacher.class) {

                Teacher teacher = (Teacher) listOfUsers.get(i);

                System.out.printf("%-10d %-20s %-20s %-20s %-20s \n",
                        (i+1),
                        teacher.getFirstName(),
                        teacher.getLastName(),
                        dateFormat.format(teacher.getBirthdate()),
                        teacher.getAge());
            }

        }
    }


}
