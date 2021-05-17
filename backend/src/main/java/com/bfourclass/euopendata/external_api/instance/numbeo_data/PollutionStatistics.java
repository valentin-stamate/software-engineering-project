package com.bfourclass.euopendata.external_api.instance.numbeo_data;

public class PollutionStatistics {
    /*
     * For each attribute, you can interpret the value as it follows:
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

    public PollutionStatistics() { }

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