package com.anto.yourgarage.interfaces;

import com.anto.yourgarage.models.CarEntity;

import java.util.ArrayList;

public interface ListInterface {

    public interface View{
        void startFormActivity();
        void startFormActivity(String id);
        void startSearchActivity();
        void startAboutActivity();
        ArrayList<CarEntity> getAllItemsSumarize();

    }

    public interface Presenter{
        void onClickAddCar();
        void onClickSearch();
        void onClickAbout();
        void onClickRecyclerViewItem(String id);
        ArrayList<CarEntity> getAllItemsSumarize();
        void deleteCarBySwipe(String id);
    }

}