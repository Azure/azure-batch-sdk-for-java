/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.batch.protocol.models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

/**
 * Defines values for OnAllTasksComplete.
 */
public enum OnAllTasksComplete {
    /** Do nothing. The job remains active unless terminated or disabled by some other means. */
    NO_ACTION("noaction"),

    /** Terminate the job. The job's terminateReason is set to 'AllTasksComplete'. */
    TERMINATE_JOB("terminatejob");

    /** The actual serialized value for a OnAllTasksComplete instance. */
    private String value;

    OnAllTasksComplete(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a OnAllTasksComplete instance.
     *
     * @param value the serialized value to parse.
     * @return the parsed OnAllTasksComplete object, or null if unable to parse.
     */
    @JsonCreator
    public static OnAllTasksComplete fromString(String value) {
        OnAllTasksComplete[] items = OnAllTasksComplete.values();
        for (OnAllTasksComplete item : items) {
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
