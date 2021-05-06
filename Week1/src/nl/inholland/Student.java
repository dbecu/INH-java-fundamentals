package nl.inholland;

public class Student {

    public String getName() {
        return name;
    }

    public boolean isPresent() {
        return present;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPresent(boolean present) {
        this.present = present;
    }

    private String name;
    private boolean present;

    public Student(String name)
    {
        this.name = name;
    }


}
