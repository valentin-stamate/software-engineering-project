package com.bfourclass.euopendata.ExternalAPI.instance.aqicn_data;

public class AirPollution
{
    /**
     * <p>The air quality index's values range between 0 and 500, with the following meanings:</p>
     * <p>  - [0, 50] -> Good</p>
     * <p>  - [50, 100] -> Moderate</p>
     * <p>  - [100, 150] -> Unhealthy for Sensitive Groups</p>
     * <p>  - [150, 200] -> Unhealthy</p>
     * <p>  - [200, 300] -> Very Unhealthy</p>
     * <p>  - [300, 500] -> Hazardous</p>
     */
    private Integer airQualityIndex;

    /**
     * <p>The level of inhalable big particles in air (for example, dust particles) in the last 24 hours. The lower, the better.</p>
     * <p>The value range between 0 and 500, with the following meanings:</p>
     * <p>  - [0, 50] -> Good</p>
     * <p>  - [50, 100] -> Moderate</p>
     * <p>  - [100, 150] -> Unhealthy for Sensitive Groups</p>
     * <p>  - [150, 200] -> Unhealthy</p>
     * <p>  - [200, 300] -> Very Unhealthy</p>
     * <p>  - [300, 500] -> Hazardous</p>
     */
    private Integer pm10Value;

    /**
     * The value (exprimed in mb) range between 830 and 1090.
     */
    private Integer airPressure;

    /**
     * The procentage of air humidity.
     */
    private Float airHumidity;

    public AirPollution()
    {
        airQualityIndex = null;
    }

    public Integer getAirQualityIndex() {
        return airQualityIndex;
    }

    public void setAirQualityIndex(Integer airQualityIndex) {
        this.airQualityIndex = airQualityIndex;
    }

    public Integer getPm10Value() {
        return pm10Value;
    }

    public void setPm10Value(Integer pm10Value) {
        this.pm10Value = pm10Value;
    }

    public Integer getAirPressure() {
        return airPressure;
    }

    public void setAirPressure(Integer airPressure) {
        this.airPressure = airPressure;
    }

    public Float getAirHumidity() {
        return airHumidity;
    }

    public void setAirHumidity(Float airHumidity) {
        this.airHumidity = airHumidity;
    }
}
