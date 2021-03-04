package com.anto.yourgarage.presenters;

import com.anto.yourgarage.interfaces.ListInterface;
import com.anto.yourgarage.models.CarEntity;
import com.anto.yourgarage.models.CarModel;

import java.util.ArrayList;
import java.util.Date;

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
    public void onClickHelp(){
        view.startHelpActivity();
    }

    @Override
    public void onClickRecyclerViewItem(String id) {
        view.startFormActivity(id);
    }

    @Override
    public ArrayList<CarEntity> getAllItemsSumarize() {
        ArrayList<CarEntity> myCars;
        myCars = CarModel.getAllSummarize();

        return myCars;
    }

    @Override
    public void deleteCarBySwipe(String id) {
        CarModel.delete(id);
    }

    @Override
    public ArrayList<CarEntity> getItemsFiltered(String ownerName, Date receptionDate, String fuelType) {
        return CarModel.filterElement(ownerName,receptionDate,fuelType);
    }

    @Override
    public ArrayList<String> getFuelTypes() {
        return CarModel.getAllSpinnerItemsForSearch();
    }

    public void onSwipeRecyclerViewItem(String id){
        /*
        view.removeRecyclerViewItem();
        view.showToast("Error");
        */
    }
}
