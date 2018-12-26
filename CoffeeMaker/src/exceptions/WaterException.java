package exceptions;

public class WaterException extends Exception {

    private int amountOfCups;

    public WaterException() {
        super("Not enough water to be supplied");
    }

}
