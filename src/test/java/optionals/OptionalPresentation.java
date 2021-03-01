package optionals;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;

/**
God Class being used to demonstrate how to use Optionals and their best practices
 **/
class OptionalPresentation {

    //
    // ---------------------------------- MENU ------------------------------------
    //
    /*
        - What are Optionals in Java
        - Functional programming overview
        - How to use Optionals
        - Best Practices
        - Wrap Up
        - Q/A
     */

    //
    // ------------------------------ INTRODUCTION --------------------------------
    //

    /*
    What are Optionals?
    "A class is to provide a type-level solution for representing optional values
    instead of null references."

    Optionals were introduced in Java 8, and there's Java's attempt at null type safety.
    */
    @Test
    void throwsANullPointException() {
        String someValue = null;
        someValue.toUpperCase();
    }

    //
    // ----------------------------- APPETIZERS --------------------------------
    //

    /*
    Appetizer #1: Anonymous functions, aka:
    Lambda functions (by the Math guys),
    Callback functions (by JS guys)
    Closures (By the Groovy guys, sidebar, I disagree with the naming, but that's a diff discussion)

    What is a anonymous function? In the scope of presentation, it's a function/method
    that is declared without a name.
     */
    @Test
    void usesARegularFunction() {
        // This function/method explicitly has a name
        String greeting = sayHelloToMe("Chandra Pippen");
        System.out.println(greeting);

    }

    @Test
    void usesAnonymousFunction() {
        // N.B. the expression doesn't have a
        Function<String, String> annon = (name) -> "Hi! " + "My name is " + name;

        String greeting = annon.apply("Chandra Pippen");
        System.out.println(greeting);
    }

    /*
    Ok, I'll admit the above example isn't the strongest use case of how
    powerful and useful, lambda functions are, but trust me, we'll see more
    throughout the presentation.

    Before we move on, let me make note of 4 of the most common lambda functions
    that we'll be using in this presentation.

    1.  Functions that don't have an input but return a value: () -> Some value      : Supplier
    2.  Functions that have an input but don't return a value: (input) -> void       : Consumer
    3.  Functions that have an input and return a value: (input) -> output           : Function
    3.1 Functions that have an input and return a boolean value: (input) -> boolean  : Predicate
     */
    @Test
    void javaFunctionalInterfaces() {

        // 1.
        Supplier<String> supplier = () -> "Hello World";

        // 2.
        Consumer<String> consumer = (message) -> System.out.println(message);

        // 3.
        Function<String, String> makeUpperCase = (word) -> word.toUpperCase();

        // 3.1
        Predicate<String> enjoysCoffee = (person) -> "Developer".equalsIgnoreCase(person) == true;
    }

   /*
    Appetizer #2: Higher order function/methods:

    Functions that can take a function as an argument.
    Prior to Java 8, we didn't have this ability.

    Most of the popular languages these allow for this, and it enables us to
    write more generic functions.
    That's good news b/c more generic functions = more reusable
    -> less code
    -> less bugs
    -> less maintenance

    There are 3 high order functions that are popular and the newer languages
    have native support for them. The names might vary depending on the language,
    but the concepts are the same. They are:
    1. Filter
    2. Map
    3. Reduce
     */

    /*
    filter() extracts values from a collection that match a condition (Predicate function).
    Groovy guys: .find { }
    */
    @Test
    void filterExample() {
        List<Integer> numbers = listOfNumbers(10);
        System.out.println("Before filter: " + numbers);

        List<Integer> evenNumbers = numbers.stream()
                .filter(it -> it % 2 == 0) // This is the predicate lambda function
                .collect(Collectors.toList());

        System.out.println("After filter:  " + evenNumbers);
    }

    /*
    map() transforms values in a collection from one form into another. Takes a Function lambda
    Groovy guys: .collect { } Horrible name in my opinion
    */
    @Test
    void mapExample() {
        List<Integer> numbers = listOfNumbers(10);
        System.out.println("Before map: " + numbers);

        List<Integer> doubledNumbers = numbers.stream()
                .map(it -> it * 2) // f(x): x * 2
                .collect(Collectors.toList());

        System.out.println("After map:  " + doubledNumbers);
    }

    /*
    reduce() collapses a collection into 1 value. Takes a BiFunction lambda
    Groovy guys: .inject { } // Another horrible name, I know!
    */
    @Test
    void reduceExample() {
        List<Integer> numbers = listOfNumbers(5);
        System.out.println("Before reduce: " + numbers);

        Integer sum = numbers.stream()
                .reduce(0, (accruingTotal, next) -> accruingTotal + next); // f(x,y): x + y
        // 0
        // 0 + 1 = 1
        // 1 + 2 = 3
        // 3 + 3 = 6
        // ...

        System.out.println("After reduce:  " + sum);
    }

    //
    // ----------------------------- MAIN COURSE --------------------------------
    //

    //
    // -------------------- DISH 1: Operating on Optionals -----------------------
    //

    /*
    Let's start with an example on how life looked prior to Java 8.
     */
    @Test
    void printSuccessor() {

        Donor elonMusk = getDonorWithoutSuccessor();

        // This is gonna be null b/c Elon doesn't have a successor
        System.out.println(elonMusk.getSuccessor());
    }

    @Test
    void printSuccessorSafely() {

        Donor elonMusk = getDonorWithoutSuccessor();

        if (null != elonMusk.getSuccessor()) {
            System.out.println(elonMusk.getSuccessor());
        }

    }

    /*
    The above code works, and it's safe.

    We had to add nesting if blocks to defensively program around it.
    But it's boiler plate code just to get a simple accessor value.

    Now let's look at how Optionals solve this issue for us
     */
    @Test
    void printSuccessorUsingOptional() {

        Donor elonMusk = getDonorWithoutSuccessor();

        // First wrap the value in an Optional.
        // Optional provides static methods to accomplish this.
        // use of(T) -> Optional<T>
        // or use ofNullable(T) -> Optional<T>
        final Optional<Successor> successor = Optional.ofNullable(elonMusk.getSuccessor());

        // ifPresent takes a lambda function as an argument.
        // Specifically a Consumer. (value) -> void
        successor.ifPresent(it -> System.out.println(it));
    }

    /*
    The advantage of the above implementation is that it removes the boilerplate
    and is more declarative. Sadly in this example, we had to wrap the successor value
    but imagine if the API returned a wrapped value for us, this it would have been a
    one-liner.
     */

    /*
    Let's circle back to a previous example,
    but let's modify it set a default successor if Elon doesn't have one.
     */

    /*
    Null check implementation
     */
    @Test
    void printSuccessorWithADefaultSuccessor() {

        Donor elonMusk = getDonorWithoutSuccessor();

        if (null != elonMusk.getSuccessor()) {
            System.out.println(elonMusk.getSuccessor());
        }

        Successor defaultSuccessor = new Successor("Richard", "Walker");
        System.out.println(defaultSuccessor);
    }

    /*
    Optional implementation 1: Using orElse()
     */
    @Test
    void printSuccessorUsingOptionalWithADefaultSuccessor() {

        Donor elonMusk = getDonorWithoutSuccessor();

        // The orElse() method allows us to provide a fallback value
        final Successor successor = Optional
                .ofNullable(elonMusk.getSuccessor())
                .orElse(new Successor("Richard", "Walker"));

        // Even better than before :)
        System.out.println(successor);
    }

    /*
    Optional implementation 2 Using orElseGet()
     */
    @Test
    void printSuccessorNameUsingOptionalWithADefaultSuccessorUsingOrElseGet() {

        Donor elonMusk = getDonorWithoutSuccessor();

        // The orElseGet() method allows us to provide a fallback value
        // N.B instead of taking a Successor, it takes a lambda function (Supplier<Successor>)
        // It's a subtle difference which we'll discuss.
        final Successor successor = Optional
                .ofNullable(elonMusk.getSuccessor())
                .orElseGet(() -> new Successor("Richard", "Walker")); // () -> value

        // Even better than before :)
        System.out.println(successor);
    }

    /*
    The difference between orElse() and orElseGet()
     */
    @Test
    void differenceBetweenOrElseAndOrElseGetPart1() {

        // N.B. he doesn't have a successor right now
        Donor elonMusk = getDonorWithoutSuccessor();

        System.out.println("Using orElse()");
        Successor successor = Optional
                .ofNullable(elonMusk.getSuccessor())
                .orElse(getLuckyPerson());

        System.out.println(successor);

        System.out.println("----------------");

        System.out.println("Using orElseGet()");
        successor = Optional
                .ofNullable(elonMusk.getSuccessor())
                .orElseGet(() -> getLuckyPerson()); // () -> value

        System.out.println(successor);
    }

    @Test
    void differenceBetweenOrElseAndOrElseGetPart2() {

        // N.B. Now look what happens when he does have one
        Donor elonMusk = getDonorWithSuccessor();

        System.out.println("Using orElse()");
        Successor successor = Optional
                .ofNullable(elonMusk.getSuccessor())
                .orElse(getLuckyPerson());

        System.out.println(successor);

        System.out.println("----------------");

        System.out.println("Using orElseGet()");
        successor = Optional
                .ofNullable(elonMusk.getSuccessor())
                .orElseGet(() -> getLuckyPerson()); // () -> value

        System.out.println(successor);
    }

    /*
    Imagine if getLuckyPerson() was actually an expensive operation?
    For performance reasons we'd want to avoid the call if it's not needed.
    */

    /* BEST PRACTICE: Consider using orElseGet() if the default value is expensive to create */


    /*
    Sometimes we can't provide an default value, and we have to throw an exception.
    There's a method for that as well
     */
    @Test
    void printSuccessorUsingOptionalWithException() {

        Donor elonMusk = getDonorWithoutSuccessor();

        // The orElseThrow() method take a lambda: () -> value
        // Pop Quiz, which Functional interface does Java use for this?
        final Successor successor = Optional
                .ofNullable(elonMusk.getSuccessor())
                .orElseThrow(() -> new RuntimeException("ERROR, DOES NOT COMPUTE"));

        // Bonus: JDK 10 added an overloaded orElseThrow() that takes no arguments
        // Will throw a NoSuchElementException
        final Successor successor2 = Optional
                .ofNullable(elonMusk.getSuccessor())
                .orElseThrow();

        System.out.println(successor);
        System.out.println(successor2);
    }

    //
    // ----------------------- DISH 2: Accessing values from Optionals -----------------------
    //

    /*
    Ok, we know how to create optionals, but how do we access the values inside of them?
    Let's take a look at the implementation below
     */
    @Test
    void assertThatElonGivesHisWealthToHisChild() {
        Donor elonMusk = getDonorWithSuccessor();

        // Baby Musk should actually get Elon's fortune
        Successor rightfulHeir = new Successor("X Æ A-12", "Musk");

        // Remember, this is how we wrap a potentially null value
        Optional<Successor> optionalSuccessor = Optional.ofNullable(elonMusk.getSuccessor());

        // And we know that Elon will have a successor in this case
        Successor successor = optionalSuccessor.get();

        // Then make sure that we have the correct successor
        System.out.println(successor);
        assertEquals(rightfulHeir, successor);

    }

    /*
    Let's take a look on what will happen if he didn't decide to leave his money behind to his baby
     */
    @Test
    void shouldFailBecauseElonDidNotAssignHisWealth() {

        // He forgot to update his will
        Donor elonMusk = getDonorWithoutSuccessor();

        // Baby Musk should actually get Elon's fortune
        Successor rightfulHeir = new Successor("X Æ A-12", "Musk");

        // Remember, this is how we wrap a potentially null value
        Optional<Successor> optionalSuccessor = Optional.ofNullable(elonMusk.getSuccessor());

        // And we know that Elon does not have a successor in this case
        Successor successor = optionalSuccessor.get();

        // Then should fail b/c he didn't assign his successor
        assertNotEquals(rightfulHeir, successor);
    }

    /*
    Whoops... we got a NoSuchElementException.
    So we traded a NPE for a NoSuchElementException.
    This defeats the purpose of us using an Optional in the first place.
     */
    @Test
    void shouldFailBecauseElonDidNotAssignHisWealthInASafeWay() {

        Donor elonMusk = getDonorWithoutSuccessor();
        Successor rightfulHeir = new Successor("X Æ A-12", "Musk");

        // Remember, this is how we wrap a potentially null value
        // And we know for a fact that it will be null in this case
        Optional<Successor> optionalSuccessor = Optional.ofNullable(elonMusk.getSuccessor());

        // Safely check if the successor is the right one
        // Remember he didn't assign a successor so this should be false
        assertFalse(
                optionalSuccessor
                        .filter(it -> it.equals(rightfulHeir))
                        .isPresent() // isPresent returns a boolean
        );

    }

    /*
    We see that it's the safer way to work with the Optional values.

    The above example is somewhat trivial, so let's take a look at
    a more involved scenario.
     */

    @Test
    void assertThatSuccessorGetsTaxWriteOff() {

        // Get's the tax break
        assertTrue(willGetATaxWriteOff(
                new Successor("Chris", "Jones", new BigDecimal(1000)))
        );

        // Has too much money
        assertFalse(willGetATaxWriteOff(
                new Successor("James", "Johnson", new BigDecimal(11_000)))
        );

        // Just a tad bit shy
        assertFalse(willGetATaxWriteOff(
                new Successor("Maddie", "Thompson", new BigDecimal(999)))
        );

        // Is hiding his income
        assertFalse(willGetATaxWriteOff(
                new Successor("Dewayne", "Walker", null))
        );
    }

    /*
    In a different reality, you only qualify for a tax write off
    if you have between $1000 - $10,000 (inclusive)
     */
    private boolean willGetATaxWriteOff(Successor successor) {

        final BigDecimal lowerBound = new BigDecimal(1000);
        final BigDecimal upperBound = new BigDecimal(10000);
        boolean getWriteOff = false;

        // Boilerplate safety checks
        if (null != successor && null != successor.getNetWorth()) {

            BigDecimal netWorth = successor.getNetWorth();

            // Ugh... How we have to do comparison on BigDecimal in Java...
            boolean lowerBoundComparison = netWorth.compareTo(lowerBound) >= 0;
            boolean upperBoundComparison = netWorth.compareTo(upperBound) <= 0;

            // The business logic we actually care about
            if (lowerBoundComparison && upperBoundComparison) {
                getWriteOff = true;
            }

        }

        return getWriteOff;
    }

    /*
    Let's rewrite this using Optional.
     */
    private boolean willGetATaxWriteOffOptionalImpl(Successor successor) {

        final BigDecimal lowerBound = new BigDecimal(1000);
        final BigDecimal upperBound = new BigDecimal(10000);

        // 1st, we'll wrap the potentially null value
        return Optional.ofNullable(successor)
                .map(it -> it.getNetWorth()) // Function lambda
                .filter(netWorth -> netWorth.compareTo(lowerBound) >= 0) // Predicate lambda
                .filter(netWorth -> netWorth.compareTo(upperBound) <= 0) // Predicate lambda
                .isPresent();

    }

    /*
    We used a new method, .map().
    map() takes a Function lambda, and transforms our wrapped value from one form into another.

    In the example above we went from Successor to a BigDecimal.

    We see that the boilerplate has been removed. Only thing left back is the business
    logic we care for.
     */

    /*
    Make sure we re-run the tests to verify that we didn't regress functionality
     */
    @Test
    void assertThatSuccessorIsAMillionaireUsingOptionalImpl() {

        // Get's the tax break
        assertTrue(willGetATaxWriteOffOptionalImpl(
                new Successor("Chris", "Jones", new BigDecimal(1000)))
        );

        // Has too much money
        assertFalse(willGetATaxWriteOffOptionalImpl(
                new Successor("James", "Johnson", new BigDecimal(11_000)))
        );

        // Just a tad bit shy
        assertFalse(willGetATaxWriteOffOptionalImpl(
                new Successor("Maddie", "Thompson", new BigDecimal(999)))
        );

        // Is hiding his income
        assertFalse(willGetATaxWriteOffOptionalImpl(
                new Successor("Dewayne", "Walker", null))
        );
    }

    //
    // ---------------------- DISH 3: Bad Practices for using Optionals ---------------------
    //

    /*
    We've gone over the popular use cases for Optionals, and we've seen how to use them.
    Let's briefly talk about how about how NOT to use them
     */

    /*
    BAD PRACTICE: Don't use Optional as input params
     */
    private List<Successor> findSuccessors(List<Successor> successors,
            String firstName,
            Optional<BigDecimal> netWorth) { // Not meant to be used like this

        return successors.stream()
                .filter(s -> s.getFirstName().equals(firstName))
                .filter(s -> s.getNetWorth().compareTo(netWorth.orElse(BigDecimal.ZERO)) == 0)
                .collect(Collectors.toList());

    }

    @Test
    void shouldFindSuccessor() {

        // Fun fact: Java 11 added factory method .of() to the Collections
        List<Successor> successors = List.of(
                new Successor("Zach", "Auten", new BigDecimal(100)),
                new Successor("Daniel", "Franks", new BigDecimal(200)),
                new Successor("Sawyer", "Harris", new BigDecimal(300))
        );

        // Client still can provide a null value... Throws a NPE
        findSuccessors(successors, "Zach", null);

    }

    /*
   BEST PRACTICE: Optionals are meant to be used as return types from methods
   where a value may exist.
    */
    private Optional<String> isWordInList(String word) {
        final List<String> words = List.of("FOO", "BAR", "BAZ");

        return words.stream()
                .filter(it -> it.equalsIgnoreCase(word))
                .findAny();
    }

    @Test
    void barExistsInList() {
        assertTrue(
                isWordInList("bar").isPresent()
        );
    }

    /*
    BAD PRACTICE: Don't use Optional as field variables
     */
    private void optionalAsFieldVariableBadPractice() {
        final Optional<LocalDate> currentTime = Optional.of(LocalDate.now());
        new Grant(BigDecimal.TEN, currentTime); // Let's look at the Grant class
    }

    /*
    BAD PRACTICE: Don't use Optional to convert into JSON
     */
    private void optionalForJSONSerialization() {
        final Optional<LocalDate> currentTime = Optional.of(LocalDate.now());
        Grant grant = new Grant(BigDecimal.TEN, currentTime);

        grant.getScheduleDate(); // Take a look in this method
    }

    /*
    BAD PRACTICE: Don't use Optional on JPA entities
    Please note I didn't add JPA to this project

    Example:


    // Some JPA Entity
    @Entity
    public class UserOptionalField implements Serializable {
        @Id
        private long userId;

        private Optional<String> firstName;

        // ... getters and setters
    }

    // The calling code
    UserOptionalField user = new UserOptionalField();
    user.setUserId(1l);
    user.setFirstName(Optional.of("Richie"));
    entityManager.persist(user);

    // You'd see something similar to the following stack trace
    Caused by: javax.persistence.PersistenceException: [PersistenceUnit: io.richie.optionalReturnType] Unable to build Hibernate SessionFactory
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.persistenceException(EntityManagerFactoryBuilderImpl.java:1015)
	at org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl.build(EntityManagerFactoryBuilderImpl.java:941)
	at org.hibernate.jpa.HibernatePersistenceProvider.createEntityManagerFactory(HibernatePersistenceProvider.java:56)
	at javax.persistence.Persistence.createEntityManagerFactory(Persistence.java:79)
	at javax.persistence.Persistence.createEntityManagerFactory(Persistence.java:54)

    Caused by: org.hibernate.MappingException:
    Could not determine type for: java.util.Optional,
    at table: UserOptionalField, for columns: [org.hibernate.mapping.Column(firstName)]

     */

    //
    // --------------------------------------- DESSERT ---------------------------------------
    //

    /*
    Summary:
    - We reviewed lambdas and higher-order functions
    - Introduced Optionals
    - Use Optionals to operate on present values - isPresent()
    - Use Optionals to return default values - orElse() and orElseGet()
    - Use Optionals to throw exceptions - orElseThrow()
    - Safely access values inside Optionals - filter()
    - Transforms values inside Optionals - map()
    - Discussed when and when not to use Optionals
     */

    //
    // -------------------------------- COMPLIMENTS TO THE CHEF --------------------------------
    //

    /*
     https://www.baeldung.com/java-optional
     https://www.baeldung.com/java-optional-return
     */

    //
    // --------------------------------------- LEFTOVERS ---------------------------------------
    //

    private String sayHelloToMe(String name) {
        return "Hi! " + "My name is " + name;
    }

    private List<Integer> listOfNumbers(int endInclusive) {
        return IntStream.rangeClosed(1, endInclusive)
                .boxed()
                .collect(Collectors.toList());
    }

    private Donor getDonorWithoutSuccessor() {
        return new Donor(
                "Elon",
                "Musk",
                true,
                new BigDecimal(10000),
                null);
    }

    private Donor getDonorWithSuccessor() {
        return new Donor(
                "Elon",
                "Musk",
                true,
                new BigDecimal(10000),
                new Successor("X Æ A-12", "Musk")
        );
    }

    private Successor getLuckyPerson() {
        System.out.println("Person being contacted with the lucky news... approx time 2 months...");
        return new Successor("Madison", "Thompson");
    }
}
