package com.anto.yourgarage.presenters;
;
import com.anto.yourgarage.interfaces.SearchInterface;

public class SearchPresenter implements SearchInterface.Presenter {
    private SearchInterface.View view;

    public SearchPresenter(SearchInterface.View view){
        this.view = view;
    }


    @Override
    public void onClickSearchCar() {
        view.closeSearchActivity();
    }
}
