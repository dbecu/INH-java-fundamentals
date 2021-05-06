package nl.inholland;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

public class Teacher extends User{


    private final Scanner scanner = new Scanner(System.in);
    protected ArrayList<User> listOfUsers;

    public void setListOfUsers(ArrayList<User> listOfUsers)
    {
        this.listOfUsers = listOfUsers;
    }


    public Teacher() {}

    public Teacher(String username, String password, String firstName, String lastName, Date birthdate, int age) {
        super(username, password, firstName, lastName, birthdate, age);
    }


    public void showTeacherScreen(ArrayList<User> collectionOfUsers)
    {
        listOfUsers = collectionOfUsers;

        System.out.print("S. Display Students  |  T. Display Teacher |  A. Add Students  | R. Display Reports  |  X. Exit  | \n Please, enter a choice: ");

        char userChoice = scanner.next().charAt(0);

        Student student = new Student();

        switch (userChoice)
        {
            case 'S':
                student.showAllStudents(listOfUsers);
                break;
            case 'T':
                student.showAllTeachers(listOfUsers);
                break;
            case 'A':
                addStudent();
                break;
            case 'R':
                showReport(listOfUsers);
                showStudentReportOptions();

                break;
            case 'X':
                System.out.println("Leaving the program now...");
                System.exit(0);
                break;
            default:
                System.out.println("Please choose a correct option");
                break;
        }

        showTeacherScreen(listOfUsers);
    }

    protected void addStudent()
    {
        DataSeeder dataSeeder = new DataSeeder();

        try {
            String[] studentData = new String[7];

            System.out.print("Choose a username: ");
            studentData[0] = scanner.next();

            System.out.print("Choose a password: ");
            studentData[1] = scanner.next();

            System.out.print("Enter first name: ");
            studentData[2] = scanner.next();

            System.out.print("Enter last name: ");
            studentData[3] = scanner.next();

            System.out.print("Please enter date of birth in MM/DD/YYYY: ");
            studentData[4] = scanner.next();

            System.out.print("Enter group: ");
            studentData[5] = scanner.next();

            Date birthDate = new SimpleDateFormat("dd/MM/yyyy").parse(studentData[4]);

            Student newStudent = new Student(studentData[0], studentData[1], studentData[2], studentData[3],
                    birthDate, calculateAge(birthDate), studentData[5]);

            dataSeeder.addUser(newStudent);
            listOfUsers.add(newStudent);

        }
        catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private int calculateAge(Date birthDate)
    {
        Date currentDate = new Date();

        int age = currentDate.getYear() - birthDate.getYear();
        if ( birthDate.compareTo(currentDate) < 0)
        {
            age--;
        }

        return age;

    }

    protected void showReport(ArrayList<User> users)
    {
        System.out.println("\n  STUDENT RESULTS\n");
        System.out.printf("%-10s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s \n", "Id", "FirstName", "LastName", "Birthdate", "Age", "Group", "Java", "CSharp", "Python", "PHP");
        System.out.printf("%-10s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s \n", "**", "*********", "********", "*********", "***", "*****", "****", "******", "******", "***");

        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");

        for(int i = 0; i < users.size(); i++)
        {
            if (users.get(i).getClass() == Student.class)
            {

                Student student = (Student) users.get(i);

                System.out.printf("%-10s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s %-15s \n",
                        (i+1),
                        student.getFirstName(),
                        student.getLastName(),
                        dateFormat.format(student.getBirthdate()),
                        student.getAge(),
                        student.getGroup(),
                        student.getGrades()[0],
                        student.getGrades()[1],
                        student.getGrades()[2],
                        student.getGrades()[3]);
            }

        }
    }

    public String showStudentReport(int index, Student student)
    {
        String report = putDots("Student Id", Integer.toString(index));
        report += putDots("First name", student.getFirstName());
        report += putDots("Last name", student.getLastName());
        report += putDots("Age", Integer.toString(student.getAge()));

        report += String.format("\n\n%20s\n", "COURSES");
        report += putDots("Java", Integer.toString(student.getGrades()[0]));
        report += putDots("CSharp", Integer.toString(student.getGrades()[1]));
        report += putDots("Python", Integer.toString(student.getGrades()[2]));
        report += putDots("PHP", Integer.toString(student.getGrades()[3]));

        report += String.format("\n\n%20s\n", "RESULTS");

        String passed = "Passed";
        int retakes = 0;
        for (int i = 0; i < 4; i++)
        {
            if (student.getGrades()[i] < 55) {
                passed = "Not Passed";
                retakes++;
            }
        }

        report += putDots("Result", passed);
        report += putDots("Retakes", Integer.toString(retakes));

        //System.out.println(report + "\n\n");

        return report;

    }

    private String putDots(String text, String data)
    {
        int charLength = 20;

        String result = "\n" + text + "   ";

        for(int i = text.length(); i < charLength; i++)
        {
            result += ".";
        }

        result += "   " + data;

        return result;

    }

    private void showReportScreen(Student student)
    {

        System.out.print("A. Add (Update) Report  |  R. Display Reports  |  B. Back to Main  | X. Exit  |\n" +
                "Please, enter a choice: ");
        char userChoice = scanner.next().charAt(0);

        switch (userChoice) {
            case 'A':
                updateStudentReport(student);
                break;
            case 'R':
                showReport(listOfUsers);
                showStudentReportOptions();
                break;
            case 'B':
                showTeacherScreen(listOfUsers);
                break;
            case 'X':
                System.exit(0);
                break;
            default:
                System.out.println("Please enter a correct value!");
                break;
        }

        showReportScreen(student);
    }

    private void updateStudentReport(Student student)
    {
        Student oldStudent = new Student(student.getUsername(), student.getPassword(), student.getFirstName(), student.getLastName(),
                student.getBirthdate(), student.getAge(), student.getGroup(), student.getGrades());

        int[] newGrades = new int[4];

        try {
            System.out.printf("Current grade Java is %d Enter (new) grades: ", student.getGrades()[0]);
            newGrades[0] = Integer.parseInt(scanner.next());

            System.out.printf("Current grade CSharp is %d Enter (new) grades: ", student.getGrades()[1]);
            newGrades[1] = Integer.parseInt(scanner.next());

            System.out.printf("Current grade Python is %d Enter (new) grades: ", student.getGrades()[2]);
            newGrades[2] = Integer.parseInt(scanner.next());

            System.out.printf("Current grade PHP is %d Enter (new) grades: ", student.getGrades()[3]);
            newGrades[3] = Integer.parseInt(scanner.next());

            student.setGrades(newGrades);

            DataSeeder dataSeeder = new DataSeeder();
            dataSeeder.updateStudentGrade(oldStudent, student);
        }
        catch(Exception e)
        {
            System.out.println(e.getMessage() + "\nGrades returned to previous state.");
        }



    }

    protected void showStudentReportOptions()
    {
        while(true)
        {
            System.out.print("Enter student id (Report details) | Or 0 back to main menu: ");
            try
            {
                int index =  Integer.parseInt(scanner.next());
                if (index > 0 && index <= listOfUsers.size())
                {
                    Student student1 = (Student) listOfUsers.get(index - 1);
                    System.out.println(showStudentReport(index, student1));
                    showReportScreen(student1);
//                    showTeacherScreen(listOfUsers);
                }
//                if (index == 0)
//                    showTeacherScreen(listOfUsers);

                break;
            }
            catch(Exception e)
            {
                System.out.println(e.getMessage() + "\nPlease enter a correct value!!");
            }
        }
    }



}
