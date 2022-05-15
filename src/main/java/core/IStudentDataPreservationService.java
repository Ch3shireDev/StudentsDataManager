package core;

import java.util.Collection;


public interface IStudentDataPreservationService {
    void save(Collection<StudentData> studentData) throws Exception;

    Collection<StudentData> load() throws Exception;
}

