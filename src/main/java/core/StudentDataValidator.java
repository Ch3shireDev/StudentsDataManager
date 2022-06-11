package core;


import common.LocalizationUtil;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * Serwis walidujący dane studenta.
 */
public class StudentDataValidator implements IStudentDataValidator {


    /**
     * Konstruktor serwisu walidującego.
     */
    public StudentDataValidator() {
    }

    /**
     * Waliduje dane studenta.
     *
     * @param studentData Dane studenta.
     * @return Prawda jeśli dane są poprawne, fałsz w przeciwnym wypadku.
     */
    public boolean validate(StudentData studentData) {
        if (!validateAlbum(studentData.getAlbum())) return false;
        if (!validateName(studentData.getName())) return false;
        if (!validateGroup(studentData.getGroup())) return false;
        if (!validateHomeworkPoints(studentData.getHomeworkPoints())) return false;
        if (!validateActivityPoints(studentData.getActivityPoints())) return false;
        if (!validateProjectPoints(studentData.getProjectPoints())) return false;
        if (!validateTest1Points(studentData.getTest1Points())) return false;
        if (!validateTest2Points(studentData.getTest2Points())) return false;
        return validateExamPoints(studentData.getExamPoints());
    }


    /**
     * Waliduje punkty z pracy domowej.
     *
     * @param homeworkPoints Liczba punktów z pracy domowej.
     * @return Prawda jeśli dane są poprawne, fałsz w przeciwnym wypadku.
     */
    public boolean validateHomeworkPoints(int homeworkPoints) {
        return homeworkPoints >= 0 && homeworkPoints <= 5;
    }

    /**
     * Waliduje numer albumu.
     *
     * @param album Numer albumu studenta.
     * @return Prawda jeśli dane są poprawne, fałsz w przeciwnym wypadku.
     */
    @Override
    public boolean validateAlbum(String album) {
        if (album == null) return false;
        if (album.equals("")) return false;
        return album.matches("\\d+");
    }

    /**
     * Waliduje imię i nazwisko studenta.
     *
     * @param name Imię i nazwisko studenta.
     * @return Prawda jeśli dane są poprawne, fałsz w przeciwnym wypadku.
     */
    @Override
    public boolean validateName(String name) {
        if (name == null) return false;
        if (name.isBlank()) return false;
        return name.split("\\s").length >= 2;
    }

    /**
     * Waliduje grupę studenta.
     *
     * @param group Grupa studenta.
     * @return Prawda jeśli dane są poprawne, fałsz w przeciwnym wypadku.
     */
    @Override
    public boolean validateGroup(String group) {
        if (group == null) return false;
        return !group.isBlank();
    }

    /**
     * Waliduje liczbę punktów za aktywność.
     *
     * @param activityPoints Liczba punktów za aktywność.
     * @return Prawda jeśli dane są poprawne, fałsz w przeciwnym wypadku.
     */
    @Override
    public boolean validateActivityPoints(int activityPoints) {
        return activityPoints >= 0 && activityPoints <= 5;
    }

    /**
     * Waliduje liczbę punktów z projektu.
     *
     * @param projectPoints Liczba punktów z projektu.
     * @return Prawda jeśli dane są poprawne, fałsz w przeciwnym wypadku.
     */
    @Override
    public boolean validateProjectPoints(int projectPoints) {
        return projectPoints >= 0 && projectPoints <= 10;
    }

    /**
     * Waliduje liczbę punktów z pierwszego kolokwium.
     *
     * @param test1Points Liczba punktów z pierwszego kolokwium.
     * @return Prawda jeśli dane są poprawne, fałsz w przeciwnym wypadku.
     */
    @Override
    public boolean validateTest1Points(int test1Points) {
        return test1Points >= 0 && test1Points <= 20;
    }

    /**
     * Waliduje liczbę punktów z drugiego kolokwium.
     *
     * @param test2Points Liczba punktów z drugiego kolokwium.
     * @return Prawda jeśli dane są poprawne, fałsz w przeciwnym wypadku.
     */
    @Override
    public boolean validateTest2Points(int test2Points) {
        return test2Points >= 0 && test2Points <= 20;
    }

    /**
     * Waliduje liczbę punktów z egzaminu.
     *
     * @param examPoints Liczba punktów z egzaminu.
     * @return Prawda jeśli dane są poprawne, fałsz w przeciwnym wypadku.
     */
    @Override
    public boolean validateExamPoints(int examPoints) {
        return examPoints >= 0 && examPoints <= 40;
    }


    /**
     * Zwraca informacje na temat nieprawidłowości w danych studenta.
     *
     * @param studentData Dane studenta.
     * @return Lista informacji o nieprawidłowościach.
     */
    @Override
    public Collection<String> getMessages(StudentData studentData) {
        List<String> list = new ArrayList<String>();
        if (!validateAlbum(studentData.getAlbum())) list.add(LocalizationUtil.getText("invalidAlbumMessage"));
        if (!validateName(studentData.getName())) list.add(LocalizationUtil.getText("invalidNameMessage"));
        if (!validateGroup(studentData.getGroup())) list.add(LocalizationUtil.getText("invalidGroupMessage"));
        if (!validateHomeworkPoints(studentData.getHomeworkPoints()))
            list.add(LocalizationUtil.getText("invalidHomeworkPointsMessage"));
        if (!validateActivityPoints(studentData.getActivityPoints()))
            list.add(LocalizationUtil.getText("invalidActivityPointsMessage"));
        if (!validateProjectPoints(studentData.getProjectPoints()))
            list.add(LocalizationUtil.getText("invalidProjectPointsMessage"));
        if (!validateTest1Points(studentData.getTest1Points()))
            list.add(LocalizationUtil.getText("invalidTest1PointsMessage"));
        if (!validateTest2Points(studentData.getTest2Points()))
            list.add(LocalizationUtil.getText("invalidTest2PointsMessage"));
        if (!validateExamPoints(studentData.getExamPoints()))
            list.add(LocalizationUtil.getText("invalidExamPointsMessage"));
        return list;

    }
}

