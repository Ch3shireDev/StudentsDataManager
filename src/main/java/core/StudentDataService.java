package core;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Optional;

public class StudentDataService implements IStudentDataService {

    private final IStudentDataPreservationService preservationService;
    Collection<StudentData> students;
    private IStudentDataValidator validatorService;

    public StudentDataService(IStudentDataPreservationService preservationService) {
        students = new LinkedList<>();
        this.preservationService = preservationService;
    }

    public StudentData get(String album) throws Exception {
        Optional<StudentData> sd = students.stream().filter(s -> s.getAlbum() == album).findFirst();
        if (sd.isEmpty()) throw new Exception(String.format("Brak studenta o numerze albumu %s", album));
        return sd.get();
    }

    @Override
    public int size() {
        return students.size();
    }

    @Override
    public void clear() throws Exception {
        students.clear();
    }

    @Override
    public Collection<StudentData> getData() throws Exception {
        return students;
    }

    @Override
    public void update(StudentData studentData) throws Exception {
        String album = studentData.getAlbum();
        StudentData student = get(album);
        student.set(studentData);
    }

    @Override
    public void delete(StudentData studentData) throws Exception {
        String album = studentData.getAlbum();
        StudentData data = get(album);
        students.remove(data);
    }

    public boolean exists(String album) throws Exception {
        return students.stream().anyMatch(studentData1 -> studentData1.getAlbum() == album);
    }

    @Override
    public void add(StudentData studentData) throws Exception {
        if (!validate(studentData)) throw new Exception("Niepoprawne dane.");
        String album = studentData.getAlbum();
        if (exists(album)) {
            throw new Exception(String.format("Student o numerze albumu %s już istnieje.", album));
        }
        students.add(studentData);
    }

    @Override
    public void save() throws Exception {
        preservationService.save(students);
    }

    @Override
    public void load() throws Exception {
        students = preservationService.load();
    }

    @Override
    public void deleteAll() throws Exception {
        students.clear();
    }

    public boolean validate(StudentData studentData) {
        if (validatorService == null) return true;
        return validatorService.validate(studentData);
    }
}

