package core;
import com.fasterxml.jackson.databind.ObjectMapper;
import core.mockups.MockFilesystemService;
import core.mockups.MockValidatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


/**
 * Testy sprawdzające poprawność działania zapisu i odczytu do pliku.
 */
class StudentDataPersistentStorageServiceTest {
    MockFilesystemService filesystemService;
    IStudentDataPersistentStorageService preservationService;
    IStudentDataValidator validatorService;
    IStudentDataService studentDataService;

    @BeforeEach
    void setup() throws Exception {
        filesystemService = new MockFilesystemService();
        preservationService = new StudentDataPersistentStorageService("a.json", filesystemService);
        validatorService = new MockValidatorService();

        studentDataService = new StudentDataService(validatorService);

        studentDataService.add(new StudentData("111", "Jan Kowalski", "IZ06IO1"));
        studentDataService.add(new StudentData("112", "Adam Nowak", "IZ06IO1"));
        studentDataService.add(new StudentData("113", "Anna Nowak", "IZ06IO1"));
    }

    /**
     * Test sprawdzający poprawność zapisu do pliku.
     *
     * @throws Exception Wyjątek w przypadku błędu zapisu.
     */
    @Test
    void save() throws Exception {
        studentDataService.save(preservationService);
        Assertions.assertNotNull(filesystemService.content);

        ObjectMapper mapper = new ObjectMapper();
        StudentData[] students = (mapper.readValue(filesystemService.content, StudentData[].class));
        Assertions.assertEquals(3, students.length);
        Assertions.assertEquals("Jan Kowalski", students[0].getName());
        Assertions.assertEquals("Adam Nowak", students[1].getName());
        Assertions.assertEquals("Anna Nowak", students[2].getName());
    }

    /**
     * Test sprawdzający poprawność ładowania z pliku.
     *
     * @throws Exception Wyjątek w przypadku błędu zapisu lub odczytu.
     */
    @Test
    void load() throws Exception {
        studentDataService.save(preservationService);
        var c1 = filesystemService.content;

        studentDataService.clear();
        studentDataService.save(preservationService);
        var c2 = filesystemService.content;

        filesystemService.content = c1;
        studentDataService.load(preservationService);
        Assertions.assertEquals(3, studentDataService.size());

        filesystemService.content = c2;
        studentDataService.load(preservationService);
        Assertions.assertEquals(0, studentDataService.size());
    }
}

