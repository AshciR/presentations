package refactoring.longparamerter;

import refactoring.Animal;

public class PetGroomer {

    /*
       Code Smell: Long Parameter List

       Long parameter lists are hard to understand, because they become inconsistent and difficult.
       You have to remember the correct order of the arguments, this makes them prone to errors.

       How to fix: Introduce Parameter Object
       1. Create a new class to represent the group of parameters
       2. Make it immutable

       Let's take a look at groom's param list.
    */
    public Animal groom(Animal pet,
                        String serviceRequest,
                        Double price,
                        String groomerName,
                        Boolean callWhenReady) {

        System.out.println(groomerName + " will " + serviceRequest + " to " + pet.getName() + " for " + price + " dollars.");

        if (callWhenReady) {
            System.out.println(groomerName + " will call you when " + pet.getName() + " is ready");
        } else {
            System.out.println("We won't call you");
        }

        return pet;
    }

}
