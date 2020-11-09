package com.anto.yourgarage.presenters;

import com.anto.yourgarage.interfaces.ListInterface;

public class ListPresenter implements ListInterface.Presenter {

    private ListInterface.View view;

    public ListPresenter(ListInterface.View view){
        this.view = view;
    }


    @Override
    public void onClickAddCar() {
        //Log.d("");
        view.startFormActivity();
    }

}
