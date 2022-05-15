package mockups;

import core.IStudentDataPreservationService;
import core.StudentData;

import java.util.Collection;
import java.util.LinkedList;

public class MockPreservationService implements IStudentDataPreservationService {

    Collection<StudentData> students;

    public MockPreservationService() {
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

