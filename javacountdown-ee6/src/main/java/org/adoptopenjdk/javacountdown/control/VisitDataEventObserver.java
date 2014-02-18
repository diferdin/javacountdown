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

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Asynchronous;
import javax.enterprise.event.Observes;
import javax.enterprise.event.TransactionPhase;
import javax.inject.Inject;

import org.adoptopenjdk.javacountdown.control.DataAccessObject.Type;
import org.adoptopenjdk.javacountdown.entity.AdoptionReportCountry;
import org.adoptopenjdk.javacountdown.entity.Visit;

import com.google.code.morphia.Key;
import com.google.code.morphia.dao.BasicDAO;


/**
 * Observes events fired by the VisitDAO
 * 
 * @author Alex Theedom
 *
 */
@Asynchronous
public class VisitDataEventObserver {
    
    private static final Logger logger = Logger.getLogger(VisitDataEventObserver.class.getName());
    
    @Inject @DataAccessObject(Type.REPORT)
    BasicDAO<AdoptionReportCountry, Key<AdoptionReportCountry>> adoptionReportDAO;
    
    
    /**
     * If the Visit object has been persisted successfully we can update the adoption report data.
     * 
     * @param visit
     */
    public void onSuccess(@Observes(during = TransactionPhase.AFTER_SUCCESS) Visit visit) {     
                
        logger.log(Level.FINE, "Enter VisitDataEventObserver onSuccess");  
        
        AdoptionReportCountry adoptionReportCountry = ((AdoptionReportDAO) adoptionReportDAO).getCountryTotals(visit.getCountry());
        if(adoptionReportCountry == null){
            adoptionReportCountry = new AdoptionReportCountry(visit);
        }
        adoptionReportCountry.updateTotals(visit);
        Key<AdoptionReportCountry> key = adoptionReportDAO.save(adoptionReportCountry);  
 
        logger.log(Level.FINE, "Exit VisitDataEventObserver onSuccess. Persisted key {0}", key);
    }

    
    /**
     * If there is a failure in persisting the Visit object we log it and don't update the 
     * adoption report data.
     * @param visit
     */
    public void onFailure( @Observes(during = TransactionPhase.AFTER_FAILURE) Visit visit) {
        logger.log(Level.FINE, "VisitDataEventObserver onFailure");
    }

}
