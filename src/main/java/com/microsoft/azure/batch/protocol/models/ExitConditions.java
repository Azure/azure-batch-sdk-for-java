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
 * Specifies how the Batch service should respond when the task completes.
 */
public class ExitConditions {
    /**
     * A list of individual task exit codes and how the Batch service should
     * respond to them.
     */
    @JsonProperty(value = "exitCodes")
    private List<ExitCodeMapping> exitCodes;

    /**
     * A list of task exit code ranges and how the Batch service should respond
     * to them.
     */
    @JsonProperty(value = "exitCodeRanges")
    private List<ExitCodeRangeMapping> exitCodeRanges;

    /**
     * How the Batch service should respond if the task fails with a scheduling
     * error.
     */
    @JsonProperty(value = "schedulingError")
    private ExitOptions schedulingError;

    /**
     * How the Batch service should respond if the task fails with an exit
     * condition not covered by any of the other properties – that is, any
     * nonzero exit code not listed in the exitCodes or exitCodeRanges
     * collection, or a scheduling error if the schedulingError property is not
     * present.
     * Note that the default condition does not include exit code 0. If you
     * want non-default behaviour on exit code 0, you must list it explicitly
     * using the exitCodes or exitCodeRanges collection.
     */
    @JsonProperty(value = "default")
    private ExitOptions defaultProperty;

    /**
     * Get the exitCodes value.
     *
     * @return the exitCodes value
     */
    public List<ExitCodeMapping> exitCodes() {
        return this.exitCodes;
    }

    /**
     * Set the exitCodes value.
     *
     * @param exitCodes the exitCodes value to set
     * @return the ExitConditions object itself.
     */
    public ExitConditions withExitCodes(List<ExitCodeMapping> exitCodes) {
        this.exitCodes = exitCodes;
        return this;
    }

    /**
     * Get the exitCodeRanges value.
     *
     * @return the exitCodeRanges value
     */
    public List<ExitCodeRangeMapping> exitCodeRanges() {
        return this.exitCodeRanges;
    }

    /**
     * Set the exitCodeRanges value.
     *
     * @param exitCodeRanges the exitCodeRanges value to set
     * @return the ExitConditions object itself.
     */
    public ExitConditions withExitCodeRanges(List<ExitCodeRangeMapping> exitCodeRanges) {
        this.exitCodeRanges = exitCodeRanges;
        return this;
    }

    /**
     * Get the schedulingError value.
     *
     * @return the schedulingError value
     */
    public ExitOptions schedulingError() {
        return this.schedulingError;
    }

    /**
     * Set the schedulingError value.
     *
     * @param schedulingError the schedulingError value to set
     * @return the ExitConditions object itself.
     */
    public ExitConditions withSchedulingError(ExitOptions schedulingError) {
        this.schedulingError = schedulingError;
        return this;
    }

    /**
     * Get the defaultProperty value.
     *
     * @return the defaultProperty value
     */
    public ExitOptions defaultProperty() {
        return this.defaultProperty;
    }

    /**
     * Set the defaultProperty value.
     *
     * @param defaultProperty the defaultProperty value to set
     * @return the ExitConditions object itself.
     */
    public ExitConditions withDefaultProperty(ExitOptions defaultProperty) {
        this.defaultProperty = defaultProperty;
        return this;
    }

}
