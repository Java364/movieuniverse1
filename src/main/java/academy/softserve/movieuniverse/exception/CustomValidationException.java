package academy.softserve.movieuniverse.exception;

import javax.validation.ValidationException;

public class CustomValidationException extends ValidationException {

    private static final long serialVersionUID = 532670066458655797L;
    private String customMessage = "";

    public CustomValidationException(String customMessage) {
        this.customMessage = customMessage;
    }

    public String getCustomMessage() {
        return customMessage;
    }

}
