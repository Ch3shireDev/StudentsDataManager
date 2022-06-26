package core;

import java.io.IOException;

/**
 * Serwis zapisu i odczytu plików tekstowych z systemu plików.
 */
public interface IFilesystemService {

    /**
     * @param filename Nazwa pliku.
     * @param content  Zawartość pliku.
     * @throws IOException Wyjątek wyrzucany w przypadku błędu zapisu do pliku.
     */
    void write(String filename, String content) throws IOException;

    /**
     * @param filename Nazwa pliku.
     * @return Zawartość pliku.
     * @throws IOException Wyjątek wyrzucany w przypadku błędu odczytu z pliku.
     */
    String read(String filename) throws IOException;
}
