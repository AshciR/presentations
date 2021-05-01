package refactoring.flagconditionals;

import refactoring.Animal;

import java.util.Set;
import java.util.function.Predicate;

public class PetFeederRefactor {

    final private String name;

    public PetFeederRefactor(String name) {
        this.name = name;
    }

    /*
    Same logic is now split into smaller and separate methods.
    Small, specific methods are a good sign. They'll be easier to
    reuse ane compose, which allows more more flexible designs.
    Also, because of the rename, the client should know without a doubt
    what these methods will do.
     */
    public Animal feedPetWithCannedFoodAllowed(Animal pet, Set<String> recommendations) {
        recommendations.forEach(food -> pet.feed(food));
        return pet;
    }

    public Animal feedPetWithoutCannedFoodAllowed(Animal pet, Set<String> recommendations) {

        recommendations.stream()
                .filter(cannedFoodPredicate()) // We extracted the canned food logic
                .forEach(food -> pet.feed(food));

        return pet;
    }

    /*
    Extracted the canned food logic into this method.
    Benefit is that if the business changes their mind or if we
    come up with a better solution, we'll only have to update this method.
     */
    private Predicate<String> cannedFoodPredicate() {
        return food -> !(
                food.contains("canned") || food.contains("Canned") || food.contains("CANNED")
        );
    }

    public String getName() {
        return name;
    }
}
