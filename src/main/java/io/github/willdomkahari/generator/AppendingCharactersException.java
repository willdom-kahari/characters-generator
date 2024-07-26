package io.github.willdomkahari.generator;

/**
 * An exception thrown while trying to append characters in a character buffer.
 *
 * @author <a href="mailto:developer.wadu@gmail.com">Willdom Kahari</a>
 */
public class AppendingCharactersException extends RuntimeException{
    /**
     *Exception thrown when trying to append characters
     * @param message - The message associated with the exception
     * @param cause - The cause of the exception
     */
    public AppendingCharactersException(String message, Throwable cause) {
        super(message, cause);
    }
}
