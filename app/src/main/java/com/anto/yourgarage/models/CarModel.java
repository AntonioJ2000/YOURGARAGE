package com.anto.yourgarage.models;

import com.anto.yourgarage.R;
import com.anto.yourgarage.views.MyApplication;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.RealmResults;

public class CarModel {

    public static ArrayList<CarEntity> getAllSummarize(){
        Realm realm = Realm.getDefaultInstance();

        RealmResults<CarEntity> result = realm.where(CarEntity.class).findAll();
        ArrayList<CarEntity> carList = new ArrayList<>();
        carList.addAll(realm.copyFromRealm(result));

        realm.close();

        ArrayList<CarEntity> carListSummarize = new ArrayList<>();

        for(CarEntity car : carListSummarize){
            //CarEntity

        }
        return carList;
    }

    public static boolean insert(CarEntity car){
        car.setId(UUID.randomUUID().toString());

        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        realm.copyToRealm(car);
        realm.commitTransaction();

        realm.close();
        return true;
    }

    public static boolean update(CarEntity carToUpdate){
        Realm realm = Realm.getDefaultInstance();

        realm.executeTransaction(r -> {
            realm.copyToRealmOrUpdate(carToUpdate);
        });
        return true;
    }


    public static CarEntity getCarByID(String id){
        Realm realm = Realm.getDefaultInstance();

        CarEntity carToUpdate = new CarEntity();
        RealmQuery<CarEntity> query = realm.where(CarEntity.class);

        query.equalTo("id", id);

        CarEntity result1 = query.findFirst();

        return result1;
    }

    public static ArrayList<String> getAllSpinnerItems(){
        ArrayList<String> spinnerStrings = new ArrayList<>();
             spinnerStrings.add(MyApplication.getContext().getResources().getString(R.string.spinnerSelectItem));
             spinnerStrings.add(MyApplication.getContext().getResources().getString(R.string.spinnerSelectNew));

        Realm realm = Realm.getDefaultInstance();

        RealmResults<CarEntity> result = realm.where(CarEntity.class).distinct("fuelType").findAll();

        ArrayList<CarEntity> carList = new ArrayList<>();

        carList.addAll(realm.copyFromRealm(result));

        for(CarEntity car: carList){
            spinnerStrings.add(car.getFuelType());
        }

        return spinnerStrings;
    }

    public static ArrayList<String> getAllSpinnerItemsForSearch(){
        ArrayList<String> spinnerStrings = new ArrayList<>();
        spinnerStrings.add(MyApplication.getContext().getResources().getString(R.string.spinnerSelectItem));

        Realm realm = Realm.getDefaultInstance();

        RealmResults<CarEntity> result = realm.where(CarEntity.class).distinct("fuelType").findAll();

        ArrayList<CarEntity> carList = new ArrayList<>();

        carList.addAll(realm.copyFromRealm(result));

        for(CarEntity car: carList){
            spinnerStrings.add(car.getFuelType());
        }

        return spinnerStrings;
    }

    public static void delete(String carID){
        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        RealmResults<CarEntity> result = realm.where(CarEntity.class).equalTo("id", carID).findAll();
        result.deleteFirstFromRealm();
        realm.commitTransaction();
    }

    public static ArrayList<CarEntity> filterElement(String nombre, String fechaRecepcion, String fuelType){
        //Faltan comprobaciones si los campos vienen nulos.

        Realm realm = Realm.getDefaultInstance();
        ArrayList<CarEntity> carsFiltered = new ArrayList<>();

        RealmQuery<CarEntity> query = realm.where(CarEntity.class);
        query.equalTo("ownerName", nombre);
        query.equalTo("receptionDate", fechaRecepcion);
        query.equalTo("fuelType", fuelType);

        RealmResults<CarEntity> myCars = query.findAll();

        for(CarEntity car: myCars){
            carsFiltered.add(car);
        }

        return carsFiltered;
    }

}
