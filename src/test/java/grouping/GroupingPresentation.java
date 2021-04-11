package grouping;

import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static grouping.Dish.determineDishCaloricLevel;
import static java.util.stream.Collectors.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * God Class being used to demonstrate functional grouping in Java
 */
public class GroupingPresentation {


    //
    // ---------------------------------- Menu ------------------------------------
    //
    /*
        - Hors d'oeuvres: Taking a look on an imperative solution
        - Main Course: Grouping
        - Dessert: Partitioning
        - Q/A
     */

    //
    // ----------------------------------- Introduction -------------------------------------
    //
    /*
    What do I mean by grouping in this context? Similar to the group by database operation,
    grouping means categorizing a collection by some trait. Example:

    [Banana, Apple, Lettuce, Carrot]

    If I were to group/categorize these foods by their type,
    it would look like:

    Fruits: [Banana, Apple]
    Vegetables: [Lettuce, Carrot]

    */

    //
    // ----------------------------------- Hors d'oeuvres -------------------------------------
    //

    /*
    Before we begin, let's introduce our domain. Today, we'll be working with dishes
    and we have restaurant with the following menu.
     */
    public List<Dish> getMenu() {

        Dish bakedChicken = new Dish("Baked Chicken", 250, Dish.Type.MEAT);
        Dish steak = new Dish("Steak", 450, Dish.Type.MEAT);
        Dish pepperoniPizza = new Dish("Pepperoni Pizza", 550, Dish.Type.MEAT);

        Dish grilledSalmon = new Dish("Grilled Salmon", 300, Dish.Type.SEAFOOD);
        Dish sushi = new Dish("Sushi", 400, Dish.Type.SEAFOOD);
        Dish tunaSteak = new Dish("Tuna Steak", 450, Dish.Type.SEAFOOD);
        Dish curryShrimp = new Dish("Curry Shrimp", 550, Dish.Type.SEAFOOD);

        Dish caesarSalad = new Dish("Caesar Salad", 100, Dish.Type.VEGETARIAN);
        Dish alooGobi = new Dish("Aloo Gobi", 320, Dish.Type.VEGETARIAN);
        Dish frenchFries = new Dish("French Fries", 350, Dish.Type.VEGETARIAN);
        Dish malaiKofta = new Dish("Malai Kofta", 520, Dish.Type.VEGETARIAN);

        return List.of(
                bakedChicken, steak, pepperoniPizza,
                grilledSalmon, sushi, tunaSteak, curryShrimp,
                caesarSalad, alooGobi, frenchFries, malaiKofta
        );
    }

    /*
    Some customers prefer having their menu separated by types of dishes, i.e. meat, seafood, vegetarian,
    for convenience and dietary restrictions. In order to accommodate this,
    let's see how we'd code it prior to Java 8.
     */
    @Test
    void separateMenuByType() {
        Map<Dish.Type, List<Dish>> menuSeparatedByType = new HashMap<>();

        List<Dish> menu = getMenu();

        for (Dish dish : menu) {

            final Dish.Type type = dish.getType();

            List<Dish> dishesForType = menuSeparatedByType.get(type);
            // If there's no entry for the meal type, we have to create it
            if (dishesForType == null) {
                dishesForType = new ArrayList<>();
                menuSeparatedByType.put(type, dishesForType);
            }

            dishesForType.add(dish);
        }

        // I know I'm cheating a bit here, but I did it for brevity sake
        menuSeparatedByType.forEach(
                (type, dishes) -> System.out.println("Type: " + type + " | Dishes: " + dishes)
        );

        // And if you don't believe the print out, you can check the assertions
        // b/c we're good engineers, and we write tests :)
        final List<Dish> meatDishes = menuSeparatedByType.get(Dish.Type.MEAT);
        assertEquals(3, meatDishes.size());

        final List<Dish> expected = List.of(
                new Dish("Baked Chicken", 250, Dish.Type.MEAT),
                new Dish("Steak", 450, Dish.Type.MEAT),
                new Dish("Pepperoni Pizza", 550, Dish.Type.MEAT)
        );
        assertEquals(expected, meatDishes);

    }

    //
    // ----------------------------------- Main Course: Grouping -------------------------------------
    //

    /*
    The previous imperative implementation works, but it's a bit noisy for my taste.
    Let's see how it looks with a functional approach
     */
    @Test
    void separateMenuByTypeV2() {
        List<Dish> menu = getMenu();

        final Map<Dish.Type, List<Dish>> menuSeparatedByType = menu
                .stream()
                .collect(groupingBy(dish -> dish.getType()));

        // That's all it takes

        menuSeparatedByType.forEach(
                (type, dishes) -> System.out.println("Type: " + type + " | Dishes: " + dishes)
        );

        // And if you don't believe the print out, you can check the assertions
        // b/c we're good engineers, and we write tests :)
        final List<Dish> meatDishes = menuSeparatedByType.get(Dish.Type.MEAT);
        assertEquals(3, meatDishes.size());

        final List<Dish> expected = List.of(
                new Dish("Baked Chicken", 250, Dish.Type.MEAT),
                new Dish("Steak", 450, Dish.Type.MEAT),
                new Dish("Pepperoni Pizza", 550, Dish.Type.MEAT)
        );
        assertEquals(expected, meatDishes);
    }

    /*
    Immediately we see how concise and declarative the functional implementation is.
    Let's break it down, and see what's doing on in each spot.
     */
    @Test
    void separateMenuByTypeV2Explained() {

        List<Dish> menu = getMenu();

        final Map<Dish.Type, List<Dish>> menuSeparatedByType = menu
                .stream() // B/c menu is a Java Collection, we have access to stream()
                // .collect() is a terminal operation that takes a Collector
                .collect(groupingBy(dish -> dish.getType()));

        // Collector is an interface used for reduction in Java.
        // Reduction/Folding is a functional programming
        // concept which takes a stream/pipeline of values
        // and converts/folds/reduces them into a value.
        // i.e. a list, map, int, long, etc...

        // The Collectors class provides a number of factory methods
        // that we can use for common operations.
        // In the example above, we're using Collectors.groupingBy().

        // The groupingBy() method we used above takes 1 argument, a classification function.
        // The classification function just means how do we want to separate our groups. In the example
        // above, we chose Dish type, but it could have arbitrarily chosen calories as well.
        final Map<Integer, List<Dish>> menuSeparatedByCalories = menu
                .stream()
                .collect(groupingBy(dish -> dish.getCalories()));

        menuSeparatedByCalories.forEach(
                (calories, dishes) -> System.out.println("Calories: " + calories + " | Dishes: " + dishes)
        );

        // We should note that the key of our map is the result of our classification function.

    }

    /*
    In the examples above we used a getter as our classification function, but it's important to note
    that we're not only limited to getters or functions within the class. We can create can classification
    function as long as it satisfies the Function interface. input -> output.
     */

    /*
    Let's imagine we're watching our figure, and our dietitian told us
    to separate our foods into 3 caloric levels:
    Diet, Normal, and Fat.

     DIET <= 300
     NORMAL > 300 && <= 500
     FAT > 500

    Let's see how we'd group our menu based on these restrictions.
     */
    @Test
    void separateMenuByCaloricLevels() {

        List<Dish> menu = getMenu();

        final Map<CaloricLevel, List<Dish>> menuSeparatedByCaloricLevel = menu
                .stream()
                .collect(
                        // It's a multi-line lambda function,
                        // but it's still a single classification function
                        groupingBy(dish -> {
                                    if (dish.getCalories() <= 300) return CaloricLevel.DIET;
                                    else if (dish.getCalories() > 300 && dish.getCalories() <= 500) return CaloricLevel.NORMAL;
                                    else return CaloricLevel.FAT;
                                }
                        )
                );

        menuSeparatedByCaloricLevel.forEach(
                (caloricLevel, dishes) -> System.out.println("Caloric Level: " + caloricLevel + " | Dishes: " + dishes)
        );

        final List<Dish> fattyDishes = List.of(
                new Dish("Pepperoni Pizza", 550, Dish.Type.MEAT),
                new Dish("Curry Shrimp", 550, Dish.Type.SEAFOOD),
                new Dish("Malai Kofta", 520, Dish.Type.VEGETARIAN)
        );

        assertEquals(fattyDishes, menuSeparatedByCaloricLevel.get(CaloricLevel.FAT));
    }

    /* Same as above, but in this case I'm extracting
    the classification function into constant in another class */
    @Test
    void separateMenuByCaloricLevelsV2() {

        List<Dish> menu = getMenu();
        final Map<CaloricLevel, List<Dish>> menuSeparatedByCaloricLevel = menu
                .stream()
                .collect(groupingBy(determineDishCaloricLevel)); // This is my personal preference

        menuSeparatedByCaloricLevel.forEach(
                (caloricLevel, dishes) -> System.out.println("Caloric Level: " + caloricLevel + " | Dishes: " + dishes)
        );

        final List<Dish> fattyDishes = List.of(
                new Dish("Pepperoni Pizza", 550, Dish.Type.MEAT),
                new Dish("Curry Shrimp", 550, Dish.Type.SEAFOOD),
                new Dish("Malai Kofta", 520, Dish.Type.VEGETARIAN)
        );

        assertEquals(fattyDishes, menuSeparatedByCaloricLevel.get(CaloricLevel.FAT));

    }

    /*
    I hope you're seeing the advantages of the functional implementation so far. Let's take it up a notch
    with this use case. Imagine that you want to separate the menu by 2 criteria,
    first by type, then by caloric level.
     */
    @Test
    void separateMenuByTypeThenCaloricLevel() {

        List<Dish> menu = getMenu();

        final Map<Dish.Type, Map<CaloricLevel, List<Dish>>> menuSeparatedByTypeThenCaloricLevel = menu
                .stream()
                .collect(
                        // N.B. 2 arguments this time
                        groupingBy(dish -> dish.getType(), groupingBy(determineDishCaloricLevel))
                );

        menuSeparatedByTypeThenCaloricLevel.forEach(
                (k, v) -> System.out.println("Type: " + k + " | Calorie Map: " + v)
        );

        List<Dish> normalSeaFood = menuSeparatedByTypeThenCaloricLevel.get(Dish.Type.SEAFOOD).get(CaloricLevel.NORMAL);
        List<Dish> expected = List.of(
                new Dish("Sushi", 400, Dish.Type.SEAFOOD),
                new Dish("Tuna Steak", 450, Dish.Type.SEAFOOD)
        );
        assertEquals(expected, normalSeaFood);

    }

    /*
    As we see above, multi-level grouping can be achieved easily.
    Collectors.groupingBy() has an overloaded method that takes 2 arguments:
    the classification function, and another Collector.

    The 2 argument method allows us to create n-level groupings,
    where n is the number of groupingBy methods we use.
     */

    /*
    I want to emphasize that the 2 argument groupingBy() takes a classification function
    and another COLLECTOR. Note, that I said COLLECTOR, not another groupingBy() method.
    Let's see why this distinction is important.
     */
    @Test
    void countTheDishesByType() {
        List<Dish> menu = getMenu();

        final Map<Dish.Type, Long> numOfEachTypeOfDish = menu.stream().collect(
                groupingBy(dish -> dish.getType(), Collectors.counting())
        );

        numOfEachTypeOfDish.forEach(
                (type, count) -> System.out.println("Type: " + type + " | Count: " + count)
        );

        assertEquals(4, numOfEachTypeOfDish.get(Dish.Type.VEGETARIAN));
    }

    /*
    Suppose we wanted to find out which type of dish had highest calories based on type?
     */
    @Test
    void highestCalorieDishBasedOnType() {

        // We're going to use the comparator to compare dishes by their calories
        final Comparator<Dish> compareByCalories = Comparator.comparingInt((Dish dish) -> dish.getCalories());

        List<Dish> menu = getMenu();

        // maxBy() is a collector that returns the maximum element in a collection
        // based on a provided comparator
        Map<Dish.Type, Optional<Dish>> dishWithHighestCaloriesBasedOnType = menu.stream()
                .collect(
                        // maxBy is a collector that returns the maximum element in a collection
                        // based on a provided comparator
                        groupingBy(dish -> dish.getType(), Collectors.maxBy(compareByCalories))
                );

        dishWithHighestCaloriesBasedOnType.forEach(
                (type, dish) -> System.out.println("Type: " + type + " | Highest Calorie Dish: " + dish)
        );

        final Dish highestMeatDish = dishWithHighestCaloriesBasedOnType.get(Dish.Type.MEAT).get();
        assertEquals(new Dish("Pepperoni Pizza", 550, Dish.Type.MEAT), highestMeatDish);

        final Dish highestSeafoodDish = dishWithHighestCaloriesBasedOnType.get(Dish.Type.SEAFOOD).get();
        assertEquals(new Dish("Curry Shrimp", 550, Dish.Type.SEAFOOD), highestSeafoodDish);

        final Dish highestVegeDish = dishWithHighestCaloriesBasedOnType.get(Dish.Type.VEGETARIAN).get();
        assertEquals(new Dish("Malai Kofta", 520, Dish.Type.VEGETARIAN), highestVegeDish);
    }

    /*
    Note that value of the map was Optional<Dish>.
    This is the case because Collectors.maxBy() returns an Optional.
    If the collector was used in a different circumstance, there could be a case where
    a pipeline/stream was empty, or non of the elements matched your comparator.

    Our case is different, b/c the groupingBy function lazily
    adds a key the first time it finds an element in our stream that matches our classification.
    So sadly the Optional class is not useful here.
    Let's see how we'd remove it.
     */
    @Test
    void highestCalorieDishBasedOnTypeV2() {

        final Comparator<Dish> compareByCalories = Comparator.comparingInt((Dish dish) -> dish.getCalories());

        List<Dish> menu = getMenu();

        Map<Dish.Type, Dish> dishWithHighestCaloriesBasedOnType = menu.stream()
                .collect(
                        groupingBy(dish -> dish.getType(), // Classification function
                                Collectors.collectingAndThen( // collectingAndThen is a wrapper Collector
                                        // The same maxBy() collector followed by the next operation
                                        Collectors.maxBy(compareByCalories), optDish -> optDish.get()
                                )
                        )
                );

        dishWithHighestCaloriesBasedOnType.forEach(
                (type, dish) -> System.out.println("Type: " + type + " | Highest Calorie Dish: " + dish)
        );

        final Dish highestMeatDish = dishWithHighestCaloriesBasedOnType.get(Dish.Type.MEAT);
        assertEquals(new Dish("Pepperoni Pizza", 550, Dish.Type.MEAT), highestMeatDish);

        final Dish highestSeafoodDish = dishWithHighestCaloriesBasedOnType.get(Dish.Type.SEAFOOD);
        assertEquals(new Dish("Curry Shrimp", 550, Dish.Type.SEAFOOD), highestSeafoodDish);

        final Dish highestVegeDish = dishWithHighestCaloriesBasedOnType.get(Dish.Type.VEGETARIAN);
        assertEquals(new Dish("Malai Kofta", 520, Dish.Type.VEGETARIAN), highestVegeDish);
    }

    /*
    N.B. That the value is a Dish not an Optional<Dish>.
    Collectors.collectingAndThen() method takes 2 arguments, and returns new Collector

    1. It takes the initial collector, maxBy() in this case,
    2. A composing function, Optional.get() in this case.

    It'll do the maxBy() operation first, then it'll do the get() operation next.
     */

    /*
    Let's look at 2 more examples of what we can do with the groupingBy().
     */
    @Test
    void sumAllCaloriesBasedOnType() {

        List<Dish> menu = getMenu();

        final Map<Dish.Type, Integer> totalCaloriesPerType = menu.stream()
                .collect(
                        groupingBy(
                                dish -> dish.getType(), // Classification function
                                // summingInt() takes a function that transforms the elements
                                // into an integer then sums their values
                                Collectors.summingInt(dish -> dish.getCalories())
                        )
                );

        totalCaloriesPerType.forEach(
                (type, totalCalories) -> System.out.println("Type: " + type + " | Total Calories: " + totalCalories)
        );

        assertEquals(1250, totalCaloriesPerType.get(Dish.Type.MEAT));
        assertEquals(1700, totalCaloriesPerType.get(Dish.Type.SEAFOOD));
        assertEquals(1290, totalCaloriesPerType.get(Dish.Type.VEGETARIAN));

    }

    @Test
    void caloricLevelsByType() {
        List<Dish> menu = getMenu();

        final Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType = menu.stream()
                .collect(
                        groupingBy(
                                dish -> dish.getType(),// Classification
                                // Collectors.mapping() takes a transformation function and another Collector
                                // and returns a new Collector.

                                // In our case we want to transform a dish into Calorie level,
                                // and we want to put the results in a Set, b/c it doesn't
                                // make sense to have duplicate values for Calorie levels.
                                Collectors.mapping(determineDishCaloricLevel, toSet())
                        )
                );

        caloricLevelsByType.forEach(
                (type, calorieLevel) -> System.out.println("Type: " + type + " | Calories Level: " + calorieLevel)
        );

        Set<CaloricLevel> expected = Set.of(CaloricLevel.FAT, CaloricLevel.DIET, CaloricLevel.NORMAL);
        assertEquals(expected, caloricLevelsByType.get(Dish.Type.VEGETARIAN));

    }

    /*
    In the above example we used Collectors.toSet() to place our values in.
    The Collections package in Java provides various Set implementations such as,
    TreeSet and HashSet. If you want more control over which implementation is used,
    we can provide it as shown below.
     */
    @Test
    void caloricLevelsByTypeV2() {
        List<Dish> menu = getMenu();

        final Map<Dish.Type, Set<CaloricLevel>> caloricLevelsByType = menu.stream()
                .collect(
                        groupingBy(
                                dish -> dish.getType(),// Classification
                                // Instead of calling Collectors.toSet(), we're calling Collectors.toCollection()
                                // toCollection() takes a Supplier function which is the collection implementation
                                // you want to use. E.g. () -> new HashSet, () -> new TreeSet()
                                Collectors.mapping(determineDishCaloricLevel, toCollection(() -> new HashSet<>()))
                        )
                );

        caloricLevelsByType.forEach(
                (type, calorieLevel) -> System.out.println("Type: " + type + " | Calories Level: " + calorieLevel)
        );

        Set<CaloricLevel> expected = Set.of(CaloricLevel.FAT, CaloricLevel.DIET, CaloricLevel.NORMAL);
        assertEquals(expected, caloricLevelsByType.get(Dish.Type.VEGETARIAN));

    }

    //
    // ----------------------------------- Dessert: Partitioning -------------------------------------
    //

    /*
    Before we wrap up, let's take a look on a special use case of grouping called partitioning.
    It's similar to grouping, the only difference is that our classification function is a Predicate function instead.
    As a reminder, a predicate function is a function that returns true or false.
    With this in mind, we can think of partitioning as separating our pipeline into 2 pieces:
    1. A collection that matches our criteria,
    2. A collection that doesn't match out criteria.

    Let's take a look at an example: Imagine that your going out to eat with with your friends, and some are
    vegetarian. We need to split the menu into 2 categories, let's see how it's done
     */
    @Test
    void splitMenuIntoVegAndNonVegOptions() {

        final Predicate<Dish> isVegDish = dish -> dish.getType() == Dish.Type.VEGETARIAN;

        List<Dish> menu = getMenu();

        final Map<Boolean, List<Dish>> dividedByVeg = menu
                .stream()
                .collect(partitioningBy(isVegDish));

        dividedByVeg.forEach(
                (isVeg, dishes) -> System.out.println("Veg: " + isVeg + " | Dishes: " + dishes)
        );

        List<Dish> vegDishes = List.of(
                new Dish("Caesar Salad", 100, Dish.Type.VEGETARIAN),
                new Dish("Aloo Gobi", 320, Dish.Type.VEGETARIAN),
                new Dish("French Fries", 350, Dish.Type.VEGETARIAN),
                new Dish("Malai Kofta", 520, Dish.Type.VEGETARIAN)
        );

        assertEquals(vegDishes, dividedByVeg.get(true));
    }

    /*
    As easy as that. You could make an argument why not just use following code below:
     */
    @Test
    void splitMenuIntoVegAndNonVegOptionsV2() {
        final Predicate<Dish> isVegDish = dish -> dish.getType() == Dish.Type.VEGETARIAN;

        List<Dish> menu = getMenu();

        final List<Dish> vegDishes = menu.stream()
                .filter(isVegDish)
                .collect(toList());

        System.out.println(vegDishes);
    }

    /*
    You could, but with the above case, you lose the other dishes.
    So depending on your use case, you might want preserve that info without
    needing to create a new stream with the negation of the predicate.

    Additionally, all of the functionalities we discussed earlier,
    i.e. multi-level grouping and additional mapping can be used with the partitioningBy() method.
     */

    //
    // ----------------------------------- Wrapping up -------------------------------------
    //

    /*
    Hopefully this presentation was useful to you, and it highlights new ways to solve problems
    in Java using a functional paradigm. To reiterate, I'm not saying that this is the only way
    to solve problems, nor it's the best way. But it is useful to have many tools available to you
    as engineers.
     */

    //
    // ----------------------------------- Reference -------------------------------------
    //
    /*
    This presentation was heavily influences by Chapter 6, Collecting data with streams
    from Java 8 in Action. https://www.manning.com/books/modern-java-in-action

    If you want to learn more about functional programming in Java, I recommend you look into it.
     */

}
