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

import java.util.Arrays;

/**
 * Captures an object that was not found in Inventory
 * @author Joseph Curtis
 * @version 2021.12.09
 */

public class InvObjNotFoundException extends Exception {

    private Object orphanedObject;

    /**
     * Constructs a new exception with a message detail.
     * The object in question is not specified
     * @param message  a detailed message saved for later
     */
    public InvObjNotFoundException(String message) {
        super(message);
    }

    /**
     * Constructs a new exception with a message detail and
     * the orphaned object in question
     * @param message  a detailed message saved for later
     * @param orphanedObject the object not found in Inventory
     */
    public InvObjNotFoundException(String message, Object orphanedObject) {
        super(message);
        this.orphanedObject = orphanedObject;
    }

    /** Returns a diagnostic description of this exception.
     * @return A string containing basic exception info
     */
    @Override
    public String toString() {
        String strOut = "Object in Inventory not found!\n" + getMessage();

        if (orphanedObject != null)
            strOut += "Object:\n" + orphanedObject.toString() + "\n";

        strOut += "Stack Trace:\n" + Arrays.toString(getStackTrace());

        return strOut;
    }

    /**
     * Gets the orphaned object that caused this exception
     * @return  the object not found in Inventory
     */
    public Object getOrphanedObject() {
        return orphanedObject;
    }
}
