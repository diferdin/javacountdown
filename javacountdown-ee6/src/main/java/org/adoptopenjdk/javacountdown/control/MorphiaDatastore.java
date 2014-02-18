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

import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import com.mongodb.MongoClient;
import com.google.code.morphia.DatastoreImpl;
import com.google.code.morphia.Morphia;

/**
 * 
 * Produces Morphia datastore objects used by the DAO 
 * to persist data in MongoDB.
 * 
 * @author Alex Theedom
 * 
 */
@Startup
@Singleton
@ApplicationScoped
public class MorphiaDatastore {

    private static final Logger logger = Logger.getLogger(MorphiaDatastore.class.getName());
    
    private static final String DATABASE_NAME     = "jcountdown";    
    private static final String HOST             = "localhost";
    private static final int PORT                 = 27017;

    
    @Produces
    public DatastoreImpl getDatastore() {

        logger.log(Level.FINE, "Enter MorphiaDatastore");
        
        MongoClient mongoClient = null;
        
        try {
            mongoClient = new MongoClient(HOST, PORT);
        } catch (UnknownHostException e) {
            logger.log(Level.FINE, "Exception thrown while creating Mongo client: {0}", e.getMessage());
        }

        Morphia morphia = new Morphia();

        DatastoreImpl datastore = (DatastoreImpl) morphia.createDatastore(mongoClient, DATABASE_NAME);
        logger.log(Level.FINE, "Exit MorphiaDatastore: Mongo Datastore Created");
    
        return datastore;
    }

}
