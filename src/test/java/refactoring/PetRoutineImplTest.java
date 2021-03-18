package refactoring;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import refactoring.duplicatedcode.PetRoutineImpl;
import refactoring.duplicatedcode.PetRoutineRefactorImpl;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

class PetRoutineImplTest {

    @Test
    void morningRoutine() {

        // Given: We have a pet with an empty stomach
        Animal lulu = new Dog("Lulu");

        // When: After we finish the morning routine
        new PetRoutineImpl().morningRoutine(lulu);

        // Then: The pet stomach should have food in there
        Assertions.assertTrue(lulu.getStomach().size() > 0);
        Assertions.assertTrue(lulu.getStomach().contains("Puppy Chow"));

    }

    @Test
    void morningRoutineRefactored() {

        // Given: We have a pet with an empty stomach
        Animal lulu = new Dog("Lulu");

        // When: After we finish the morning routine
        new PetRoutineRefactorImpl().morningRoutine(lulu);

        // Then: The pet stomach should have food in there
        Assertions.assertTrue(lulu.getStomach().size() > 0);
        Assertions.assertTrue(lulu.getStomach().contains("Puppy Chow"));

    }

    @Test
    void eveningRoutine() {

        // Given: We have a pet with a full stomach
        Queue<String> stomach = new LinkedList<>(List.of("Puppy Chow", "Kibbles", "Bone Morrow"));
        Animal lulu = new Dog("Lulu", stomach);

        // When: After we finish the evening routine
        new PetRoutineImpl().eveningRoutine(lulu);

        // Then: The pet stomach should have 2 things in there
        Assertions.assertEquals(2, lulu.getStomach().size());
        Assertions.assertTrue(lulu.getStomach().contains("Kibbles"));
        Assertions.assertTrue(lulu.getStomach().contains("Bone Morrow"));

    }

    @Test
    void eveningRoutineRefactored() {

        // Given: We have a pet with a full stomach
        Queue<String> stomach = new LinkedList<>(List.of("Puppy Chow", "Kibbles", "Bone Morrow"));
        Animal lulu = new Dog("Lulu", stomach);

        // When: After we finish the evening routine
        new PetRoutineRefactorImpl().eveningRoutine(lulu);

        // Then: The pet stomach should have 2 things in there
        Assertions.assertEquals(2, lulu.getStomach().size());
        Assertions.assertTrue(lulu.getStomach().contains("Kibbles"));
        Assertions.assertTrue(lulu.getStomach().contains("Bone Morrow"));

    }
}