package refactoring.switchstatements;

public class Payment {
    private final Double value;
    private final PaymentType type;

    public Payment(Double value) {
        this.value = value;
        this.type = PaymentType.CASH;
    }

    public Payment(Double value, PaymentType type) {
        this.value = value;
        this.type = type;
    }

    public Double getValue() {
        return value;
    }

    public PaymentType getType() {
        return type;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "value=" + value +
                ", type=" + type +
                '}';
    }

    public static Payment createCashPayment() {
        return new Payment(100.0, PaymentType.CASH);
    }

    public static Payment createDebitPayment() {
        return new Payment(100.0, PaymentType.DEBIT);
    }

    public static Payment createCreditPayment() {
        return new Payment(100.0, PaymentType.CREDIT);
    }

}
