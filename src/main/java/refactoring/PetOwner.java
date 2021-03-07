package refactoring;

public class PetOwner {

    /*
    Code Smell: Long Method.
    "The first rule of functions is that they should be small.
    The second rule of functions is that they should be smaller than that" -- Robert (Uncle Bob) Martin

    Long methods are usually harder to reason about, and less reusable.
    Ideally, functions should be as reusable as possible.
    This allows us to add new functionality easily.

    How to fix:
    1. Find the parts of the method that seem to go nicely together
    2. Extract Method(s) for the identified parts

    Let's take a look at these methods, specifically the feeding part
     */

    private final String name;
    private final Animal pet;

    public PetOwner(String name, Animal pet) {
        this.name = name;
        this.pet = pet;
    }

    /**
     * How we treat our pet on a daily basis.
     * We do the following:
     * Greet them, feed them, let them exercise, then poop, feed again, then put them to bed.
     *
     * @return our pet.
     */
    public Animal dailyRoutine() {

        // Morning Routine

        // Greet
        System.out.println("Good Morning " + pet.getName() + ", I love you!");

        // Feed
        if (pet.getStomach().size() < 2) {
            pet.feed("Puppy Chow");
            System.out.println("Feeding " + pet.getName() + " Puppy Chow.");
        } else {
            System.out.println(pet.getName() + "'s belly is full, no food for them.");
        }

        // Let them exercise
        System.out.println(pet.getName() + "'s is running around");

        // Evening Routine

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

        return this.pet;

    }

    public String getName() {
        return name;
    }

    public Animal getPet() {
        return pet;
    }

    @Override
    public String toString() {
        return "PetOwner{" +
                "name='" + name + '\'' +
                ", pet=" + pet +
                '}';
    }

}
