package refactoring.switchstatements;

public class DepartmentStoreRefactor {

    public DepartmentStoreRefactor() {
    }

    /*
    By using polymorphism, we won't have to modify
    this method when new requirements are added in
    future.

    Whenever a new payment type is added,
    a new subclass will be added. We are OPEN to
    extending functionality by adding a new class,
    and this method is CLOSED to modification

    Also, this particular refactor resulted in a OOP design
    pattern called: Strategy Pattern.
     */
    public Double applyDiscount(PaymentRefactor payment) {
        return payment.applyDiscount();
    }
}
