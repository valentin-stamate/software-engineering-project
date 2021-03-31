package com.bfourclass.euopendata.ExternalAPI.instance.eu_open_data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class EUOpenData {
    private Double pollution;
    private Double traffic;
    private Double noise;
    private Double criminalityRate;
    private Double politicalInstabilityRate;
    private Long id;

    /**
     * Should build a new object? Get data from database? Both?
     */
    public EUOpenData() {
    }

    public void setPollution(Double pollution) {
        this.pollution = pollution;
    }

    public void setTraffic(Double traffic) {
        this.traffic = traffic;
    }

    public void setNoise(Double noise) {
        this.noise = noise;
    }

    public void setCriminalityRate(Double criminalityRate) {
        this.criminalityRate = criminalityRate;
    }

    public void setPoliticalInstabilityRate(Double politicalInstabilityRate) {
        this.politicalInstabilityRate = politicalInstabilityRate;
    }

    public Double getPollution() {
        return pollution;
    }

    public Double getTraffic() {
        return traffic;
    }

    public Double getNoise() {
        return noise;
    }

    public Double getCriminalityRate() {
        return criminalityRate;
    }

    public Double getPoliticalInstabilityRate() {
        return politicalInstabilityRate;
    }


    public void setId(Long id) {
        this.id = id;
    }

    @Id
    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "EUOpenData{" +
                "pollution=" + pollution +
                ", traffic=" + traffic +
                ", noise=" + noise +
                ", criminalityRate=" + criminalityRate +
                ", politicalInstabilityRate=" + politicalInstabilityRate +
                ", id=" + id +
                '}';
    }
}
