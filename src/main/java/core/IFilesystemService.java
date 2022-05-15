package core;

import java.io.IOException;

public interface IFilesystemService {
    void write(String filename, String content) throws IOException;

    String read(String filename) throws IOException;
}
