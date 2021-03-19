package refactoring.switchstatements;

import java.util.function.Function;

public class DepartmentStoreFunctionalRefactor {

    public static final Function<Payment, Double> CASH_DISCOUNT = payment -> payment.getValue() * 0.7;
    public static final Function<Payment, Double> CREDIT_DISCOUNT = payment -> payment.getValue() * 0.8;
    public static final Function<Payment, Double> DEBIT_DISCOUNT = payment -> payment.getValue() * 0.9;

    public DepartmentStoreFunctionalRefactor() {
    }

    /*
    Languages with support for functional programming can replace
    the OOP Strategy pattern by passing in functions as arguments.
    In essence, the Strategy pattern came into existence,
    b/c all functionality had to be wrapped by a class. And only classes
    and primitive could be passed into methods.
     */
    public static Double applyDiscount(Payment payment,
                                       // f(x): x * 0.8 How it would look in Math
                                       //  x -> x * 0.8 How it looks in Java
                                       Function<Payment, Double> discountCalculator) {

        return discountCalculator.apply(payment);
    }
}
