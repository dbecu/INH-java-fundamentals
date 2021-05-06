package nl.inholland;

import java.util.*;

public class Main {

    private static Scanner scanner;

    public static void main(String[] args) {
	    initialize();

        int numberOfStudents = getNumberStudents();

	    Student[] collectionOfStudents = areStudentsPresent(numberOfStudents, getStudents(numberOfStudents));

        printStudents(numberOfStudents, collectionOfStudents);
    }


    private static void initialize()
    {
        scanner = new Scanner(System.in);
    }

    private static int getNumberStudents()
    {
        System.out.print("Number of students in the group? ");
        int numberOfStudents;

        while(true) {
            try {
                numberOfStudents = Integer.parseInt(scanner.nextLine());
                if(numberOfStudents > 0)
                    break;
                else
                    throw new Exception();
            } catch (Exception e) {
                System.out.print("Enter a proper number: ");
            }
        }
        return numberOfStudents;
    }

    private static Student[] getStudents(int numberOfStudents)
    {
        Student[] collectionOfStudents = new Student[numberOfStudents];

        for(int i = 0; i < numberOfStudents; i++)
        {
            System.out.print("Enter name of student " + (i+1) + ": ");
            collectionOfStudents[i] = new Student(scanner.nextLine());
        }

        for(int i = 0; i < numberOfStudents; i++)
        {
            System.out.println(collectionOfStudents[i].getName());
        }

        return collectionOfStudents;

    }

    private static Student[] areStudentsPresent(int numberOfStudents, Student[] collectionOfStudents) {

        for (int i = 0; i < numberOfStudents; i++) {

            String userInput;

            while (true) {
                System.out.print("Is student #" + (i + 1) + " " + collectionOfStudents[i].getName() + " present? (y/n) ");
                userInput = scanner.nextLine();

                if (userInput.equals("n")) {
                    collectionOfStudents[i].setPresent(false);
                    break;
                } else if (userInput.equals("y")) {
                    collectionOfStudents[i].setPresent(true);
                    break;
                } else
                    System.out.print("Try again: ");
            }
        }
        return collectionOfStudents;
    }

    private static void printStudents(int numberOfStudents, Student[] collectionOfStudents)
    {
        for(int i = 0; i < numberOfStudents; i++)
        {
            String endMessage = String.format("Student #%d %-10s Present: %s", i+1, collectionOfStudents[i].getName(), Boolean.toString(collectionOfStudents[i].isPresent()));
            System.out.println(endMessage);
        }
    }



}
