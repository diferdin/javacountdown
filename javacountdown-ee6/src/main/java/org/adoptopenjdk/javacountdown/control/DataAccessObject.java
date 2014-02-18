package org.adoptopenjdk.javacountdown.control;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.inject.Qualifier;


@Qualifier
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface DataAccessObject {

    Type value();
    
    enum Type{ VISIT, GEOPOSITION, REPORT } 

}
