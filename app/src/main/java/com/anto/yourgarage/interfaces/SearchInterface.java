package com.anto.yourgarage.interfaces;

import java.util.ArrayList;

public interface SearchInterface {

    public interface View{
        void startHelpActivity();
        void SearchCar();
    }

    public interface Presenter{
        void onClickSearchCar();
        void onClickHelp();
        ArrayList<String> getAllFuelTypesForSearch();
    }
}
