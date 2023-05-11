package selfish;

/**
 * The GameException class represents an exception that occurs during the game.
 */
public class GameException extends Exception {

    /**
     * Constructs a new game exception with null as its detail message.
     */
    public GameException() {
        super();
    }

    /**
     * Constructs a new game exception with the specified detail message.
     * @param message the detail message
     */
    public GameException(String message) {
        super(message);
    }

    /**
     * Constructs a new game exception with the specified detail message and cause.
     * @param message the detail message
     * @param cause the cause
     */
    public GameException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Constructs a new game exception with the specified cause.
     * @param cause the cause
     */
    public GameException(Throwable cause) {
        super(cause);
    }
}

