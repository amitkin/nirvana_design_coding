package com.mylearning.coffeemachine;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class CoffeeMachineApplication {
    /*
    Design a coffee machine which makes different beverages based on set ingredients. The initialization of the recipes for each drink should be hard-coded,
    although it should be relatively easy to add new drinks. The machine should display the ingredient stock (+cost) and menu upon startup, and after every
    piece of valid user input. Drink cost is determined by the combination of ingredients. For example, Coffee is 3 units of coffee (75 cents per), 1 unit
    of sugar (25 cents per), 1 unit of cream (25 cents per). Ingredients and Menu items should be printed in alphabetical order. If the drink is out of stock,
    it should print accordingly. If the drink is in stock, it should print "Dispensing: ". To select a drink, the user should input a relevant number.
    If they submit "r" or "R" the ingredients should restock, and "q" or "Q" should quit. Blank lines should be ignored, and invalid input should print an
    invalid input message.
    */
    //https://codereview.stackexchange.com/questions/83195/designing-another-coffee-machine-application
    public static void main(String[] args) {
        List<Ingredient> ingredients = Default.getDefaultIngredients();
        Map<Ingredient, Integer> ingredientStock = ingredients.stream().
                collect(Collectors.toMap(ingredient -> ingredient, ingredient -> 10));
        List<Drink> drinks = Default.getDefaultDrinks();

        CoffeeMachine coffeeMachine = new CoffeeMachine(ingredients, ingredientStock, drinks);
        CoffeeMachineDisplay coffeeMachineDisplay = new CoffeeMachineDisplay(coffeeMachine);

        do {
            coffeeMachineDisplay.displayCurrentStock();
            coffeeMachineDisplay.displayMenu();
        } while (coffeeMachineDisplay.processInputAndContinue());
    }
}
