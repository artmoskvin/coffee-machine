package machine;

import java.util.Scanner;

public class CoffeeMachine {
    private int currentWater = 400;
    private int currentMilk = 540;
    private int currentCoffee = 120;
    private int currentCups = 9;
    private int currentMoney = 550;

    private CoffeeMachineState state = CoffeeMachineState.IDLE;

    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);
        CoffeeMachine coffeeMachine = new CoffeeMachine();
        while (true) {
            System.out.print("Write action (buy, fill, take, remaining, exit): ");
            String input = scanner.next();
            coffeeMachine.processInput(input);
        }
    }

    public void processInput(String input) {
        CoffeeMachineState currentState = getCurrentState();
        switch (currentState) {
            case IDLE:
                processAction(input);
                break;
            case WAITING_COFFEE_TYPE:
                int coffeeType = Integer.parseInt(input);
                makeCoffee(coffeeType);
                state = CoffeeMachineState.IDLE;
                break;
            case FILLING_WATER:
                int newWater = Integer.parseInt(input);
                fillWater(newWater);
                state = CoffeeMachineState.FILLING_MILK;
                suggestFillMilk();
                break;
            case FILLING_MILK:
                int newMilk = Integer.parseInt(input);
                fillMilk(newMilk);
                state = CoffeeMachineState.FILLING_COFFEE;
                suggestFillCoffee();
                break;
            case FILLING_COFFEE:
                int newCoffee = Integer.parseInt(input);
                fillCoffee(newCoffee);
                state = CoffeeMachineState.FILLING_CUPS;
                suggestFillCups();
                break;
            case FILLING_CUPS:
                int newCups = Integer.parseInt(input);
                fillCups(newCups);
                state = CoffeeMachineState.IDLE;
                break;
            default:
                System.out.println("Unknown state: " + currentState);
        }
    }

    private CoffeeMachineState getCurrentState() {
        return state;
    }

    private void processAction(String action) {
        switch (action) {
            case "remaining":
                printState();
                break;
            case "buy":
                suggestCoffee();
                state = CoffeeMachineState.WAITING_COFFEE_TYPE;
                break;
            case "fill":
                suggestFillWater();
                state = CoffeeMachineState.FILLING_WATER;
                break;
            case "take":
                giveMoney();
                break;
            case "exit":
                System.exit(0);
            default:
                System.out.println("Unknown action: " + action);
        }
    }

    private void printState() {
        System.out.println("The coffee machine has:");
        System.out.println(currentWater + " of currentWater");
        System.out.println(currentMilk + " of milk");
        System.out.println(currentCoffee + " of coffee beans");
        System.out.println(currentCups + " of disposable cups");
        System.out.println(currentMoney + " of money");
    }

    private void suggestCoffee() {
        System.out.print("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino: ");
    }

    private void makeCoffee(int coffeeType) {
        switch (coffeeType) {
            case 1:
                makeEspresso();
                break;
            case 2:
                makeLatte();
                break;
            case 3:
                makeCappuccino();
                break;
            default:
                System.out.println("Unknown coffee: " + coffeeType);
        }
    }

    private void makeEspresso() {
        int requiredWater = 250;
        int requiredCoffee = 16;
        int cost = 4;

        currentWater -= requiredWater;
        currentCoffee -= requiredCoffee;
        currentMoney += cost;
    }

    private void makeLatte() {
        int requiredWater = 350;
        int requiredMilk = 75;
        int requiredCoffee = 20;
        int cost = 7;

        currentWater -= requiredWater;
        currentMilk -= requiredMilk;
        currentCoffee -= requiredCoffee;
        currentMoney += cost;
    }

    private void makeCappuccino() {
        int requiredWater = 200;
        int requiredMilk = 100;
        int requiredCoffee = 12;
        int cost = 6;

        currentWater -= requiredWater;
        currentMilk -= requiredMilk;
        currentCoffee -= requiredCoffee;
        currentMoney += cost;
    }

    private void suggestFillWater() {
        System.out.print("Write how many ml of water do you want to add: ");
    }

    private void suggestFillMilk() {
        System.out.print("Write how many ml of milk do you want to add: ");
    }

    private void suggestFillCoffee() {
        System.out.print("Write how many grams of coffee beans do you want to add: ");
    }

    private void suggestFillCups() {
        System.out.print("Write how many disposable cups of coffee do you want to add: ");
    }

    private void fillWater(int newWater) {
        currentWater += newWater;
    }

    private void fillMilk(int newMilk) {
        currentMilk += newMilk;
    }

    private void fillCoffee(int newCoffee) {
        currentCoffee += newCoffee;
    }

    private void fillCups(int newCups) {
        currentCups += newCups;
    }

    private void giveMoney() {
        System.out.println("I gave you $" + currentMoney);
        currentMoney = 0;
    }

}
