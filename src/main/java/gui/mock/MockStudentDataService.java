//package gui.mock;
//
//import core.IStudentDataPersistentStorageService;
//import core.IStudentDataService;
//import core.StudentData;
//
//import java.util.Collection;
//import java.util.LinkedList;
//import java.util.Objects;
//import java.util.Optional;
//
//public class MockStudentDataService implements IStudentDataService {
//    private static final Collection<StudentData> mockData  = new LinkedList<>();
//
//    private final IStudentDataPersistentStorageService service;
//
//    public MockStudentDataService(IStudentDataPersistentStorageService service) {
//        this.service = service;
//        mockData.add(new StudentData("111", "Jan Kowalski", "IZ06IO1"));
//        mockData.add(new StudentData("112", "Anna Rub", "IZ06IO2"));
//        mockData.add(new StudentData("113", "Adam Adamowski", "IZ06IO1"));
//    }
//    @Override
//    public Collection<StudentData> getAll() throws Exception {
//
//
//        return mockData;
//    }
//
//    @Override
//    public void update(StudentData studentData) throws Exception {
//
//    }
//
//    @Override
//    public void delete(String album) throws Exception {
//
//    }
//
//    @Override
//    public void add(StudentData studentData) throws Exception {
//        mockData.add(studentData);
//    }
//
//    @Override
//    public void save(String writer) throws Exception {
//        service.save(writer);
//    }
//
//
//
//    @Override
//    public StudentData get(String album) throws Exception {
//        Optional<StudentData> sd = mockData.stream().filter(s -> Objects.equals(s.getAlbum(), album)).findFirst();
//        if (sd.isEmpty()) throw new Exception(String.format("Brak studenta o numerze albumu %s", album));
//        return sd.get();
//    }
//
//    @Override
//    public int size() throws Exception {
//        return 0;
//    }
//
//    @Override
//    public void clear() throws Exception {
//        mockData.clear();
//    }
//
//    @Override
//    public boolean exists(String album) throws Exception {
//        return false;
//    }
//
//    @Override
//    public boolean validate(StudentData studentData) {
//        return false;
//    }
//}
