package gui;

import core.StudentData;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Klasa pomocnicza konwertująca obiekty {@link StudentData}
 * */
public class StudentDataConverter {

    /**
     * Indeks kolumny Albumu studenta wyświetlany w tabeli
     * */
    public static final int NO_ALUBM_INDEX = 0;

    /**
     * Indeks kolumny Studenta (imię i nazwisko) wyświetlany w tabeli
     * */
    public static final int PERSON_INDEX = 1;
    /**
     * Indeks kolumny Grupy studenta wyświetlany w tabeli
     * */
    public static final int GROUP_INDEX = 2;
    /**
     * Indeks kolumny liczby punktów za pracę domową wyświetlany w tabeli
     * */
    public static final int HOMEWORK_INDEX =3;
    /**
     * Indeks kolumny Liczby punktów za aktywność wyświetlany w tabeli
     * */
    public static final int ACTIVITY_INDEX = 4;
    /**
     * Indeks kolumny Liczby punktów za projekt wyswietlany w tabeli
     * */
    public static final int PROJECT_INDEX = 5;
    /**
     * Indeks kolumny Liczby punktów za Kolokwium 1 wyświetlany w tabeli
     * */
    public static final int COLLOQIUM1_INDEX = 6;
    /**
     * Indeks kolumny Liczby punktów za Kolokwium 2 wyświetlany w tabeli
     * */
    public static final int COLLOQIUM2_INDEX = 7;
    /**
     * Indeks kolumny Liczby punktów za egzamin wyświetlany w tabeli
     * */
    public static final int EXAM_INDEX = 8;
    /**
     * Indeks kolumny Sumy punktów wyświetlany w tabeli
     * */
    public static final int SUM_INDEX = 9;

    /**
     * Mapa zawierająca indeksy kolumn tabeli i ich definicji sposobu ekstrakcji danych z obiektu {@link StudentData}
     * */
    private  static final Map<Integer, Function<StudentData, Object>> fieldMapping = new HashMap<>();
    static {
        fieldMapping.put(NO_ALUBM_INDEX, StudentData::getAlbum);
        fieldMapping.put(PERSON_INDEX, StudentData::getName);
        fieldMapping.put(GROUP_INDEX, StudentData::getGroup);
        fieldMapping.put(COLLOQIUM1_INDEX, StudentData::getTest1Points);
        fieldMapping.put(COLLOQIUM2_INDEX, StudentData::getTest2Points);
        fieldMapping.put(EXAM_INDEX, StudentData::getExamPoints);
        fieldMapping.put(SUM_INDEX, StudentData::getSum);
        fieldMapping.put(PROJECT_INDEX, StudentData::getProjectPoints);
        fieldMapping.put(HOMEWORK_INDEX, StudentData::getHomeworkPoints);
        fieldMapping.put(ACTIVITY_INDEX, StudentData::getActivityPoints);
    }

    /**
     * Metoda konwertująca obiekty {@link StudentData} to tablicy dwuwymiarowej
     * @param studentDataCollection - Kolekcja obiektów {@link StudentData}
     * @return Wypełniona dwuwymiarowa tablica. Pusta w przypadku wystąpienia wyjątku.
     */
    public static Object[][] convertToViewModelData(Collection<StudentData> studentDataCollection){
        try {
            int columnsSize = 10;
            Object[][] x = new Object[studentDataCollection.size()][columnsSize];
            StudentData[] studentData = studentDataCollection.toArray(StudentData[]::new);

            for (int i = 0; i < x.length; i++) {
                for (int j = 0; j < columnsSize; j++) {
                    x[i][j] = fieldMapping.get(j).apply(studentData[i]);
                }
            }
            return x;
        } catch (Exception e) {
            return new Object[0][0];
        }
    }
}
