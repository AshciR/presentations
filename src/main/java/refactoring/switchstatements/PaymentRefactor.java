package refactoring.switchstatements;

abstract public class PaymentRefactor {

    final protected Double value;

    public PaymentRefactor(Double value) {
        this.value = value;
    }

    /**
     * Applies a discount for particular payment type
     *
     * @return the discounted value
     */
    abstract Double applyDiscount();

    public Double getValue() {
        return value;
    }

}
