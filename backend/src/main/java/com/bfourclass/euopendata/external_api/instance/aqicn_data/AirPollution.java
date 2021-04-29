package com.bfourclass.euopendata.external_api.instance.aqicn_data;

public class AirPollution
{
    /*
     * Every index's value range between 0 and 500, with the following meanings:
     *   - [0, 50] -> Good
     *   - [50, 100] -> Moderate
     *   - [100, 150] -> Unhealthy for Sensitive Groups
     *   - [150, 200] -> Unhealthy
     *   - [200, 300] -> Very Unhealthy
     *   - [300, 500] -> Hazardous
     */

    private String location;
    /**
     * <p>The air quality index's values range between 0 and 500.</p>
     */
    private Integer airQualityIndex;

    /**
     * The level of inhalable big particles in air (for example, dust particles) in the last 24 hours. The lower, the better.
     */
    private Integer pm10ValueIndex;

    /**
     * The level of inhalable small particles in air in the last 24 hours. The lower, the better.
     */
    private Integer pm25ValueIndex;

    /**
     * The level of NO2 (Nitrogen dioxide) particles present in the air.
     */
    private Float NO2ValueIndex;

    /**
     * The level of SO2 (Sulfur dioxide) particles present in the air.
     */
    private Float SO2ValueIndex;

    /**
     * The level of CO (Carbon monoxide) particles present in the air.
     */
    private Float COValueIndex;

    /**
     * The level of O3 (Ozone) particles present in the air (at the earth level).
     */
    private Float O3ValueIndex;

    /**
     * The value (exprimed in mb) range between 830 and 1090.
     */
    private Float airPressure;

    /**
     * The procentage of air humidity.
     */
    private Float airHumidity;

    /**
     * Value in meters per second.
     */
    private Float windSpeed;

    public AirPollution(String location)
    {
        this.location = location;
        airQualityIndex = null;
        pm10ValueIndex = null;
        pm25ValueIndex = null;
        O3ValueIndex = null;
        NO2ValueIndex = null;
        SO2ValueIndex = null;
        COValueIndex = null;
        airPressure = null;
        airHumidity = null;
        windSpeed = null;
    }

    public Integer getAirQualityIndex() {
        return airQualityIndex;
    }

    public void setAirQualityIndex(Integer airQualityIndex) {
        this.airQualityIndex = airQualityIndex;
    }

    public Integer getPm10ValueIndex() {
        return pm10ValueIndex;
    }

    public void setPm10ValueIndex(Integer pm10Value) {
        this.pm10ValueIndex = pm10Value;
    }

    public Integer getPm25ValueIndex() {
        return pm25ValueIndex;
    }

    public void setPm25ValueIndex(Integer pm25ValueIndex) {
        this.pm25ValueIndex = pm25ValueIndex;
    }

    public Float getO3ValueIndex() {
        return O3ValueIndex;
    }

    public void setO3ValueIndex(Float o3ValueIndex) {
        O3ValueIndex = o3ValueIndex;
    }

    public Float getNO2ValueIndex() {
        return NO2ValueIndex;
    }

    public void setNO2ValueIndex(Float NO2ValueIndex) {
        this.NO2ValueIndex = NO2ValueIndex;
    }

    public Float getSO2ValueIndex() {
        return SO2ValueIndex;
    }

    public void setSO2ValueIndex(Float SO2ValueIndex) {
        this.SO2ValueIndex = SO2ValueIndex;
    }

    public Float getCOValueIndex() {
        return COValueIndex;
    }

    public void setCOValueIndex(Float COValueIndex) {
        this.COValueIndex = COValueIndex;
    }

    public Float getAirPressure() {
        return airPressure;
    }

    public void setAirPressure(Float airPressure) {
        this.airPressure = airPressure;
    }

    public Float getAirHumidity() {
        return airHumidity;
    }

    public void setAirHumidity(Float airHumidity) {
        this.airHumidity = airHumidity;
    }

    public Float getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Float windSpeed) {
        this.windSpeed = windSpeed;
    }
}
