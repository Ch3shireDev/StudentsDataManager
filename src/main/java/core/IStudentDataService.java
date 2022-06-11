package core;

import java.util.Collection;

/**
 * Serwis danych studentów.
 */
public interface IStudentDataService {


    /**
     * @return Zwraca kopię listy studentów.
     * @throws Exception Wyjątek w przypadku błędu komunikacji z listą studentów.
     */
    Collection<StudentData> getAll() throws Exception;

    /**
     * Aktualizuje dane studenta o danym numerze albumu.
     *
     * @param studentData Dane studenta.
     * @throws Exception Wyjątek w przypadku błędu połączenia z listą studentów.
     */
    void update(StudentData studentData) throws Exception;

    /**
     * Usuwa dane studenta o podanym numerze albumu.
     *
     * @param album Numer albumu.
     * @throws Exception Wyjątek w przypadku błędu połączenia z listą studentów.
     */
    void delete(String album) throws Exception;

    /**
     * Dodaje dane studenta.
     *
     * @param studentData Dane studenta.
     * @throws ValidationException Wyjątek w przypadku nieprawidłowych danych studenta.
     */
    void add(StudentData studentData) throws ValidationException;

    /**
     * Zapisuje dane studentów.
     *
     * @param storageService Moduł przechowujący dane studenta
     * @throws Exception Wyjątek w przypadku błędu zapisu w zewnętrznym serwisie.
     */
    void save(IStudentDataPersistentStorageService storageService) throws Exception;

    /**
     * Ładuje dane studentów.
     *
     * @param reader Obiekt przechowywania danych z którego są czytane dane studentów.
     * @throws Exception Wyjątek w przypadku błędu ładowania z zewnętrznego serwisu.
     */
    void load(IStudentDataPersistentStorageService reader) throws Exception;


    /**
     * Pobiera dane studenta na podstawie numeru albumu.
     *
     * @param album Numer albumu studenta.
     * @return Dane studenta.
     * @throws Exception Wyjątek wyrzucany w przypadku braku numeru studenta, lub błędu wyszukiwania danych.
     */
    StudentData get(String album) throws Exception;


    /**
     * @return Liczba obiektów StudentData w bazie danych.
     * @throws Exception Wyjątek w przypadku błędu połączenia z listą studentów.
     */
    int size() throws Exception;


    /**
     * Kasuje załadowane dane studentów.
     *
     * @throws Exception Wyjątek w przypadku błędu komunikacji z listą studentów.
     */
    void clear() throws Exception;


    /**
     * Sprawdza czy istnieją dane studenta o podanym numerze albumu.
     *
     * @param album Numer albumu.
     * @return Prawda jeśli istnieją dane studenta o podanym numerze albumu, fałsz w przeciwnym wypadku.
     * @throws Exception Wyjątek w przypadku błędu połączenia z listą studentów.
     */
    boolean exists(String album) throws Exception;

    /**
     * Waliduje dane studenta.
     *
     * @param studentData Dane studenta.
     * @return Prawda jeśli dane studenta są poprawne, fałsz w przeciwnym wypadku.
     */
    boolean validate(StudentData studentData);

}
