package nl.inholland;

import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Manager extends Teacher {

    public  Manager() {}
    public Manager(String username, String password, String firstName, String lastName, Date birthdate, int age) {
        super(username, password, firstName, lastName, birthdate, age);
    }

    private final Scanner scanner = new Scanner(System.in);
    private ArrayList<User> managerListOfUsers;

    public void showManagerScreen(ArrayList<User> collectionOfUsers)
    {
        managerListOfUsers = collectionOfUsers;

        System.out.print("S. Display Students  |  T. Display Teacher |  A. Add Students  | R. Display Reports  |  V. Save Reports |  X. Exit  | \n Please, enter a choice: ");

        char userChoice = scanner.next().charAt(0);

        Student student = new Student();

        switch (userChoice)
        {
            case 'S':
                student.showAllStudents(managerListOfUsers);
                break;
            case 'T':
                student.showAllTeachers(managerListOfUsers);
                break;
            case 'A':
                addStudent();
                break;
            case 'R':
                showReport(managerListOfUsers);
                showStudentReportOptions();
                break;
            case 'V':
                DataSeeder seeder = new DataSeeder();
                seeder.generateWord();
                break;
            case 'X':
                System.out.println("Leaving the program now...");
                System.exit(0);
                break;
            default:
                System.out.println("Please choose a correct option");
                break;
        }

        showManagerScreen(managerListOfUsers);

    }
}
