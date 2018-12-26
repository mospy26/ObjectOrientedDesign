package exceptions;

public class StaleCoffeeException extends Exception {

    private int timeSinceLastBrew;

    public StaleCoffeeException() {
        super("Stale coffee cannot be poured.");
    }

}
