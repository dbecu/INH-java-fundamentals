package nl.inholland;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DataSeeder {

    private final String PATH_TO_FILE = "src//nl//inholland//userlist.csv";

    public ArrayList<User> getUserList() {
        ArrayList<User> userArrayList = new ArrayList<>();

        String line;

        try {
            BufferedReader csvReader = new BufferedReader(new FileReader(PATH_TO_FILE));
            while ((line = csvReader.readLine()) != null) {
                String[] data = line.split(",");

                switch (data[0]) {
                    case "basic":
                        int[] grades = { Integer.parseInt(data[8]), Integer.parseInt(data[9]), Integer.parseInt(data[10]), Integer.parseInt(data[11]) };

                        userArrayList.add(new Student(
                                data[1],
                                data[2],
                                data[3],
                                data[4],
                                new SimpleDateFormat("dd/MM/yyyy").parse(data[5]),
                                Integer.parseInt(data[6]),
                                data[7],
                                grades));
                        break;
                    case "editor":
                        userArrayList.add(new Teacher(
                                data[1],
                                data[2],
                                data[3],
                                data[4],
                                new SimpleDateFormat("dd/MM/yyyy").parse(data[5]),
                                Integer.parseInt(data[6])));
                        break;
                    case "admin":
                        userArrayList.add(new Manager(
                                data[1],
                                data[2],
                                data[3],
                                data[4],
                                new SimpleDateFormat("dd/MM/yyyy").parse(data[5]),
                                Integer.parseInt(data[6])));
                    default:
                        break;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return userArrayList;
    }

    public void addUser(User user) {
        try {

            // Open given file in append mode.
            BufferedWriter out = new BufferedWriter(
                    new FileWriter(PATH_TO_FILE, true));

            out.newLine();
            out.write(convertUserToFileString(user));
            out.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private String convertUserToFileString(User user)
    {
        String userString = "";

        if (user.getClass() == Student.class)
        {
            Student student = (Student) user;

            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            userString = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s,%s", "basic", student.getUsername(), student.getPassword(), student.getFirstName(),
                    student.getLastName(), formatter.format(student.getBirthdate()),  student.getAge(), student.getGroup(),
                    student.getGrades()[0], student.getGrades()[1], student.getGrades()[2], student.getGrades()[3]);
        }

        return userString;
    }

    public void updateStudentGrade(Student oldStudentData, Student newStudentData)
    {
        try {
            Scanner scanner = new Scanner(new File(PATH_TO_FILE));
            StringBuffer buffer = new StringBuffer();
            while (scanner.hasNextLine())
                buffer.append(scanner.nextLine() + System.lineSeparator());

            String fileContents = buffer.toString();
            //System.out.println(fileContents);
            scanner.close();

            String oldLine = convertUserToFileString(oldStudentData);
            String newLine = convertUserToFileString(newStudentData);
            fileContents = fileContents.replaceAll(oldLine, newLine);

            FileWriter writer = new FileWriter(PATH_TO_FILE);
            //System.out.println("");
            writer.append(fileContents);
            writer.flush();


        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }






    public void generateWord()
    {
        try {

            ArrayList<User> users = getUserList();

            Teacher teacher = new Teacher();
            int index = 1;

            for (User user : users) {
                //Blank Document
                XWPFDocument document = new XWPFDocument();
                //Write the Document in file system

                if(user.getClass().equals(Student.class)) {
                    FileOutputStream out = new FileOutputStream(
                            new File("Reports//" + index + " " + user.getFirstName() + " " + user.getLastName() + ".docx"));

                    //create Paragraph
                    XWPFParagraph paragraph = document.createParagraph();
                    XWPFRun run = paragraph.createRun();

                    String report = String.format("Report of student %s %s \n", user.getFirstName(), user.getLastName());
                    report += teacher.showStudentReport(index, (Student) user);

                    run.setText(report);
                    document.write(out);

                    //Close document
                    out.close();
                    System.out.println(index + " " + user.getFirstName() + " " + user.getLastName() + ".docx" + " written successfully");
                }

                index++;
            }
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
