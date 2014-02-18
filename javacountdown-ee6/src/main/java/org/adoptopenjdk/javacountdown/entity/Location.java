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

import com.google.code.morphia.annotations.Embedded;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * This entity represents the location embedded entity with the
 * geoPosistion entity.
 * 
 * @author Alex Theedom
 *
 */
@Embedded
public class Location implements Serializable{
    
    private static final long serialVersionUID = -6886204318924106851L;
    private String type;
    private List<Float> coordinates = new ArrayList<>();
    
    
    public Location() {
    }

    public Location(String type) {
        this.type = type;
    }

    public Location(String type, List<Float> coordinates) {
        this.type = type;
        this.coordinates = coordinates;
    }
        
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type=type;
    }
    
    
    public List<Float> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(List<Float> coordinates) {
        this.coordinates=coordinates;
    }
    
    
    public void addCoordinates(String lon, String lat){        
        coordinates.add(Float.valueOf(lon));
        coordinates.add(Float.valueOf(lat));
    }
    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (coordinates != null ? coordinates.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Location)) {
            return false;
        }
        Location other = (Location) object;
        if ((this.coordinates == null && other.coordinates != null)
                || (this.coordinates != null && !this.coordinates.equals(other.coordinates))) {
            return false;
        }
        return true;
    }
    
    @Override
    public String toString() {
        return "Location(type: " + type + ", coordinates: " + coordinates + ")";
    }
    
}
