/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.batch.protocol.models;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A specification for uploading files from an Azure Batch node to another
 * location after the Batch service has finished executing the task process.
 */
public class OutputFile {
    /**
     * A pattern indicating which file(s) to upload.
     * Both relative and absolute paths are supported. Relative paths are
     * relative to the task working directory. For wildcards, use * to match
     * any character and ** to match any directory. For example, **\*.txt
     * matches any file ending in .txt in the task working directory or any
     * subdirectory. Note that \ and / are treated interchangeably and mapped
     * to the correct directory separator on the compute node operating system.
     */
    @JsonProperty(value = "filePattern", required = true)
    private String filePattern;

    /**
     * The destination for the output file(s).
     */
    @JsonProperty(value = "destination", required = true)
    private OutputFileDestination destination;

    /**
     * Additional options for the upload operation, including under what
     * conditions to perform the upload.
     */
    @JsonProperty(value = "uploadOptions", required = true)
    private OutputFileUploadOptions uploadOptions;

    /**
     * Get the filePattern value.
     *
     * @return the filePattern value
     */
    public String filePattern() {
        return this.filePattern;
    }

    /**
     * Set the filePattern value.
     *
     * @param filePattern the filePattern value to set
     * @return the OutputFile object itself.
     */
    public OutputFile withFilePattern(String filePattern) {
        this.filePattern = filePattern;
        return this;
    }

    /**
     * Get the destination value.
     *
     * @return the destination value
     */
    public OutputFileDestination destination() {
        return this.destination;
    }

    /**
     * Set the destination value.
     *
     * @param destination the destination value to set
     * @return the OutputFile object itself.
     */
    public OutputFile withDestination(OutputFileDestination destination) {
        this.destination = destination;
        return this;
    }

    /**
     * Get the uploadOptions value.
     *
     * @return the uploadOptions value
     */
    public OutputFileUploadOptions uploadOptions() {
        return this.uploadOptions;
    }

    /**
     * Set the uploadOptions value.
     *
     * @param uploadOptions the uploadOptions value to set
     * @return the OutputFile object itself.
     */
    public OutputFile withUploadOptions(OutputFileUploadOptions uploadOptions) {
        this.uploadOptions = uploadOptions;
        return this;
    }

}
