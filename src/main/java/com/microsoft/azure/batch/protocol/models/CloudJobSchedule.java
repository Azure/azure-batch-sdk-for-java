/**
 * Copyright (c) Microsoft Corporation. All rights reserved.
 * Licensed under the MIT License. See License.txt in the project root for
 * license information.
 *
 * Code generated by Microsoft (R) AutoRest Code Generator.
 */

package com.microsoft.azure.batch.protocol.models;

import org.joda.time.DateTime;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * A job schedule that allows recurring jobs by specifying when to run jobs and
 * a specification used to create each job.
 */
public class CloudJobSchedule {
    /**
     * A string that uniquely identifies the schedule within the account.
     */
    @JsonProperty(value = "id")
    private String id;

    /**
     * The display name for the schedule.
     */
    @JsonProperty(value = "displayName")
    private String displayName;

    /**
     * The URL of the job schedule.
     */
    @JsonProperty(value = "url")
    private String url;

    /**
     * The ETag of the job schedule.
     * This is an opaque string. You can use it to detect whether the job
     * schedule has changed between requests. In particular, you can be pass
     * the ETag with an Update Job Schedule request to specify that your
     * changes should take effect only if nobody else has modified the schedule
     * in the meantime.
     */
    @JsonProperty(value = "eTag")
    private String eTag;

    /**
     * The last modified time of the job schedule.
     * This is the last time at which the schedule level data, such as the job
     * specification or recurrence information, changed. It does not factor in
     * job-level changes such as new jobs being created or jobs changing state.
     */
    @JsonProperty(value = "lastModified")
    private DateTime lastModified;

    /**
     * The creation time of the job schedule.
     */
    @JsonProperty(value = "creationTime")
    private DateTime creationTime;

    /**
     * The current state of the job schedule.
     * Possible values include: 'active', 'completed', 'disabled',
     * 'terminating', 'deleting'.
     */
    @JsonProperty(value = "state")
    private JobScheduleState state;

    /**
     * The time at which the job schedule entered the current state.
     */
    @JsonProperty(value = "stateTransitionTime")
    private DateTime stateTransitionTime;

    /**
     * The previous state of the job schedule.
     * This property is not present if the job schedule is in its initial
     * active state. Possible values include: 'active', 'completed',
     * 'disabled', 'terminating', 'deleting'.
     */
    @JsonProperty(value = "previousState")
    private JobScheduleState previousState;

    /**
     * The time at which the job schedule entered its previous state.
     * This property is not present if the job schedule is in its initial
     * active state.
     */
    @JsonProperty(value = "previousStateTransitionTime")
    private DateTime previousStateTransitionTime;

    /**
     * The schedule according to which jobs will be created.
     */
    @JsonProperty(value = "schedule")
    private Schedule schedule;

    /**
     * The details of the jobs to be created on this schedule.
     */
    @JsonProperty(value = "jobSpecification")
    private JobSpecification jobSpecification;

    /**
     * Information about jobs that have been and will be run under this
     * schedule.
     */
    @JsonProperty(value = "executionInfo")
    private JobScheduleExecutionInformation executionInfo;

    /**
     * A list of name-value pairs associated with the schedule as metadata.
     * The Batch service does not assign any meaning to metadata; it is solely
     * for the use of user code.
     */
    @JsonProperty(value = "metadata")
    private List<MetadataItem> metadata;

    /**
     * The lifetime resource usage statistics for the job schedule. The
     * statistics may not be immediately available. The Batch service performs
     * periodic roll-up of statistics. The typical delay is about 30 minutes.
     */
    @JsonProperty(value = "stats")
    private JobScheduleStatistics stats;

    /**
     * Get the id value.
     *
     * @return the id value
     */
    public String id() {
        return this.id;
    }

    /**
     * Set the id value.
     *
     * @param id the id value to set
     * @return the CloudJobSchedule object itself.
     */
    public CloudJobSchedule withId(String id) {
        this.id = id;
        return this;
    }

    /**
     * Get the displayName value.
     *
     * @return the displayName value
     */
    public String displayName() {
        return this.displayName;
    }

    /**
     * Set the displayName value.
     *
     * @param displayName the displayName value to set
     * @return the CloudJobSchedule object itself.
     */
    public CloudJobSchedule withDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    /**
     * Get the url value.
     *
     * @return the url value
     */
    public String url() {
        return this.url;
    }

    /**
     * Set the url value.
     *
     * @param url the url value to set
     * @return the CloudJobSchedule object itself.
     */
    public CloudJobSchedule withUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * Get this is an opaque string. You can use it to detect whether the job schedule has changed between requests. In particular, you can be pass the ETag with an Update Job Schedule request to specify that your changes should take effect only if nobody else has modified the schedule in the meantime.
     *
     * @return the eTag value
     */
    public String eTag() {
        return this.eTag;
    }

    /**
     * Set this is an opaque string. You can use it to detect whether the job schedule has changed between requests. In particular, you can be pass the ETag with an Update Job Schedule request to specify that your changes should take effect only if nobody else has modified the schedule in the meantime.
     *
     * @param eTag the eTag value to set
     * @return the CloudJobSchedule object itself.
     */
    public CloudJobSchedule withETag(String eTag) {
        this.eTag = eTag;
        return this;
    }

    /**
     * Get this is the last time at which the schedule level data, such as the job specification or recurrence information, changed. It does not factor in job-level changes such as new jobs being created or jobs changing state.
     *
     * @return the lastModified value
     */
    public DateTime lastModified() {
        return this.lastModified;
    }

    /**
     * Set this is the last time at which the schedule level data, such as the job specification or recurrence information, changed. It does not factor in job-level changes such as new jobs being created or jobs changing state.
     *
     * @param lastModified the lastModified value to set
     * @return the CloudJobSchedule object itself.
     */
    public CloudJobSchedule withLastModified(DateTime lastModified) {
        this.lastModified = lastModified;
        return this;
    }

    /**
     * Get the creationTime value.
     *
     * @return the creationTime value
     */
    public DateTime creationTime() {
        return this.creationTime;
    }

    /**
     * Set the creationTime value.
     *
     * @param creationTime the creationTime value to set
     * @return the CloudJobSchedule object itself.
     */
    public CloudJobSchedule withCreationTime(DateTime creationTime) {
        this.creationTime = creationTime;
        return this;
    }

    /**
     * Get possible values include: 'active', 'completed', 'disabled', 'terminating', 'deleting'.
     *
     * @return the state value
     */
    public JobScheduleState state() {
        return this.state;
    }

    /**
     * Set possible values include: 'active', 'completed', 'disabled', 'terminating', 'deleting'.
     *
     * @param state the state value to set
     * @return the CloudJobSchedule object itself.
     */
    public CloudJobSchedule withState(JobScheduleState state) {
        this.state = state;
        return this;
    }

    /**
     * Get the stateTransitionTime value.
     *
     * @return the stateTransitionTime value
     */
    public DateTime stateTransitionTime() {
        return this.stateTransitionTime;
    }

    /**
     * Set the stateTransitionTime value.
     *
     * @param stateTransitionTime the stateTransitionTime value to set
     * @return the CloudJobSchedule object itself.
     */
    public CloudJobSchedule withStateTransitionTime(DateTime stateTransitionTime) {
        this.stateTransitionTime = stateTransitionTime;
        return this;
    }

    /**
     * Get this property is not present if the job schedule is in its initial active state. Possible values include: 'active', 'completed', 'disabled', 'terminating', 'deleting'.
     *
     * @return the previousState value
     */
    public JobScheduleState previousState() {
        return this.previousState;
    }

    /**
     * Set this property is not present if the job schedule is in its initial active state. Possible values include: 'active', 'completed', 'disabled', 'terminating', 'deleting'.
     *
     * @param previousState the previousState value to set
     * @return the CloudJobSchedule object itself.
     */
    public CloudJobSchedule withPreviousState(JobScheduleState previousState) {
        this.previousState = previousState;
        return this;
    }

    /**
     * Get this property is not present if the job schedule is in its initial active state.
     *
     * @return the previousStateTransitionTime value
     */
    public DateTime previousStateTransitionTime() {
        return this.previousStateTransitionTime;
    }

    /**
     * Set this property is not present if the job schedule is in its initial active state.
     *
     * @param previousStateTransitionTime the previousStateTransitionTime value to set
     * @return the CloudJobSchedule object itself.
     */
    public CloudJobSchedule withPreviousStateTransitionTime(DateTime previousStateTransitionTime) {
        this.previousStateTransitionTime = previousStateTransitionTime;
        return this;
    }

    /**
     * Get the schedule value.
     *
     * @return the schedule value
     */
    public Schedule schedule() {
        return this.schedule;
    }

    /**
     * Set the schedule value.
     *
     * @param schedule the schedule value to set
     * @return the CloudJobSchedule object itself.
     */
    public CloudJobSchedule withSchedule(Schedule schedule) {
        this.schedule = schedule;
        return this;
    }

    /**
     * Get the jobSpecification value.
     *
     * @return the jobSpecification value
     */
    public JobSpecification jobSpecification() {
        return this.jobSpecification;
    }

    /**
     * Set the jobSpecification value.
     *
     * @param jobSpecification the jobSpecification value to set
     * @return the CloudJobSchedule object itself.
     */
    public CloudJobSchedule withJobSpecification(JobSpecification jobSpecification) {
        this.jobSpecification = jobSpecification;
        return this;
    }

    /**
     * Get the executionInfo value.
     *
     * @return the executionInfo value
     */
    public JobScheduleExecutionInformation executionInfo() {
        return this.executionInfo;
    }

    /**
     * Set the executionInfo value.
     *
     * @param executionInfo the executionInfo value to set
     * @return the CloudJobSchedule object itself.
     */
    public CloudJobSchedule withExecutionInfo(JobScheduleExecutionInformation executionInfo) {
        this.executionInfo = executionInfo;
        return this;
    }

    /**
     * Get the Batch service does not assign any meaning to metadata; it is solely for the use of user code.
     *
     * @return the metadata value
     */
    public List<MetadataItem> metadata() {
        return this.metadata;
    }

    /**
     * Set the Batch service does not assign any meaning to metadata; it is solely for the use of user code.
     *
     * @param metadata the metadata value to set
     * @return the CloudJobSchedule object itself.
     */
    public CloudJobSchedule withMetadata(List<MetadataItem> metadata) {
        this.metadata = metadata;
        return this;
    }

    /**
     * Get the stats value.
     *
     * @return the stats value
     */
    public JobScheduleStatistics stats() {
        return this.stats;
    }

    /**
     * Set the stats value.
     *
     * @param stats the stats value to set
     * @return the CloudJobSchedule object itself.
     */
    public CloudJobSchedule withStats(JobScheduleStatistics stats) {
        this.stats = stats;
        return this;
    }

}
