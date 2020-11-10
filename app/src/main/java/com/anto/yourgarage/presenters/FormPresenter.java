package com.anto.yourgarage.presenters;

import com.anto.yourgarage.interfaces.FormInterface;


public class FormPresenter implements FormInterface.Presenter {

    private FormInterface.View view;

    public FormPresenter(FormInterface.View view){
        this.view = view;
    }

    @Override
    public void onClickSaveCar() {
        view.closeFormActivity();
    }
}
