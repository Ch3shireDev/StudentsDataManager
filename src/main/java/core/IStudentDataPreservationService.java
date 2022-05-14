package core;

import java.util.Collection;

interface IStudentDataPreservationService {
    void save();

    Collection<StudentData> load();
}
