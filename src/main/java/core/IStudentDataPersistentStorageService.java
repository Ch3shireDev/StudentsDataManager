package core;

import java.util.Collection;


/**
 * Serwis umożliwiający zapis i odczyt danych studentów z i do zewnętrznego serwisu.
 */
public interface IStudentDataPersistentStorageService {

    /**
     * @param studentData Dane studentów w postaci kolekcji.
     * @throws Exception Wyjątek wyrzucany w przypadku błędu zapisu danych w serwisie.
     */
    void save(String filename, Collection<StudentData> studentData) throws Exception;

    /**
     * @return Dane studentów w postaci kolekcji.
     * @throws Exception Wyjątek wyrzucany w przypadku błędu odczytu danych w serwisie.
     */
    Collection<StudentData> load(String file) throws Exception;
}

