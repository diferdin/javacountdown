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

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.adoptopenjdk.javacountdown.entity.GeoPosition;
import org.bson.types.ObjectId;

import com.google.code.morphia.DatastoreImpl;
import com.google.code.morphia.Key;
import com.google.code.morphia.dao.BasicDAO;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

/**
 * 
 * Data Access Object for the GeoPosition collection.
 * 
 * @author Alex Theedom
 *
 */
public class GeoPositionDAO extends BasicDAO<GeoPosition, Key<GeoPosition>> {
    
    private static final Logger logger = Logger.getLogger(GeoPositionDAO.class.getName());
        
    public GeoPositionDAO(Class<GeoPosition> entityClass, DatastoreImpl datastore) {
        super(entityClass, datastore);
    }
    
    
    /**
     * This method retrieves the country code based on the given latitude/longitude. 
     * It will return a GeoPosition object containing the ISO 3166 alpha-2 code.
     * Refer to http://www.maxmind.com/en/worldcities for the data behind it.
     * 
     * @param double latitude
     * @param double longitude
     * @return A GeoPosition entity
     */
    public GeoPosition getGeoPosition(double latitude, double longitude){
        
        logger.log(Level.FINE, "Enter GeoPositionDAO getGeoPosition.");
        
        GeoPosition geoPosition = new GeoPosition();    
        
        // Construct the query necessary to find the country code based on the given coords
        List<Double> coordinates = new ArrayList<>();
        coordinates.add(longitude);
        coordinates.add(latitude);
        
        BasicDBObject query =     new BasicDBObject("location.coordinates", 
                                new BasicDBObject("$near", 
                                new BasicDBObject("$geometry",
                                new BasicDBObject("type", "Point").append("coordinates", coordinates))));
        
        DBCursor cursor =  getCollection().find(query).limit(1);

        // Check that there is at least one result and populate the GeoPosition object
        if (cursor.hasNext()) {            
            DBObject dbObject     = cursor.next();            
            geoPosition.setId((ObjectId) dbObject.get("_id"));
            geoPosition.setCountry((String) dbObject.get("country"));
            geoPosition.setCity((String) dbObject.get("city"));
        } else {
            logger.log(Level.FINE, "No data was return by the query.");        
        }

        logger.log(Level.FINE, "Exit GeoPositionDAO getGeoPosition.");
        
        return geoPosition;        
    }

}
