package core;

import java.util.Collection;

public interface IStudentDataService {
    Collection<StudentData> getData();

    void update(StudentData studentData);

    void delete(StudentData studentData);

    void add(StudentData studentData);

    void save();

    void load();
}
