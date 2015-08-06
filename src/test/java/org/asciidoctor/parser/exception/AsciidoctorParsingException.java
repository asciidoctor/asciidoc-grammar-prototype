package org.asciidoctor.parser.exception;

/**
 * Exception to handle parsing exceptions
 * 
 * 
 * TODO: should be named Asciidoctor or AsciiDoc?
 */
public class AsciidoctorParsingException extends RuntimeException {

    /**
     * 
     */
    private static final long serialVersionUID = 5975369861071254105L;

    public AsciidoctorParsingException() {
    }

    public AsciidoctorParsingException(String message) {
        super(message);
    }

    public AsciidoctorParsingException(Throwable cause) {
        super(cause);
    }

    public AsciidoctorParsingException(String message, Throwable cause) {
        super(message, cause);
    }

    public AsciidoctorParsingException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
