package nl.inholland;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static Scanner scanner;
    private static ArrayList<User> collectionOfUsers;

    private static Student student;
    private static Teacher teacher;
    private static Manager manager;

    public static void main(String[] args) {
        initialize();

        System.out.print("Enter username: ");
        String username = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        for(int i = 0; i < collectionOfUsers.size(); i++)
        {
            if (collectionOfUsers.get(i).getUsername().equals(username))
            {
                if (collectionOfUsers.get(i).getPassword().equals(password))
                {
                    System.out.println("Password success");
                    User user = collectionOfUsers.get(i);

                    if (Student.class.equals(user.getClass())) {
                        student.showStudentScreen(collectionOfUsers);
                    }
                    else if (Teacher.class.equals(user.getClass()))
                    {
                        teacher.showTeacherScreen(collectionOfUsers);
                    }
                    else if (Manager.class.equals(user.getClass()))
                    {
                        manager.showManagerScreen(collectionOfUsers);
                    }

                }
                else
                    System.out.println("Password failure");
            }
        }

    }

    private static void initialize()
    {
        scanner = new Scanner(System.in);

        DataSeeder dataSeeder = new DataSeeder();
        collectionOfUsers = dataSeeder.getUserList();

        student = new Student();
        teacher = new Teacher();
        manager = new Manager();

//        User user1 = new Student("emma", "emma12", "Emma", "Smith", new Date(1997-4-12), 23, "IT-02-A");
//        User user2 = new Student("jackie", "jackiechan", "Jack", "Brown", new Date(1993-7-8), 27, "IT-02-A");
//        User user3 = new Student("mike", "12345", "Michael", "Garcia", new Date(1999-1-11), 21, "IT-02-A");
//
//        User user4 = new Teacher("david", "davdavdav", "David", "Taylor", new Date(1965-15-6), 55);
//        User user5 = new Teacher("SOPH", "sophieanders", "Sophie", "Anderson", new Date(1987-6-1), 33);
//
//        collectionOfUsers.add(user1);
//        collectionOfUsers.add(user2);
//        collectionOfUsers.add(user3);
//        collectionOfUsers.add(user4);
//        collectionOfUsers.add(user5);
    }
}
