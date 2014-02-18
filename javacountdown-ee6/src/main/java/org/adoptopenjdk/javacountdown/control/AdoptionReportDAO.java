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

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.adoptopenjdk.javacountdown.entity.AdoptionReportCountry;

import com.google.code.morphia.DatastoreImpl;
import com.google.code.morphia.Key;
import com.google.code.morphia.dao.BasicDAO;
import com.google.code.morphia.query.Query;

/**
 * 
 * Data Access Object for the JdkAdoption collection.
 * 
 * @author Alex Theedom
 *
 */
public class AdoptionReportDAO extends BasicDAO<AdoptionReportCountry, Key<AdoptionReportCountry>> {
    
    private static final Logger logger = Logger.getLogger(AdoptionReportDAO.class.getName());
        
    
    public AdoptionReportDAO(Class<AdoptionReportCountry> entityClass, DatastoreImpl datastore) {
        super(entityClass, datastore);
    }

    
    
    /**
     * Finds the document for the given country in the JDK adoption collecting
     * 
     * @param country
     * @return
     */
    public AdoptionReportCountry getCountryTotals(String country){        
        Query<AdoptionReportCountry> query = ds.createQuery(AdoptionReportCountry.class).field("country").equal(country);    
        return query.get();
    }
    
    
    
    /**
     * Returns the data used to generate the world map of JDK 7 adoption.
     * @return
     */
    public Map<String, Integer> getJdkAdoption(){        
            logger.log(Level.FINE, "Enter AdoptionReportDAO getJdkAdoption");
        List<AdoptionReportCountry> adoptionByCountry = ds.createQuery(AdoptionReportCountry.class).retrievedFields(true, "country", "percentage").asList();    
        
        Map<String, Integer> countryPercentageAdoption = new HashMap<>();
        for(AdoptionReportCountry country : adoptionByCountry){
            countryPercentageAdoption.put(country.getCountry(), country.getPercentage());
        }

            logger.log(Level.FINE, "Exit AdoptionReportDAO getJdkAdoption: {0}", countryPercentageAdoption);
        return countryPercentageAdoption;
    }
    

}
