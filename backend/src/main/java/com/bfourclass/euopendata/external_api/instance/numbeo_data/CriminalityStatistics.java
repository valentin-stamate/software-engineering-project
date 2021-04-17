package com.bfourclass.euopendata.external_api.instance.numbeo_data;

public class CriminalityStatistics {
    /*
     * For each attribute, you can interpret the value as it follows:
     *      - VERY LOW level, if value is lower than 20
     *      - LOW level, if value is between 20 and 40
     *      - MODERATE level, if value is between 40 and 60
     *      - HIGH level, if value is between 60 and 80
     *      - VERY HIGH level, if value is higher than 80
     *
     * crimeIndex + safetyIndex = 100
     */

    /**
     * Crime index is an estimation of overall level of crime in a given city. The lower, the better.
     */
    private Double crimeIndex;

    /**
     * The opposite of Crime Index. The higher, the better.
     */
    private Double safetyIndex;

    /**
     * Concerns about crime increasing in the past 3 years. The lower, the better.
     */
    private Double crimeIncreasingPast3YearsIndex;

    /**
     * Concerns about house breakings. The lower, the better.
     */
    private Double brokenHomesIndex;

    /**
     * Worries about being mugged or robbed. The lower, the better.
     */
    private Double robbingIndex;

    /**
     * Concerns about stolen cars. The lower, the better.
     */
    private Double stolenCarsIndex;

    /**
     * Worries about things stolen from cars. The lower, the better.
     */
    private Double stolenObjectsFromCarsIndex;

    /**
     * Concerns about being attacked. The lower, the better.
     */
    private Double attackedIndex;

    /**
     * Concerns about being insulted. The lower, the better.
     */
    private Double insultedIndex;

    /**
     * Concerns about being attacked because of your skin color, ethnic origin, gender or religion. The lower, the better.
     */
    private Double racismIndex;

    /**
     * The index about people using or dealing drugs. The lower, the better.
     */
    private Double drugsIndex;

    /**
     * The index about violent crimes, such as assault and armed robbery. The lower, the better.
     */
    private Double violentCrimesIndex;

    /**
     * The index about political corruption and bribery. The lower, the better.
     */
    private Double corruptionIndex;

    /**
     * Concerns about being safe while walking alone during night. The higher, the better.
     */
    private Double nightWalkingSafetyIndex;

    public CriminalityStatistics() { }

    public void setCrimeIndex(Double crimeIndex) {
        this.crimeIndex = crimeIndex;
    }

    public Double getCrimeIndex() {
        return crimeIndex;
    }

    public void setSafetyIndex(Double safetyIndex) {
        this.safetyIndex = safetyIndex;
    }

    public Double getSafetyIndex() {
        return safetyIndex;
    }

    public void setCrimeIncreasingPast3YearsIndex(Double crimeIncreasingPast3YearsIndex) {
        this.crimeIncreasingPast3YearsIndex = crimeIncreasingPast3YearsIndex;
    }

    public Double getCrimeIncreasingPast3YearsIndex() {
        return crimeIncreasingPast3YearsIndex;
    }

    public void setBrokenHomesIndex(Double brokenHomesIndex) {
        this.brokenHomesIndex = brokenHomesIndex;
    }

    public Double getBrokenHomesIndex() {
        return brokenHomesIndex;
    }

    public Double getRobbingIndex() {
        return robbingIndex;
    }

    public void setRobbingIndex(Double robbingIndex) {
        this.robbingIndex = robbingIndex;
    }

    public Double getStolenCarsIndex() {
        return stolenCarsIndex;
    }

    public void setStolenCarsIndex(Double stolenCarsIndex) {
        this.stolenCarsIndex = stolenCarsIndex;
    }

    public Double getStolenObjectsFromCarsIndex() {
        return stolenObjectsFromCarsIndex;
    }

    public void setStolenObjectsFromCarsIndex(Double stolenObjectsFromCarsIndex) {
        this.stolenObjectsFromCarsIndex = stolenObjectsFromCarsIndex;
    }

    public Double getAttackedIndex() {
        return attackedIndex;
    }

    public void setAttackedIndex(Double attackedIndex) {
        this.attackedIndex = attackedIndex;
    }

    public Double getInsultedIndex() {
        return insultedIndex;
    }

    public void setInsultedIndex(Double insultedIndex) {
        this.insultedIndex = insultedIndex;
    }

    public Double getRacismIndex() {
        return racismIndex;
    }

    public void setRacismIndex(Double racismIndex) {
        this.racismIndex = racismIndex;
    }

    public Double getDrugsIndex() {
        return drugsIndex;
    }

    public void setDrugsIndex(Double drugsIndex) {
        this.drugsIndex = drugsIndex;
    }

    public Double getViolentCrimesIndex() {
        return violentCrimesIndex;
    }

    public void setViolentCrimesIndex(Double violentCrimesIndex) {
        this.violentCrimesIndex = violentCrimesIndex;
    }

    public Double getCorruptionIndex() {
        return corruptionIndex;
    }

    public void setCorruptionIndex(Double corruptionIndex) {
        this.corruptionIndex = corruptionIndex;
    }

    public Double getNightWalkingSafetyIndex() {
        return nightWalkingSafetyIndex;
    }

    public void setNightWalkingSafetyIndex(Double nightWalkingSafetyIndex) {
        this.nightWalkingSafetyIndex = nightWalkingSafetyIndex;
    }
}
