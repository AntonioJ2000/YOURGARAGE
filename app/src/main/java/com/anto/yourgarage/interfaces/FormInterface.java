package com.anto.yourgarage.interfaces;

import com.anto.yourgarage.models.CarEntity;

import java.util.ArrayList;

public interface FormInterface {

    public interface View{
        void closeFormActivity();
        void addImage();
    }

    public interface Presenter{
        void onClickSaveCar(CarEntity car);
        void addImage();
        String getError(String error_code);
        void onClickUpdateCar(CarEntity car);
        ArrayList<String> getAllFuelTypes();
        void deleteCar(String id);

    }

}
