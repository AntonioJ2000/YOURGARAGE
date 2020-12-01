package com.anto.yourgarage.models;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CarEntity {
    private String OwnerName;
    private String brandName;
    private String modelName;
    private String enrollmentName;
    private Date receptionDate;
    private String fuelType;
    private String carFault;
    private boolean carStatusSerious;


    public CarEntity() {
    }

    public String getName() {
        return OwnerName;
    }

    public boolean setName(String name) {
        if (name.matches("[A-Za-z ]*")) {
            this.OwnerName = OwnerName;
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

    public boolean setModelName(String modelName) {
        if(modelName.matches("[A-Za-z ]*")){
            this.modelName = modelName;
            return true;
        }else{
            return false;
        }
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
        SimpleDateFormat newDate = new SimpleDateFormat("dd/MM/yyyy");
        String RegEx = "^(?:(?:(?:0?[1-9]|1\\d|2[0-8])[/](?:0?[1-9]|1[0-2])|(?:29|30)[/](?:0?[13-9]|1[0-2])|31[/](?:0?[13578]|1[02]))[/]" +
                "(?:0{2,3}[1-9]|0{1,2}[1-9]\\d|0?[1-9]\\d{2}|[1-9]\\d{3})|29[/]0?2[/]" +
                "(?:\\d{1,2}(?:0[48]|[2468][048]|[13579][26])|(?:0?[48]|[13579][26]|[2468][048])00))$";

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
