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


}
