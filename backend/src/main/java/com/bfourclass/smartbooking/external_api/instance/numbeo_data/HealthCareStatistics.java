package com.bfourclass.smartbooking.external_api.instance.numbeo_data;

public class HealthCareStatistics {
    private String location;
    private float skillAndCompetencyOfMedicalStaff;
    private float speedInCompletingExaminationAndReports;
    private float equipmentForModernDiagnosisAndTreatment;
    private float accuracyAndCompletenessInFillingOutReports;
    private float friendlinessAndCourtesyOfTheStaff;
    private float satisfactionWithResponsivenessInMedicalInstitutions;
    private float satisfactionWithCostToYou;
    private float convenienceOfLocationForYou;

    public HealthCareStatistics(String location) {
        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }



    public float getSkillAndCompetencyOfMedicalStaff() {
        return skillAndCompetencyOfMedicalStaff;
    }

    public void setSkillAndCompetencyOfMedicalStaff(float skillAndCompetencyOfMedicalStaff) {
        this.skillAndCompetencyOfMedicalStaff = skillAndCompetencyOfMedicalStaff;
    }

    public float getSpeedInCompletingExaminationAndReports() {
        return speedInCompletingExaminationAndReports;
    }

    public void setSpeedInCompletingExaminationAndReports(float speedInCompletingExaminationAndReports) {
        this.speedInCompletingExaminationAndReports = speedInCompletingExaminationAndReports;
    }

    public float getEquipmentForModernDiagnosisAndTreatment() {
        return equipmentForModernDiagnosisAndTreatment;
    }

    public void setEquipmentForModernDiagnosisAndTreatment(float equipmentForModernDiagnosisAndTreatment) {
        this.equipmentForModernDiagnosisAndTreatment = equipmentForModernDiagnosisAndTreatment;
    }

    public float getAccuracyAndCompletenessInFillingOutReports() {
        return accuracyAndCompletenessInFillingOutReports;
    }

    public void setAccuracyAndCompletenessInFillingOutReports(float accuracyAndCompletenessInFillingOutReports) {
        this.accuracyAndCompletenessInFillingOutReports = accuracyAndCompletenessInFillingOutReports;
    }

    public float getFriendlinessAndCourtesyOfTheStaff() {
        return friendlinessAndCourtesyOfTheStaff;
    }

    public void setFriendlinessAndCourtesyOfTheStaff(float friendlinessAndCourtesyOfTheStaff) {
        this.friendlinessAndCourtesyOfTheStaff = friendlinessAndCourtesyOfTheStaff;
    }

    public float getSatisfactionWithResponsivenessInMedicalInstitutions() {
        return satisfactionWithResponsivenessInMedicalInstitutions;
    }

    public void setSatisfactionWithResponsivenessInMedicalInstitutions(float satisfactionWithResponsivenessInMedicalInstitutions) {
        this.satisfactionWithResponsivenessInMedicalInstitutions = satisfactionWithResponsivenessInMedicalInstitutions;
    }

    public float getSatisfactionWithCostToYou() {
        return satisfactionWithCostToYou;
    }

    public void setSatisfactionWithCostToYou(float satisfactionWithCostToYou) {
        this.satisfactionWithCostToYou = satisfactionWithCostToYou;
    }

    public float getConvenienceOfLocationForYou() {
        return convenienceOfLocationForYou;
    }

    public void setConvenienceOfLocationForYou(float convenienceOfLocationForYou) {
        this.convenienceOfLocationForYou = convenienceOfLocationForYou;
    }
}

