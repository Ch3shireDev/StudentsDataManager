package mockups;

import core.IFilesystemService;

import java.io.IOException;

/**
 * Mockowy serwis systemu plików. Zapisuje i odczytuje treść do zmiennej content.
 */
public class MockFilesystemService implements IFilesystemService {

    public String filename;
    public String content;

    @Override
    public void write(String filename, String content) throws IOException {
        this.filename = filename;
        this.content = content;
    }

    @Override
    public String read(String filename) throws IOException {
        return content;
    }
}
