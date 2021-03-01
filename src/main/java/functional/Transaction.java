package functional;

import java.util.Objects;
import java.util.Random;

/**
 * Represents a generic transaction.
 * We're using this as the domain object
 * for the functional programming presentation.
 */
public class Transaction {

    private final Integer id;
    private final Double amount;
    private final String recipient;
    private final Flag flag;

    public Transaction(Integer id, Double amount, String recipient, Flag flag) {
        this.id = id;
        this.amount = amount;
        this.recipient = recipient;
        this.flag = flag;
    }

    // Static Factory methods can be a good substitute for creating objects
    // instead of traditional constructors.
    public static Transaction createDefaultTransactionWithNoFlag() {
        return new Transaction(new Random().nextInt(1000), 100.0, "Matt", Flag.NONE);
    }

    public static Transaction createDefaultTransactionWithGreenFlag() {
        return new Transaction(new Random().nextInt(1000), 200.0, "Sai", Flag.GREEN);
    }

    public static Transaction createDefaultTransactionWithRedFlag() {
        return new Transaction(new Random().nextInt(1000), 300.0, "Ivey", Flag.RED);
    }

    // Rest of the boilerplate methods...
    public Integer getId() {
        return id;
    }

    public Double getAmount() {
        return amount;
    }

    public String getRecipient() {
        return recipient;
    }

    public Flag getFlag() {
        return flag;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", recipient='" + recipient + '\'' +
                ", flag=" + flag +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return amount.equals(that.amount) && recipient.equals(that.recipient) && flag == that.flag;
    }

    @Override
    public int hashCode() {
        return Objects.hash(amount, recipient, flag);
    }

}
