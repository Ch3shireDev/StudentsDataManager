package core.mockups;

import core.IStudentDataValidator;
import core.StudentData;

import java.util.Collection;

/**
 * Mockowy serwis walidujący dane.
 */
public class MockValidatorService implements IStudentDataValidator {

    /**
     * Czy serwis został użyty. Do celów testów.
     */
    public boolean isUsed = false;

    /**
     * Mockowa metoda zwracająca dane walidacyjne. Niezaimplementowana.
     * @param studentData Dane studenta do walidacji.
     * @return Powiadomienia o błędnej walidacji.
     */
    @Override
    public Collection<String> getMessages(StudentData studentData) {
        return null;
    }

    /**
     * Mockowa funkcja walidujące dane studenta. Zawsze zwraca prawdę.
     * @param studentData Dane studenta.
     * @return Zawsze prawda.
     */
    @Override
    public boolean validate(StudentData studentData) {
        isUsed = true;
        return true;
    }

    /**
     * Mockowa walidacja albumu. Zawsze zwraca prawdę.
     * @param album Numer albumu studenta.
     * @return Zawsze prawda.
     */
    @Override
    public boolean validateAlbum(String album) {
        return true;
    }

    /**
     * Mockowa walidacja imienia i nazwiska. Zawsze zwraca prawdę.
     * @param name Imię i nazwisko studenta.
     * @return Zawsze prawda.
     */
    @Override
    public boolean validateName(String name) {
        return true;
    }

    /**
     * Mockowa walidacja numeru grupy. Zawsze zwraca prawdę.
     * @param group Numer grupy studenta.
     * @return Zawsze prawda.
     */
    @Override
    public boolean validateGroup(String group) {
        return true;
    }
    /**
     * Mockowa walidacja punktów. Zawsze zwraca prawdę.
     * @param homeworkPoints Liczba punktów z pracy domowej studenta.
     * @return Zawsze prawda.
     */

    @Override
    public boolean validateHomeworkPoints(int homeworkPoints) {
        return true;
    }

    /**
     * Mockowa walidacja punktów. Zawsze zwraca prawdę.
     * @param activityPoints Liczba punktów za aktywność.
     * @return Zawsze prawda.
     */
    @Override
    public boolean validateActivityPoints(int activityPoints) {
        return true;
    }

    /**
     * Mockowa walidacja punktów. Zawsze zwraca prawdę.
     * @param projectPoints Liczba punktów z projektu.
     * @return Zawsze prawda.
     */
    @Override
    public boolean validateProjectPoints(int projectPoints) {
        return true;
    }
    /**
     * Mockowa walidacja punktów. Zawsze zwraca prawdę.
     * @param test1Points Liczba punktów z pierwszego kolokwium.
     * @return Zawsze prawda.
     */

    @Override
    public boolean validateTest1Points(int test1Points) {
        return true;
    }
    /**
     * Mockowa walidacja punktów. Zawsze zwraca prawdę.
     * @param test2Points Liczba punktów z drugiego kolokwium.
     * @return Zawsze prawda.
     */

    @Override
    public boolean validateTest2Points(int test2Points) {
        return true;
    }

    /**
     * Mockowa walidacja punktów. Zawsze zwraca prawdę.
     * @param examPoints Liczba punktów z egzaminu.
     * @return Zawsze prawda.
     */
    @Override
    public boolean validateExamPoints(int examPoints) {
        return true;
    }
}
