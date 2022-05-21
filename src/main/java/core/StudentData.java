package core;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

public class StudentData implements Serializable {

    private String album;
    private String name;
    private String group;
    private int homeworkPoints;
    private int activityPoints;
    private int projectPoints;
    private int test1Points;
    private int test2Points;
    private int examPoints;

    public StudentData() {
    }

    /**
     * Konstruktor obiektu StudentData.
     *
     * @param album Numer albumu (unikalny).
     */
    public StudentData(String album) {
        this.album = album;
    }

    /**
     * Konstruktor klonujący dane studenta. Tworzy nowy obiekt o identycznych parametrach, razem z numerem albumu.
     *
     * @param studentData Dane studenta.
     */
    public StudentData(StudentData studentData) {
        this.album = studentData.album;
        name = studentData.name;
        group = studentData.group;
        homeworkPoints = studentData.homeworkPoints;
        activityPoints = studentData.activityPoints;
        projectPoints = studentData.projectPoints;
        test1Points = studentData.test1Points;
        test2Points = studentData.test2Points;
        examPoints = studentData.examPoints;
    }

    /**
     * Konstruktor obiektu Studentdata.
     *
     * @param album Numer albumu (unikalny).
     * @param name  Imię i nazwisko studenta.
     * @param group Grupa zajęciowa.
     */
    public StudentData(String album, String name, String group) {
        this.album = album;
        this.name = name;
        this.group = group;
    }

    public StudentData(String album, String name, String group, int homeworkPoints, int activityPoints, int projectPoints, int test1Points, int test2Points, int examPoints) {
        this.album = album;
        this.name = name;
        this.group = group;
        this.homeworkPoints = homeworkPoints;
        this.activityPoints = activityPoints;
        this.projectPoints = projectPoints;
        this.test1Points = test1Points;
        this.test2Points = test2Points;
        this.examPoints = examPoints;
    }

    /**
     * @return Numer albumu studenta (unikalny).
     */
    public String getAlbum() {
        return album;
    }


    /**
     * @return Imię i nazwisko studenta.
     */
    public String getName() {
        return name;
    }

    /**
     * Ustawia imię i nazwisko studenta.
     *
     * @param name Imię i nazwisko studenta
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Grupa zajęciowa.
     */
    public String getGroup() {
        return group;
    }

    /**
     * Ustawia grupę zajęciową studenta.
     *
     * @param group Grupa zajęciowa studenta.
     */
    public void setGroup(String group) {
        this.group = group;
    }

    /**
     * @return Liczba punktów z prac domowych.
     */
    public int getHomeworkPoints() {
        return homeworkPoints;
    }

    /**
     * Ustawia liczbę punktów z prac domowych (0-5 pkt).
     *
     * @param homeworkPoints Liczba punktów z prac domowych.
     */
    public void setHomeworkPoints(int homeworkPoints) {
        this.homeworkPoints = homeworkPoints;
    }

    /**
     * @return Liczba punktów za aktywność.
     */
    public int getActivityPoints() {
        return activityPoints;
    }

    /**
     * Ustawia liczbę punktów za aktywność (0-5 pkt).
     *
     * @param activityPoints Liczba punktów za aktywność.
     */
    public void setActivityPoints(int activityPoints) {
        this.activityPoints = activityPoints;
    }

    /**
     * @return Liczba punktów z projektu.
     */
    public int getProjectPoints() {
        return projectPoints;
    }

    /**
     * Ustawia liczbę punktów z projektu (0-10 pkt).
     *
     * @param projectPoints Liczba punktów z projektu.
     */
    public void setProjectPoints(int projectPoints) {
        this.projectPoints = projectPoints;
    }

    /**
     * @return Liczba punktów z pierwszego kolokwium.
     */
    public int getTest1Points() {
        return test1Points;
    }

    /**
     * Ustawia liczbę punktów z pierwszego kolokwium (0-20 pkt).
     *
     * @param test1Points Liczba punktów z pierwszego kolokwium.
     */
    public void setTest1Points(int test1Points) {
        this.test1Points = test1Points;
    }

    /**
     * @return Liczba punktów z drugiego kolokwium.
     */
    public int getTest2Points() {
        return test2Points;
    }

    /**
     * Ustawia liczbę punktów z drugiego kolokwium (0-20 pkt).
     *
     * @param test2Points Liczba punktów z drugiego kolokwium.
     */
    public void setTest2Points(int test2Points) {
        this.test2Points = test2Points;
    }

    /**
     * @return Liczba punktów z egzaminu.
     */
    public int getExamPoints() {
        return examPoints;
    }

    /**
     * Ustawia liczbę punktów z egzaminu (0-40 pkt).
     *
     * @param examPoints Liczba punktów z egzaminu.
     */
    public void setExamPoints(int examPoints) {
        this.examPoints = examPoints;
    }

    /**
     * @return Całkowita suma punktów.
     */
    @JsonIgnore
    public int getSum() {
        return homeworkPoints + activityPoints + test1Points + test2Points + examPoints + projectPoints;
    }

    /**
     * Przypisuje wszystkie wartości z obiektu poza numerem albumu.
     *
     * @param studentData Dane studenta.
     */
    public void set(StudentData studentData) {
        name = studentData.name;
        group = studentData.group;
        homeworkPoints = studentData.homeworkPoints;
        activityPoints = studentData.activityPoints;
        projectPoints = studentData.projectPoints;
        test1Points = studentData.test1Points;
        test2Points = studentData.test2Points;
        examPoints = studentData.examPoints;
    }
}
