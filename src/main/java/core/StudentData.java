package core;


public class StudentData {

    private String Album;
    private String Name;
    private String Group;
    private int Homework;
    private int Activity;
    private int Project;
    private int Kolokwium1;
    private int Kolokwium2;
    private int Exam;

    public StudentData(String album, String name, String group) {
        Album = album;
        Name = name;
        Group = group;
    }

    public String getAlbum() {
        return Album;
    }


    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGroup() {
        return Group;
    }

    public void setGroup(String group) {
        Group = group;
    }

    public int getHomework() {
        return Homework;
    }

    public void setHomework(int homework) {
        Homework = homework;
    }

    public int getActivity() {
        return Activity;
    }

    public void setActivity(int activity) {
        Activity = activity;
    }

    public int getProject() {
        return Project;
    }

    public void setProject(int project) {
        Project = project;
    }

    public int getKolokwium1() {
        return Kolokwium1;
    }

    public void setKolokwium1(int kolokwium1) {
        Kolokwium1 = kolokwium1;
    }

    public int getKolokwium2() {
        return Kolokwium2;
    }

    public void setKolokwium2(int kolokwium2) {
        Kolokwium2 = kolokwium2;
    }

    public int getExam() {
        return Exam;
    }

    public void setExam(int exam) {
        Exam = exam;
    }

    public int getSum() {
        return Homework + Activity + Kolokwium1 + Kolokwium2 + Exam + Project;
    }

}
