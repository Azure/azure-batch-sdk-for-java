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
 * Defines values for StorageAccountType.
 */
public enum StorageAccountType {
    /** Enum value Standard_LRS. */
    STANDARD_LRS("Standard_LRS"),

    /** Enum value Premium_LRS. */
    PREMIUM_LRS("Premium_LRS");

    /** The actual serialized value for a StorageAccountType instance. */
    private String value;

    StorageAccountType(String value) {
        this.value = value;
    }

    /**
     * Parses a serialized value to a StorageAccountType instance.
     *
     * @param value the serialized value to parse.
     * @return the parsed StorageAccountType object, or null if unable to parse.
     */
    @JsonCreator
    public static StorageAccountType fromString(String value) {
        StorageAccountType[] items = StorageAccountType.values();
        for (StorageAccountType item : items) {
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
