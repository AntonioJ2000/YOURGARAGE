package com.anto.yourgarage;

import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.anto.yourgarage.models.CarEntity;
import com.anto.yourgarage.models.CarModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

@RunWith(AndroidJUnit4.class)
public class CarInstrumentedTest {

    private CarEntity car;

    @Before
    public void setUp() {
        this.car = new CarEntity();
    }

    //Prueba getAllSummarize introduciendo un coche, por lo tanto también prueba el insert
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
        exampleCar.setCarFault("Tubo de escape roto");
        exampleCar.setCarStatus(true);
        exampleCar.setFuelType("Gasolina");

        CarModel.insert(exampleCar);
        allcars = CarModel.getAllSummarize();
        int sizeAfter = allcars.size();

        assertEquals(sizeAfter, sizeBefore + 1);
    }

    //Poner la id no sirve de nada ya que luego en el insert se genera una aleatoria
    @Test
    public void update(){
        CarEntity exampleCar = new CarEntity();
        exampleCar.setId("2"); //<-
        exampleCar.setName("Paco");
        exampleCar.setModelName("Golf");
        exampleCar.setBrandName("Volkswagen");
        exampleCar.setEnrollmentName("2857 CFG");
        exampleCar.setCarFault("Tubo de escape roto");
        exampleCar.setCarStatus(true);
        exampleCar.setFuelType("Gasolina");

        CarModel.insert(exampleCar);

        exampleCar.setName("Antonio");

        assertEquals(true, CarModel.update(exampleCar));
    }

    @Test
    public void delete(){
        CarEntity exampleCar = new CarEntity();
        exampleCar.setName("Paco");
        exampleCar.setModelName("Golf");
        exampleCar.setBrandName("Volkswagen");
        exampleCar.setEnrollmentName("2857 CFG");
        exampleCar.setCarFault("Tubo de escape roto");
        exampleCar.setCarStatus(true);
        exampleCar.setFuelType("Gasolina");

        CarModel.insert(exampleCar);

        ArrayList<CarEntity> allcars = CarModel.getAllSummarize();
        int sizeBefore = allcars.size();

        CarModel.delete(exampleCar.getId());

        allcars = CarModel.getAllSummarize();
        int sizeAfter = allcars.size();

        assertEquals(sizeAfter, sizeBefore-1);
    }

    @Test
    public void getCarByID(){
        CarEntity exampleCar = new CarEntity();
        exampleCar.setName("Paco");
        exampleCar.setModelName("Golf");
        exampleCar.setBrandName("Volkswagen");
        exampleCar.setEnrollmentName("2857 CFG");
        exampleCar.setCarFault("Tubo de escape roto");
        exampleCar.setCarStatus(true);
        exampleCar.setFuelType("Gasolina");

        CarModel.insert(exampleCar);

        assertEquals(exampleCar.getName(), CarModel.getCarByID(exampleCar.getId()).getName());
    }

    @Test
    public void getFuelTypes(){
        ArrayList<String> fueltypes = new ArrayList<>();
        fueltypes.add("Seleccione combustible");
        fueltypes.add("Otro...");
        fueltypes.add("Gasolina");

        assertEquals(fueltypes, CarModel.getAllSpinnerItems());
    }

    @Test
    public void searchCar(){
        ArrayList<CarEntity> allCars = new ArrayList<>();

        CarEntity exampleCar = new CarEntity();
        exampleCar.setName("Paco");
        exampleCar.setModelName("Golf");
        exampleCar.setBrandName("Volkswagen");
        exampleCar.setEnrollmentName("2857 CFG");
        exampleCar.setReceptionDate("11/01/2000");
        exampleCar.setCarFault("Tubo de escape roto");
        exampleCar.setCarStatus(true);
        exampleCar.setFuelType("Gasolina");

        allCars.add(exampleCar);

        SimpleDateFormat newDate = new SimpleDateFormat("dd/MM/yyyy");

        try {
            //assertEquals(allCars.get(0).getName(), CarModel.filterElement("Paco", (newDate.parse("11/01/2000")),"Gasolina").get(0).getName());
            assertEquals(allCars.get(0).getName(), CarModel.filterElement("", newDate.parse(""), "Gasolina").get(0).getName());
            assertEquals(allCars.get(0).getName(), CarModel.filterElement("Paco", newDate.parse(""), "").get(0).getName());
            assertEquals(allCars.get(0).getName(), CarModel.filterElement("", newDate.parse(""), "").get(0).getName());
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

}
