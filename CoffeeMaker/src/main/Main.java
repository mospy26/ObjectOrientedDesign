package main;

import java.util.Scanner;
import model.CoffeeMaker;
import exceptions.TimeException;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Welcome! Would you like some coffee? (Yes/No)");
        if (sc.nextLine() == "No") {
            System.out.println("Well, no worries! Thank you.");
            System.exit(0);
        } else {
            System.out.println("Great! What is your name?");
            String name = sc.nextLine();
            System.out.println("How many cups would you like " + name + "?");
            int cups = sc.nextInt();
            CoffeeMaker coffee = new CoffeeMaker(cups, 0);
            try {
                coffee.setTimeSinceLastBrew(100);
            }
            catch(TimeException t) {
                System.out.println("Exception thrown");
                System.exit(-1);
            }
            System.out.println("Here you go!");
            try {
                coffee.pourCoffee();
            }
            catch(Exception e) {
                System.out.println("Exception thrown.");
            }
            System.out.println("Thanks!");
            System.exit(0);
        }

    }
}