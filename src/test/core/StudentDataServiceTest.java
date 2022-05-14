package core;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.LinkedList;

class MockStudentDataPreservationService implements IStudentDataPreservationService {

    Collection<StudentData> students;

    public MockStudentDataPreservationService() {
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

class StudentDataServiceTest {

    IStudentDataService service;
    MockStudentDataPreservationService preservationService;

    @BeforeEach
    void setUp() throws Exception {
        preservationService = new MockStudentDataPreservationService();
        service = new StudentDataService(preservationService);
        service.add(new StudentData("111", "Jan Kowalski", "IZ06IO1"));
        service.add(new StudentData("112", "Anna Rub", "IZ06IO2"));
        service.add(new StudentData("113", "Adam Adamowski", "IZ06IO1"));
    }
    
    @Test
    void getData() throws Exception {
        Collection<StudentData> students = service.getData();
        Assert.assertEquals(3, students.size());
    }

    @Test
    void update() throws Exception {
        Assert.assertEquals("Anna Rub", service.get("112").getName());
        service.update(new StudentData("112", "Anna Kowal", "IZ06IO1"));
        Assert.assertEquals("Anna Kowal", service.get("112").getName());
    }

    @Test
    void delete() throws Exception {
        Assert.assertEquals("Jan Kowalski", service.get("111").getName());
        service.delete(new StudentData("111"));
        Assert.assertFalse(service.exists("111"));
    }

    @Test
    void add() throws Exception {
        service.add(new StudentData("115", "Igor Nowicki", "IZ06IO1"));
        StudentData sd = service.get("115");
        Assert.assertEquals("Igor Nowicki", sd.getName());
    }

    @Test
    void save() throws Exception {
        Assert.assertEquals(0, preservationService.getSavedStudentData().size());
        service.save();
        Assert.assertEquals(3, preservationService.getSavedStudentData().size());
    }

    @Test
    void load() throws Exception {
        service.save();
        service.clear();
        Assert.assertEquals(0, service.size());
        service.load();
        Assert.assertEquals(3, service.size());
    }
}