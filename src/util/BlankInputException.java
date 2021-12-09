package util;

import javafx.scene.control.TextInputControl;

import java.util.List;

/**
 * An exception to raise when input is left blank
 * @author Joseph Curtis
 * @version 2021.12.09
 */

public class BlankInputException extends InvalidInputException {

    public BlankInputException(String message) {
        super(message);
    }

    public BlankInputException(String message, TextInputControl field) {
        super(message, field);
    }

    public BlankInputException(String message, TextInputControl field, String fieldLabel) {
        super(message, field, fieldLabel);
    }

    public BlankInputException(String message, List<TextInputControl> fieldsList) {
        super(message, fieldsList);
    }

}
