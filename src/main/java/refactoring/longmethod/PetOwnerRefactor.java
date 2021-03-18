package refactoring.longmethod;

import refactoring.Animal;

public class PetOwnerRefactor {

    String name;
    Animal pet;
    PetRoutine routine;

    public PetOwnerRefactor(String name, Animal pet, PetRoutine routine) {
        this.name = name;
        this.pet = pet;
        this.routine = routine;
    }

    /**
     * How we treat our pet on a daily basis.
     * We do the following:
     * Greet them, feed them, let them exercise, then poop, feed again, then put them to bed.
     *
     * @return our pet.
     */
    public Animal dailyRoutine() {

        routine.morningRoutine(pet);
        routine.eveningRoutine(pet);

        return pet;

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
