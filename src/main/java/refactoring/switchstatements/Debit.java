package refactoring.switchstatements;

public class Debit extends PaymentRefactor {

    private static final double DISCOUNT = 0.9;

    public Debit(Double value) {
        super(value);
    }

    @Override
    Double applyDiscount() {
        return super.getValue() * DISCOUNT;
    }
}
