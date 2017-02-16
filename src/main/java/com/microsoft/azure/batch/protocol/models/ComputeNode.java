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
 * A compute node in the Batch service.
 */
public class ComputeNode {
    /**
     * The ID of the compute node.
     * Every node that is added to a pool is assigned a unique ID. Whenever a
     * node is removed from a pool, all of its local files are deleted, and the
     * ID is reclaimed and could be reused for new nodes.
     */
    @JsonProperty(value = "id")
    private String id;

    /**
     * The URL of the compute node.
     */
    @JsonProperty(value = "url")
    private String url;

    /**
     * The current state of the compute node.
     * Possible values include: 'idle', 'rebooting', 'reimaging', 'running',
     * 'unusable', 'creating', 'starting', 'waitingforstarttask',
     * 'starttaskfailed', 'unknown', 'leavingpool', 'offline'.
     */
    @JsonProperty(value = "state")
    private ComputeNodeState state;

    /**
     * Whether the compute node is available for task scheduling.
     * Possible values are: enabled – Tasks can be scheduled on the node.
     * disabled – No new tasks will be scheduled on the node. Tasks already
     * running on the node may still run to completion. All nodes start with
     * scheduling enabled. Possible values include: 'enabled', 'disabled'.
     */
    @JsonProperty(value = "schedulingState")
    private SchedulingState schedulingState;

    /**
     * The time at which the compute node entered its current state.
     */
    @JsonProperty(value = "stateTransitionTime")
    private DateTime stateTransitionTime;

    /**
     * The time at which the compute node was started.
     * This property may not be present if the node state is unusable.
     */
    @JsonProperty(value = "lastBootTime")
    private DateTime lastBootTime;

    /**
     * The time at which this compute node was allocated to the pool.
     */
    @JsonProperty(value = "allocationTime")
    private DateTime allocationTime;

    /**
     * The IP address that other compute nodes can use to communicate with this
     * compute node.
     * Every node that is added to a pool is assigned a unique IP address.
     * Whenever a node is removed from a pool, all of its local files are
     * deleted, and the IP address is reclaimed and could be reused for new
     * nodes.
     */
    @JsonProperty(value = "ipAddress")
    private String ipAddress;

    /**
     * An identifier which can be passed when adding a task to request that the
     * task be scheduled close to this compute node.
     */
    @JsonProperty(value = "affinityId")
    private String affinityId;

    /**
     * The size of the virtual machine hosting the compute node.
     * For information about available sizes of virtual machines for Cloud
     * Services pools (pools created with cloudServiceConfiguration), see Sizes
     * for Cloud Services
     * (http://azure.microsoft.com/documentation/articles/cloud-services-sizes-specs/).
     * Batch supports all Cloud Services VM sizes except ExtraSmall. For
     * information about available VM sizes for pools using images from the
     * Virtual Machines Marketplace (pools created with
     * virtualMachineConfiguration) see Sizes for Virtual Machines (Linux)
     * (https://azure.microsoft.com/documentation/articles/virtual-machines-linux-sizes/)
     * or Sizes for Virtual Machines (Windows)
     * (https://azure.microsoft.com/documentation/articles/virtual-machines-windows-sizes/).
     * Batch supports all Azure VM sizes except STANDARD_A0 and those with
     * premium storage (STANDARD_GS, STANDARD_DS, and STANDARD_DSV2 series).
     */
    @JsonProperty(value = "vmSize")
    private String vmSize;

    /**
     * The total number of job tasks completed on the compute node. This
     * includes Job Preparation, Job Release and Job Manager tasks, but not the
     * pool start task.
     */
    @JsonProperty(value = "totalTasksRun")
    private Integer totalTasksRun;

    /**
     * The total number of currently running job tasks on the compute node.
     * This includes Job Preparation, Job Release, and Job Manager tasks, but
     * not the pool start task.
     */
    @JsonProperty(value = "runningTasksCount")
    private Integer runningTasksCount;

    /**
     * The total number of job tasks which completed successfully (with
     * exitCode 0) on the compute node. This includes Job Preparation, Job
     * Release, and Job Manager tasks, but not the pool start task.
     */
    @JsonProperty(value = "totalTasksSucceeded")
    private Integer totalTasksSucceeded;

    /**
     * The list of tasks that are currently running on the compute node.
     */
    @JsonProperty(value = "recentTasks")
    private List<TaskInformation> recentTasks;

    /**
     * The task specified to run on the compute node as it joins the pool.
     */
    @JsonProperty(value = "startTask")
    private StartTask startTask;

    /**
     * Runtime information about the execution of the start task on the compute
     * node.
     */
    @JsonProperty(value = "startTaskInfo")
    private StartTaskInformation startTaskInfo;

    /**
     * The list of certificates installed on the compute node.
     * For Windows compute nodes, the Batch service installs the certificates
     * to the specified certificate store and location. For Linux compute
     * nodes, the certificates are stored in a directory inside the task
     * working directory and an environment variable AZ_BATCH_CERTIFICATES_DIR
     * is supplied to the task to query for this location. For certificates
     * with visibility of remoteuser, a certs directory is created in the
     * user's home directory (e.g., /home/&lt;user-name&gt;/certs) where
     * certificates are placed.
     */
    @JsonProperty(value = "certificateReferences")
    private List<CertificateReference> certificateReferences;

    /**
     * The list of errors that are currently being encountered by the compute
     * node.
     */
    @JsonProperty(value = "errors")
    private List<ComputeNodeError> errors;

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
     * @return the ComputeNode object itself.
     */
    public ComputeNode withId(String id) {
        this.id = id;
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
     * @return the ComputeNode object itself.
     */
    public ComputeNode withUrl(String url) {
        this.url = url;
        return this;
    }

    /**
     * Get the state value.
     *
     * @return the state value
     */
    public ComputeNodeState state() {
        return this.state;
    }

    /**
     * Set the state value.
     *
     * @param state the state value to set
     * @return the ComputeNode object itself.
     */
    public ComputeNode withState(ComputeNodeState state) {
        this.state = state;
        return this;
    }

    /**
     * Get the schedulingState value.
     *
     * @return the schedulingState value
     */
    public SchedulingState schedulingState() {
        return this.schedulingState;
    }

    /**
     * Set the schedulingState value.
     *
     * @param schedulingState the schedulingState value to set
     * @return the ComputeNode object itself.
     */
    public ComputeNode withSchedulingState(SchedulingState schedulingState) {
        this.schedulingState = schedulingState;
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
     * @return the ComputeNode object itself.
     */
    public ComputeNode withStateTransitionTime(DateTime stateTransitionTime) {
        this.stateTransitionTime = stateTransitionTime;
        return this;
    }

    /**
     * Get the lastBootTime value.
     *
     * @return the lastBootTime value
     */
    public DateTime lastBootTime() {
        return this.lastBootTime;
    }

    /**
     * Set the lastBootTime value.
     *
     * @param lastBootTime the lastBootTime value to set
     * @return the ComputeNode object itself.
     */
    public ComputeNode withLastBootTime(DateTime lastBootTime) {
        this.lastBootTime = lastBootTime;
        return this;
    }

    /**
     * Get the allocationTime value.
     *
     * @return the allocationTime value
     */
    public DateTime allocationTime() {
        return this.allocationTime;
    }

    /**
     * Set the allocationTime value.
     *
     * @param allocationTime the allocationTime value to set
     * @return the ComputeNode object itself.
     */
    public ComputeNode withAllocationTime(DateTime allocationTime) {
        this.allocationTime = allocationTime;
        return this;
    }

    /**
     * Get the ipAddress value.
     *
     * @return the ipAddress value
     */
    public String ipAddress() {
        return this.ipAddress;
    }

    /**
     * Set the ipAddress value.
     *
     * @param ipAddress the ipAddress value to set
     * @return the ComputeNode object itself.
     */
    public ComputeNode withIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
        return this;
    }

    /**
     * Get the affinityId value.
     *
     * @return the affinityId value
     */
    public String affinityId() {
        return this.affinityId;
    }

    /**
     * Set the affinityId value.
     *
     * @param affinityId the affinityId value to set
     * @return the ComputeNode object itself.
     */
    public ComputeNode withAffinityId(String affinityId) {
        this.affinityId = affinityId;
        return this;
    }

    /**
     * Get the vmSize value.
     *
     * @return the vmSize value
     */
    public String vmSize() {
        return this.vmSize;
    }

    /**
     * Set the vmSize value.
     *
     * @param vmSize the vmSize value to set
     * @return the ComputeNode object itself.
     */
    public ComputeNode withVmSize(String vmSize) {
        this.vmSize = vmSize;
        return this;
    }

    /**
     * Get the totalTasksRun value.
     *
     * @return the totalTasksRun value
     */
    public Integer totalTasksRun() {
        return this.totalTasksRun;
    }

    /**
     * Set the totalTasksRun value.
     *
     * @param totalTasksRun the totalTasksRun value to set
     * @return the ComputeNode object itself.
     */
    public ComputeNode withTotalTasksRun(Integer totalTasksRun) {
        this.totalTasksRun = totalTasksRun;
        return this;
    }

    /**
     * Get the runningTasksCount value.
     *
     * @return the runningTasksCount value
     */
    public Integer runningTasksCount() {
        return this.runningTasksCount;
    }

    /**
     * Set the runningTasksCount value.
     *
     * @param runningTasksCount the runningTasksCount value to set
     * @return the ComputeNode object itself.
     */
    public ComputeNode withRunningTasksCount(Integer runningTasksCount) {
        this.runningTasksCount = runningTasksCount;
        return this;
    }

    /**
     * Get the totalTasksSucceeded value.
     *
     * @return the totalTasksSucceeded value
     */
    public Integer totalTasksSucceeded() {
        return this.totalTasksSucceeded;
    }

    /**
     * Set the totalTasksSucceeded value.
     *
     * @param totalTasksSucceeded the totalTasksSucceeded value to set
     * @return the ComputeNode object itself.
     */
    public ComputeNode withTotalTasksSucceeded(Integer totalTasksSucceeded) {
        this.totalTasksSucceeded = totalTasksSucceeded;
        return this;
    }

    /**
     * Get the recentTasks value.
     *
     * @return the recentTasks value
     */
    public List<TaskInformation> recentTasks() {
        return this.recentTasks;
    }

    /**
     * Set the recentTasks value.
     *
     * @param recentTasks the recentTasks value to set
     * @return the ComputeNode object itself.
     */
    public ComputeNode withRecentTasks(List<TaskInformation> recentTasks) {
        this.recentTasks = recentTasks;
        return this;
    }

    /**
     * Get the startTask value.
     *
     * @return the startTask value
     */
    public StartTask startTask() {
        return this.startTask;
    }

    /**
     * Set the startTask value.
     *
     * @param startTask the startTask value to set
     * @return the ComputeNode object itself.
     */
    public ComputeNode withStartTask(StartTask startTask) {
        this.startTask = startTask;
        return this;
    }

    /**
     * Get the startTaskInfo value.
     *
     * @return the startTaskInfo value
     */
    public StartTaskInformation startTaskInfo() {
        return this.startTaskInfo;
    }

    /**
     * Set the startTaskInfo value.
     *
     * @param startTaskInfo the startTaskInfo value to set
     * @return the ComputeNode object itself.
     */
    public ComputeNode withStartTaskInfo(StartTaskInformation startTaskInfo) {
        this.startTaskInfo = startTaskInfo;
        return this;
    }

    /**
     * Get the certificateReferences value.
     *
     * @return the certificateReferences value
     */
    public List<CertificateReference> certificateReferences() {
        return this.certificateReferences;
    }

    /**
     * Set the certificateReferences value.
     *
     * @param certificateReferences the certificateReferences value to set
     * @return the ComputeNode object itself.
     */
    public ComputeNode withCertificateReferences(List<CertificateReference> certificateReferences) {
        this.certificateReferences = certificateReferences;
        return this;
    }

    /**
     * Get the errors value.
     *
     * @return the errors value
     */
    public List<ComputeNodeError> errors() {
        return this.errors;
    }

    /**
     * Set the errors value.
     *
     * @param errors the errors value to set
     * @return the ComputeNode object itself.
     */
    public ComputeNode withErrors(List<ComputeNodeError> errors) {
        this.errors = errors;
        return this;
    }

}
