package refactoring.flagconditionals;

import refactoring.Animal;

import java.util.Set;

public class PetFeeder {

    /*
    Code Smell: Flag Parameters.

    When you have a variable that acts as a flag (director) that determines
    how a method behaves.

    We should aim to have our methods be as clear and intuitive as possible.
    Studies have shown we spend approximately 10x more reading code than writing it.
    As such, we want to make the experience pleasant, not only for ourselves, but
    for the next person who will maintain it. Flag conditionals detract from this experience.

    Why is it a smell?

    1. It's not obvious what the method will do without looking inside of it
    2. Can be the cause of silly mistakes b/c of the non-intuition.

    How to fix:
    1. Create separate explicit methods that would do each conditional
    2. Determine if there is any shared functionality between the conditional and place it in it's own method.

    Let's take a look at an example below.
     */

    final private String name;

    public PetFeeder(String name) {
        this.name = name;
    }

    /**
     * Feeds the pet food based on recommendation provided.
     *
     * @param pet             the pet to be fed
     * @param recommendations a set (collection without duplicates) of food the pet can be fed
     * @param cannedFood      true if canned food is allowed. This is the flag parameter
     * @return the pet after it's fed
     */
    public Animal feedPet(Animal pet, Set<String> recommendations, Boolean cannedFood) {

        // When true, there are no restrictions on the food that can be eaten
        if (cannedFood) {
            recommendations.forEach(food -> pet.feed(food));
        } else {
            // We have to remove the canned food from the recommendations
            recommendations.stream()
                    .filter(food -> !(food.contains("canned") || food.contains("Canned") || food.contains("CANNED")))
                    .forEach(food -> pet.feed(food));
        }

        return pet;
    }

    /*
    The above method will get the job, but from the caller's perspective, what they will see is this.
    feedPet(lassie, foodRecommendations, true);

    It's not obvious what 'true' means in this case..
    True that we'll feed the pet?
    True that the food exists?
    True that all pets must die?
     */

    public String getName() {
        return name;
    }
}
