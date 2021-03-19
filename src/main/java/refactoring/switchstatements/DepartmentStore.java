package refactoring.switchstatements;

public class DepartmentStore {

    public DepartmentStore() {
    }

    /*
    Code Smell: Switch methods or conditionals.
    Switch... Case statement is used for conditional operations.
    Sometimes, it is considered in the category of code smell.

    Switch case is not a bad syntax, but its usage in some cases categorizes it under code smell.
    Especially in Object-oriented programming.
    Thus, we should use Switch case judiciously...

    Why is it a smell?

    1. Violation of Open and close principle:
    Each time a developer wants to add a new type
    then he or she has to insert new case in each section.
    It requires modification of an existing code.

    2. Difficult to maintain
    Case can grow on the basis of new requirements.
    Switch case is being kept in a single function,
    so function will increase and and maintenance will be hard.

    How to fix:
    Usually when you see a switch statement, it's a sign that polymorphism can replace it.
    1. Identify the common functionality
    2. Create an abstract method for the functionality, via an abstract class or interface
    3. Replace the switch statement with the above method

    Implementation:
    Let's take a look at the code below. We notice that depending on the payment type
    we apply a different discount. It seems the common functionality is applying a discount.
    Let's make Payment abstract and a getDiscounted() method.
     */
    public Double applyDiscount(Payment payment) {
        double discountedPrice;

        switch (payment.getType()) {
            case CASH:
                discountedPrice = payment.getValue() * 0.7;
                break;
            case CREDIT:
                discountedPrice = payment.getValue() * 0.8;
                break;
            case DEBIT:
                discountedPrice = payment.getValue() * 0.9;
                break;
            default:
                discountedPrice = payment.getValue();
                break;
        }

        return discountedPrice;
    }

}
