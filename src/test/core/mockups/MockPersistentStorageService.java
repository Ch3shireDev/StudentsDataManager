package mockups;

import core.IStudentDataPersistentStorageService;
import core.StudentData;

import java.util.Collection;
import java.util.LinkedList;

public class MockPersistentStorageService implements IStudentDataPersistentStorageService {

    Collection<StudentData> students;

    public MockPersistentStorageService() {
        students = new LinkedList<>();
    }

    public Collection<StudentData> getSavedStudentData() {
        return students;
    }

    @Override
    public void save(Collection<StudentData> students) {
        this.students = new LinkedList<>(students);
    }

    @Override
    public Collection<StudentData> load() {
        return new LinkedList<>(students);
    }
}

