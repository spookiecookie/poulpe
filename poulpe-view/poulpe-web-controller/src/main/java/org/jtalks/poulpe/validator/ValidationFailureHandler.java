/**
 * Copyright (C) 2011  JTalks.org Team
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301  USA
 */
package org.jtalks.poulpe.validator;

import java.util.Map;

import org.jtalks.common.validation.ValidationError;
import org.jtalks.common.validation.ValidationResult;
import org.zkoss.util.resource.Labels;
import org.zkoss.zul.impl.InputElement;

import com.google.common.collect.ImmutableMap;

/**
 * Handler which alerts an error message stuck to given {@link InputElement}
 * object. It gets {@link ValidationResult} and checks if it has components with
 * this name. If it has, alerts an error message near the appropriate one.
 * 
 * @author Alexey Grigorev
 * @author Tatiana Birina
 */
public class ValidationFailureHandler implements ValidationFailure {

    private final Map<String, InputElement> comps;

    /**
     * Creates handler with only one input element
     * 
     * @param name of the field associated with the input element
     * @param input element with input data near which alert will be shown
     */
    public ValidationFailureHandler(String name, InputElement input) {
        comps = ImmutableMap.of(name, input);
    }

    /**
     * Creates handler with two input elements
     * 
     * @param name1 name associated with input1
     * @param input1 input data for field1
     * @param name2 name associated with input2
     * @param input2 input data for field2
     */
    public ValidationFailureHandler(String name1, InputElement input1, String name2, InputElement input2) {
        comps = ImmutableMap.of(name1, input1, name2, input2);
    }

    /**
     * Creates handler with 3 input elements
     * 
     * @param name1 name associated with input1
     * @param input1 input data for field1
     * @param name2 name associated with input2
     * @param input2 input data for field2
     * @param name3 name associated with input3
     * @param input3 input data for field3
     */
    public ValidationFailureHandler(String name1, InputElement input1, String name2, InputElement input2, String name3,
            InputElement input3) {
        comps = ImmutableMap.of(name1, input1, name2, input2, name3, input3);
    }

    /**
     * Looks through a collection of saved ui elements with input data, and if
     * found one, which is violated according to gived {@link ValidationResult},
     * alerts a message near this element
     * @param result validation result for ui elements
     */
    @Override
    public void validationFailure(ValidationResult result) {
        for (ValidationError error : result.getErrors()) {
            String fieldName = error.getFieldName();
            if (comps.containsKey(fieldName)) {
                showErrorMessage(fieldName, error);
            }
        }
    }

    /**
     * Alerts a message near ui element associated with given field name
     * 
     * @param fieldName violated field
     * @param error to be shown
     */
    private void showErrorMessage(String fieldName, ValidationError error) {
        String message = Labels.getLabel(error.getErrorMessageCode());
        InputElement ie = comps.get(fieldName);
        ie.setErrorMessage(message);
    }

}
