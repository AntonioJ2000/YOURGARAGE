package com.anto.yourgarage.models;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class CarEntity extends RealmObject {

    @PrimaryKey
    private String id;

    private String Image;
    private String ownerName;
    private String modelName;
    private String brandName;
    private String enrollmentName;
    private Date receptionDate;
    private String fuelType;
    private String carFault;
    private boolean carStatus;

    public CarEntity(){ }

    public CarEntity(String id, String Image,
                     String ownerName,
                     String enrollmentName, String brandName,
                     String modelName, Date receptionDate,
                     String fuelType, String carFault,
                     boolean carStatus){
        this.id = id;
        this.Image = Image;
        this.ownerName = ownerName;
        this.enrollmentName = enrollmentName;
        this.brandName = brandName;
        this.modelName = modelName;
        this.receptionDate = receptionDate;
        this.fuelType = fuelType;
        this.carFault = carFault;
        this.carStatus = carStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage() {
        return Image;
    }

    public void setImage(String image) {
        this.Image = image;
    }

    public String getName() {
        return ownerName;
    }

    public boolean setName(String name) {
        if (name.matches("[A-Za-z ]*")) {
            this.ownerName = name;
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
            this.modelName = modelName;
            return true;
    }

    public String getEnrollmentName() {
        return enrollmentName;
    }

    public boolean setEnrollmentName(String enrollmentName) {
        if(enrollmentName.matches(".{1,10}$.*")){
            this.enrollmentName = enrollmentName;
            return true;
        }else{
            return false;
        }
    }

    public String getReceptionDate() {
        DateFormat dateFormat = new SimpleDateFormat("dd/mm/yyyy");
        String strDate = dateFormat.format(this.receptionDate);
        return strDate;
    }

    public boolean setReceptionDate(String receptionDate) {
        boolean result = false;
        SimpleDateFormat newDate = new SimpleDateFormat("dd/mm/yyyy");
        String RegEx = "^(?:(?:(?:0?[1-9]|1\\d|2[0-8])[/](?:0?[1-9]|1[0-2])|(?:29|30)[/](?:0?[13-9]|1[0-2])|31[/](?:0?[13578]|1[02]))[/](?:0{2,3}[1-9]|0{1,2}[1-9]\\d|0?[1-9]\\d{2}|[1-9]\\d{3})|29[/]0?2[/](?:\\d{1,2}(?:0[48]|[2468][048]|[13579][26])|(?:0?[48]|[13579][26]|[2468][048])00))$";

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

    public boolean setCarFault(String carFault) {
        this.carFault = carFault;
        return true;
    }

    public String getCarStatus() {
        String status1 = "Grave";
        String status2 = "No es grave";
        if(carStatus == true){
            return status1;
        }else if(carStatus == false){
            return status2;
        }
        return status2;
    }

    public void setCarStatus(boolean carStatus) {
        this.carStatus = carStatus;
    }

}
