package model;

/**
 * A coffee maker used to train baristas.
 *
 * Class invariant: cups remaining >= 0, time since last brew >= 0
 */

import exceptions.NoCupsRemainingException;
import exceptions.StaleCoffeeException;
import exceptions.TooManyBeansException;
import exceptions.NotEnoughBeansException;
import exceptions.WaterException;
import exceptions.TimeException;

public class CoffeeMaker {

    private int cups;
    private int lastBrewTime;
    private static int FULL_CUPS = 20;

    public CoffeeMaker(int cups, int lastBrewTime){
        this.cups = cups;
        this.lastBrewTime = lastBrewTime;
    }

    // getters
    public int getTimeSinceLastBrew() {
        return this.lastBrewTime;
    }
    public int getCupsRemaining() {
        return this.cups;
    }

    // EFFECTS: return true if there are coffee cups remaining
    public boolean areCupsRemaining() {
        return (getCupsRemaining() > 0);
    }

    //EFFECTS: if time is negative, throws TimeException
    //          otherwise sets time since last brew
    public void setTimeSinceLastBrew(int time) throws TimeException {
        if (time < 0) {
            throw new TimeException();
        }
        this.lastBrewTime = time;
    }

    //EFFECTS: Throws the respective exceptions for outlying values
    //          otherwise sets cups remaining to full (20 cups) and time since last brew to 0
    public void brew(double beans, double water) throws NotEnoughBeansException, TooManyBeansException, WaterException {
        if (beans > 2.60) {
            throw new TooManyBeansException(beans);
        }
        else if (beans < 2.40) {
            throw new NotEnoughBeansException(beans);
        }
        else if (water < 14.75) {
            throw new WaterException();
        }
        this.cups = FULL_CUPS;
        this.lastBrewTime = 0;
    }

    //MODIFIES: this
    //EFFECTS: If there is no cups remaining, it will throw NoCupsRemainingException,
    //          throws StaleCoffeeException if time since last brew is less than 60 seconds
    //          otherwise subtracts one cup from cups remaining
    public void pourCoffee() throws NoCupsRemainingException, StaleCoffeeException {
        if (!areCupsRemaining()) {
            throw new NoCupsRemainingException();
        }
        if (getTimeSinceLastBrew() < 60) {
            throw new StaleCoffeeException();
        }
        this.cups--;
    }


}
