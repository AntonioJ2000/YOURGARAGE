package com.anto.yourgarage.interfaces;

import java.util.ArrayList;

public interface SearchInterface {

    public interface View{
        void closeSearchActivity();
        void SearchCar();
    }

    public interface Presenter{
        void onClickSearchCar();
        ArrayList<String> getAllFuelTypesForSearch();
    }
}
