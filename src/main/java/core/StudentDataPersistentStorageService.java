package core;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.Collection;


public class StudentDataPersistentStorageService implements IStudentDataPersistentStorageService {

    private final IFilesystemService filesystemService;

    public StudentDataPersistentStorageService(IFilesystemService filesystemService) {
        this.filesystemService = filesystemService;
    }


    public void save(String filename,Collection<StudentData> studentData) throws Exception {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(studentData);
            filesystemService.write(filename, jsonInString);
        } catch (Exception e) {
            throw new Exception(String.format("Błąd zapisu danych. Informacja o błędzie: %s", e.getMessage()));
        }
    }


    @Override
    public Collection<StudentData> load(String filename) throws Exception {
        try {
            String json = filesystemService.read(filename);
            ObjectMapper mapper = new ObjectMapper();
            return Arrays.asList(mapper.readValue(json, StudentData[].class));
        } catch (Exception e) {
            throw new Exception(String.format("Błąd odczytu danych. Informacja o błędzie: %s", e.getMessage()));
        }
    }
}
