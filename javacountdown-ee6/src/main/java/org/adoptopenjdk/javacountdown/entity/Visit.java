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
import java.util.Date;

import javax.enterprise.context.RequestScoped;
import org.bson.types.ObjectId;

import com.google.code.morphia.annotations.Entity;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Reference;

/**
 * Visit class, represents an end user hitting a website with their Java applet
 * enabled event.
 * 
 * @author Alex Theedom
 */
@RequestScoped
@Entity(value = "visit", noClassnameStored = true)
public class Visit implements Serializable {

    
    private static final long serialVersionUID = -5580843065068184730L;
    @Id
    private ObjectId id;
    private int version;
    private VersionInfo versionInfo;
    private String country;
    @Reference
    private GeoPosition geoPosition;
    private String browser;
    private String os;
    private Date time;

    public Visit() {
    }
    
    public boolean isVersion(int version){
        return this.version == version;
    }

    public GeoPosition getGeoPosition() {
        return geoPosition;
    }

    public void setGeoPosition(GeoPosition geoPosition) {
        this.geoPosition = geoPosition;
    }

    public int getVersion() {
        return this.version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    /**
     * Return a clone of the time to follow thread-safe programming practices
     * 
     * @return a clone of the time
     */
    public Date getTime() {
        return (Date) time.clone();
    }

    /**
     * Set a clone of the time to follow thread-safe programming practices
     * 
     * @param time
     */
    public void setTime(Date time) {
        this.time = (Date) time.clone();
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public VersionInfo getVersionInfo() {
        return versionInfo;
    }

    public void setVersionInfo(VersionInfo versionInfo) {
        this.versionInfo = versionInfo;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getOs() {
        return os;
    }

    public void setOs(String os) {
        this.os = os;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + (this.id != null ? this.id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Visit other = (Visit) obj;
        return this.id == other.id
                || (this.id != null && this.id.equals(other.id));
    }

    @Override
    public String toString() {
        return "Visit [id=" + id + ", version=" + version + ", versionInfo="
                + versionInfo + ", country=" + country + ", geoPosition="
                + geoPosition + ", browser=" + browser + ", os=" + os
                + ", time=" + time + "]";
    }

    
}
