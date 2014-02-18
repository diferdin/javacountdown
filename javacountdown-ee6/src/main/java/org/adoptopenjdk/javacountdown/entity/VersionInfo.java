/*
 * Copyright [2013] Adopt OpenJDK Programme
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */
package org.adoptopenjdk.javacountdown.entity;

import java.io.Serializable;

import com.google.code.morphia.annotations.Embedded;

/**
 * 
 * This entity represents the visitor's Java version information
 * 
 * @author Alex Theedom
 * 
 */
@Embedded
public class VersionInfo implements Serializable {


    private static final long serialVersionUID = -1203925642378324622L;
    
    private int majorVersion;
    private int minorVersion;
    private int patchVersion;
    private int buildVersion;
    
    
    public int getMajorVersion() {
        return majorVersion;
    }

    public void setMajorVersion(int majorVersion) {
        this.majorVersion = majorVersion;
    }

    public int getMinorVersion() {
        return minorVersion;
    }

    public void setMinorVersion(int minorVersion) {
        this.minorVersion = minorVersion;
    }

    public int getPatchVersion() {
        return patchVersion;
    }

    public void setPatchVersion(int patchVersion) {
        this.patchVersion = patchVersion;
    }

    public int getBuildVersion() {
        return buildVersion;
    }

    public void setBuildVersion(int buildVersion) {
        this.buildVersion = buildVersion;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + buildVersion;
        result = prime * result + majorVersion;
        result = prime * result + minorVersion;
        result = prime * result + patchVersion;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        VersionInfo other = (VersionInfo) obj;
        if (buildVersion != other.buildVersion)
            return false;
        if (majorVersion != other.majorVersion)
            return false;
        if (minorVersion != other.minorVersion)
            return false;
        if (patchVersion != other.patchVersion)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Visit{majorVersion=" + majorVersion + ", minorVersion=" + minorVersion + ", patchVersion="
                + patchVersion + ", buildVersion=" + buildVersion + "}";
    }

}
