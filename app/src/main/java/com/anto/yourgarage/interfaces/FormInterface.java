package com.anto.yourgarage.interfaces;

import com.anto.yourgarage.models.CarEntity;

public interface FormInterface {

    public interface View{
        void closeFormActivity();
        void addImage();
    }

    public interface Presenter{
        void onClickSaveCar(CarEntity car);
        void addImage();
        String getError(String error_code);

    }

}
