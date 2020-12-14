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

    @Override
    public void onClickSearch() {
        view.startSearchActivity();
    }

    @Override
    public void onClickAbout(){
        view.startAboutActivity();
    }

    @Override
    public void onClickRecyclerViewItem(String id) {
        view.startFormActivity(id);
    }

    public void onSwipeRecyclerViewItem(String id){
        /*
        view.removeRecyclerViewItem();
        view.showToast("Error");
        */
    }
}
