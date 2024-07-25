package io.github.willdomkahari.generator;

/**
 * @author <a href="mailto:willdomkahari@gmail.com">Willdom Kahari</a>
 */
public class AppendingCharactersException extends RuntimeException{
    public AppendingCharactersException() {
        super();
    }

    public AppendingCharactersException(String message) {
        super(message);
    }

    public AppendingCharactersException(String message, Throwable cause) {
        super(message, cause);
    }

    public AppendingCharactersException(Throwable cause) {
        super(cause);
    }

    protected AppendingCharactersException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
