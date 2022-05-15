import core.IStudentDataService;
import core.StudentData;
import core.StudentDataService;
import mockups.MockPreservationService;
import mockups.MockValidatorService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Collection;

/**
 * Zestaw testów dla serwisu StudentDataService.
 */
class StudentDataServiceTest {

    IStudentDataService service;
    MockPreservationService preservationService;
    MockValidatorService validationService;

    /**
     * Przygotowuje środowisko testowe. Tworzy dane trzech przykładowych studentów.
     *
     * @throws Exception
     */
    @BeforeEach
    void setUp() throws Exception {
        preservationService = new MockPreservationService();
        validationService = new MockValidatorService();
        service = new StudentDataService(preservationService, validationService);
        service.add(new StudentData("111", "Jan Kowalski", "IZ06IO1"));
        service.add(new StudentData("112", "Anna Rub", "IZ06IO2"));
        service.add(new StudentData("113", "Adam Adamowski", "IZ06IO1"));
    }

    /**
     * Sprawdza poprawność działania funkcji getAll().
     * Funkcja powinna zwracać kopię danych trzech studentów.
     * Nie powinno być możliwe modyfikowanie danych studentów z bazy danych.
     *
     * @throws Exception
     */
    @Test
    void getAll() throws Exception {
        // Po pobraniu danych z bazy mamy trzy rekordy.
        Collection<StudentData> students = service.getAll();
        Assertions.assertEquals(3, students.size());

        // Kasujemy dane ze zwróconej listy.
        students.remove(students.stream().findFirst().get());
        Assertions.assertEquals(2, students.size());

        // Po ponownym pobraniu danych powinniśmy znowu dostać trzy rekordy.
        // Modyfikacja listy lokalnej nie powinna zmieniać wewnętrznych danych w serwisie.
        Collection<StudentData> students2 = service.getAll();
        Assertions.assertEquals(3, students2.size());

        // Wybieramy pierwszego studenta i zmieniamy jego nazwę grupy.
        StudentData student = students2.stream().findFirst().get();
        String group = student.getGroup();
        student.setGroup("XXX");

        // Po ponownym pobraniu danych student nie powinien mieć zmienionej grupy.
        // Zwrócone dane są kopią danych z serwisu. Użytkownik nie ma bezpośredniego dostępu do listy studentów.
        Collection<StudentData> students3 = service.getAll();
        StudentData student2 = students3.stream().findFirst().get();
        Assertions.assertEquals(group, student2.getGroup());
    }

    /**
     * Sprawdzamy dzialanie funkcji update - powinna pozwalać na modyfikację nazwiska studenta.
     *
     * @throws Exception
     */
    @Test
    void update() throws Exception {
        Assertions.assertEquals("Anna Rub", service.get("112").getName());
        service.update(new StudentData("112", "Anna Kowal", "IZ06IO1"));
        Assertions.assertEquals("Anna Kowal", service.get("112").getName());
    }

    /**
     * Sprawdzamy działanie funkcji delete - powinna kasować dane studenta z serwisu.
     *
     * @throws Exception
     */
    @Test
    void delete() throws Exception {
        Assertions.assertEquals("Jan Kowalski", service.get("111").getName());
        service.delete("111");
        Assertions.assertFalse(service.exists("111"));
    }

    /**
     * Sprawdzamy działanie funkcji add - powinna dodawać dane nowego studenta.
     * Powinna też zwracać błąd w przypadku próby dodania dwa razy danych studenta o tym samym numerze albumu.
     *
     * @throws Exception
     */
    @Test
    void add() throws Exception {
        StudentData student1 = new StudentData("115", "Igor Nowicki", "IZ06IO1");
        StudentData student2 = new StudentData("115", "Igor Nowicki", "IZ06IO1");

        service.add(student1);
        StudentData sd = service.get("115");
        Assertions.assertEquals("Igor Nowicki", sd.getName());

        // W przypadku próby dodania danych o istniejącym numerze albumu powinniśmy dostać błąd.
        Assertions.assertThrows(Exception.class, () -> service.add(student2));
    }

    /**
     * Sprawdzamy działanie funkcji save - powinna wywoływać save w serwisie preservationService.
     *
     * @throws Exception
     */
    @Test
    void save() throws Exception {
        Assertions.assertEquals(0, preservationService.getSavedStudentData().size());
        service.save();
        Assertions.assertEquals(3, preservationService.getSavedStudentData().size());
    }

    /**
     * Sprawdzamy działanie funkcji load - powinna wywoływać load w serwisie preservationService
     * i przywracać zapisany stan serwisu danych studentów.
     *
     * @throws Exception
     */
    @Test
    void load() throws Exception {
        service.save();
        service.clear();
        Assertions.assertEquals(0, service.size());
        service.load();
        Assertions.assertEquals(3, service.size());
    }

    /**
     * Sprawdzamy działanie funkcji validate - powinna ona odwoływać się do wewnętrznego serwisu IStudentDataValidator.
     */
    @Test
    void validate() {
        validationService.isUsed = false;
        service.validate(new StudentData("111"));
        Assertions.assertTrue(validationService.isUsed);
    }

}