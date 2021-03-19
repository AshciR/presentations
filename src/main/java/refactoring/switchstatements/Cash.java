package refactoring.switchstatements;

public class Cash extends PaymentRefactor {

    private static final double DISCOUNT = 0.7;

    public Cash(Double value) {
        super(value);
    }

    @Override
    Double applyDiscount() {
        return super.getValue() * DISCOUNT;
    }
}
