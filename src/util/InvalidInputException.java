package util;

import javafx.scene.control.TextInputControl;

import java.util.Arrays;
import java.util.List;

/**
 * An exception to raise when an attempt to save an object
 * must fail due to input validation
 * @author Joseph Curtis
 * @version 2021.12.09
 */

public class InvalidInputException extends Exception {

    TextInputControl field;
    String fieldLabel;
    List<TextInputControl> fieldsList;

    public InvalidInputException(String message) {
        super(message);
    }

    public InvalidInputException(String message, TextInputControl field) {
        super(message);
        this.field = field;
    }

    public InvalidInputException(String message, TextInputControl field, String fieldLabel) {
        super(message);
        this.field = field;
        this.fieldLabel = fieldLabel;
    }

    public InvalidInputException(String message, List<TextInputControl> fieldsList) {
        super(message);
        this.fieldsList.addAll(fieldsList);
    }

    /**
     * @return A diagnostic string containing basic exception info
     */
    @Override
    public String toString() {
        String strOut = "Invalid input detected\n" + getMessage();

        if (fieldLabel != null)
            strOut += "InputField Name: " + fieldLabel + "\n";

        if (field != null)
            strOut += "InputField:\n" + field.toString() + "\n";

        strOut += "Stack Trace:\n" + Arrays.toString(getStackTrace());

        return strOut;
    }

    /**
     * @return the JavaFX textField that failed parsing
     */
    public TextInputControl getField() {
        return field;
    }

    /**
     * @return the name of input field in question
     */
    public String getFieldLabel() {
        return fieldLabel;
    }

}
