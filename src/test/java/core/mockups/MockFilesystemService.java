package core.mockups;

import core.IFilesystemService;

import java.io.IOException;

/**
 * Mockowy serwis systemu plików. Zapisuje i odczytuje treść do zmiennej content.
 */
public class MockFilesystemService implements IFilesystemService {

    public String filename;
    public String content;

    /**
     * Udawany zapis do pliku. Zapamiętuje nazwę pliku i podaną zawartość.
     * @param filename Nazwa pliku.
     * @param content  Zawartość pliku.
     * @throws IOException Wyjątek, nigdy nie zostanie zgłoszony.
     */
    @Override
    public void write(String filename, String content) throws IOException {
        this.filename = filename;
        this.content = content;
    }

    /**
     * Udawany odczyt z pliku. Zwraca zapamiętaną zawartość
     * @param filename Nazwa pliku.
     * @return Zawartość zmiennej content.
     * @throws IOException Nigdy nie zwraca.
     */
    @Override
    public String read(String filename) throws IOException {
        return content;
    }
}
