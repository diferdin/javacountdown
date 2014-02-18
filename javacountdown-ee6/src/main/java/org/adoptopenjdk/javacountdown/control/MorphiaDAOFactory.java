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


import com.google.code.morphia.DatastoreImpl;
import com.google.code.morphia.Key;
import com.google.code.morphia.dao.BasicDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.adoptopenjdk.javacountdown.control.DataAccessObject.Type;
import org.adoptopenjdk.javacountdown.entity.AdoptionReportCountry;
import org.adoptopenjdk.javacountdown.entity.Visit;
import org.adoptopenjdk.javacountdown.entity.GeoPosition;


/**
 * 
 * Factory that produces the Data Access Objects.
 * 
 * @author Alex Theedom
 *
 */
@ApplicationScoped
public class MorphiaDAOFactory {    
    
    @Inject
    private DatastoreImpl datastore;
        
    @Produces @DataAccessObject(Type.VISIT)
    public BasicDAO<Visit, Key<Visit>> createVisitDAO(){    
        return new VisitDAO(Visit.class, datastore);            
    }
    
    @Produces @DataAccessObject(Type.GEOPOSITION)
    public BasicDAO<GeoPosition, Key<GeoPosition>> createGeoPositionDOA(){        
        return new GeoPositionDAO(GeoPosition.class, this.datastore);    
    }
    
    @Produces  @DataAccessObject(Type.REPORT)
    public BasicDAO<AdoptionReportCountry, Key<AdoptionReportCountry>> createReportDOA(){        
        return new AdoptionReportDAO(AdoptionReportCountry.class, this.datastore);    
    }
}
