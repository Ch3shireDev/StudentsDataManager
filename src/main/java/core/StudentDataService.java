package core;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Serwis danych studentów.
 */
public class StudentDataService implements IStudentDataService {

    private final IStudentDataPersistentStorageService preservationService;
    private static final Collection<StudentData> students  = new LinkedList<>();;
    private final IStudentDataValidator validatorService;

    public StudentDataService(IStudentDataValidator validatorService,
                              IStudentDataPersistentStorageService preservationService) {
        this.preservationService = preservationService;
        this.validatorService = validatorService;
    }

    /**
     * Pobiera dane studenta na podstawie numeru albumu.
     *
     * @param album Numer albumu studenta.
     * @return Dane studenta.
     * @throws Exception Wyjątek wyrzucany w przypadku braku numeru studenta, lub błędu wyszukiwania danych.
     */
    public StudentData get(String album) throws Exception {
        Optional<StudentData> sd = students.stream().filter(s -> Objects.equals(s.getAlbum(), album)).findFirst();
        if (sd.isEmpty()) throw new Exception(String.format("Brak studenta o numerze albumu %s", album));
        return sd.get();
    }

    /**
     * @return Liczba obiektów StudentData w bazie danych.
     * @throws Exception Wyjątek w przypadku błędu połączenia z listą studentów.
     */
    @Override
    public int size() throws Exception {
        return students.size();
    }

    /**
     * Kasuje załadowane dane studentów.
     *
     * @throws Exception Wyjątek w przypadku błędu komunikacji z listą studentów.
     */
    @Override
    public void clear() throws Exception {
        students.clear();
    }

    /**
     * @return Zwraca kopię listy studentów.
     * @throws Exception Wyjątek w przypadku błędu komunikacji z listą studentów.
     */
    @Override
    public Collection<StudentData> getAll() throws Exception {
        return students.stream().map(StudentData::new).collect(Collectors.toList());
    }

    /**
     * Aktualizuje dane studenta o danym numerze albumu.
     *
     * @param studentData Dane studenta.
     * @throws Exception Wyjątek w przypadku błędu połączenia z listą studentów.
     */
    @Override
    public void update(StudentData studentData) throws Exception {
        String album = studentData.getAlbum();
        StudentData student = get(album);
        student.set(studentData);
    }

    /**
     * Usuwa dane studenta o podanym numerze albumu.
     *
     * @param album Numer albumu.
     * @throws Exception Wyjątek w przypadku błędu połączenia z listą studentów.
     */
    @Override
    public void delete(String album) throws Exception {
        StudentData data = get(album);
        students.remove(data);
    }

    /**
     * Sprawdza czy istnieją dane studenta o podanym numerze albumu.
     *
     * @param album Numer albumu.
     * @return Prawda jeśli istnieją dane studenta o podanym numerze albumu, fałsz w przeciwnym wypadku.
     * @throws Exception Wyjątek w przypadku błędu połączenia z listą studentów.
     */
    public boolean exists(String album) {
        return students.stream().anyMatch(studentData1 -> Objects.equals(studentData1.getAlbum(), album));
    }

    /**
     * Dodaje dane studenta.
     *
     * @param studentData Dane studenta.
     * @throws Exception Wyjątek w przypadku błędu połączenia z listą studentów.
     * @throws Exception Wyjątek w przypadku próby dodania studenta z istniejącym w bazie numerem albumu.
     * @throws Exception Wyjątek w przypadku próby dodania studenta z niepoprawnymi danymi.
     */
    @Override
    public void add(StudentData studentData) throws ValidationException {
        if (!validate(studentData)) throw new ValidationException("Niepoprawne dane.");
        String album = studentData.getAlbum();
        if (exists(album)) {
            //todo internationalize
            throw new ValidationException(String.format("Student o numerze albumu %s już istnieje.", album));
        }
        students.add(studentData);
    }

    /**
     * Zapisuje dane studentów w serwisie danych trwałych.
     *
     * @throws Exception Wyjątek w przypadku błędu połączenia z listą studentów.
     */
    @Override
    public void save(String filename) throws Exception {
        preservationService.save(filename, students);
    }

//    /**
//     * Ładuje dane studentów z serwisu danych trwałych.
//     *
//     * @throws Exception Wyjątek w przypadku błędu połączenia z listą studentów.
//     * @throws Exception Wyjątek w przypadku błędu ładowania danych z serwisu danych trwałych.
//     */
//    @Override
//    public void load(IStudentDataPersistentStorageService preservationService) throws Exception {
//        Collection<StudentData> loadedData = preservationService.load();
//        students.clear();
//        students.addAll(loadedData);
//    }

    /**
     * Waliduje dane studenta.
     *
     * @param studentData Dane studenta.
     * @return Prawda jeśli dane studenta są poprawne, fałsz w przeciwnym wypadku.
     */
    @Override
    public boolean validate(StudentData studentData) throws ValidationException {
        if (validatorService == null) return true;
        return validatorService.validate(studentData);
    }
}

