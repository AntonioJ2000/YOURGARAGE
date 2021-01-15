package com.anto.yourgarage.models;

import java.util.UUID;

import io.realm.Realm;

public class CarModel {

    public static boolean insert(CarEntity car){
        car.setId(UUID.randomUUID().toString());

        Realm realm = Realm.getDefaultInstance();

        realm.beginTransaction();
        realm.copyToRealm(car);
        realm.commitTransaction();

        realm.close();
        return true;
    }

}
