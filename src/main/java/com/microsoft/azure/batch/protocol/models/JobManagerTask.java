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

import java.util.List;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Specifies details of a Job Manager task.
 * The Job Manager task is automatically started when the job is created. The
 * Batch service tries to schedule the Job Manager task before any other tasks
 * in the job. When shrinking a pool, the Batch service tries to preserve
 * compute nodes where Job Manager tasks are running for as long as possible
 * (that is, nodes running 'normal' tasks are removed before nodes running Job
 * Manager tasks). When a Job Manager task fails and needs to be restarted, the
 * system tries to schedule it at the highest priority. If there are no idle
 * nodes available, the system may terminate one of the running tasks in the
 * pool and return it to the queue in order to make room for the Job Manager
 * task to restart. Note that a Job Manager task in one job does not have
 * priority over tasks in other jobs. Across jobs, only job level priorities
 * are observed. For example, if a Job Manager in a priority 0 job needs to be
 * restarted, it will not displace tasks of a priority 1 job.
 */
public class JobManagerTask {
    /**
     * A string that uniquely identifies the Job Manager task within the job.
     * The ID can contain any combination of alphanumeric characters including
     * hyphens and underscores and cannot contain more than 64 characters.
     */
    @JsonProperty(value = "id", required = true)
    private String id;

    /**
     * The display name of the Job Manager task.
     * It need not be unique and can contain any Unicode characters up to a
     * maximum length of 1024.
     */
    @JsonProperty(value = "displayName")
    private String displayName;

    /**
     * The command line of the Job Manager task.
     * The command line does not run under a shell, and therefore cannot take
     * advantage of shell features such as environment variable expansion. If
     * you want to take advantage of such features, you should invoke the shell
     * in the command line, for example using "cmd /c MyCommand" in Windows or
     * "/bin/sh -c MyCommand" in Linux.
     */
    @JsonProperty(value = "commandLine", required = true)
    private String commandLine;

    /**
     * The settings for the container under which the Job Manager task runs.
     * If the pool that will run this task has containerConfiguration set, this
     * must be set as well. If the pool that will run this task doesn't have
     * containerConfiguration set, this must not be set. When this is
     * specified, all directories recursively below the AZ_BATCH_NODE_ROOT_DIR
     * (the root of Azure Batch directories on the node) are mapped into the
     * container, all task environment variables are mapped into the container,
     * and the task command line is executed in the container.
     */
    @JsonProperty(value = "containerSettings")
    private TaskContainerSettings containerSettings;

    /**
     * A list of files that the Batch service will download to the compute node
     * before running the command line.
     * Files listed under this element are located in the task's working
     * directory.
     */
    @JsonProperty(value = "resourceFiles")
    private List<ResourceFile> resourceFiles;

    /**
     * A list of files that the Batch service will upload from the compute node
     * after running the command line.
     * For multi-instance tasks, the files will only be uploaded from the
     * compute node on which the primary task is executed.
     */
    @JsonProperty(value = "outputFiles")
    private List<OutputFile> outputFiles;

    /**
     * A list of environment variable settings for the Job Manager task.
     */
    @JsonProperty(value = "environmentSettings")
    private List<EnvironmentSetting> environmentSettings;

    /**
     * Constraints that apply to the Job Manager task.
     */
    @JsonProperty(value = "constraints")
    private TaskConstraints constraints;

    /**
     * Whether completion of the Job Manager task signifies completion of the
     * entire job.
     * If true, when the Job Manager task completes, the Batch service marks
     * the job as complete. If any tasks are still running at this time (other
     * than Job Release), those tasks are terminated. If false, the completion
     * of the Job Manager task does not affect the job status. In this case,
     * you should either use the onAllTasksComplete attribute to terminate the
     * job, or have a client or user terminate the job explicitly. An example
     * of this is if the Job Manager creates a set of tasks but then takes no
     * further role in their execution. The default value is true. If you are
     * using the onAllTasksComplete and onTaskFailure attributes to control job
     * lifetime, and using the Job Manager task only to create the tasks for
     * the job (not to monitor progress), then it is important to set
     * killJobOnCompletion to false.
     */
    @JsonProperty(value = "killJobOnCompletion")
    private Boolean killJobOnCompletion;

    /**
     * The user identity under which the Job Manager task runs.
     * If omitted, the task runs as a non-administrative user unique to the
     * task.
     */
    @JsonProperty(value = "userIdentity")
    private UserIdentity userIdentity;

    /**
     * Whether the Job Manager task requires exclusive use of the compute node
     * where it runs.
     * If true, no other tasks will run on the same compute node for as long as
     * the Job Manager is running. If false, other tasks can run simultaneously
     * with the Job Manager on a compute node. The Job Manager task counts
     * normally against the node's concurrent task limit, so this is only
     * relevant if the node allows multiple concurrent tasks. The default value
     * is true.
     */
    @JsonProperty(value = "runExclusive")
    private Boolean runExclusive;

    /**
     * A list of application packages that the Batch service will deploy to the
     * compute node before running the command line.
     * Application packages are downloaded and deployed to a shared directory,
     * not the task working directory. Therefore, if a referenced package is
     * already on the compute node, and is up to date, then it is not
     * re-downloaded; the existing copy on the compute node is used. If a
     * referenced application package cannot be installed, for example because
     * the package has been deleted or because download failed, the task fails.
     */
    @JsonProperty(value = "applicationPackageReferences")
    private List<ApplicationPackageReference> applicationPackageReferences;

    /**
     * The settings for an authentication token that the task can use to
     * perform Batch service operations.
     * If this property is set, the Batch service provides the task with an
     * authentication token which can be used to authenticate Batch service
     * operations without requiring an account access key. The token is
     * provided via the AZ_BATCH_AUTHENTICATION_TOKEN environment variable. The
     * operations that the task can carry out using the token depend on the
     * settings. For example, a task can request job permissions in order to
     * add other tasks to the job, or check the status of the job or of other
     * tasks under the job.
     */
    @JsonProperty(value = "authenticationTokenSettings")
    private AuthenticationTokenSettings authenticationTokenSettings;

    /**
     * Whether the Job Manager task may run on a low-priority compute node.
     * The default value is false.
     */
    @JsonProperty(value = "allowLowPriorityNode")
    private Boolean allowLowPriorityNode;

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
     * @return the JobManagerTask object itself.
     */
    public JobManagerTask withId(String id) {
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
     * @return the JobManagerTask object itself.
     */
    public JobManagerTask withDisplayName(String displayName) {
        this.displayName = displayName;
        return this;
    }

    /**
     * Get the commandLine value.
     *
     * @return the commandLine value
     */
    public String commandLine() {
        return this.commandLine;
    }

    /**
     * Set the commandLine value.
     *
     * @param commandLine the commandLine value to set
     * @return the JobManagerTask object itself.
     */
    public JobManagerTask withCommandLine(String commandLine) {
        this.commandLine = commandLine;
        return this;
    }

    /**
     * Get the containerSettings value.
     *
     * @return the containerSettings value
     */
    public TaskContainerSettings containerSettings() {
        return this.containerSettings;
    }

    /**
     * Set the containerSettings value.
     *
     * @param containerSettings the containerSettings value to set
     * @return the JobManagerTask object itself.
     */
    public JobManagerTask withContainerSettings(TaskContainerSettings containerSettings) {
        this.containerSettings = containerSettings;
        return this;
    }

    /**
     * Get the resourceFiles value.
     *
     * @return the resourceFiles value
     */
    public List<ResourceFile> resourceFiles() {
        return this.resourceFiles;
    }

    /**
     * Set the resourceFiles value.
     *
     * @param resourceFiles the resourceFiles value to set
     * @return the JobManagerTask object itself.
     */
    public JobManagerTask withResourceFiles(List<ResourceFile> resourceFiles) {
        this.resourceFiles = resourceFiles;
        return this;
    }

    /**
     * Get the outputFiles value.
     *
     * @return the outputFiles value
     */
    public List<OutputFile> outputFiles() {
        return this.outputFiles;
    }

    /**
     * Set the outputFiles value.
     *
     * @param outputFiles the outputFiles value to set
     * @return the JobManagerTask object itself.
     */
    public JobManagerTask withOutputFiles(List<OutputFile> outputFiles) {
        this.outputFiles = outputFiles;
        return this;
    }

    /**
     * Get the environmentSettings value.
     *
     * @return the environmentSettings value
     */
    public List<EnvironmentSetting> environmentSettings() {
        return this.environmentSettings;
    }

    /**
     * Set the environmentSettings value.
     *
     * @param environmentSettings the environmentSettings value to set
     * @return the JobManagerTask object itself.
     */
    public JobManagerTask withEnvironmentSettings(List<EnvironmentSetting> environmentSettings) {
        this.environmentSettings = environmentSettings;
        return this;
    }

    /**
     * Get the constraints value.
     *
     * @return the constraints value
     */
    public TaskConstraints constraints() {
        return this.constraints;
    }

    /**
     * Set the constraints value.
     *
     * @param constraints the constraints value to set
     * @return the JobManagerTask object itself.
     */
    public JobManagerTask withConstraints(TaskConstraints constraints) {
        this.constraints = constraints;
        return this;
    }

    /**
     * Get the killJobOnCompletion value.
     *
     * @return the killJobOnCompletion value
     */
    public Boolean killJobOnCompletion() {
        return this.killJobOnCompletion;
    }

    /**
     * Set the killJobOnCompletion value.
     *
     * @param killJobOnCompletion the killJobOnCompletion value to set
     * @return the JobManagerTask object itself.
     */
    public JobManagerTask withKillJobOnCompletion(Boolean killJobOnCompletion) {
        this.killJobOnCompletion = killJobOnCompletion;
        return this;
    }

    /**
     * Get the userIdentity value.
     *
     * @return the userIdentity value
     */
    public UserIdentity userIdentity() {
        return this.userIdentity;
    }

    /**
     * Set the userIdentity value.
     *
     * @param userIdentity the userIdentity value to set
     * @return the JobManagerTask object itself.
     */
    public JobManagerTask withUserIdentity(UserIdentity userIdentity) {
        this.userIdentity = userIdentity;
        return this;
    }

    /**
     * Get the runExclusive value.
     *
     * @return the runExclusive value
     */
    public Boolean runExclusive() {
        return this.runExclusive;
    }

    /**
     * Set the runExclusive value.
     *
     * @param runExclusive the runExclusive value to set
     * @return the JobManagerTask object itself.
     */
    public JobManagerTask withRunExclusive(Boolean runExclusive) {
        this.runExclusive = runExclusive;
        return this;
    }

    /**
     * Get the applicationPackageReferences value.
     *
     * @return the applicationPackageReferences value
     */
    public List<ApplicationPackageReference> applicationPackageReferences() {
        return this.applicationPackageReferences;
    }

    /**
     * Set the applicationPackageReferences value.
     *
     * @param applicationPackageReferences the applicationPackageReferences value to set
     * @return the JobManagerTask object itself.
     */
    public JobManagerTask withApplicationPackageReferences(List<ApplicationPackageReference> applicationPackageReferences) {
        this.applicationPackageReferences = applicationPackageReferences;
        return this;
    }

    /**
     * Get the authenticationTokenSettings value.
     *
     * @return the authenticationTokenSettings value
     */
    public AuthenticationTokenSettings authenticationTokenSettings() {
        return this.authenticationTokenSettings;
    }

    /**
     * Set the authenticationTokenSettings value.
     *
     * @param authenticationTokenSettings the authenticationTokenSettings value to set
     * @return the JobManagerTask object itself.
     */
    public JobManagerTask withAuthenticationTokenSettings(AuthenticationTokenSettings authenticationTokenSettings) {
        this.authenticationTokenSettings = authenticationTokenSettings;
        return this;
    }

    /**
     * Get the allowLowPriorityNode value.
     *
     * @return the allowLowPriorityNode value
     */
    public Boolean allowLowPriorityNode() {
        return this.allowLowPriorityNode;
    }

    /**
     * Set the allowLowPriorityNode value.
     *
     * @param allowLowPriorityNode the allowLowPriorityNode value to set
     * @return the JobManagerTask object itself.
     */
    public JobManagerTask withAllowLowPriorityNode(Boolean allowLowPriorityNode) {
        this.allowLowPriorityNode = allowLowPriorityNode;
        return this;
    }

}
