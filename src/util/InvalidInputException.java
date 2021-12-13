/*
 Copyright 2021 Joseph Curtis Licensed under the Educational
 Community License, Version 2.0 (the "License"); you may not use this file
 except in compliance with the License. You may obtain a copy of the License at

 http://opensource.org/licenses/ECL-2.0

  Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 License for the specific language governing permissions and limitations under
 the License.

 ******************************************************************************/

package util;

import javafx.scene.control.TextInputControl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * An exception to raise when an attempt to save an object
 * must fail due to input validation
 * @author Joseph Curtis
 * @version 2021.12.09
 */

public class InvalidInputException extends Exception {

    private TextInputControl field;
    private String fieldLabel;
    private final List<TextInputControl> fieldsList = new ArrayList<TextInputControl>();

    /**
     * Constructs a new exception with a message detail.
     * The input field in question is not specified.
     * @param message  a detailed message saved for later.
     */
    public InvalidInputException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with a message detail and
     * the input field in question.
     * @param message  a detailed message saved for later.
     * @param field the input field that caused this exception.
     */
    public InvalidInputException(String message, TextInputControl field) {
        super(message);
        this.field = field;
    }

    /**
     * Constructs a new exception with a message detail,
     * the input field in question, and what the field is called in the program.
     * @param message  a detailed message saved for later.
     * @param field the input field that caused this exception.
     * @param fieldLabel the labeled name the field goes by.
     */
    public InvalidInputException(String message, TextInputControl field, String fieldLabel) {
        super(message);
        this.field = field;
        this.fieldLabel = fieldLabel;
    }

    /**
     * Constructs a new exception with a message detail and
     * a list of the input fields in question.
     * @param message  a detailed message saved for later.
     * @param fieldsList the multiple input fields that caused this exception.
     */
    public InvalidInputException(String message, List<TextInputControl> fieldsList) {
        super(message);
        this.fieldsList.addAll(fieldsList);
    }

    /** Returns a diagnostic description of this exception.
     * @return A string containing basic exception info
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
     * Returns the input field in question
     * @return the JavaFX textField that failed validation
     */
    public final TextInputControl getField() {
        return field;
    }

    /**
     * Returns the name as a string of input field in question
     * @return the name the input field is described by
     */
    public final String getFieldLabel() {
        return fieldLabel;
    }

    /**
     * Returns a list of fields that all failed input validation
     * @return list of Textual input controls in question
     */
    public final List<TextInputControl> getFieldsList() {
        return fieldsList;
    }

}
