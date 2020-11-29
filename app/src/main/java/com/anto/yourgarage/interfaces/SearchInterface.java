package com.anto.yourgarage.interfaces;

public interface SearchInterface {

    public interface View{
        void closeSearchActivity();
    }

    public interface Presenter{
        void onClickSearchCar();

    }
}
