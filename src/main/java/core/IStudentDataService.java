package core;

import java.util.Collection;

public interface IStudentDataService {
    Collection<StudentData> getData() throws Exception;

    void update(StudentData studentData) throws Exception;

    void delete(StudentData studentData) throws Exception;

    void add(StudentData studentData) throws Exception;

    void save() throws Exception;

    void load() throws Exception;

    void deleteAll() throws Exception;

    StudentData get(String album) throws Exception;

    int size() throws Exception;

    void clear() throws Exception;

    boolean exists(String album) throws Exception;
}
