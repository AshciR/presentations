package refactoring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PetOwnerTest {

    @Test
    void dailyRoutine() {

        // Given: We have a pet with an empty stomach
        Animal lulu = new Dog("Lulu");

        // And: We have a owner
        PetOwner sasha = new PetOwner("Sasha", lulu);

        // When: We she does her daily routine
        sasha.dailyRoutine();

        // Then: The pet stomach should have food in there
        Assertions.assertEquals(1, lulu.getStomach().size());
        Assertions.assertTrue(lulu.getStomach().contains("Puppy Chow"));

    }

    @Test
    void dailyRoutineRefactor() {

        // Given: We have a pet with an empty stomach
        Animal lulu = new Dog("Lulu");

        // And: We have a owner
        PetOwnerRefactor sasha = new PetOwnerRefactor("Sasha", lulu, new PetRoutineRefactorImpl());

        // When: We she does her daily routine
        sasha.dailyRoutine();

        // Then: The pet stomach should have food in there
        Assertions.assertEquals(1, lulu.getStomach().size());
        Assertions.assertTrue(lulu.getStomach().contains("Puppy Chow"));

    }
}