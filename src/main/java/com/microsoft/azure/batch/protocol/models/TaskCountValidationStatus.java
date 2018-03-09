/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 * Changes may cause incorrect behavior and will be lost if the code is
 * regenerated.
 */

package com.microsoft.azure.batch.protocol.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Defines values for TaskCountValidationStatus.
 */
public enum TaskCountValidationStatus {
    /** The Batch service has validated the state counts against the task states as reported in the List Tasks API. */
    VALIDATED("validated"),

    /** The Batch service has not been able to check state counts against the task states as reported in the List Tasks API. The validationStatus may be unvalidated if the job contains more than 200,000 tasks. */
    UNVALIDATED("unvalidated");

    /** The actual serialized value for a TaskCountValidationStatus instance. */
    private String value;

    TaskCountValidationStatus(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a TaskCountValidationStatus instance.
     *
     * @param value the serialized value to parse.
     * @return the parsed TaskCountValidationStatus object, or null if unable to parse.
     */
    @JsonCreator
    public static TaskCountValidationStatus fromString(String value) {
        TaskCountValidationStatus[] items = TaskCountValidationStatus.values();
        for (TaskCountValidationStatus item : items) {
            if (item.toString().equalsIgnoreCase(value)) {
                return item;
            }
        }
        return null;
    }

    @JsonValue
    @Override
    public String toString() {
        return this.value;
    }
}
