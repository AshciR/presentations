package refactoring.duplicatedcode;

import refactoring.Animal;
import refactoring.PetRoutine;

public class PetRoutineImpl implements PetRoutine {

    /*
    Code Smell: Duplicated code.
    If you the same code structure in more than one place,
    you can be sure that your program will be better if you find a way to unify them.

    How to fix: Extract Method
    Extract Method: You have a code fragment that can be grouped together,
    turn the fragment into a method whose name explains the purpose of the method.

    Let's take a look at these methods, specifically the feeding part
     */
    @Override
    public Animal morningRoutine(Animal pet) {

        // Greet
        System.out.println("Good Morning " + pet.getName() + ", I love you!");

        // Feed
        if (pet.getStomach().size() < 2) {
            pet.feed("Puppy Chow");
            System.out.println("Feeding " + pet.getName() + " Puppy Chow.");
        } else {
            System.out.println(pet.getName() + "'s belly is full, no food for them.");
        }

        // Exercise
        System.out.println(pet.getName() + "'s is running around");

        return pet;
    }

    @Override
    public Animal eveningRoutine(Animal pet) {

        // Let them poop
        pet.poop();
        System.out.println(pet.getName() + " pooped.");

        // Feed
        if (pet.getStomach().size() < 2) {
            pet.feed("Puppy Chow");
            System.out.println("Feeding " + pet.getName() + " Puppy Chow.");
        } else {
            System.out.println(pet.getName() + "'s belly is full, no food for them.");
        }

        // Let them go to bed
        System.out.println("Putting " + pet.getName() + " to bed.");

        return pet;
    }

}
