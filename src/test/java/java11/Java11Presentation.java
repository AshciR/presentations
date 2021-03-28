package java11;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * God Class being used to demonstrate Java 11 features
 **/
public class Java11Presentation {

    //
    // ---------------------------------- TABLE OF CONTENTS ------------------------------------
    //
    /*
        - Introduction
        - Chapter 1: Added in Java 9
        - Chapter 2: Added in Java 10
        - Chapter 3: Added in Java 11
        - Wrap Up
        - Q/A
     */

    //
    // -------------------- CHAPTER 1: What was new in Java 9 -----------------------
    //

    /*
    1. JShell (Read Eval Print Loop, REPL) was added
    This allows us to quickly try out code bits using JShell.
    To use:
    1. Make sure Java 11 is on your path, you can check by typing "java -version"
    2. Type "jshell" in order to access it
    3. Use the command /exit to exit
     */

    /*
    2. Factory Methods for Immutable List, Set, Map
     */
    @Test
    void mapsBeforeJava9() {

        // This is how we had to initialize and create immutable collections before Java 9
        Map<String, String> dcSuperHeroes = new HashMap<>();
        dcSuperHeroes.put("Clark Kent", "Superman");
        dcSuperHeroes.put("Bruce Wayne", "Batman");
        dcSuperHeroes.put("Diana", "Wonder Woman");

        // HERO'S WILL NEVER DIE AND THEY SHOULD NEVER CHANGE!
        Map<String, String> immutableHeroes = Collections.unmodifiableMap(dcSuperHeroes);

        immutableHeroes.forEach((hiddenIdentity, heroName) -> System.out.println(heroName + " is " + hiddenIdentity));

        // When: We try to change the list,
        // Then: An exception will be thrown
        assertThrows(UnsupportedOperationException.class, () ->
                immutableHeroes.put("Tony Stark", "IronMan") // Wrong universe
        );

    }

    @Test
    void mapAfterJava9() {

        // We can use the Collection classes .of() factory methods
        // HERO'S WILL NEVER DIE AND THEY SHOULD NEVER CHANGE!
        Map<String, String> immutableHeroes = Map.of(
                "Clark Kent", "Superman",
                "Bruce Wayne", "Batman",
                "Diana", "Wonder Woman");

        immutableHeroes.forEach((hiddenIdentity, heroName) -> System.out.println(heroName + " is " + hiddenIdentity));

        // When: We try to change the list,
        // Then: An exception will be thrown
        assertThrows(UnsupportedOperationException.class, () ->
                immutableHeroes.put("Tony Stark", "IronMan") // Wrong universe
        );

    }

    @Test
    void listsAndSetsAfterJava9() {

        List<String> dcVillains = List.of("Joker", "Zod", "Lex Luther");
        Set<String> marvelVillains = Set.of("Thanos", "Ultron", "Venom");

        dcVillains.forEach(villain -> System.out.println(villain));
        System.out.println("---");
        marvelVillains.forEach(villain -> System.out.println(villain));

    }

    /*
    3. Improvements to the Optional class.
    Before, we if had a fallback value for an Optional,
    we would have have do computations on separate lines.
     */
    @Test
    void optionalIfPresentAndOrElseBeforeJava9() {

        // Given: A person might have a middle or not
        Optional<String> maybeMiddleName = Optional.of("Kyle");
        Optional<String> noMiddleName = Optional.empty();

        // When: We try to print the name
        maybeMiddleName.ifPresent(it -> System.out.println(it)); // Kyle will be printed

        noMiddleName.ifPresent(it -> System.out.println(it));    // Nothing will happen

        if (!noMiddleName.isPresent()) {
            System.out.println("NO MIDDLE NAME"); // Our fallback action will occur
        }

    }

    /*
    Java 9 came with a ifPresentOrElse() method.
    If the value, perform a given operation, if its not, do something else.
     */
    @Test
    void mapOptionalAfterJava9() {

        // Given: A person might have a middle or not
        Optional<String> maybeMiddleName = Optional.of("Kyle");
        Optional<String> noMiddleName = Optional.empty();

        // When: We try to print the name
        maybeMiddleName.ifPresentOrElse(it -> System.out.println(it), () -> System.out.println("NO MIDDLE NAME"));
        noMiddleName.ifPresentOrElse(it -> System.out.println(it), () -> System.out.println("NO MIDDLE NAME"));

    }

    //
    // -------------------- CHAPTER 2: What was new in Java 10 -----------------------
    //
    /*
    1. The headliner for Java 10 LocalVariable Type-Inference.
    Until Java 9, we had to mention the type of the local variable explicitly
    and ensure it was compatible with the initializer used to initialize it.
     */
    @Test
    void explicitTypingPriorToJava10() {
        int theAnsToLife = 42; // We had to explicitly type the variable as Integer
        System.out.println("The answer to life the universe and everything is " + theAnsToLife);

        var theAnsToEverything = 42;
        System.out.println("The answer to life the universe and everything is " + theAnsToEverything);

        // Let's look how it removes boilerplate from some of the collections
        var immutableHeroes = Map.of(
                "Clark Kent", "Superman",
                "Bruce Wayne", "Batman",
                "Diana", "Wonder Woman");

        immutableHeroes.forEach((hiddenIdentity, heroName) -> System.out.println(heroName + " is " + hiddenIdentity));
    }

    /*
    How not to use var:
    1. var n; // error: cannot use 'var' on variable without initializer
    2. var emptyList = null; // error: variable initializer is 'null'
    3. public var = "hello"; // error: 'var' is not allowed here
    4. var p = (String s) -> s.length() > 10; // error: lambda expression needs an explicit target-type
     */

    /*
    2. There were some additions to the Unmodifiable Collections
     */
    @Test
    void copyOfCollectionPriorToJava10() {

        // Given We have some persons
        List<Person> people = List.of(
                new Person("John", 30),
                new Person("Mary", 25)
        );

        // When: We create a mutable defensive copy
        List<Person> copy = new ArrayList<>(people);

        System.out.println("Original: " + people);
        System.out.println("Copy: " + copy);
        System.out.println("---");

        // We're able to modify this list
        copy.add(new Person("Bob", 45));

        System.out.println("Original: " + people);
        System.out.println("Copy: " + copy);

    }

    @Test
    void copyOfCollection() {

        // Given We have a collection
        List<Person> people = List.of(
                new Person("John", 30),
                new Person("Mary", 25)
        );

        // When: We create a defensive copy
        List<Person> copy = List.copyOf(people); // The copy is immutable

        System.out.println("Original: " + people);
        System.out.println("Copy: " + copy);
        System.out.println("---");

        // We're unable to modify this list
        assertThrows(UnsupportedOperationException.class, () ->
                copy.add(new Person("Bob", 45))
        );
    }

    /*
    3. Steams can collect to unmodifiableCollections
     */
    @Test
    void mutableCollectionsPriorToJava10() {
        List<String> names = List.of("Bob", "Mary", "George");

        final List<Integer> nameLengths = names
                .stream()
                .map(n -> n.length())
                .collect(Collectors.toList());

        System.out.println(nameLengths);
        System.out.println("---");
        nameLengths.add(999); // Imagine some mischievous code did this...
        System.out.println(nameLengths);
    }

    @Test
    void immutableCollectionsAfterToJava10() {
        List<String> names = List.of("Bob", "Mary", "George");

        final List<Integer> nameLengths = names
                .stream()
                .map(n -> n.length())
                .collect(Collectors.toUnmodifiableList());

        System.out.println(nameLengths);

        // We can't modify the list after the collect operation
        assertThrows(UnsupportedOperationException.class, () -> nameLengths.add(999));
    }

    //
    // -------------------- CHAPTER 3: What was new in Java 11 -----------------------
    //

    /*
    Overall, I'd consider one a quality of life update. Utilities were added to
    ease development effort.
     */

    /*
    1. There were some additions to the String class
    */
    @Test
    void java11StringMethods() {

        // .repeatMethod()
        String song = "Row ".repeat(3) + "your boat gently down the stream";
        assertEquals("Row Row Row your boat gently down the stream", song);

        // .strip() removes all white space characters
        String greeting = "\n \t  Hello \u2005  "; // \u2005 is FOUR-PER-EM SPACE
        assertEquals("Hello", greeting.strip());

        // N.B. We had .trim() before, but .trim() doesn't take unicode into consideration
        assertNotEquals("Hello", greeting.trim()); // Notice the extra space at the end

        // .isBlank()
        assertTrue("".isBlank());
        assertTrue(" ".isBlank());
        assertTrue("\n\t".isBlank());
        assertTrue("\u2005".isBlank());

        // .lines() returns a Stream<String>
        String multilineStr = "This is\n a multiline\n string.";
        Long numberOfLines = multilineStr.lines().count();
        assertEquals(3, numberOfLines);

    }

    /*
    2. Collection interface provides default toArray method.
    */
    @Test
    void convertListToArray(){
        List<Integer> numbers = List.of(1,2,3,4,5);
        Integer[] numArray = numbers.toArray(value -> new Integer[value]);

        System.out.println(numbers);
        System.out.println("---");
        System.out.println(Arrays.toString(numArray));
    }

    /*
    3. Not Predicate method
     */
    @Test
    void notPredicateMethod(){

        // 1..15 inclusive
        final List<Integer> numbers = IntStream.rangeClosed(1, 15)
                .boxed() // We have to box the int into Integers
                .collect(Collectors.toUnmodifiableList());

        // Even number criteria
        final Predicate<Integer> filterEvenNumbers = n -> n % 2 == 0;

        // Find the even numbers
        List<Integer> evenNumbers = numbers
                .stream()
                .filter(filterEvenNumbers)
                .collect(Collectors.toUnmodifiableList());

        System.out.println(evenNumbers);
        System.out.println("---");

        // Find the odd numbers
        List<Integer> oddNumbers = numbers
                .stream()
                .filter(Predicate.not(filterEvenNumbers)) // Opposite of even number criteria
                .collect(Collectors.toUnmodifiableList());

        System.out.println(oddNumbers);

    }

    //
    // -------------------- Wrap Up -----------------------
    //
    /*
    We've see some of the new feature we can use to reduce the code we write
    in Java 11.

    - JShell (REPL) for testing code blocks
    - Factory methods for creating and copying collections
    - Added methods to the Optional class
    - Local variable type Inference with the var keyword
    - New String methods

    Please not that this list isn't extensive, there are several other additions,
    ranging from performance improvements, garbage collection, HTTP classes, etc.
    I recommend that you research the others.
     */

    //
    // -------------------- References -----------------------
    //
    /*

    - https://www.baeldung.com/new-java-9
    - https://www.baeldung.com/java-10-overview
    - https://www.baeldung.com/java-11-new-features
    - https://mkyong.com/java/what-is-new-in-java-11/
    - https://www.baeldung.com/java-10-local-variable-type-inference
    - https://www.baeldung.com/java-11-string-api
     */

}
