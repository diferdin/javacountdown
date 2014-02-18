/*
 * Copyright 2013 Adopt OpenJDK.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.adoptopenjdk.javacountdown.control;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Schedule;
import javax.ejb.Singleton;
import javax.inject.Inject;

/**
 * Caches the data use to generate the world map of JDK adoption
 * 
 * @author Markus Eisele <markus at eisele.net>
 */
@Singleton
public class ResultCache {

    private static final Logger logger = Logger.getLogger(ResultCache.class.getName());
    
    String json = "";

    @Inject
    private DataProvider dataProvider;

    public String getCountryData() {    
        if (json.isEmpty()) {
            json = dataProvider.getJdkAdoptionReport();
        }
        return json;
    }

        
    @Schedule(minute = "*/2", persistent = false)
    public void rebuildCache() {
        logger.log(Level.FINE, "ResultCache rebuildCache");
        json = dataProvider.getJdkAdoptionReport();
    }
}
