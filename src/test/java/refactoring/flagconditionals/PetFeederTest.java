package refactoring.flagconditionals;

import org.junit.jupiter.api.Test;
import refactoring.Animal;
import refactoring.Dog;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toUnmodifiableSet;
import static org.junit.jupiter.api.Assertions.assertEquals;

class PetFeederTest {

    @Test
    void shouldFeedThePetAllTheFood() {

        // Given: We have food recommendations and a pet
        final Animal lassie = new Dog("Lassie");
        final Set<String> foodRecommendations = Set.of(
                "Canned Chicken",
                "Canned Beef",
                "Kibbles and Bits",
                "Bone Marrow"
        );

        // When: We feed the pet without restrictions
        Animal fedPet = new PetFeeder("Holly").feedPet(lassie, foodRecommendations, true);

        // Then: It should have everything in it's stomach
        final Queue<String> stomachContents = fedPet.getStomach();
        final Queue<String> expectedContents = new LinkedList<>(foodRecommendations);

        assertEquals(4, stomachContents.size());
        assertEquals(expectedContents, stomachContents);
    }

    @Test
    void shouldOnlyFeedThePetNonCannedFood() {

        // Given: We have food recommendations and a pet
        final Animal lassie = new Dog("Lassie");
        final Set<String> foodRecommendations = Set.of(
                "Canned Chicken",
                "Canned Beef",
                "Kibbles and Bits",
                "Bone Marrow"
        );

        // When: We feed the pet without restrictions
        Animal fedPet = new PetFeeder("Holly").feedPet(lassie, foodRecommendations, false);

        // Then: It should have everything in it's stomach
        final Set<String> stomachContents = fedPet.getStomach().stream().collect(toUnmodifiableSet());
        final Set<String> expectedContents = Set.of("Kibbles and Bits", "Bone Marrow");

        assertEquals(2, stomachContents.size());
        assertEquals(expectedContents, stomachContents);
    }

    @Test
    void shouldFeedThePetAllTheFoodRefactor() {

        // Given: We have food recommendations and a pet
        final Animal lassie = new Dog("Lassie");
        final Set<String> foodRecommendations = Set.of(
                "Canned Chicken",
                "Canned Beef",
                "Kibbles and Bits",
                "Bone Marrow"
        );

        // When: We feed the pet without restrictions
        // Note how descriptive the method is
        Animal fedPet = new PetFeederRefactor("Holly").feedPetWithCannedFoodAllowed(lassie, foodRecommendations);

        // Then: It should have everything in it's stomach
        final Queue<String> stomachContents = fedPet.getStomach();
        final Queue<String> expectedContents = new LinkedList<>(foodRecommendations);

        assertEquals(4, stomachContents.size());
        assertEquals(expectedContents, stomachContents);
    }

    @Test
    void shouldOnlyFeedThePetNonCannedFoodRefactor() {

        // Given: We have food recommendations and a pet
        final Animal lassie = new Dog("Lassie");
        final Set<String> foodRecommendations = Set.of(
                "Canned Chicken",
                "Canned Beef",
                "Kibbles and Bits",
                "Bone Marrow"
        );

        // When: We feed the pet without restrictions
        // Note how descriptive the method is
        Animal fedPet = new PetFeederRefactor("Holly").feedPetWithoutCannedFoodAllowed(lassie, foodRecommendations);

        // Then: It should have everything in it's stomach
        final Set<String> stomachContents = fedPet.getStomach().stream().collect(toUnmodifiableSet());
        final Set<String> expectedContents = Set.of("Kibbles and Bits", "Bone Marrow");

        assertEquals(2, stomachContents.size());
        assertEquals(expectedContents, stomachContents);
    }

}