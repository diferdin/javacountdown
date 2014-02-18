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
package org.adoptopenjdk.javacountdown.control;

import java.io.Serializable;
import java.util.Date;


/**
 * This class is a used to transfer data received via REST from the
 * visitor to the DAO. We use this DTO to avoid
 * the need to format data in the correct JSON format on the client side.
 * 
 * @author Alex Theedom
 * 
 */
public class VisitTransfer implements Serializable{
    
    private static final long serialVersionUID = 5128072717995441603L;
    private String version;
    private int vMajor;
    private int vMinor;
    private int vPatch;
    private int vBuild;
    private double latitude;
    private double longitude;
    private String country;
    private String browser;
    private String os;
    private Date time;

 

    public VisitTransfer() {
    }

    public String getVersion() {
        return this.version;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
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

    public int getvMajor() {
        return vMajor;
    }

    public void setvMajor(int vMajor) {
        this.vMajor = vMajor;
    }

    public int getvMinor() {
        return vMinor;
    }

    public void setvMinor(int vMinor) {
        this.vMinor = vMinor;
    }

    public int getvPatch() {
        return vPatch;
    }

    public void setvPatch(int vPatch) {
        this.vPatch = vPatch;
    }

    public int getvBuild() {
        return vBuild;
    }

    public void setvBuild(int vBuild) {
        this.vBuild = vBuild;
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
    public String toString() {
        return "VisitTransfer [version=" + version + ", vMajor=" + vMajor
                + ", vMinor=" + vMinor + ", vPatch=" + vPatch + ", vBuild="
                + vBuild + ", latitude=" + latitude + ", longitude="
                + longitude + ", country=" + country + ", browser=" + browser
                + ", os=" + os + ", time=" + time + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((browser == null) ? 0 : browser.hashCode());
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        long temp;
        temp = Double.doubleToLongBits(latitude);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(longitude);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + ((os == null) ? 0 : os.hashCode());
        result = prime * result + ((time == null) ? 0 : time.hashCode());
        result = prime * result + vBuild;
        result = prime * result + vMajor;
        result = prime * result + vMinor;
        result = prime * result + vPatch;
        result = prime * result + ((version == null) ? 0 : version.hashCode());
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
        VisitTransfer other = (VisitTransfer) obj;
        if (browser == null) {
            if (other.browser != null)
                return false;
        } else if (!browser.equals(other.browser))
            return false;
        if (country == null) {
            if (other.country != null)
                return false;
        } else if (!country.equals(other.country))
            return false;
        if (Double.doubleToLongBits(latitude) != Double
                .doubleToLongBits(other.latitude))
            return false;
        if (Double.doubleToLongBits(longitude) != Double
                .doubleToLongBits(other.longitude))
            return false;
        if (os == null) {
            if (other.os != null)
                return false;
        } else if (!os.equals(other.os))
            return false;
        if (time == null) {
            if (other.time != null)
                return false;
        } else if (!time.equals(other.time))
            return false;
        if (vBuild != other.vBuild)
            return false;
        if (vMajor != other.vMajor)
            return false;
        if (vMinor != other.vMinor)
            return false;
        if (vPatch != other.vPatch)
            return false;
        if (version == null) {
            if (other.version != null)
                return false;
        } else if (!version.equals(other.version))
            return false;
        return true;
    }

}
