package refactoring;

public interface PetRoutine {

    /**
     * How a caretaker treats their pet in the morning
     *
     * @param pet the pet to take care of
     * @return the same pet after it's been taken care of
     */
    Animal morningRoutine(Animal pet);

    /**
     * How a caretaker treats their pet in the evening
     *
     * @param pet the pet to take care of
     * @return the same pet after it's been taken care of
     */
    Animal eveningRoutine(Animal pet);

}
