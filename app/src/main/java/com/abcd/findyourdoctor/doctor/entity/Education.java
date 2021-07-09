package com.abcd.findyourdoctor.doctor.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Education {

    @SerializedName("education_name")
    @Expose
    private String educationName;
    @SerializedName("passing_year")
    @Expose
    private String passingYear;
    @SerializedName("institute")
    @Expose
    private String institute;

    public String getEducationName() {
        return educationName;
    }

    public void setEducationName(String educationName) {
        this.educationName = educationName;
    }

    public String getPassingYear() {
        return passingYear;
    }

    public void setPassingYear(String passingYear) {
        this.passingYear = passingYear;
    }

    public String getInstitute() {
        return institute;
    }

    public void setInstitute(String institute) {
        this.institute = institute;
    }

}

