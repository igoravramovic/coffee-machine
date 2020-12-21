package machine;

import java.util.Scanner;

public class CoffeeMachine {
    static boolean doExit = false;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Write action (buy, fill, take, remaining, exit):");

        while (!doExit) {

            doExit = CoffeeMachineClass.newCommand(scanner.nextLine());
        }

    }
}

enum CoffeeMachineStates {
    START, INITIAL, BUY, FILLWATER, FILLMILK, FILLBEANS, FILLCUPS
}

class CoffeeMachineClass {

    static CoffeeMachineStates coffeeMachineState = CoffeeMachineStates.INITIAL;
    static int coffeeMachineMoneyStatus = 550;
    static int coffeeMachineWaterStatus = 400;
    static int coffeeMachineMilkStatus = 540;
    static int coffeeMachineBeansStatus = 120;
    static int coffeeMachineCupsStatus = 9;

    public static boolean newCommand(String newCommand) {
        switch (coffeeMachineState) {
            case START:
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                coffeeMachineState = CoffeeMachineStates.INITIAL;
                break;
            case INITIAL:
                switch (newCommand) {
                    case "remaining":
                        printCoffeeMachineState();
                        break;
                    case "buy":
                        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
                        coffeeMachineState = CoffeeMachineStates.BUY;
                        break;
                    case "fill":
                        System.out.println("Write how many ml of water do you want to add:");
                        coffeeMachineState = CoffeeMachineStates.FILLWATER;
                        break;
                    case "take":
                        takeMoneyFromCoffeeMachine();
                        break;
                    case "exit":
                        return true;
                }
                break;
            case BUY:
                prepareCoffee(newCommand);
                coffeeMachineState = CoffeeMachineStates.INITIAL;
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                break;
            case FILLWATER:
                fillWater(Integer.parseInt(newCommand));
                coffeeMachineState = CoffeeMachineStates.FILLMILK;
                break;
            case FILLMILK:
                fillMilk(Integer.parseInt(newCommand));
                coffeeMachineState = CoffeeMachineStates.FILLBEANS;
                break;
            case FILLBEANS:
                fillBeans(Integer.parseInt(newCommand));
                coffeeMachineState = CoffeeMachineStates.FILLCUPS;
                break;
            case FILLCUPS:
                fillCups(Integer.parseInt(newCommand));
                coffeeMachineState = CoffeeMachineStates.INITIAL;
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                break;
        }
        return false;
    }

    private static void printCoffeeMachineState() {
        System.out.println("The coffee machine has:");
        System.out.println(coffeeMachineWaterStatus + " of water");
        System.out.println(coffeeMachineMilkStatus + " of milk");
        System.out.println(coffeeMachineBeansStatus + " of coffee beans");
        System.out.println(coffeeMachineCupsStatus + " of disposable cups");
        System.out.println(coffeeMachineMoneyStatus + " of money");

        coffeeMachineState = CoffeeMachineStates.INITIAL;
        System.out.println("Write action (buy, fill, take, remaining, exit):");

    }

    public static void prepareCoffee(String typeOfCofeee) {
        switch (typeOfCofeee) {
            case "1":
                if (coffeeMachineWaterStatus >= 250 && coffeeMachineBeansStatus >= 16) {
                    System.out.println("I have enough resources, making you a coffee!");
                    coffeeMachineWaterStatus -= 250;
                    coffeeMachineBeansStatus -= 16;
                    coffeeMachineMoneyStatus += 4;
                    --coffeeMachineCupsStatus;
                } else if (coffeeMachineWaterStatus < 250) {
                    System.out.println("Sorry, not enough water!");
                } else if (coffeeMachineBeansStatus < 16) {
                    System.out.println("Sorry, not enough water!");
                }
                break;
            case "2":
                if (coffeeMachineWaterStatus >= 350 && coffeeMachineBeansStatus >= 20 && coffeeMachineMilkStatus >= 75) {
                    System.out.println("I have enough resources, making you a coffee!");
                    coffeeMachineMoneyStatus += 7;
                    --coffeeMachineCupsStatus;
                    coffeeMachineWaterStatus -= 350;
                    coffeeMachineMilkStatus -= 75;
                    coffeeMachineBeansStatus -= 20;
                } else if (coffeeMachineWaterStatus < 350) {
                    System.out.println("Sorry, not enough water!");
                } else if (coffeeMachineBeansStatus < 20) {
                    System.out.println("Sorry, not enough coffee beans!");
                } else if (coffeeMachineMilkStatus < 75) {
                    System.out.println("Sorry, not enough milk!");
                }

                break;
            case "3":
                if (coffeeMachineWaterStatus >= 200 && coffeeMachineBeansStatus >= 12 && coffeeMachineMilkStatus >= 100) {
                    System.out.println("I have enough resources, making you a coffee!");
                    coffeeMachineMoneyStatus += 6;
                    --coffeeMachineCupsStatus;
                    coffeeMachineWaterStatus -= 200;
                    coffeeMachineMilkStatus -= 100;
                    coffeeMachineBeansStatus -= 12;
                } else if (coffeeMachineWaterStatus < 200) {
                    System.out.println("Sorry, not enough water!");
                } else if (coffeeMachineBeansStatus < 12) {
                    System.out.println("Sorry, not enough coffee beans!");
                } else if (coffeeMachineMilkStatus < 100) {
                    System.out.println("Sorry, not enough milk!");
                }

                break;

            case "back":
                coffeeMachineState = CoffeeMachineStates.INITIAL;
                System.out.println("Write action (buy, fill, take, remaining, exit):");
                break;
        }
    }

    public static void fillWater(int amount) {
        coffeeMachineWaterStatus += amount;
        System.out.println("Write how many ml of milk do you want to add:");
        coffeeMachineState = CoffeeMachineStates.FILLMILK;
    }

    public static void fillMilk(int amount) {
        coffeeMachineMilkStatus += amount;
        System.out.println("Write how many grams of coffee beans do you want to add:");
        coffeeMachineState = CoffeeMachineStates.FILLBEANS;

    }

    public static void fillBeans(int amount) {
        coffeeMachineBeansStatus += amount;
        System.out.println("Write how many disposable cups of coffee do you want to add:");
        coffeeMachineState = CoffeeMachineStates.FILLCUPS;

    }

    public static void fillCups(int amount) {
        coffeeMachineCupsStatus += amount;
        coffeeMachineState = CoffeeMachineStates.INITIAL;
    }


    public static void takeMoneyFromCoffeeMachine() {
        System.out.println("I gave you $" + coffeeMachineMoneyStatus);
        coffeeMachineMoneyStatus = 0;

        coffeeMachineState = CoffeeMachineStates.INITIAL;
        System.out.println("Write action (buy, fill, take, remaining, exit):");
    }
}
