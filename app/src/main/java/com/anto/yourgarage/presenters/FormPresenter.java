package com.anto.yourgarage.presenters;

import com.anto.yourgarage.R;
import com.anto.yourgarage.interfaces.FormInterface;
import com.anto.yourgarage.models.CarEntity;
import com.anto.yourgarage.models.CarModel;
import com.anto.yourgarage.views.CarAdapter;
import com.anto.yourgarage.views.MyApplication;

import java.util.ArrayList;


public class FormPresenter implements FormInterface.Presenter {

    private FormInterface.View view;
    private CarModel carModel;

    public FormPresenter(FormInterface.View view){
        this.view = view;
        carModel = new CarModel();
    }

    @Override
    public void addImage() {
        view.addImage();
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

            case "faultError":
                error_msg = MyApplication.getContext().getResources().getString(R.string.faultError);
        }
        return error_msg;
    }


    @Override
    public void onClickSaveCar(CarEntity car) {
        if(CarModel.insert(car)){
            view.closeFormActivity();
        }else{

            //Mostrar error en el formulario.
        }
    }

    @Override
    public void onClickHelp() {
        view.startHelpActivity();
    }

    @Override
    public void onClickUpdateCar(CarEntity car) {
        if(CarModel.update(car)){
            view.closeFormActivity();
        }
    }

    @Override
    public ArrayList<String> getAllFuelTypes() {
        ArrayList<String> fuelTypes = new ArrayList<>();
        fuelTypes = CarModel.getAllSpinnerItems();

        return fuelTypes;
    }

    @Override
    public void deleteCar(String id) {
        CarModel.delete(id);
    }

}
