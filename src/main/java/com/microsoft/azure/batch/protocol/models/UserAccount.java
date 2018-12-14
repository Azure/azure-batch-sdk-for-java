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
 * Properties used to create a user used to execute tasks on an Azure Batch
 * node.
 */
public class UserAccount {
    /**
     * The name of the user account.
     */
    @JsonProperty(value = "name", required = true)
    private String name;

    /**
     * The password for the user account.
     */
    @JsonProperty(value = "password", required = true)
    private String password;

    /**
     * The elevation level of the user account.
     * The default value is nonAdmin. Possible values include: 'nonAdmin',
     * 'admin'.
     */
    @JsonProperty(value = "elevationLevel")
    private ElevationLevel elevationLevel;

    /**
     * The Linux-specific user configuration for the user account.
     * This property is ignored if specified on a Windows pool. If not
     * specified, the user is created with the default options.
     */
    @JsonProperty(value = "linuxUserConfiguration")
    private LinuxUserConfiguration linuxUserConfiguration;

    /**
     * The Windows-specific user configuration for the user account.
     * This property can only be specified if the user is on a Windows pool. If
     * not specified and on a Windows pool, the user is created with the
     * default options.
     */
    @JsonProperty(value = "windowsUserConfiguration")
    private WindowsUserConfiguration windowsUserConfiguration;

    /**
     * Get the name value.
     *
     * @return the name value
     */
    public String name() {
        return this.name;
    }

    /**
     * Set the name value.
     *
     * @param name the name value to set
     * @return the UserAccount object itself.
     */
    public UserAccount withName(String name) {
        this.name = name;
        return this;
    }

    /**
     * Get the password value.
     *
     * @return the password value
     */
    public String password() {
        return this.password;
    }

    /**
     * Set the password value.
     *
     * @param password the password value to set
     * @return the UserAccount object itself.
     */
    public UserAccount withPassword(String password) {
        this.password = password;
        return this;
    }

    /**
     * Get the default value is nonAdmin. Possible values include: 'nonAdmin', 'admin'.
     *
     * @return the elevationLevel value
     */
    public ElevationLevel elevationLevel() {
        return this.elevationLevel;
    }

    /**
     * Set the default value is nonAdmin. Possible values include: 'nonAdmin', 'admin'.
     *
     * @param elevationLevel the elevationLevel value to set
     * @return the UserAccount object itself.
     */
    public UserAccount withElevationLevel(ElevationLevel elevationLevel) {
        this.elevationLevel = elevationLevel;
        return this;
    }

    /**
     * Get this property is ignored if specified on a Windows pool. If not specified, the user is created with the default options.
     *
     * @return the linuxUserConfiguration value
     */
    public LinuxUserConfiguration linuxUserConfiguration() {
        return this.linuxUserConfiguration;
    }

    /**
     * Set this property is ignored if specified on a Windows pool. If not specified, the user is created with the default options.
     *
     * @param linuxUserConfiguration the linuxUserConfiguration value to set
     * @return the UserAccount object itself.
     */
    public UserAccount withLinuxUserConfiguration(LinuxUserConfiguration linuxUserConfiguration) {
        this.linuxUserConfiguration = linuxUserConfiguration;
        return this;
    }

    /**
     * Get this property can only be specified if the user is on a Windows pool. If not specified and on a Windows pool, the user is created with the default options.
     *
     * @return the windowsUserConfiguration value
     */
    public WindowsUserConfiguration windowsUserConfiguration() {
        return this.windowsUserConfiguration;
    }

    /**
     * Set this property can only be specified if the user is on a Windows pool. If not specified and on a Windows pool, the user is created with the default options.
     *
     * @param windowsUserConfiguration the windowsUserConfiguration value to set
     * @return the UserAccount object itself.
     */
    public UserAccount withWindowsUserConfiguration(WindowsUserConfiguration windowsUserConfiguration) {
        this.windowsUserConfiguration = windowsUserConfiguration;
        return this;
    }

}
