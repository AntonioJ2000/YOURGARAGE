package com.anto.yourgarage.interfaces;

public interface ListInterface {

    public interface View{
        void startFormActivity();
        void startSearchActivity();
        void startAboutActivity();
        //Hacer void startAboutActivity
    }

    public interface Presenter{
        void onClickAddCar();
        void onClickSearch();
        void onClickAbout();
    }

}