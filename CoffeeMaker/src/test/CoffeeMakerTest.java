package test;

import org.junit.Before;
import org.junit.Test;
import model.CoffeeMaker;
import static org.junit.Assert.*;
import exceptions.TimeException;
import exceptions.TooManyBeansException;
import exceptions.NotEnoughBeansException;
import exceptions.WaterException;
import exceptions.NoCupsRemainingException;
import exceptions.StaleCoffeeException;

public class CoffeeMakerTest {

    private static CoffeeMaker coffee;

    @Before
    public void setUp(){
        // TODO: instantiate your test objects here
        coffee = new CoffeeMaker(10, 80);
    }

    @Test
    public void testGetters() {
        assertEquals("Cups remaining is not right!", 10, coffee.getCupsRemaining());
        assertEquals("Time since last brew is not right!", 80, coffee.getTimeSinceLastBrew());
    }

    @Test
    public void testSetTimeSinceLastBrewPositive() {
        try {
            coffee.setTimeSinceLastBrew((int)Math.random()*Integer.MAX_VALUE);
        }
        catch (TimeException t) {
            fail("TimeException should not have been caught!");
        }
    }

    @Test
    public void testSetTimeSinceLastBrewNegative() {
        try {
            coffee.setTimeSinceLastBrew(-100);
            fail("Should have failed the negative time test!");
        }
        catch (TimeException t) {
            assertTrue(true);
        }
    }

    @Test
    public void testBrewShouldPass() {
        try {
            coffee.brew(2.50, 15);
        }
        catch (Exception e) {
            //fail if any type of exception is caught
            fail("No exception should have been caught here!");
        }
    }

    @Test
    public void testBrewTooManyBeansException() {
        try {
            coffee.brew(2.70, 15);
        }
        catch(TooManyBeansException t) {
            assertTrue(true);
        }
        catch(NotEnoughBeansException | WaterException e) {
            fail("Water or Not enough beans exception should not have been caught!");
        }
    }

    @Test
    public void testBrewNotEnoughBeansException() {
        try {
            coffee.brew(2, 15);
        }
        catch(NotEnoughBeansException n) {
            assertTrue(true);
        }
        catch(WaterException | TooManyBeansException e) {
            fail("These exceptions should not have been caught!");
        }
    }

    @Test
    public void testBrewWaterException() {
        try {
            coffee.brew(2.5, 13);
        }
        catch(WaterException w) {
            assertTrue(true);
        }
        catch(NotEnoughBeansException | TooManyBeansException e) {
            fail("These exceptions should not have been caught!");
        }
    }

    @Test
    public void testBrewTwoExceptionsTooManyBeansExceptionFirst() {
        try {
            coffee.brew(2.7, 13);
        }
        catch(TooManyBeansException t) {
            assertTrue(true);
        }
        catch(WaterException | NotEnoughBeansException e) {
            fail("These exceptions should have been caught!");
        }

    }

    @Test
    public void testPourCoffeeNoCupsException() {
        try {

            emptyCups();
            coffee.pourCoffee();
        }
        catch(NoCupsRemainingException n) {
            assertTrue(true);
        }
        catch(StaleCoffeeException s) {
            fail("Stale Coffee Exception should not have been thrown!");
        }

    }

    @Test
    public void testPourCoffeeStaleCoffeeException() throws TooManyBeansException, WaterException, NotEnoughBeansException, TimeException {
        try {
            coffee.brew(2.5, 15);
            coffee.setTimeSinceLastBrew(50); //set time >60
            coffee.pourCoffee();
        }
        catch(StaleCoffeeException s) {
            assertTrue(true);
        }
        catch(NoCupsRemainingException n) {
            fail("This exception should not have been thrown!");
        }
    }

    @Test
    public void testPourCoffeeSuccess() {
        try {
            coffee.brew(2.5, 15);
            coffee.setTimeSinceLastBrew(100);
            coffee.pourCoffee();
        }
        catch(Exception e) {
            System.out.println(e.getMessage());
            fail("No exception should have been thrown here!");
        }
    }

    private void emptyCups() throws StaleCoffeeException, NoCupsRemainingException {
        for(int i = 0; i < 20; i++) {
            coffee.pourCoffee();
        }
    }

}