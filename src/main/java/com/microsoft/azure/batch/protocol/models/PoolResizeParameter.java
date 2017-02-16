/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.batch.protocol.models;

import org.joda.time.Period;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Options for changing the size of a pool.
 */
public class PoolResizeParameter {
    /**
     * The desired number of compute nodes in the pool.
     */
    @JsonProperty(value = "targetDedicated", required = true)
    private int targetDedicated;

    /**
     * The timeout for allocation of compute nodes to the pool or removal of
     * compute nodes from the pool.
     * The default value is 15 minutes. The minimum value is 5 minutes. If you
     * specify a value less than 5 minutes, the Batch service returns an error;
     * if you are calling the REST API directly, the HTTP status code is 400
     * (Bad Request).
     */
    @JsonProperty(value = "resizeTimeout")
    private Period resizeTimeout;

    /**
     * When nodes may be removed from the pool, if the pool size is decreasing.
     * The default value is requeue. Possible values include: 'requeue',
     * 'terminate', 'taskcompletion', 'retaineddata'.
     */
    @JsonProperty(value = "nodeDeallocationOption")
    private ComputeNodeDeallocationOption nodeDeallocationOption;

    /**
     * Get the targetDedicated value.
     *
     * @return the targetDedicated value
     */
    public int targetDedicated() {
        return this.targetDedicated;
    }

    /**
     * Set the targetDedicated value.
     *
     * @param targetDedicated the targetDedicated value to set
     * @return the PoolResizeParameter object itself.
     */
    public PoolResizeParameter withTargetDedicated(int targetDedicated) {
        this.targetDedicated = targetDedicated;
        return this;
    }

    /**
     * Get the resizeTimeout value.
     *
     * @return the resizeTimeout value
     */
    public Period resizeTimeout() {
        return this.resizeTimeout;
    }

    /**
     * Set the resizeTimeout value.
     *
     * @param resizeTimeout the resizeTimeout value to set
     * @return the PoolResizeParameter object itself.
     */
    public PoolResizeParameter withResizeTimeout(Period resizeTimeout) {
        this.resizeTimeout = resizeTimeout;
        return this;
    }

    /**
     * Get the nodeDeallocationOption value.
     *
     * @return the nodeDeallocationOption value
     */
    public ComputeNodeDeallocationOption nodeDeallocationOption() {
        return this.nodeDeallocationOption;
    }

    /**
     * Set the nodeDeallocationOption value.
     *
     * @param nodeDeallocationOption the nodeDeallocationOption value to set
     * @return the PoolResizeParameter object itself.
     */
    public PoolResizeParameter withNodeDeallocationOption(ComputeNodeDeallocationOption nodeDeallocationOption) {
        this.nodeDeallocationOption = nodeDeallocationOption;
        return this;
    }

}
