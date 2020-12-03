package com.anto.yourgarage.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CarEntity {
    private String image;
    private String ownerName;
    private String brandName;
    private String modelName;
    private String enrollmentName;
    private Date receptionDate;
    private String fuelType;
    private String carFault;
    private boolean carStatusSerious;


    public CarEntity() {
    }

    public CarEntity(String ownerName, String modelName){
        this.ownerName = ownerName;
        this.modelName = modelName;
    };

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return ownerName;
    }

    public boolean setName(String name) {
        if (name.matches("[A-Za-z ]*")) {
            this.ownerName = ownerName;
            return true;
        } else {
            return false;
        }
    }

    public String getBrandName() {
        return brandName;
    }

    public boolean setBrandName(String brandName) {
        if(brandName.matches("[A-Za-z ]*")){
            this.brandName = brandName;
            return true;
        }else{
            return false;
        }
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
            this.modelName = modelName;
    }

    public String getEnrollmentName() {
        return enrollmentName;
    }

    public boolean setEnrollmentName(String enrollmentName) {
        if(enrollmentName.matches(".{1,8}$.*")){
            this.enrollmentName = enrollmentName;
            return true;
        }else{
            return false;
        }
    }

    public Date getReceptionDate() {
        return receptionDate;
    }

    public boolean setReceptionDate(String receptionDate) {
        boolean result = false;
        SimpleDateFormat newDate = new SimpleDateFormat("mm/dd/yyyy");
        String RegEx = "^(0[1-9]|1[012])[- /.] (0[1-9]|[12][0-9]|3[01])[- /.] (19|20)\\d\\d$";

        if(receptionDate.matches(RegEx)){
            try {
                this.receptionDate = newDate.parse(receptionDate);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            result = true;
        }
        return result;
    }

    public String getFuelType() {
        return fuelType;
    }

    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    public String getCarFault() {
        return carFault;
    }

    public void setCarFault(String carFault) {
        this.carFault = carFault;
    }

    public boolean isCarStatusSerious() {
        return carStatusSerious;
    }

    public void setCarStatusSerious(boolean carStatusSerious) {
        this.carStatusSerious = carStatusSerious;
    }

}
