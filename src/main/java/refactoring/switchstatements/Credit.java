package refactoring.switchstatements;

public class Credit extends PaymentRefactor{

    private static final double DISCOUNT = 0.8;

    public Credit(Double value) {
        super(value);
    }

    @Override
    Double applyDiscount() {
        return super.getValue() * DISCOUNT;
    }
}
