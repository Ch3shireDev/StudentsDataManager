package core;

/**
 * Wyjątek wyrzucany w przypadku błędu walidacji, np. przy dodawaniu nowych danych studenta.
 */
public class ValidationException extends Exception {

    /**
     * Konstruktor.
     * @param message Informacja o błędzie.
     */
    public ValidationException(String message) {
        super(message);
    }
}
