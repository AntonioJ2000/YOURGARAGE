package com.anto.yourgarage.interfaces;

import com.anto.yourgarage.models.CarEntity;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;

public interface ListInterface {

    public interface View{
        void startFormActivity();
        void startFormActivity(String id);
        void startSearchActivity();
        void startAboutActivity();
        void startHelpActivity();
        ArrayList<CarEntity> getAllItemsSumarize();

    }

    public interface Presenter{
        void onClickAddCar();
        void onClickSearch();
        void onClickAbout();
        void onClickHelp();
        void onClickRecyclerViewItem(String id);
        ArrayList<CarEntity> getAllItemsSumarize();
        void deleteCarBySwipe(String id);
        ArrayList<CarEntity> getItemsFiltered(String ownerName, Date receptionDate, String fuelType);
        ArrayList<String> getFuelTypes();
    }

}