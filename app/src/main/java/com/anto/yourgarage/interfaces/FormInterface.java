package com.anto.yourgarage.interfaces;

public interface FormInterface {

    public interface View{
        void closeFormActivity();


    }

    public interface Presenter{
        void onClickSaveCar();
        String getError(String error_code);

    }

}