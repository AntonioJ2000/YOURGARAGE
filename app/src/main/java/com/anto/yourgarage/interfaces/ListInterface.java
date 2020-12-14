package com.anto.yourgarage.interfaces;

public interface ListInterface {

    public interface View{
        void startFormActivity();
        void startFormActivity(String id);
        void startSearchActivity();
        void startAboutActivity();


    }

    public interface Presenter{
        void onClickAddCar();
        void onClickSearch();
        void onClickAbout();
        void onClickRecyclerViewItem(String id);
    }

}