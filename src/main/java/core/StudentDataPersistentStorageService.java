package core;

import com.fasterxml.jackson.databind.ObjectMapper;
import common.LocalizationUtil;

import java.util.Arrays;
import java.util.Collection;


/**
 * Klasa przechowywania danych studenta w systemie plików.
 */
public class StudentDataPersistentStorageService implements IStudentDataPersistentStorageService {

    /**
     * Serwis systemu plików.
     */
    private final IFilesystemService filesystemService;

    /**
     * Nazwa pliku.
     */
    private final String filename;

    /**
     * Konstruktor. Należy podać nazwę pliku json, do którego będą zapisywane dane oraz serwis systemu plików.
     *
     * @param filename          Nazwa pliku json.
     * @param filesystemService Serwis systemu plików.
     */
    public StudentDataPersistentStorageService(String filename, IFilesystemService filesystemService) {
        this.filesystemService = filesystemService;
        this.filename = filename;
    }


    /**
     * Metoda zapisująca dane do pliku wyszczególnionego w konstruktorze.
     *
     * @param studentData Dane studentów w postaci kolekcji.
     * @throws Exception Wyjątek w przypadku błędu zapisu danych.
     */
    public void save(Collection<StudentData> studentData) throws Exception {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(studentData);
            filesystemService.write(filename, jsonInString);
        }
        catch (Exception e) {
            throw new Exception(String.format(LocalizationUtil.getText("saveErrorMessage"), e.getMessage()));
        }
    }


    /**
     * Metoda ładująca dane studentów z pliku wyszczególnionego w konstruktorze.
     *
     * @return Lista danych studentów.
     * @throws Exception Wyjątek w przypadku błędu odczytu danych.
     */
    @Override
    public Collection<StudentData> load() throws Exception {
        try {
            String json = filesystemService.read(filename);
            ObjectMapper mapper = new ObjectMapper();
            return Arrays.asList(mapper.readValue(json, StudentData[].class));
        }
        catch (Exception e) {
            throw new Exception(String.format(LocalizationUtil.getText("loadErrorMessage"), e.getMessage()));
        }
    }
}
