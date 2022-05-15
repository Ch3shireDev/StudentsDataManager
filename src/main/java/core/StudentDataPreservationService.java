package core;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Arrays;
import java.util.Collection;


public class StudentDataPreservationService implements IStudentDataPreservationService {

    private final IFilesystemService filesystemService;
    private final String filename;

    public StudentDataPreservationService(IFilesystemService filesystemService) {
        this.filesystemService = filesystemService;
        this.filename = "data.json";
    }

    public void save(Collection<StudentData> studentData) throws Exception {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper
                    .writerWithDefaultPrettyPrinter()
                    .writeValueAsString(studentData);
            filesystemService.write(filename, jsonInString);
        }
        catch (Exception e) {
            throw new Exception(String.format("Błąd zapisu danych. Informacja o błędzie: %s", e.getMessage()));
        }
    }

    public Collection<StudentData> load() throws Exception {
        try {
            String json = filesystemService.read(filename);
            ObjectMapper mapper = new ObjectMapper();
            return Arrays.asList(mapper.readValue(json, StudentData[].class));
        }
        catch (Exception e) {
            throw new Exception(String.format("Błąd odczytu danych. Informacja o błędzie: %s", e.getMessage()));
        }
    }
}
