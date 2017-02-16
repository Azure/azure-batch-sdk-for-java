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
 * An Azure Batch job.
 */
public class CloudJob {
    /**
     * A string that uniquely identifies the job within the account.
     * The ID can contain any combination of alphanumeric characters including
     * hyphens and underscores, and cannot contain more than 64 characters. It
     * is common to use a GUID for the id.
     */
    @JsonProperty(value = "id")
    private String id;

    /**
     * The display name for the job.
     */
    @JsonProperty(value = "displayName")
    private String displayName;

    /**
     * The flag that determines if this job will use tasks with dependencies.
     */
    @JsonProperty(value = "usesTaskDependencies")
    private Boolean usesTaskDependencies;

    /**
     * The URL of the job.
     */
    @JsonProperty(value = "url")
    private String url;

    /**
     * The ETag of the job.
     * This is an opaque string. You can use it to detect whether the job has
     * changed between requests. In particular, you can be pass the ETag when
     * updating a job to specify that your changes should take effect only if
     * nobody else has modified the job in the meantime.
     */
    @JsonProperty(value = "eTag")
    private String eTag;

    /**
     * The last modified time of the job.
     * This is the last time at which the job level data, such as the job state
     * or priority, changed. It does not factor in task-level changes such as
     * adding new tasks or tasks changing state.
     */
    @JsonProperty(value = "lastModified")
    private DateTime lastModified;

    /**
     * The creation time of the job.
     */
    @JsonProperty(value = "creationTime")
    private DateTime creationTime;

    /**
     * The current state of the job.
     * Possible values include: 'active', 'disabling', 'disabled', 'enabling',
     * 'terminating', 'completed', 'deleting'.
     */
    @JsonProperty(value = "state")
    private JobState state;

    /**
     * The time at which the job entered its current state.
     */
    @JsonProperty(value = "stateTransitionTime")
    private DateTime stateTransitionTime;

    /**
     * The previous state of the job.
     * This property is not set if the job is in its initial Active state.
     * Possible values include: 'active', 'disabling', 'disabled', 'enabling',
     * 'terminating', 'completed', 'deleting'.
     */
    @JsonProperty(value = "previousState")
    private JobState previousState;

    /**
     * The time at which the job entered its previous state.
     * This property is not set if the job is in its initial Active state.
     */
    @JsonProperty(value = "previousStateTransitionTime")
    private DateTime previousStateTransitionTime;

    /**
     * The priority of the job.
     * Priority values can range from -1000 to 1000, with -1000 being the
     * lowest priority and 1000 being the highest priority. The default value
     * is 0.
     */
    @JsonProperty(value = "priority")
    private Integer priority;

    /**
     * The execution constraints for the job.
     */
    @JsonProperty(value = "constraints")
    private JobConstraints constraints;

    /**
     * Details of a Job Manager task to be launched when the job is started.
     */
    @JsonProperty(value = "jobManagerTask")
    private JobManagerTask jobManagerTask;

    /**
     * The Job Preparation task.
     * The Job Preparation task is a special task run on each node before any
     * other task of the job.
     */
    @JsonProperty(value = "jobPreparationTask")
    private JobPreparationTask jobPreparationTask;

    /**
     * The Job Release task.
     * The Job Release task is a special task run at the end of the job on each
     * node that has run any other task of the job.
     */
    @JsonProperty(value = "jobReleaseTask")
    private JobReleaseTask jobReleaseTask;

    /**
     * The list of common environment variable settings. These environment
     * variables are set for all tasks in the job (including the Job Manager,
     * Job Preparation and Job Release tasks).
     */
    @JsonProperty(value = "commonEnvironmentSettings")
    private List<EnvironmentSetting> commonEnvironmentSettings;

    /**
     * The pool settings associated with the job.
     */
    @JsonProperty(value = "poolInfo")
    private PoolInformation poolInfo;

    /**
     * The action the Batch service should take when all tasks in the job are
     * in the completed state.
     * Permitted values are: noaction – do nothing. The job remains active
     * unless terminated or disabled by some other means. terminatejob –
     * terminate the job. The job's terminateReason is set to
     * 'AllTasksComplete'. The default is noaction. Possible values include:
     * 'noAction', 'terminateJob'.
     */
    @JsonProperty(value = "onAllTasksComplete")
    private OnAllTasksComplete onAllTasksComplete;

    /**
     * The action the Batch service should take when any task in the job fails.
     * A task is considered to have failed if it completes with a non-zero exit
     * code and has exhausted its retry count, or if it had a scheduling error.
     * Permitted values are: noaction – do nothing. performexitoptionsjobaction
     * – take the action associated with the task exit condition in the task's
     * exitConditions collection. (This may still result in no action being
     * taken, if that is what the task specifies.) The default is noaction.
     * Possible values include: 'noAction', 'performExitOptionsJobAction'.
     */
    @JsonProperty(value = "onTaskFailure")
    private OnTaskFailure onTaskFailure;

    /**
     * A list of name-value pairs associated with the job as metadata.
     * The Batch service does not assign any meaning to metadata; it is solely
     * for the use of user code.
     */
    @JsonProperty(value = "metadata")
    private List<MetadataItem> metadata;

    /**
     * The execution information for the job.
     */
    @JsonProperty(value = "executionInfo")
    private JobExecutionInformation executionInfo;

    /**
     * Resource usage statistics for the entire lifetime of the job.
     */
    @JsonProperty(value = "stats")
    private JobStatistics stats;

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
     * @return the CloudJob object itself.
     */
    public CloudJob withId(String id) {
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
     * @return the CloudJob object itself.
     */
    public CloudJob withDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    /**
     * Get the usesTaskDependencies value.
     *
     * @return the usesTaskDependencies value
     */
    public Boolean usesTaskDependencies() {
        return this.usesTaskDependencies;
    }

    /**
     * Set the usesTaskDependencies value.
     *
     * @param usesTaskDependencies the usesTaskDependencies value to set
     * @return the CloudJob object itself.
     */
    public CloudJob withUsesTaskDependencies(Boolean usesTaskDependencies) {
        this.usesTaskDependencies = usesTaskDependencies;
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
     * @return the CloudJob object itself.
     */
    public CloudJob withUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * Get the eTag value.
     *
     * @return the eTag value
     */
    public String eTag() {
        return this.eTag;
    }

    /**
     * Set the eTag value.
     *
     * @param eTag the eTag value to set
     * @return the CloudJob object itself.
     */
    public CloudJob withETag(String eTag) {
        this.eTag = eTag;
        return this;
    }

    /**
     * Get the lastModified value.
     *
     * @return the lastModified value
     */
    public DateTime lastModified() {
        return this.lastModified;
    }

    /**
     * Set the lastModified value.
     *
     * @param lastModified the lastModified value to set
     * @return the CloudJob object itself.
     */
    public CloudJob withLastModified(DateTime lastModified) {
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
     * @return the CloudJob object itself.
     */
    public CloudJob withCreationTime(DateTime creationTime) {
        this.creationTime = creationTime;
        return this;
    }

    /**
     * Get the state value.
     *
     * @return the state value
     */
    public JobState state() {
        return this.state;
    }

    /**
     * Set the state value.
     *
     * @param state the state value to set
     * @return the CloudJob object itself.
     */
    public CloudJob withState(JobState state) {
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
     * @return the CloudJob object itself.
     */
    public CloudJob withStateTransitionTime(DateTime stateTransitionTime) {
        this.stateTransitionTime = stateTransitionTime;
        return this;
    }

    /**
     * Get the previousState value.
     *
     * @return the previousState value
     */
    public JobState previousState() {
        return this.previousState;
    }

    /**
     * Set the previousState value.
     *
     * @param previousState the previousState value to set
     * @return the CloudJob object itself.
     */
    public CloudJob withPreviousState(JobState previousState) {
        this.previousState = previousState;
        return this;
    }

    /**
     * Get the previousStateTransitionTime value.
     *
     * @return the previousStateTransitionTime value
     */
    public DateTime previousStateTransitionTime() {
        return this.previousStateTransitionTime;
    }

    /**
     * Set the previousStateTransitionTime value.
     *
     * @param previousStateTransitionTime the previousStateTransitionTime value to set
     * @return the CloudJob object itself.
     */
    public CloudJob withPreviousStateTransitionTime(DateTime previousStateTransitionTime) {
        this.previousStateTransitionTime = previousStateTransitionTime;
        return this;
    }

    /**
     * Get the priority value.
     *
     * @return the priority value
     */
    public Integer priority() {
        return this.priority;
    }

    /**
     * Set the priority value.
     *
     * @param priority the priority value to set
     * @return the CloudJob object itself.
     */
    public CloudJob withPriority(Integer priority) {
        this.priority = priority;
        return this;
    }

    /**
     * Get the constraints value.
     *
     * @return the constraints value
     */
    public JobConstraints constraints() {
        return this.constraints;
    }

    /**
     * Set the constraints value.
     *
     * @param constraints the constraints value to set
     * @return the CloudJob object itself.
     */
    public CloudJob withConstraints(JobConstraints constraints) {
        this.constraints = constraints;
        return this;
    }

    /**
     * Get the jobManagerTask value.
     *
     * @return the jobManagerTask value
     */
    public JobManagerTask jobManagerTask() {
        return this.jobManagerTask;
    }

    /**
     * Set the jobManagerTask value.
     *
     * @param jobManagerTask the jobManagerTask value to set
     * @return the CloudJob object itself.
     */
    public CloudJob withJobManagerTask(JobManagerTask jobManagerTask) {
        this.jobManagerTask = jobManagerTask;
        return this;
    }

    /**
     * Get the jobPreparationTask value.
     *
     * @return the jobPreparationTask value
     */
    public JobPreparationTask jobPreparationTask() {
        return this.jobPreparationTask;
    }

    /**
     * Set the jobPreparationTask value.
     *
     * @param jobPreparationTask the jobPreparationTask value to set
     * @return the CloudJob object itself.
     */
    public CloudJob withJobPreparationTask(JobPreparationTask jobPreparationTask) {
        this.jobPreparationTask = jobPreparationTask;
        return this;
    }

    /**
     * Get the jobReleaseTask value.
     *
     * @return the jobReleaseTask value
     */
    public JobReleaseTask jobReleaseTask() {
        return this.jobReleaseTask;
    }

    /**
     * Set the jobReleaseTask value.
     *
     * @param jobReleaseTask the jobReleaseTask value to set
     * @return the CloudJob object itself.
     */
    public CloudJob withJobReleaseTask(JobReleaseTask jobReleaseTask) {
        this.jobReleaseTask = jobReleaseTask;
        return this;
    }

    /**
     * Get the commonEnvironmentSettings value.
     *
     * @return the commonEnvironmentSettings value
     */
    public List<EnvironmentSetting> commonEnvironmentSettings() {
        return this.commonEnvironmentSettings;
    }

    /**
     * Set the commonEnvironmentSettings value.
     *
     * @param commonEnvironmentSettings the commonEnvironmentSettings value to set
     * @return the CloudJob object itself.
     */
    public CloudJob withCommonEnvironmentSettings(List<EnvironmentSetting> commonEnvironmentSettings) {
        this.commonEnvironmentSettings = commonEnvironmentSettings;
        return this;
    }

    /**
     * Get the poolInfo value.
     *
     * @return the poolInfo value
     */
    public PoolInformation poolInfo() {
        return this.poolInfo;
    }

    /**
     * Set the poolInfo value.
     *
     * @param poolInfo the poolInfo value to set
     * @return the CloudJob object itself.
     */
    public CloudJob withPoolInfo(PoolInformation poolInfo) {
        this.poolInfo = poolInfo;
        return this;
    }

    /**
     * Get the onAllTasksComplete value.
     *
     * @return the onAllTasksComplete value
     */
    public OnAllTasksComplete onAllTasksComplete() {
        return this.onAllTasksComplete;
    }

    /**
     * Set the onAllTasksComplete value.
     *
     * @param onAllTasksComplete the onAllTasksComplete value to set
     * @return the CloudJob object itself.
     */
    public CloudJob withOnAllTasksComplete(OnAllTasksComplete onAllTasksComplete) {
        this.onAllTasksComplete = onAllTasksComplete;
        return this;
    }

    /**
     * Get the onTaskFailure value.
     *
     * @return the onTaskFailure value
     */
    public OnTaskFailure onTaskFailure() {
        return this.onTaskFailure;
    }

    /**
     * Set the onTaskFailure value.
     *
     * @param onTaskFailure the onTaskFailure value to set
     * @return the CloudJob object itself.
     */
    public CloudJob withOnTaskFailure(OnTaskFailure onTaskFailure) {
        this.onTaskFailure = onTaskFailure;
        return this;
    }

    /**
     * Get the metadata value.
     *
     * @return the metadata value
     */
    public List<MetadataItem> metadata() {
        return this.metadata;
    }

    /**
     * Set the metadata value.
     *
     * @param metadata the metadata value to set
     * @return the CloudJob object itself.
     */
    public CloudJob withMetadata(List<MetadataItem> metadata) {
        this.metadata = metadata;
        return this;
    }

    /**
     * Get the executionInfo value.
     *
     * @return the executionInfo value
     */
    public JobExecutionInformation executionInfo() {
        return this.executionInfo;
    }

    /**
     * Set the executionInfo value.
     *
     * @param executionInfo the executionInfo value to set
     * @return the CloudJob object itself.
     */
    public CloudJob withExecutionInfo(JobExecutionInformation executionInfo) {
        this.executionInfo = executionInfo;
        return this;
    }

    /**
     * Get the stats value.
     *
     * @return the stats value
     */
    public JobStatistics stats() {
        return this.stats;
    }

    /**
     * Set the stats value.
     *
     * @param stats the stats value to set
     * @return the CloudJob object itself.
     */
    public CloudJob withStats(JobStatistics stats) {
        this.stats = stats;
        return this;
    }

}
