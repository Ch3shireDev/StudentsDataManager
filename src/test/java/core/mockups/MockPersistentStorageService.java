package core.mockups;

import core.IStudentDataPersistentStorageService;
import core.StudentData;

import java.util.Collection;
import java.util.LinkedList;

/**
 * Mockowy serwis pamięci trwałej danych studentów.
 */
public class MockPersistentStorageService implements IStudentDataPersistentStorageService {

    /**
     * Pojemnik na dane studentów.
     */
    Collection<StudentData> students;

    /**
     * Konstruktor.
     */
    public MockPersistentStorageService() {
        students = new LinkedList<>();
    }


    /**
     * Zwraca zapisane dane studentów.
     * @return
     */
    public Collection<StudentData> getSavedStudentData() {
        return students;
    }

    /**
     * Zapisuje dane studentów w pamięci trwałej.
     * @param students Dane studentów.
     */
    @Override
    public void save(Collection<StudentData> students) {
        this.students = new LinkedList<>(students);
    }

    /**
     * Odczytuje dane studentów z pamięci trwałej.
     * @return Dane studentów.
     */
    @Override
    public Collection<StudentData> load() {
        return new LinkedList<>(students);
    }
}

