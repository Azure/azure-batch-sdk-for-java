/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.batch.protocol.models;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * An error response received from the Azure Batch service.
 */
public class BatchError {
    /**
     * An identifier for the error. Codes are invariant and are intended to be
     * consumed programmatically.
     */
    @JsonProperty(value = "code")
    private String code;

    /**
     * A message describing the error, intended to be suitable for display in a
     * user interface.
     */
    @JsonProperty(value = "message")
    private ErrorMessage message;

    /**
     * A collection of key-value pairs containing additional details about the
     * error.
     */
    @JsonProperty(value = "values")
    private List<BatchErrorDetail> values;

    /**
     * Get the code value.
     *
     * @return the code value
     */
    public String code() {
        return this.code;
    }

    /**
     * Set the code value.
     *
     * @param code the code value to set
     * @return the BatchError object itself.
     */
    public BatchError withCode(String code) {
        this.code = code;
        return this;
    }

    /**
     * Get the message value.
     *
     * @return the message value
     */
    public ErrorMessage message() {
        return this.message;
    }

    /**
     * Set the message value.
     *
     * @param message the message value to set
     * @return the BatchError object itself.
     */
    public BatchError withMessage(ErrorMessage message) {
        this.message = message;
        return this;
    }

    /**
     * Get the values value.
     *
     * @return the values value
     */
    public List<BatchErrorDetail> values() {
        return this.values;
    }

    /**
     * Set the values value.
     *
     * @param values the values value to set
     * @return the BatchError object itself.
     */
    public BatchError withValues(List<BatchErrorDetail> values) {
        this.values = values;
        return this;
    }

}
