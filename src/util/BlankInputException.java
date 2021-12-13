package util;

import javafx.scene.control.TextInputControl;

import java.util.List;

/**
 * An exception to raise when input is left blank
 * @author Joseph Curtis
 * @version 2021.12.09
 */

public class BlankInputException extends InvalidInputException {

    /**
     * Constructs a new exception with a message detail.
     * The input field in question is not specified.
     * @param message  a detailed message saved for later.
     */
    public BlankInputException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with a message detail and
     * the input field in question.
     * @param message  a detailed message saved for later.
     * @param field the input field that caused this exception.
     */
    public BlankInputException(String message, TextInputControl field) {
        super(message, field);
    }

    /**
     * Constructs a new exception with a message detail,
     * the input field in question, and what the field is called in the program.
     * @param message  a detailed message saved for later.
     * @param field the input field that caused this exception.
     * @param fieldLabel the labeled name the field goes by.
     */
    public BlankInputException(String message, TextInputControl field, String fieldLabel) {
        super(message, field, fieldLabel);
    }

    /**
     * Constructs a new exception with a message detail and
     * a list of the input fields in question.
     * @param message  a detailed message saved for later.
     * @param fieldsList the multiple input fields that caused this exception.
     */
    public BlankInputException(String message, List<TextInputControl> fieldsList) {
        super(message, fieldsList);
    }

}
