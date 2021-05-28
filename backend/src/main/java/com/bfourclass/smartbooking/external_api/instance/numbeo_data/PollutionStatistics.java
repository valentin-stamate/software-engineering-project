package com.bfourclass.smartbooking.external_api.instance.numbeo_data;

public class PollutionStatistics {
    private String location;

    /*
     * Every below index's value range between 0 and 500, with the following meanings:
     *   - [0, 50] -> Good
     *   - [50, 100] -> Moderate
     *   - [100, 150] -> Unhealthy for Sensitive Groups
     *   - [150, 200] -> Unhealthy
     *   - [200, 300] -> Very Unhealthy
     *   - [300, 500] -> Hazardous
     */

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

    /*
     * For each attribute displayed below, you can interpret the value as it follows:
     *      - VERY LOW level, if value is lower than 20
     *      - LOW level, if value is between 20 and 40
     *      - MODERATE level, if value is between 40 and 60
     *      - HIGH level, if value is between 60 and 80
     *      - VERY HIGH level, if value is higher than 80
     */

    /**
     * Pollution Index is an estimation of the overall pollution in the city.
     * The biggest weight is given to air pollution, than to water pollution/accessibility, two main pollution factors.
     * Small weight is given to other pollution types.
     */
    private Double pollutionIndex;

    /**
     * Is an estimation of the pollution and inaccesibility level of drinking water in the city.
     */
    private Double drinkingWaterPollutionAndInaccesibilityIndex;

    /**
     * Is an estimation of the dissatisfaction level about garbage disposal in the city.
     */
    private Double dissatisfactionGarbageDisposalIndex;

    /**
     * Is an estimation of the dirty and untidy levels in the city.
     */
    private Double dirtyAndUntidyIndex;

    /**
     * Is an estimation of the noise and light pollution levels in the city.
     */
    private Double noiseAndLightPollutionIndex;

    /**
     * Is an estimation of the level of water pollution in the city.
     */
    private Double waterPollutionIndex;

    /**
     * Is an estimation of the dissatisfaction level about green areas and parks in the city.
     */
    private Double dissatisfactionGreenAndParksIndex;

    public PollutionStatistics(String location) {
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

        pollutionIndex = null;
        drinkingWaterPollutionAndInaccesibilityIndex = null;
        dissatisfactionGarbageDisposalIndex = null;
        dirtyAndUntidyIndex = null;
        noiseAndLightPollutionIndex = null;
        waterPollutionIndex = null;
    }

    public String getLocation() {
        return location;
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

    public Double getPollutionIndex() {
        return pollutionIndex;
    }

    public void setPollutionIndex(Double pollutionIndex) {
        this.pollutionIndex = pollutionIndex;
    }

    public Double getDrinkingWaterPollutionAndInaccesibilityIndex() {
        return drinkingWaterPollutionAndInaccesibilityIndex;
    }

    public void setDrinkingWaterPollutionAndInaccesibilityIndex(Double drinkingWaterPollutionAndInaccesibilityIndex) {
        this.drinkingWaterPollutionAndInaccesibilityIndex = drinkingWaterPollutionAndInaccesibilityIndex;
    }

    public Double getDissatisfactionGarbageDisposalIndex() {
        return dissatisfactionGarbageDisposalIndex;
    }

    public void setDissatisfactionGarbageDisposalIndex(Double dissatisfactionGarbageDisposalIndex) {
        this.dissatisfactionGarbageDisposalIndex = dissatisfactionGarbageDisposalIndex;
    }

    public Double getDirtyAndUntidyIndex() {
        return dirtyAndUntidyIndex;
    }

    public void setDirtyAndUntidyIndex(Double dirtyAndUntidyIndex) {
        this.dirtyAndUntidyIndex = dirtyAndUntidyIndex;
    }

    public Double getNoiseAndLightPollutionIndex() {
        return noiseAndLightPollutionIndex;
    }

    public void setNoiseAndLightPollutionIndex(Double noiseAndLightPollutionIndex) {
        this.noiseAndLightPollutionIndex = noiseAndLightPollutionIndex;
    }

    public Double getWaterPollutionIndex() {
        return waterPollutionIndex;
    }

    public void setWaterPollutionIndex(Double waterPollutionIndex) {
        this.waterPollutionIndex = waterPollutionIndex;
    }

    public Double getDissatisfactionGreenAndParksIndex() {
        return dissatisfactionGreenAndParksIndex;
    }

    public void setDissatisfactionGreenAndParksIndex(Double dissatisfactionGreenAndParksIndex) {
        this.dissatisfactionGreenAndParksIndex = dissatisfactionGreenAndParksIndex;
    }
}