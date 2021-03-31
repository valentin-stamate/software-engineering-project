package com.bfourclass.euopendata.ExternalAPI.instance.covid_restrictions;

public class CovidRestrictions {
    double infectionRate;
    boolean needVaccine;
    boolean needPCRTest;
    boolean isQuarantined;
    String maxCapacityOfLocation;

    public double getInfectionRate() {
        return infectionRate;
    }

    public String getMaxCapacityOfLocation() {
        return maxCapacityOfLocation;
    }

    public boolean isQuarantined() {
        return isQuarantined;
    }

    public boolean isNeedPCRTest() {
        return needPCRTest;
    }

    public boolean isNeedVaccine() {
        return needVaccine;
    }
}
