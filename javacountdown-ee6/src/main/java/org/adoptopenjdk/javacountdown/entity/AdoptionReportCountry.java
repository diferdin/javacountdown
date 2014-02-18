package org.adoptopenjdk.javacountdown.entity;

import java.io.Serializable;
import javax.enterprise.context.RequestScoped;
import org.bson.types.ObjectId;
import com.google.code.morphia.annotations.Id;
import com.google.code.morphia.annotations.Entity;

/**
 * This entity represents the data used to generate the world map showing the
 * global adoption of Java 7.
 * 
 * @author Alex Theedom
 * 
 */
@RequestScoped
@Entity(value = "jdkadoption", noClassnameStored = true)
public class AdoptionReportCountry implements Serializable {

    private static final long serialVersionUID = 5988042409552325372L;

    private static final int VERSION_SEVEN = 7;

    @Id
    private ObjectId id;
    private String country;
    private int version;
    private int total;
    private int percentage;

    
    public AdoptionReportCountry(){}    
    
    /**
     * Constructor that creates a new JdkAdoptionCountry 
     * from a Visit object.
     * @param visit
     */
    public AdoptionReportCountry(Visit visit) {
        this.setCountry(visit.getCountry());    
    }
    
    

    /**
     * Updates the totals for this country and calculates the percentage.
     * @param visit
     */
    public void updateTotals(Visit visit) {

        int visitTotal = getTotal();
        this.setTotal(++visitTotal);

        int versionTotal = this.getVersion();

        if (visit.isVersion(VERSION_SEVEN)) {
            this.setVersion(++versionTotal);
        }        

        this.setPercentage(Math.round(((float)versionTotal/visitTotal) * 100));
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPercentage() {
        return percentage;
    }

    public void setPercentage(int percentage) {
        this.percentage = percentage;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((country == null) ? 0 : country.hashCode());
        result = prime * result + percentage;
        result = prime * result + total;
        result = prime * result + version;
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
        AdoptionReportCountry other = (AdoptionReportCountry) obj;
        if (country == null) {
            if (other.country != null)
                return false;
        } else if (!country.equals(other.country))
            return false;
        if (percentage != other.percentage)
            return false;
        if (total != other.total)
            return false;
        if (version != other.version)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "JdkAdoption [country=" + country + ", version=" + version
                + ", total=" + total + ", percentage=" + percentage + "]";
    }

}
