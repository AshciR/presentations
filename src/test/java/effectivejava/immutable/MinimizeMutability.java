package effectivejava.immutable;

import effectivejava.immuatable.ImmutablePerson;
import effectivejava.immuatable.MutablePerson;
import org.junit.jupiter.api.Test;

import java.util.Set;
import java.util.stream.Collectors;

import static effectivejava.immuatable.ImmutablePerson.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Item: 17 Minimize Mutability
 */
public class MinimizeMutability {

    /*
    Item 17: Minimize Mutability

    An immutable class is class whose instances can't be modified.
    Whenever the instance is created the values won't change
    for the lifetime of the object.

    Java has a number of classes that follow this principle already.
    E.g. String, BigDecimal, Integer etc...

    Why should we aim to minimize the mutability? Immutable classes
    are easier to design, implement, maintain, and test.
    */

    /*
    Advantage 1: Immutable objects are "simple"

    An immutable object can only be in one state, the state that it was
    created in. The programmers or clients using the class don't have to
    keep track of potential complexities which may result in bugs.
     */

    @Test
    void mutablePerson() {

        // Humble young boy in season 1
        MutablePerson jonSnow = new MutablePerson("Jon", "Snow", "Bastard");
        System.out.println(jonSnow);
        assertEquals("Bastard", jonSnow.getTitle());

        // After he went to the Night's Watch
        jonSnow.setTitle("Lord Commander");
        System.out.println(jonSnow);
        assertEquals("Lord Commander", jonSnow.getTitle());

        // His title (state) changed many times throughout the show, and we forgot to update it...
        System.out.println(jonSnow);
        assertEquals("King of the North", jonSnow.getTitle());

    }

    @Test
    void immutablePerson() {

        // Humble young boy in season 1
        ImmutablePerson jonSnow = new ImmutablePerson("Jon", "Snow", "Bastard");
        System.out.println(jonSnow);
        assertEquals("Bastard", jonSnow.getTitle());

        // Whenever Jon gets promoted, we'll create a new object to represent him
        // This is kinda of reminiscent of the spread operator from Javascript
        // ...jonSnow, title: "Lord Commander
        ImmutablePerson lordCommander = jonSnow.changeTitle("Lord Commander");
        System.out.println(lordCommander);
        assertEquals("Lord Commander", lordCommander.getTitle());

        // Less likely to forget the promotion this time, b/c we're not changing the
        // state of Jon
        ImmutablePerson kingOfTheNorth = lordCommander.changeTitle("King of the North");
        System.out.println(kingOfTheNorth);
        assertEquals("King of the North", kingOfTheNorth.getTitle());

    }

    /*
    Advantage 2: Immutable objects are inherently thread safe

    They don't require synchronization because multiple threads can't change
    or corrupt their values.
     */
    @Test
    void threadsOnMutableObjectsPt1() throws InterruptedException {

        // The same lovable bastard
        MutablePerson jonSnow = new MutablePerson("Jon", "Snow", "Bastard");

        // The poor guy was always multitasking
        Thread thread1 = new Thread(jonSnow);
        Thread.sleep(2000);
        thread1.start();

        Thread thread2 = new Thread(jonSnow);
        Thread.sleep(2000);
        thread2.start();

        Thread thread3 = new Thread(jonSnow);
        Thread.sleep(2000);
        thread3.start();

        Thread.sleep(2000);
    }

    /*
    The above example isn't too bad. Jon's titled stayed the same
    every throughout the threads.

    Let's see what happens while his title changes.
     */

    @Test
    void threadsOnMutableObjectsPt2() throws InterruptedException {

        // The same lovable bastard
        MutablePerson jonSnow = new MutablePerson("Jon", "Snow", "Bastard");

        // The poor guy was always multitasking
        Thread thread1 = new Thread(jonSnow);
        Thread.sleep(2000);
        thread1.start();

        Thread thread2 = new Thread(jonSnow);
        jonSnow.setTitle("Lord Commander"); // Promotion
        Thread.sleep(2000);
        thread2.start();

        Thread thread3 = new Thread(jonSnow);
        jonSnow.setTitle("King of the North"); // Promotion
        Thread.sleep(2000);
        thread3.start();

        Thread.sleep(2000);
    }

    /*
    Despite us slowing down the execution, the titles still did not appear as expected...
     */

    /*
    Let's see how the similar code works with the immutable person.
     */
    @Test
    void threadsOnImmutableObjects() throws InterruptedException {

        // The same lovable bastard
        ImmutablePerson jonSnow = new ImmutablePerson("Jon", "Snow", "Bastard");

        // The poor guy was always multitasking
        Thread thread1 = new Thread(jonSnow);
        Thread.sleep(2000);
        thread1.start();

        ImmutablePerson lordCommander = jonSnow.changeTitle("Lord Commander");
        Thread thread2 = new Thread(lordCommander);
        Thread.sleep(2000);
        thread2.start();

        ImmutablePerson kingOfTheNorth = jonSnow.changeTitle("King of the North");
        Thread thread3 = new Thread(kingOfTheNorth);
        Thread.sleep(2000);
        thread3.start();

        Thread.sleep(2000);
    }

    /*
    Ah. In this case, we got our expected values. Yay. Mark, the above
    examples were a rudimentary attempt at multi-threaded processing.
    But from the limited example, we see that additional work
    would be needed to make the class thread safe.
     */

    /*
    Advantage 3: Immutable objects can be shared freely.

    Because their values don't change, cached versions of
    popular values can be created as constants and encouraged
    to use where possible.

    E.g.: Collections.emptyList() returns the same object
    no matter where it's called.

     */
    @Test
    void sharedImmutableObject() {

        // We realize that we've been reusing
        // Jon Snow, Lord Commander, and King of the North.
        // Intuition tells us that we could create constants
        // for those objects.
        System.out.println(JON_SNOW_BASTARD);
        assertEquals("Bastard", JON_SNOW_BASTARD.getTitle());

        System.out.println(LORD_COMMANDER_SNOW);
        assertEquals("Lord Commander", LORD_COMMANDER_SNOW.getTitle());

        System.out.println(KING_OF_THE_NORTH);
        assertEquals("King of the North", KING_OF_THE_NORTH.getTitle());

    }

    /*
    Advantage 4: Safer use with Stream APIs.

    When you read the Javadocs for the stream operations.
    i.e. .filter() and .map(), it states the following:

    "Stream pipeline results may be NONDETERMINISTIC or INCORRECT
    if the behavioral parameters to the stream operations are STATEFUL."

    So we need to cognizant of what objects and lambdas we use when
    we're working with streams. As we showed earlier, when objects
    are immutable, they are inherently stateless, thus making them
    the perfect candidates for stream operations.

     */
    @Test
    void promoteEngineers() {

        // Given: We have a set of junior engineers
        Set<ImmutablePerson> juniorEngineers = Set.of(
                new ImmutablePerson("Angela", "Anderson", "Junior Engineer"),
                new ImmutablePerson("Bob", "Barker", "Junior Engineer"),
                new ImmutablePerson("Carl", "Comely", "Junior Engineer")
        );

        // When: We promote them for their hard work
        Set<ImmutablePerson> seniorEngineers = juniorEngineers.stream()
                .map(engineer -> engineer.changeTitle("Senior Engineer"))
                .collect(Collectors.toUnmodifiableSet()); // N.B. I like using immutable collections as well

        // Then: Their titles are updated
        seniorEngineers.forEach(
                engineer -> assertEquals("Senior Engineer", engineer.getTitle())
        );

    }

    /*
    Disadvantage: They require a separate object for each distinct values.
    If the it's costly to create the object, this can could cause performance issues.

    The main to cope with this is by creating a package-private companion class
    that encapsulates the mutation.

    When we say "Minimize Mutability" what we mean is
    "Minimize Mutability (that your clients can see or access)".

    From the user's point of view, your APIs, classes, packages should
    seem immutable to them. But it's ok for you have internal functions (when needed)
    that use mutation to help you calculate results.
     */

    /*
    Summary:
    1. Classes should be immutable unless there's a very good reason to make them mutable
    2. If a class cannot be made immutable, limit the mutability as much as possible
    3. Constructors should create fully initialized objects with all
    of their invariants established

    Invariant, fancy term for a condition that will always be true
    Example. 1 + 1 will always be equal to 2
    or
    You can't escape death or taxes
     */

    /*
    Ok, now that we've seen the use of immutable classes/objects.
    Let's learn how to define them in Java.

    There are 5 rules:
    1. Don't provide methods that modify the object's state (mutators) i.e. Setter methods
    2. Make all fields final
    3. Make all fields private
    4. Ensure the class can't be extended by making the class final
    5. Ensure exclusive access to fields that can be mutated. Example .changeTitle() method

    You can take a look at the ImmutablePerson class to see the implementation
     */

    /*
    Lastly, an alternative to help ensure rule 4, Ensure the class can't be extended,
    is by preventing instantiation by making the constructor private,
    and using static factory methods.
    See the presentation about Static Factory methods:
    src/test/java/effectivejava/staticfactories/StaticFactoryMethods.java

    Example:
    public class ImmutablePerson  {

    // Note that the fields are final
    private final String firstName;
    private final String lastName;
    private final String title;

    // N.B. The constructor is now private
    private ImmutablePerson(String firstName, String lastName, String title) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
    }

    // This is a static factory method
    public static ImmutablePerson valueOf(String firstName, String lastName, String title){
        return new ImmutablePerson(this.firstName, this.lastName, this.title);
    }
     */

    /*
    References:
    Effective Java: https://www.oreilly.com/library/view/effective-java/9780134686097/
     */

}
