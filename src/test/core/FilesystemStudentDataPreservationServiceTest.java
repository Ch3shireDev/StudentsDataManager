import core.StudentData;
import core.StudentDataPreservationService;
import core.StudentDataService;
import mockups.MockFilesystemService;
import mockups.MockValidatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FilesystemStudentDataPreservationServiceTest {
    MockFilesystemService filesystemService;
    StudentDataPreservationService preservationService;
    MockValidatorService validatorService;
    StudentDataService studentDataService;

    @BeforeEach
    void setup() throws Exception {
        filesystemService = new MockFilesystemService();
        preservationService = new StudentDataPreservationService(filesystemService);
        validatorService = new MockValidatorService();

        studentDataService = new StudentDataService(preservationService, validatorService);

        studentDataService.add(new StudentData("111", "Jan Kowalski", "IZ06IO1"));
        studentDataService.add(new StudentData("112", "Adam Nowak", "IZ06IO1"));
        studentDataService.add(new StudentData("113", "Anna Nowak", "IZ06IO1"));
    }

    @Test
    void save() throws Exception {
        studentDataService.save();
        Assertions.assertNotNull(filesystemService.content);
    }

    @Test
    void load() throws Exception {
        studentDataService.save();
        var c1 = filesystemService.content;

        studentDataService.clear();
        studentDataService.save();
        var c2 = filesystemService.content;

        filesystemService.content = c1;
        studentDataService.load();
        Assertions.assertEquals(3, studentDataService.size());

        filesystemService.content = c2;
        studentDataService.load();
        Assertions.assertEquals(0, studentDataService.size());
    }
}

