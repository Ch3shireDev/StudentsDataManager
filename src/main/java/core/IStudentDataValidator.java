package core;

import java.util.Collection;

/**
 * Serwis walidujący dane studenta.
 */
public interface IStudentDataValidator {


    /**
     * Zwraca informacje na temat problemów z obiektem danych studenta.
     *
     * @param studentData Dane studenta do walidacji.
     * @return Lista powiadomień o błędach.
     */
    Collection<String> getMessages(StudentData studentData);

    /**
     * Waliduje dane studenta.
     *
     * @param studentData Dane studenta.
     * @return Prawda jeśli dane są poprawne, fałsz w przeciwnym wypadku.
     */
    boolean validate(StudentData studentData);

    /**
     * Waliduje numer albumu.
     *
     * @param album Numer albumu studenta.
     * @return Prawda jeśli dane są poprawne, fałsz w przeciwnym wypadku.
     */
    boolean validateAlbum(String album);

    /**
     * Waliduje imię i nazwisko studenta.
     *
     * @param name Imię i nazwisko studenta.
     * @return Prawda jeśli dane są poprawne, fałsz w przeciwnym wypadku.
     */
    boolean validateName(String name);

    /**
     * Waliduje grupę studenta.
     *
     * @param group Grupa studenta.
     * @return Prawda jeśli dane są poprawne, fałsz w przeciwnym wypadku.
     */
    boolean validateGroup(String group);


    /**
     * Waliduje punkty z pracy domowej.
     *
     * @param homeworkPoints Liczba punktów z pracy domowej.
     * @return Prawda jeśli dane są poprawne, fałsz w przeciwnym wypadku.
     */
    boolean validateHomeworkPoints(int homeworkPoints);

    /**
     * Waliduje liczbę punktów za aktywność.
     *
     * @param activityPoints Liczba punktów za aktywność.
     * @return Prawda jeśli dane są poprawne, fałsz w przeciwnym wypadku.
     */
    boolean validateActivityPoints(int activityPoints);

    /**
     * Waliduje liczbę punktów z projektu.
     *
     * @param projectPoints Liczba punktów z projektu.
     * @return Prawda jeśli dane są poprawne, fałsz w przeciwnym wypadku.
     */
    boolean validateProjectPoints(int projectPoints);

    /**
     * Waliduje liczbę punktów z pierwszego kolokwium.
     *
     * @param test1Points Liczba punktów z pierwszego kolokwium.
     * @return Prawda jeśli dane są poprawne, fałsz w przeciwnym wypadku.
     */
    boolean validateTest1Points(int test1Points);

    /**
     * Waliduje liczbę punktów z drugiego kolokwium.
     *
     * @param test2Points Liczba punktów z drugiego kolokwium.
     * @return Prawda jeśli dane są poprawne, fałsz w przeciwnym wypadku.
     */
    boolean validateTest2Points(int test2Points);

    /**
     * Waliduje liczbę punktów z egzaminu.
     *
     * @param examPoints Liczba punktów z egzaminu.
     * @return Prawda jeśli dane są poprawne, fałsz w przeciwnym wypadku.
     */
    boolean validateExamPoints(int examPoints);
}
