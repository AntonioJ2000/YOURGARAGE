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


    @Override
    public String getError(String error_code){
        String error_msg = "";
        switch (error_code){
            case "ownerName":
                error_msg = MyApplication.getContext().getResources().getString(R.string.ownerNameError);
                break;

            case "brandName":
                error_msg = MyApplication.getContext().getResources().getString(R.string.brandNameError);
                break;

            case "modelName":
                error_msg = MyApplication.getContext().getResources().getString(R.string.modelNameError);
                break;

            case "enrollmentName":
                error_msg = MyApplication.getContext().getResources().getString(R.string.enrollmentNameError);
                break;

            case "dateError":
                error_msg = MyApplication.getContext().getResources().getString(R.string.dateError);
                break;
        }
        return error_msg;
    }
}
