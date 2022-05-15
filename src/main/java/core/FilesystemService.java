package core;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FilesystemService implements IFilesystemService {

    @Override
    public void write(String filename, String content) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
        writer.write(content);
        writer.close();
    }

    @Override
    public String read(String filename) throws IOException {
        return new String(Files.readAllBytes(Paths.get(filename)));
    }
}
