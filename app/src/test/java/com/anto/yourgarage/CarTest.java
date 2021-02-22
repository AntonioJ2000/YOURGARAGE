package com.anto.yourgarage;

import com.anto.yourgarage.models.CarEntity;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CarTest {
    private CarEntity car;

    @Before
    public void setUp() {
        this.car = new CarEntity();
    }

    @Test
    public void carOwner(){
        assertEquals(true, this.car.setName("Anto"));
        assertEquals("Anto",this.car.getName());

        assertEquals(true, this.car.setName("ANTO"));
        assertEquals("ANTO", this.car.getName());

        assertEquals(true, this.car.setName("anto"));
        assertEquals("anto", this.car.getName());


        assertEquals(false, this.car.setName("Anto11"));
    }

    @Test
    public void carModel(){
        assertEquals(true, this.car.setModelName("Focus"));
        assertEquals("Focus",this.car.getModelName());

        assertEquals(true, this.car.setModelName("M3 e46"));
        assertEquals("M3 e46", this.car.getModelName());

        assertEquals(true, this.car.setModelName("i30"));
    }

    @Test
    public void carBrand(){
        assertEquals(true, this.car.setBrandName("BMW"));
        assertEquals("BMW",this.car.getBrandName());

        assertEquals(true, this.car.setBrandName("Hyundai"));
        assertEquals("Hyundai", this.car.getBrandName());

        assertEquals(false, this.car.setBrandName("Renault11"));
    }

    @Test
    public void carEnrollment(){
        assertEquals(true, this.car.setEnrollmentName("1234 ABC"));
        assertEquals("1234 ABC",this.car.getEnrollmentName());

        assertEquals(true, this.car.setEnrollmentName("CO 2345 JK"));
        assertEquals("CO 2345 JK", this.car.getEnrollmentName());

        assertEquals(false, this.car.setEnrollmentName("123456789 ABC"));
    }

    @Test
    public void carReceptionDate(){
        assertEquals(true, this.car.setReceptionDate("11/01/2020"));
        assertEquals("11/01/2020", this.car.getReceptionDate());

        assertEquals(true, this.car.setReceptionDate("31/12/2020"));
        assertEquals("31/12/2020", this.car.getReceptionDate());

        assertEquals(false, this.car.setReceptionDate("32/12/2020"));
        assertEquals(false, this.car.setReceptionDate("31/13/2020"));
    }

    @Test
    public void carFuelType(){
        //Car fuel type setter does not return any parameter. However it gets all types of String, even if it contains numbers.
        this.car.setFuelType("Gasolina 95");
        assertEquals("Gasolina 95", this.car.getFuelType());

        this.car.setFuelType("Diésel");
        assertEquals("Diésel", this.car.getFuelType());
    }

    @Test
    public void carFault(){
        assertEquals(true, this.car.setCarFault("El tubo de escape tiene una fisura"));
        assertEquals("El tubo de escape tiene una fisura", this.car.getCarFault());

        assertEquals(true, this.car.setCarFault("Cambiar los 4 neumáticos"));
        assertEquals("Cambiar los 4 neumáticos", this.car.getCarFault());
    }


    @Test
    public void carStatus(){
        //Car status setter does not return any parameter.
        this.car.setCarStatus(true);
        assertEquals("Grave", this.car.getCarStatus());

        this.car.setCarStatus(false);
        assertEquals("No es grave", this.car.getCarStatus());
    }
}

