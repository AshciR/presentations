package effectivejava.staticfactories;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.math.BigInteger;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Item 1: Consider static factory methods instead of constructors
 */
public class StaticFactoryMethods {

    /*
    Item 1: Consider static factory methods instead of constructors

    The traditional way for obtain an instance of an object
    is to use the objects constructor. But we should know
    that there are other techniques to create objects.
    One such technique is using static factory methods.

    Static factory methods are public static methods
    that return an instance of a class. Not to be
    confused with the Factory Method pattern from Design
    Patterns (Gang of Four).

    See the example below.
     */
    @Test
    void getMovies() {

        Movie gRatedMovie = Movie.getGRatedMovie();
        Movie rRatedMovie = Movie.getRRatedMovie();

        System.out.println(gRatedMovie);
        System.out.println(rRatedMovie);

    }

    /*
    What are the advantages and disadvantages?
     */

    /*
    Advantage 1: Unlike constructors, they have names.

    Java (without tooling) doesn't have support for named
    parameters.

    If there are constructors that have multiple parameters (especially with the same type)
    it can cause simple errors to be introduced.
     */
    @Test
    void advantage1OfFactoryMethods() {
        // From reading this constructor, would you know what it does?
        BigInteger whatDoesThisDo = new BigInteger(4, 2, new Random());

        // What about this static method?
        BigInteger maybeAPrimeNumber = BigInteger.probablePrime(4, new Random());

        System.out.println(whatDoesThisDo);
        System.out.println(maybeAPrimeNumber);

    }

    /*
    Advantage 2: They don't have to create a new instance every time.

    Immutable classes can return pre-constructed instances of objects.
    This can give us performance benefits.
     */
    @Test
    void advantage2OfFactoryMethods() {

        // If you look into the implementation
        // you'll see that an empty list is a constant field.
        List<String> emptyList = Collections.emptyList();

    }

    /*
    Advantage 3: Can return objects of any subtype of their return type

    Allows APIs to return objects without making their classes public.
    By hiding the implementation classes, we can create
    to interface-based frameworks/libraries/packages.

    This a good design practice, because we should be coding
    to interfaces and not implementations in order to make our systems as decoupled as possible.
     */
    @Test
    void advantage3OfFactoryMethods() {

        // From an above example, we saw the Movie class.
        // If you look at the scope of Movie, you'll realize
        // that it's package-private (only visible within the
        // effectivejava package).

        // If wanted to make the effectivejava package an exportable library,
        // we would not want users of the library to get access to the
        // Movie implementation.
        // We provide the users a Watchable interface that they can use.
        Watchable gRatedMovie = Watchable.getGRatedMovie();
        Watchable pgRatedMovie = Watchable.getPGRatedMovie();
        Watchable rRatedMovie = Watchable.getRRatedMovie();

        List<Watchable> movies = List.of(gRatedMovie, pgRatedMovie, rRatedMovie);
        movies.forEach(it -> System.out.println(it.watch()));

    }

    /*
    Advantage 4: Can return objects can vary call to call depending on the input argument(s).

    We can hide the implementation details behind the object's construction.
    The client only cares that object fulfills it's behavior.

    Reminiscent of the Strategy pattern.
     */
    @Test
    void advantage4OfFactoryMethods() {

        int availableFreeTimeInMins = 20;
        Watchable somethingToWatch = Watchable.getMeSomethingToWatch(availableFreeTimeInMins);
        System.out.println(somethingToWatch.watch());

        // We realize that we got more time b/c we finished the sprint work early
        availableFreeTimeInMins = 60;
        somethingToWatch = Watchable.getMeSomethingToWatch(availableFreeTimeInMins);
        System.out.println(somethingToWatch.watch());

    }

    /*
    Disadvantage: Can be hard for programmers to find.

    They don't stand out in the API documentation how constructors do.
    Meaning, that you'd have to either know beforehand it exists, or
    read the Javadoc methods and see if one exists that fits your needs.

    Fortunately, there is a convention which you can use to help you identify
    these methods. Keep an eye out for methods with names similar to the ones below
     */
    @Test
    void identifyStaticFactoryMethods() {

        // from - type-conversion
        LocalDate now = LocalDate.from(ZonedDateTime.now());
        System.out.println(now);

        // of - aggregation method that takes multiple parameters and returns an object
        LocalDate aprilFoolsDay = LocalDate.of(2021, 4, 1);
        System.out.println(aprilFoolsDay);

        // valueOf - Alternative to from and of
        BigInteger keepingItReal = BigInteger.valueOf(100);
        System.out.println(keepingItReal);

        // newInstance -- creates a new instance of an object for you
        // Please don't ever do this. An angel loses its wings every time...
        Object strArr = Array.newInstance(String.class, 10);
        System.out.println(strArr);

        // getType -- returns a specific data type for you
        Watchable tvShow = Watchable.getTvShow();
        Watchable movie = Watchable.getPGRatedMovie();
        System.out.println(tvShow);
        System.out.println(movie);

    }

    // --- References ---
    // https://www.oreilly.com/library/view/effective-java/9780134686097/

}
