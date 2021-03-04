package com.anto.yourgarage.presenters;
;
import com.anto.yourgarage.interfaces.SearchInterface;
import com.anto.yourgarage.models.CarModel;

import java.util.ArrayList;

public class SearchPresenter implements SearchInterface.Presenter {
    private SearchInterface.View view;

    public SearchPresenter(SearchInterface.View view){
        this.view = view;
    }


    @Override
    public void onClickSearchCar() {
        view.SearchCar();
    }

    @Override
    public void onClickHelp() {
        view.startHelpActivity();
    }

    @Override
    public ArrayList<String> getAllFuelTypesForSearch() {
        ArrayList<String> fuelTypes = new ArrayList<>();
        fuelTypes = CarModel.getAllSpinnerItemsForSearch();

        return fuelTypes;
    }
}
