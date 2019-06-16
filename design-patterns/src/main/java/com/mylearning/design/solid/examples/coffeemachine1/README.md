## Introducing a shared interface

You can either create another abstraction, e.g., Coffee, as the superclass of CoffeeBean and GroundCoffee and use it as the type of the method parameter. That would unify the structure of both addCoffee methods, but require additional validation in both methods. The addCoffee method of the BasicCoffeeMachine class would need to check that the caller provided an instance of GroundCoffee, and the addCoffee implementation of the PremiumCoffeeMachine would require an instance of CoffeeBean. This would obviously break the Liskov Substitution Principle because the validation would fail if you provide a BasicCoffeeMachine object instead of a PremiumCoffeeMachine and vice versa.

The better approach is to exclude the addCoffee method from the interface or superclass because you can’t interchangeably implement it. 

## Follow the Interface Segregation Principle
You need to split the CoffeeMachine interface into multiple interfaces for the different kinds of coffee machines. All known implementations of the interface implement the addGroundCoffee method. So, there is no reason to remove it.

    public interface CoffeeMachine {
        void addGroundCoffee(GroundCoffee newCoffee) throws CoffeeException;
    }
 That’s not the case for the brewFilterCoffee and brewEspresso methods. You should create two new interfaces to segregate them from each other. And in this example, these two interfaces should also extend the CoffeeMachine interface. But that doesn’t have to be the case if you refactor your own application. Please check carefully if an interface hierarchy is the right approach, or if you should define a set of interfaces.
 
 After you’ve done that, the FilterCoffeeMachine interface extends the CoffeeMachine interface, and defines the brewFilterCoffee method.
 
    public interface FilterCoffeeMachine extends CoffeeMachine {
        CoffeeDrink brewFilterCoffee() throws CoffeeException;
    }

And the EspressoCoffeeMachine interface also extends the CoffeeMachine interface, and defines the brewEspresso method.

    public interface EspressoCoffeeMachine extends CoffeeMachine {
        CoffeeDrink brewEspresso() throws CoffeeException;
    }

Congratulation, you segregated the interfaces so that the functionalities of the different coffee machines are independent of each other. As a result, the BasicCoffeeMachine and the EspressoMachine class no longer need to provide empty method implementations and are independent of each other.

The BasicCoffeeMachine class now implements the FilterCoffeeMachine interface, which only defines the addGroundCoffee and the brewFilterCoffee methods.

## Extending the application

After you segregated the interfaces so that you can evolve the two coffee machine implementations independently of each other, you might be wondering how you can add different kinds of coffee machines to your applications. In general, there are four options for that:

1. The new coffee machine is a FilterCoffeeMachine or an EspressoCoffeeMachine. In this case, you only need to implement the corresponding interface.

2. The new coffee machine brews filter coffee and espresso. This situation is similar to the first one. The only difference is that your class now implements both interfaces; the FilterCoffeeMachine and the EspressoCoffeeMachine.

3. The new coffee machine is completely different to the other two. Maybe it’s one of these pad machines that you can also use to make tea or other hot drinks. In this case, you need to create a new interface and decide if you want to extend the CoffeeMachine interface. In the example of the pad machine, you shouldn’t do that because you can’t add ground coffee to a pad machine. So, your PadMachine class shouldn’t need to implement an addGroundCoffee method.

4. The new coffee machine provides new functionality, but you can also use it to brew a filter coffee or an espresso. In that case, you should define a new interface for the new functionality. Your implementation class can then implement this new interface and one or more of the existing interfaces. But please make sure to segregate the new interface from the existing ones, as you did for the FilterCoffeeMachine and the EspressoCoffeeMachine interfaces.

##Definition of the Dependency Inversion Principle

The general idea of this principle is as simple as it is important: High-level modules, which provide complex logic, should be easily reusable and unaffected by changes in low-level modules, which provide utility features. To achieve that, you need to introduce an abstraction that decouples the high-level and low-level modules from each other.

Based on this idea, Robert C. Martin’s definition of the Dependency Inversion Principle consists of two parts:

    1. High-level modules should not depend on low-level modules. Both should depend on abstractions.
    2. Abstractions should not depend on details. Details should depend on abstractions.
    
If you consequently apply the Open/Closed Principle and the Liskov Substitution Principle to your code, it will also follow the Dependency Inversion Principle.

The Open/Closed Principle required a software component to be open for extension, but closed for modification. You can achieve that by introducing interfaces for which you can provide different implementations. The interface itself is closed for modification, and you can easily extend it by providing a new interface implementation.

Your implementations should follow the Liskov Substitution Principle so that you can replace them with other implementations of the same interface without breaking your application.

#### Brewing coffee with the Dependency Inversion Principle

If you build a coffee machine application that automatically brews you a fresh cup of coffee in the morning, you can model these machines as a BasicCoffeeMachine and a PremiumCoffeeMachine class.

To implement a class that follows the Dependency Inversion Principle and can use the BasicCoffeeMachine or the PremiumCoffeeMachine class to brew a cup of coffee, you need to apply the Open/Closed and the Liskov Substitution Principle. That requires a small refactoring during which you introduce interface abstractions for both classes.

As all coffee lovers will agree, there are huge differences between filter coffee and espresso. That’s why we are using different machines to brew them, even so, some machines can do both. I, therefore, suggest to create two independent abstractions:

1. The FilterCoffeeMachine interface defines the Coffee brewFilterCoffee() method and gets implemented by all coffee machine classes that can brew a filter coffee.
2. All classes that you can use to brew an espresso, implement the EspressoMachine interface, which defines the Coffee brewEspresso() method.
    
    
    public interface CoffeeMachine {
        Coffee brewFilterCoffee();
    }
    
    public interface EspressoMachine {
        Coffee brewEspresso();
    }
    
In the next step, you need to refactor both coffee machine classes so that they implement one or both of these interfaces.