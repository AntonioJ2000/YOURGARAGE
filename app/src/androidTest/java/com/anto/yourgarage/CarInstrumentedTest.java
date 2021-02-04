package com.anto.yourgarage;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.anto.yourgarage.models.CarEntity;
import com.anto.yourgarage.models.CarModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.util.ArrayList;

@RunWith(AndroidJUnit4.class)
public class CarInstrumentedTest {

    private CarEntity car;

    @Before
    public void setUp() {
        this.car = new CarEntity();
    }

    @Test
    public void createDatabase(){
        ArrayList<CarEntity> allcars = CarModel.getAllSummarize();
        int sizeBefore = allcars.size();

        CarEntity exampleCar = new CarEntity();
        exampleCar.setId("2");
        exampleCar.setName("Paco");
        exampleCar.setModelName("Golf");
        exampleCar.setBrandName("Volkswagen");
        exampleCar.setEnrollmentName("2857 CFG");
        exampleCar.setCarFault("Motor estalla XD");
        exampleCar.setCarStatus(true);
        exampleCar.setFuelType("Gasolina");

        CarModel.insert(exampleCar);
        allcars = CarModel.getAllSummarize();
        int sizeAfter = allcars.size();

        assertEquals(sizeAfter, sizeBefore + 1);

    }
}
