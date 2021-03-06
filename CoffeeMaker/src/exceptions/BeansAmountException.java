package exceptions;

public class BeansAmountException extends Exception {

    private double beans;

    public BeansAmountException(double beans){
        super(beans+" is not right.");
    }

    protected BeansAmountException (double beans, String message) {
        super(beans + " " + message);
        this.beans = beans;
    }

    public double getBeans(){
        return beans;
    }
}
