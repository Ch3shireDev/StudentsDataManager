package core;

import java.util.Collection;

public interface IStudentDataService {


    /**
     * @return Zwraca kopię listy studentów.
     * @throws Exception
     */
    Collection<StudentData> getAll() throws Exception;

    /**
     * Aktualizuje dane studenta o danym numerze albumu.
     *
     * @param studentData Dane studenta.
     * @throws Exception
     */
    void update(StudentData studentData) throws Exception;

    /**
     * Usuwa dane studenta o podanym numerze albumu.
     *
     * @param album Numer albumu.
     * @throws Exception
     */
    void delete(String album) throws Exception;

    /**
     * Dodaje dane studenta.
     *
     * @param studentData Dane studenta.
     */
    void add(StudentData studentData) throws Exception;

    /**
     * Zapisuje dane studentów.
     *
     * @throws Exception
     */
    void save() throws Exception;

    /**
     * Ładuje dane studentów.
     */
    void load() throws Exception;


    /**
     * Zwraca dane studenta na podstawie numeru albumu.
     */
    StudentData get(String album) throws Exception;

    /**
     * @return Liczba załadowanych obiektów StudentData.
     */
    int size() throws Exception;

    /**
     * Kasuje załadowane dane studentów.
     */
    void clear() throws Exception;

    /**
     * Sprawdza czy istnieją dane studenta o podanym numerze albumu.
     */
    boolean exists(String album) throws Exception;

    /**
     * Waliduje dane studenta.
     *
     * @param studentData Dane studenta
     * @return Prawda jeśli dane studenta są poprawne, fałsz w przeciwnym wypadku.
     */
    boolean validate(StudentData studentData);
}
