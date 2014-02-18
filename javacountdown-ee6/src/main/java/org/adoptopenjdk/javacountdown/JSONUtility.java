package org.adoptopenjdk.javacountdown;

import java.util.ArrayList;
import java.util.List;

import com.mongodb.BasicDBObject;

public class JSONUtility {
        
    /**
     * Constructs a JSON document based on a String Array.
     * @param data
     * @return JSON document
     */
    public BasicDBObject constructJSONDocument(String[] data){
            
        List<Float> coordinates = new ArrayList<>();
        coordinates.add(Float.valueOf(data[6])); // Longitude
        coordinates.add(Float.valueOf(data[5])); // Latitude
        
        BasicDBObject embeddedGeoJSON     = new BasicDBObject("type", "Point")
                                                        .append("coordinates", coordinates);    
        
        BasicDBObject document             = new BasicDBObject("country", data[0])
                                                        .append("city", data[2])
                                                        .append("location", embeddedGeoJSON);        

        return document;
    }

}
