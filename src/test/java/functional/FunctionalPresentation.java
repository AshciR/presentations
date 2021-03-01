package functional;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

import static functional.Transaction.*;
import static functional.TransactionValidatorFunctionalImpl.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * God Class being used to demonstrate functional programming in Java
 */
public class FunctionalPresentation {

    //
    // ---------------------------------- PROGRAMME ------------------------------------
    //
    /*
        - Trailer: What is functional programming
        - ACT 1: Why it's useful (Case study)
        - ACT 2: Composition
        - ACT 3: Currying
        - Credits: Wrap Up
        - Q/A
     */

    //
    // ----------------------------------- TRAILER -------------------------------------
    //

    /*
    What is functional programming?

    "Basically, functional programming is a style of writing computer programs
    that treat computations as evaluating mathematical functions.

    So, what is a function in mathematics?
    This is an example: f(x): x + 2
    f(2) = 4
    f(4) = 6
    f(6) = 8

    Another example: f(x,y): x + y
    f(1,2) = 3
    f(2,2) = 4
     */

    /*
    Programming Paradigms:

    Let's talk briefly about programming paradigms. There are 2 main ones:
    - Imperative, explicitly give instructions and operations in order to achieve a result.
    - Focus on HOW do to something:

        def numbers = [1,2,3,4,5]
        def evenNumbers = []
        for(number : numbers){
            if(number % 2 == 0){
                evenNumbers.add(number)
            }
        }

    - Declarative, tell what has to be done; doesn't necessarily give detail instructions.
    - Focus WHAT you want done

        def numbers = [1,2,3,4,5]
        def evenNumbers = numbers.findAll(number -> number % 2 == 0)

     A number of modern languages support multiple paradigms. Java was added to the list since version 8
     */

    /*
    Functional Programming Key Concepts:

    1. Functions as 1st class citizens
    Methods/Functions can accept other functions as input params
    Example: numbers.findAll(number -> number % 2 == 0)

    2 & 3. Pure Functions & Immutability
    Function should return a value solely based on it's arguments, and produce no side effects.
    Example:

    def evenNumbers = []
    def findEvenNumbers(List numbers){
        for(number : numbers){
            if(number % 2 == 0){
                evenNumbers.add(number)
            }
        }
    }

    def numbers = [1,2,3,4,5]
    def evenNumbers = findEvenNumbers(numbers) // [2,4]
    evenNumbers = findEvenNumbers(numbers) // [2,4,2,4], we'd have to evenNumbers.clear() each time with this implementation

    Vs.

    def evenNumbers = numbers.findAll(number -> number % 2 == 0) // [2,4]
    evenNumbers = numbers.findAll(number -> number % 2 == 0) // [2,4]
    evenNumbers = numbers.findAll(number -> number % 2 == 0) // [2,4] // Will always return the ans.

    Benefits:
    - Predictable behavior
    - Easier to test
    - Inherently parallelizable

    4. Referential Transparency
    It just means that a function can be replaced with the value of it's output without any change to the program.
    Example:

    def numbers = [1,2,3,4,5]
    assert [2,4] == numbers.findAll(number -> number % 2 == 0)
    assert [2,4] == [2,4]
     */

    //
    // ----------------------------------- ACT 1 -------------------------------------
    //

    /*
    The tale of Why should we care about functional programming, a case study.
     */

    /*
    Let's consider a transaction valid if there are no flags.
     */
    @Test
    void areTheTransactionsValid() {

        // Given: All transactions are not flagged
        List<Transaction> transactions = List.of(
                createDefaultTransactionWithNoFlag(),
                createDefaultTransactionWithNoFlag(),
                createDefaultTransactionWithNoFlag()
        );

        // When: We check if the transactions are valid
        boolean areAllValid = TransactionValidatorImpl.areAllTransactionsValid(transactions);

        // Then: All the transactions should be valid
        assertTrue(areAllValid);
    }

    @Test
    void notAllTransactionsAreValid() {

        // Given: Some transactions are flagged
        List<Transaction> transactions = List.of(
                createDefaultTransactionWithNoFlag(),
                createDefaultTransactionWithNoFlag(),
                createDefaultTransactionWithRedFlag() // This one is flagged
        );

        // When: We check if the transactions are valid
        boolean areAllValid = TransactionValidatorImpl.areAllTransactionsValid(transactions);

        // Then: All the transactions should not be valid
        assertFalse(areAllValid);
    }

    /*
    Ok, it works fine, but you know how life goes as a developer. Next sprint, we find out that
    the business changed their mind, and valid transactions are now transactions with either no flags
    or green flags.
     */
    @Test
    void areTheTransactionsValidV2() {

        // Given: All transactions are valid
        List<Transaction> transactions = List.of(
                createDefaultTransactionWithNoFlag(),
                createDefaultTransactionWithNoFlag(),
                createDefaultTransactionWithGreenFlag() // Green Flag
        );

        // When: We check if the transactions are valid
        boolean areAllValid = TransactionValidatorImpl.areAllTransactionsValidV2(transactions);

        // Then: All the transactions should be valid
        assertTrue(areAllValid);
    }

    @Test
    void notAllTransactionsAreValidV2() {

        // Given: Some transactions are flagged
        List<Transaction> transactions = List.of(
                createDefaultTransactionWithNoFlag(),
                createDefaultTransactionWithGreenFlag(), // Green flag
                createDefaultTransactionWithRedFlag() // Red flag
        );

        // When: We check if the transactions are valid
        boolean areAllValid = TransactionValidatorImpl.areAllTransactionsValidV2(transactions);

        // Then: All the transactions should not be valid
        assertFalse(areAllValid);
    }

    /*
    Ok, so far it works, and it's good for this sprint. Now imagine when your PO comes next sprint and
    changes the requirement again. What happens? We're going to have to modify the code in the method.

    We know that the moment we modify existing functionality, we're introducing risk into the system.
    And if we want to be purists or good/lazy engineers, we'll design our code with the Open/Closed principle in mind.

    Open/Closed principle: Code can be open for extension, but should be closed for modification.
    With this in mind, let's see if we can refactor the code.
     */

    @Test
    void areTheTransactionsValidAfterRefactor() {

        // Given: All transactions are not flagged
        List<Transaction> transactions = List.of(
                createDefaultTransactionWithNoFlag(),
                createDefaultTransactionWithNoFlag(),
                createDefaultTransactionWithNoFlag()
        );

        // When: We check if the transactions are valid
        boolean areAllValid = TransactionValidatorFunctionalImpl.areAllTransactionsAreValid(transactions);

        // Then: All the transactions should be valid
        assertTrue(areAllValid);
    }

    @Test
    void areTheTransactionsValidV2AfterRefactor() {

        // Given: All transactions are not flagged
        List<Transaction> transactions = List.of(
                createDefaultTransactionWithNoFlag(),
                createDefaultTransactionWithNoFlag(),
                createDefaultTransactionWithGreenFlag() // Green Flag
        );

        // When: We check if the transactions are valid
        boolean areAllValid = TransactionValidatorFunctionalImpl.areAllTransactionsAreValidV2(transactions);

        // Then: All the transactions should be valid
        assertTrue(areAllValid);
    }

    @Test
    void notAllTransactionsAreValidV2AfterRefactor() {

        // Given: Some transactions are flagged
        List<Transaction> transactions = List.of(
                createDefaultTransactionWithNoFlag(),
                createDefaultTransactionWithGreenFlag(), // Green flag
                createDefaultTransactionWithRedFlag() // Red flag
        );

        // When: We check if the transactions are valid
        boolean areAllValid = TransactionValidatorFunctionalImpl.areAllTransactionsAreValidV2(transactions);

        // Then: All the transactions should be valid
        assertFalse(areAllValid);
    }

    /*
    Ok, we've made the code functional, and it readers better (subjectively speaking of course),
    but we're still violating the open/closed principle :(
    Let's take a step back and think about what we ideally want...

    We know that we'll always be passing in a list of transactions,
    but we need a way to make the valid condition flexible.
    If only we could pass in our criteria into the function...

    Ex: allTransactionsAreValidV2(List transactions, Criteria criteria)

    Luckily, we can. We can pass a function into our function (Higher-order functions).
    Let's take a look at how.
     */
    @Test
    void areTheTransactionsValidV3() {

        // Given: All transactions are not flagged
        List<Transaction> transactions = List.of(
                createDefaultTransactionWithNoFlag(),
                createDefaultTransactionWithNoFlag(),
                createDefaultTransactionWithNoFlag()
        );

        // When: We check if the transactions are valid
        boolean areAllValid = allTransactionsAreValidV3(transactions, t -> t.getFlag() == Flag.NONE);

        // Then: All the transactions should be valid
        assertTrue(areAllValid);
    }

    @Test
    void notAllTransactionsAreValidV3() {

        // Given: Some transactions are flagged
        List<Transaction> transactions = List.of(
                createDefaultTransactionWithNoFlag(),
                createDefaultTransactionWithGreenFlag(), // Green flag
                createDefaultTransactionWithRedFlag() // Red flag
        );

        // When: We check if the transactions are valid
        boolean areAllValid = allTransactionsAreValidV3(transactions, t -> t.getFlag() == Flag.NONE || t.getFlag() == Flag.GREEN);

        // Then: All the transactions should be valid
        assertFalse(areAllValid);
    }

    /*
    Ahh, this is what we were aiming for. Now we won't have to modify the function
    when new requirements are added in the future. We just change what we pass in to the function.
    */

    /*
    This allows us to list the requirements somewhere else, and add or remove requirements as needed
     */
    @Test
    void transactionsAreValidV3() {

        // Given: Some transactions are flagged
        List<Transaction> transactions = List.of(
                createDefaultTransactionWithNoFlag(),
                createDefaultTransactionWithGreenFlag(), // Green flag
                new Transaction(1234, 777.0, "Richie", Flag.NONE)
        );

        final Predicate<Transaction> noneOrGreen = t -> t.getFlag() == Flag.NONE || t.getFlag() == Flag.GREEN;
        final Predicate<Transaction> noneOrGreenAndMoreThan50Dollars = noneOrGreen.and(t -> t.getAmount() > 50.0);

        // Expect: All the transactions should be valid
        final boolean isAllGreen = allTransactionsAreValidV3(transactions, noneOrGreen);
        assertTrue(isAllGreen);

        final boolean isNoneOrGreenAndMoreThan50Dollars = allTransactionsAreValidV3(transactions, noneOrGreenAndMoreThan50Dollars);
        assertTrue(isNoneOrGreenAndMoreThan50Dollars);
    }

    //
    // ----------------------------------- ACT 2 -------------------------------------
    //

    /*
    The art of composition...
     */

    /*
    We've seen the power of high-order functions. Being able to pass in functionality
    into methods allows for higher abstractions, which leads to more generality, and less code.

    But we can still do more with these functions. Let's take a look at this use case.
    Imagine it's the end of the year, and the company is feeling generous enough to give their employees'
    a bonus. Management can't decide between giving them $1000 or double their salary.
    Fortunately enough it was the Giving Season and they decided on both!
     */
    @Test
    void isTheCorrectBonusCalculated() {

        // Given: We have a pay check
        Transaction payCheck = createDefaultTransactionWithNoFlag(); // $100

        // When: The bonus is applied
        // bonus = (x + 1000) * 2
        Transaction payCheckWithBonus = calculateAmount(payCheck);

        // Then: The pay check should have the correct amount
        assertEquals(2200, payCheckWithBonus.getAmount());
    }

    /*
    Yay it worked, everyone got paid. But the company realizes that they made a mistake.
    Giving them 1000 then doubling their pay was too much money. So they had to change it :(
     */
    @Test
    void isTheCorrectBonusCalculatedV2() {

        // Given: We have a pay check
        Transaction payCheck = createDefaultTransactionWithNoFlag(); // $100

        // When: The bonus is applied
        // bonus = (x * 2) + 1000
        Transaction payCheckWithBonus = calculateAmountV2(payCheck);

        // Then: The pay check should have the correct amount
        assertEquals(1200, payCheckWithBonus.getAmount());
    }

    /*
    Ok, so we had to change the function. Luckily it wasn't too big of a change,
    but imagine if the business changed their mind again,
    and decide on a more complicated calculations. Example:
    bonus = ((x * 2) + 1000) * 1.5
    or they change their mind again
    bonus = ((x * 2) * 1.5) + 1.5

    Similarly, we could make a generic function that takes a Transaction and applies the correct bonus
    def allocateBonus(Transaction transaction, Calculation calculation)
     */

    @Test
    void isTheCorrectBonusCalculatedV3() {

        // Given: We have a pay check
        Transaction payCheck = createDefaultTransactionWithNoFlag(); // $100

        // When: The bonus is applied
        Function<Double, Double> calculation = p -> (p * 2.0) + 1000;
        Transaction payCheckWithBonus = calculateAmountV3(payCheck, calculation);

        // Then: The pay check should have the correct amount
        assertEquals(1200, payCheckWithBonus.getAmount());
    }

    /*
    Dope. We made the method more generic, and even better, we won't have to modify the
    allocateBonus method if the calculation changes again.
    We abode by the Open-Closed principle.
     */

    /*
    So far we realize that business kept changing the order of the operations.
    If only we could define the operations, then arrange the operations.
    Well turns out that we can.

    It's a functional programming concept called composition.
    If you remember your high school maths:
    g(f(x))
     */
    @Test
    void isTheCorrectBonusCalculatedComposed() {

        // Given: We have a pay check
        Transaction payCheck = createDefaultTransactionWithNoFlag(); // $100

        // When: The bonus is applied
        Function<Double, Double> timesOneAndHalfAmount = p -> p * 1.5;
        Function<Double, Double> doubleAmount = p -> p * 2.0;
        Function<Double, Double> add1000 = p -> p + 1000;

        // (x * 2) + 1000
        Function<Double, Double> calculation = doubleAmount.andThen(add1000);

        // (x + 1000) * 2
        Function<Double, Double> calculation2 = doubleAmount.compose(add1000);

        // ((x * 2) + 1000) * 1.5
        Function<Double, Double> calculation3 = doubleAmount
                .andThen(add1000)
                .andThen(timesOneAndHalfAmount);

        Transaction payCheckWithBonus = calculateAmountV3(payCheck, calculation);
        Transaction payCheckWithBonus2 = calculateAmountV3(payCheck, calculation2);
        Transaction payCheckWithBonus3 = calculateAmountV3(payCheck, calculation3);

        // Then: The pay check should have the correct amount
        assertEquals(1200, payCheckWithBonus.getAmount());
        assertEquals(2200, payCheckWithBonus2.getAmount());
        assertEquals(1800, payCheckWithBonus3.getAmount());
    }

    //
    // ----------------------------------- ACT 3 -------------------------------------
    //

    /*
    Cooking with curry....

    So far we've seen the flexibility and code reuse that can be achieved
    from having small and specific functions. We used the composition concept
    to piece together new functionalities from smaller pieces.
    Now, let's take it a bit further with the concept of Partial Application a.k.a Currying.

    Currying, is the concept of taking a function that has multiple input parameters,
    and applying some fixed values to the inputs. Take a look at this example.

    Imagine we had the following function:
    f(rate, hours) = rate * hours

    We take 2 parameters, and calculate how much money a person should be paid.
    But suppose we had 2 use cases for a fixed rate, i.e. overtime, and holidays.
    If you work overtime, you get 1.5x your pay and if you work holidays, you get
    double pay. Let's see if we can reuse the above function.

    For overtime you'd want a function that looks like this:
    f(hours) = 1.5 * hours

    and for holidays, you'd want:
    f(hours = 2 * hours.

    We notice that it's similar to f(rate, hours) = rate * hours, just that the rate is a fixed value.
    Let's see how currying allows us to reuse existing code.

    Imagine if we could do the following:
    def payCheckCalculation = (rate, hours) = rate * hours
    def overtimeCalculation = payCheckCalculation.curry(1.5)
    def overtimePay = overtimeCalculation(10) // 1.5 * 10 = 15

    By calling the .curry() method we returned a new function that made the rate = 1.5 and has only 1 input parameter.
    So whenever we call payCheckCalculation(), we will multiply the hours by 1.5.

    Sadly, as of now Java doesn't have a .curry() method like other languages, i.e. Groovy, but we
    can achieve similar functionality in a different way.
     */
    @Test
    void correctlyCalculateHourlyPay() {

        // hourlyPay, is a function that takes a rate and returns you a new function with the rate as a fixed value.
        Function<Double, Function<Double, Double>> hourlyPay = rate -> hours -> hours * rate;
        Function<Double, Double> timeAndAHalf = hourlyPay.apply(1.5);
        Function<Double, Double> doubleRate = hourlyPay.apply(2.0);

        // Given: We have a pay check
        Transaction payCheck = createDefaultTransactionWithNoFlag(); // $100

        // When:
        Transaction timeAndAHalfPayCheck = calculateAmountV3(payCheck, timeAndAHalf);
        Transaction doublePayCheck = calculateAmountV3(payCheck, doubleRate);

        // Then:
        assertEquals(150,timeAndAHalfPayCheck.getAmount());
        assertEquals(200,doublePayCheck.getAmount());

    }

    //
    // ----------------------------------- TRAILER -------------------------------------
    //

    /*
    Let's sum up what we've seen.

    1. We've seen how to create high-order functions, and their potential to reduce duplicated code.
    2. We've seen how to compose small and specific functions in order to achieve various functionalities.
    3. Lastly, we've seen how to partially apply values to functions with multiple input parameters, and convert
    them into functions with 1 input.

    To wrap up, I'm not saying that functional programming is the only way to program, nor that it's suitable for
    all use cases. But as engineers, we should have as many tools in our toolbox and use the appropriate ones for
    solving problems. If you only have a hammer, then everything looks like a nail. Hopefully, functional programming
    will add a screwdriver to your kit.
     */

    //
    // ----------------------------------- REFERENCES -------------------------------------
    //

    /*
    https://www.baeldung.com/java-functional-programming
    https://blog.mrhaki.com/2009/09/groovy-goodness-add-some-curry-for.html
     */


}
