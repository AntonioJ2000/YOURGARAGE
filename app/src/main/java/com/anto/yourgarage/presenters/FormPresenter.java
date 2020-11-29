package com.anto.yourgarage.presenters;

import com.anto.yourgarage.R;
import com.anto.yourgarage.interfaces.FormInterface;
import com.anto.yourgarage.views.MyApplication;


public class FormPresenter implements FormInterface.Presenter {

    private FormInterface.View view;

    public FormPresenter(FormInterface.View view){
        this.view = view;
    }

    @Override
    public void onClickSaveCar() {
        view.closeFormActivity();
    }
    String error_msg;

    public String getError(String error_code){
        switch (error_code){
            case "ContactName":
                error_msg = MyApplication.getContext().getResources().getString(R.string.action_settings);
                break;

        }
        return error_msg;
    }
}
