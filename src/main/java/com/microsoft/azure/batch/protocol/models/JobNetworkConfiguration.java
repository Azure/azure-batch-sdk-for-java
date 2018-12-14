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
 * The network configuration for the job.
 */
public class JobNetworkConfiguration {
    /**
     * The ARM resource identifier of the virtual network subnet which nodes
     * running tasks from the job will join for the duration of the task.
     * This is only supported for jobs running on VirtualMachineConfiguration
     * pools. This is of the form
     * /subscriptions/{subscription}/resourceGroups/{group}/providers/{provider}/virtualNetworks/{network}/subnets/{subnet}.
     * The virtual network must be in the same region and subscription as the
     * Azure Batch account. The specified subnet should have enough free IP
     * addresses to accommodate the number of nodes which will run tasks from
     * the job. For more details, see
     * https://docs.microsoft.com/en-us/azure/batch/batch-api-basics#virtual-network-vnet-and-firewall-configuration.
     */
    @JsonProperty(value = "subnetId", required = true)
    private String subnetId;

    /**
     * Get this is only supported for jobs running on VirtualMachineConfiguration pools. This is of the form /subscriptions/{subscription}/resourceGroups/{group}/providers/{provider}/virtualNetworks/{network}/subnets/{subnet}. The virtual network must be in the same region and subscription as the Azure Batch account. The specified subnet should have enough free IP addresses to accommodate the number of nodes which will run tasks from the job. For more details, see https://docs.microsoft.com/en-us/azure/batch/batch-api-basics#virtual-network-vnet-and-firewall-configuration.
     *
     * @return the subnetId value
     */
    public String subnetId() {
        return this.subnetId;
    }

    /**
     * Set this is only supported for jobs running on VirtualMachineConfiguration pools. This is of the form /subscriptions/{subscription}/resourceGroups/{group}/providers/{provider}/virtualNetworks/{network}/subnets/{subnet}. The virtual network must be in the same region and subscription as the Azure Batch account. The specified subnet should have enough free IP addresses to accommodate the number of nodes which will run tasks from the job. For more details, see https://docs.microsoft.com/en-us/azure/batch/batch-api-basics#virtual-network-vnet-and-firewall-configuration.
     *
     * @param subnetId the subnetId value to set
     * @return the JobNetworkConfiguration object itself.
     */
    public JobNetworkConfiguration withSubnetId(String subnetId) {
        this.subnetId = subnetId;
        return this;
    }

}
