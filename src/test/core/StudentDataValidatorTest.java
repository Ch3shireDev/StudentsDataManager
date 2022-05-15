import core.IStudentDataValidator;
import core.StudentData;
import core.StudentDataValidator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class StudentDataValidatorTest {

    static IStudentDataValidator validator;

    @BeforeAll
    static void setup() {
        validator = new StudentDataValidator();
    }


    /**
     * Poprawne dane studenta spełniają wszystkie testy walidacyjne.
     */
    @Test
    void validate() {
        Assertions.assertTrue(validator.validate(new StudentData("111", "Jan Nowak", "IZ06IO1", 0, 0, 0, 0, 0, 0)));
        Assertions.assertTrue(validator.validate(new StudentData("111", "Jan Nowak", "IZ06IO1", 5, 5, 10, 20, 20, 40)));
        Assertions.assertFalse(validator.validate(new StudentData("111a", "Jan Nowak", "IZ06IO1", 5, 5, 10, 20, 20, 40)));
        Assertions.assertFalse(validator.validate(new StudentData("111", "Jan", "IZ06IO1", 5, 5, 10, 20, 20, 40)));
        Assertions.assertFalse(validator.validate(new StudentData("111", "Jan Nowak", "", 5, 5, 10, 20, 20, 40)));
        Assertions.assertFalse(validator.validate(new StudentData("111", "Jan Nowak", "IZ06IO1", 6, 5, 10, 20, 20, 40)));
        Assertions.assertFalse(validator.validate(new StudentData("111", "Jan Nowak", "IZ06IO1", 5, 6, 10, 20, 20, 40)));
        Assertions.assertFalse(validator.validate(new StudentData("111", "Jan Nowak", "IZ06IO1", 5, 5, 11, 20, 20, 40)));
        Assertions.assertFalse(validator.validate(new StudentData("111", "Jan Nowak", "IZ06IO1", 5, 5, 10, 21, 20, 40)));
        Assertions.assertFalse(validator.validate(new StudentData("111", "Jan Nowak", "IZ06IO1", 5, 5, 10, 20, 21, 40)));
        Assertions.assertFalse(validator.validate(new StudentData("111", "Jan Nowak", "IZ06IO1", 5, 5, 10, 20, 20, 41)));
        Assertions.assertFalse(validator.validate(new StudentData("111", "Jan Nowak", "IZ06IO1", -1, 5, 10, 20, 20, 40)));
        Assertions.assertFalse(validator.validate(new StudentData("111", "Jan Nowak", "IZ06IO1", 5, -1, 10, 20, 20, 40)));
        Assertions.assertFalse(validator.validate(new StudentData("111", "Jan Nowak", "IZ06IO1", 5, 5, -1, 20, 20, 40)));
        Assertions.assertFalse(validator.validate(new StudentData("111", "Jan Nowak", "IZ06IO1", 5, 5, 10, -1, 20, 40)));
        Assertions.assertFalse(validator.validate(new StudentData("111", "Jan Nowak", "IZ06IO1", 5, 5, 10, 20, -1, 40)));
        Assertions.assertFalse(validator.validate(new StudentData("111", "Jan Nowak", "IZ06IO1", 5, 5, 10, 20, 20, -1)));
    }

    /**
     * Album studenta jest niepusty i składa się z samych cyfr.
     */
    @Test
    void validateAlbum() {
        Assertions.assertTrue(validator.validateAlbum("123456"));
        Assertions.assertFalse(validator.validateAlbum("123456a"));
        Assertions.assertFalse(validator.validateAlbum(""));
        Assertions.assertFalse(validator.validateAlbum(null));
    }

    /**
     * Imię i nazwisko studenta jest niepuste i ma co najmniej 2 człony
     */
    @Test
    void validateName() {
        Assertions.assertTrue(validator.validateName("Jan Kowalski"));
        Assertions.assertFalse(validator.validateName("Jan"));
        Assertions.assertFalse(validator.validateName("    "));
        Assertions.assertFalse(validator.validateName("\t"));
        Assertions.assertFalse(validator.validateName(""));
        Assertions.assertFalse(validator.validateName(null));

    }


    /**
     * Grupa studenta musi być niepusta.
     */
    @Test
    void validateGroup() {
        Assertions.assertTrue(validator.validateGroup("IZ06IO1"));
        Assertions.assertFalse(validator.validateGroup(""));
        Assertions.assertFalse(validator.validateGroup(null));
    }

    /**
     * Punkty za aktywność są liczbą z zakresu od 0 do 5.
     */
    @Test
    void validateActivityPoints() {
        Assertions.assertTrue(validator.validateActivityPoints(0));
        Assertions.assertTrue(validator.validateActivityPoints(5));
        Assertions.assertFalse(validator.validateActivityPoints(6));
        Assertions.assertFalse(validator.validateActivityPoints(-1));
    }

    /**
     * Punkty z projektu są liczbą z zakresu od 0 do 10.
     */
    @Test
    void validateProjectPoints() {
        Assertions.assertTrue(validator.validateProjectPoints(0));
        Assertions.assertTrue(validator.validateProjectPoints(5));
        Assertions.assertTrue(validator.validateProjectPoints(10));
        Assertions.assertFalse(validator.validateProjectPoints(-1));
        Assertions.assertFalse(validator.validateProjectPoints(11));
    }

    /**
     * Punkty z pracy domowej są liczbą z zakresu od 0 do 5.
     */
    @Test
    void validateHomeworkPoints() {
        Assertions.assertTrue(validator.validateHomeworkPoints(0));
        Assertions.assertTrue(validator.validateHomeworkPoints(5));
        Assertions.assertFalse(validator.validateHomeworkPoints(-1));
        Assertions.assertFalse(validator.validateHomeworkPoints(6));
    }

    /**
     * Punkty z pierwszego kolokwium są liczbą z zakresu od 0 do 20.
     */
    @Test
    void validateTest1Points() {
        Assertions.assertTrue(validator.validateTest1Points(0));
        Assertions.assertTrue(validator.validateTest1Points(10));
        Assertions.assertTrue(validator.validateTest1Points(20));
        Assertions.assertFalse(validator.validateTest1Points(-1));
        Assertions.assertFalse(validator.validateTest1Points(21));
    }

    /**
     * Punkty z drugiego kolokwium są liczbą z zakresu od 0 do 20.
     */
    @Test
    void validateTest2Points() {
        Assertions.assertTrue(validator.validateTest2Points(0));
        Assertions.assertTrue(validator.validateTest2Points(10));
        Assertions.assertTrue(validator.validateTest2Points(20));
        Assertions.assertFalse(validator.validateTest2Points(-1));
        Assertions.assertFalse(validator.validateTest2Points(21));
    }

    /**
     * Punkty z egzaminu są liczbą z zakresu od 0 do 20.
     */
    @Test
    void validateExamPoints() {
        Assertions.assertTrue(validator.validateExamPoints(0));
        Assertions.assertTrue(validator.validateExamPoints(10));
        Assertions.assertTrue(validator.validateExamPoints(40));
        Assertions.assertFalse(validator.validateExamPoints(-1));
        Assertions.assertFalse(validator.validateExamPoints(41));
    }
}