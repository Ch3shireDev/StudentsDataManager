package core;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Serwis zapisu i odczytu plików z systemu plików.
 */
public class FilesystemService implements IFilesystemService {

    private String filename;

    /**
     * @param filename Nazwa pliku.
     * @param content  Zawartość pliku.
     * @throws IOException Wyjątek wyrzucany w przypadku błędu zapisu do pliku.
     */
    @Override
    public void write(String filename, String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(content);
        writer.close();
    }

    /**
     * @param filename Nazwa pliku.
     * @return Zawartość pliku.
     * @throws IOException Wyjątek wyrzucany w przypadku będu odczytu z pliku.
     */
    @Override
    public String read(String filename) throws IOException {
        this.filename = filename;
        return new String(Files.readAllBytes(Paths.get(filename)));
    }
}
