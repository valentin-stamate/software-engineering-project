package com.bfourclass.smartbooking.external_api.instance.weather.statistical_weather;

import java.text.DecimalFormat;
import java.util.List;

public class Data {
    public double rh;
    public int max_wind_spd_ts;
    public double t_ghi;
    public double max_wind_spd;
    public double solar_rad;
    public double wind_gust_spd;
    public int max_temp_ts;
    public int min_temp_ts;
    public int clouds;
    public double max_dni;
    public double precip_gpm;
    public double wind_spd;
    public int slp;
    public int ts;
    public int max_ghi;
    public double temp;
    public double pres;
    public double dni;
    public double dewpt;
    public int snow;
    public int dhi;
    public double precip;
    public int wind_dir;
    public double max_dhi;
    public double ghi;
    public double max_temp;
    public double t_dni;
    public double max_uv;
    public double t_dhi;
    public String datetime;
    public int t_solar_rad;
    public double min_temp;
    public int max_wind_dir;
    public Object snow_depth;

    public static Data mean(List<Data> dataList) {
        int n = dataList.size();

        Data meanData = new Data();

        for (Data data : dataList) {
            meanData.rh += data.rh;
            meanData.max_wind_spd_ts += data.max_wind_spd_ts;
            meanData.t_ghi += data.t_dhi;
            meanData.max_wind_spd += data.max_wind_spd;
            meanData.solar_rad += data.solar_rad;
            meanData.wind_gust_spd += data.wind_gust_spd;
            meanData.max_temp_ts += data.max_temp_ts;
            meanData.min_temp_ts += data.min_temp_ts;
            meanData.clouds += data.clouds;
            meanData.max_dni += data.max_dni;
            meanData.precip_gpm += data.precip_gpm;
            meanData.wind_spd += data.wind_spd;
            meanData.slp += data.slp;
            meanData.ts += data.ts;
            meanData.max_ghi += data.max_ghi;
            meanData.temp += data.temp;
            meanData.pres += data.pres;
            meanData.dni += data.dni;
            meanData.dewpt += data.dewpt;
            meanData.snow += data.snow;
            meanData.dhi += data.dhi;
            meanData.precip += data.precip;
            meanData.wind_dir += data.wind_dir;
            meanData.max_dhi += data.max_dhi;
            meanData.ghi += data.ghi;
            meanData.max_temp += data.max_temp;
            meanData.t_dni += data.t_dni;
            meanData.max_uv += data.max_uv;
            meanData.solar_rad += data.solar_rad;
            meanData.t_solar_rad += data.t_solar_rad;
            meanData.min_temp += data.min_temp;
            meanData.max_wind_dir += data.max_wind_dir;
        }

        meanData.rh /= n;
        meanData.max_wind_spd_ts /= n;
        meanData.t_ghi /= n;
        meanData.max_wind_spd /= n;
        meanData.solar_rad /= n;
        meanData.wind_gust_spd /= n;
        meanData.max_temp_ts /= n;
        meanData.min_temp_ts /= n;
        meanData.clouds /= n;
        meanData.max_dni /= n;
        meanData.precip_gpm /= n;
        meanData.wind_spd /= n;
        meanData.slp /= n;
        meanData.ts /= n;
        meanData.max_ghi /= n;
        meanData.temp /= n;
        meanData.pres /= n;
        meanData.dni /= n;
        meanData.dewpt /= n;
        meanData.snow /= n;
        meanData.dhi /= n;
        meanData.precip /= n;
        meanData.wind_dir /= n;
        meanData.max_dhi /= n;
        meanData.ghi /= n;
        meanData.max_temp /= n;
        meanData.t_dni /= n;
        meanData.max_uv /= n;
        meanData.solar_rad /= n;
        meanData.t_solar_rad /= n;
        meanData.min_temp /= n;
        meanData.max_wind_dir /= n;

        return meanData;
    }

}
