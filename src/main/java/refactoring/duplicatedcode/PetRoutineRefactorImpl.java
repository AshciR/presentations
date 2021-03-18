package refactoring.duplicatedcode;

import refactoring.Animal;
import refactoring.longmethod.PetRoutine;

public class PetRoutineRefactorImpl implements PetRoutine {

    @Override
    public Animal morningRoutine(Animal pet) {

        greetPet(pet);
        feedPet(pet);
        letPetExercise(pet);

        return pet;
    }

    @Override
    public Animal eveningRoutine(Animal pet) {

        letPetPoop(pet);
        feedPet(pet);
        putPetToBed(pet);

        return pet;
    }

    private void letPetPoop(Animal pet) {
        pet.poop();
        System.out.println(pet.getName() + "'s pooped.");
    }

    private void greetPet(Animal pet) {
        System.out.println("Good Morning " + pet.getName() + ", I love you!");
    }

    private Animal feedPet(Animal pet) {

        if (pet.getStomach().size() < 2) {
            pet.feed("Puppy Chow");
            System.out.println("Feeding " + pet.getName() + " Puppy Chow.");
        } else {
            System.out.println(pet.getName() + "'s belly is full, no food for them.");
        }

        return pet;
    }

    private void letPetExercise(Animal pet) {
        System.out.println(pet.getName() + "'s is running around");
    }

    private void putPetToBed(Animal pet) {
        System.out.println("Putting " + pet.getName() +" to bed.");
    }

}
