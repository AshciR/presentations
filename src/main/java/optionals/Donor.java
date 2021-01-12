package optionals;

import java.math.BigDecimal;

public class Donor {

    private String firstName;
    private String lastName;
    private Boolean isPrimary;
    private BigDecimal givingAccountValue;
    private Successor successor;

    public Donor(String firstName, String lastName, Boolean isPrimary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.isPrimary = isPrimary;
        this.givingAccountValue = BigDecimal.ZERO;
        this.successor = null;
    }

    Donor(String firstName,
            String lastName,
            Boolean isPrimary,
            BigDecimal givingAccountValue,
            Successor successor) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.isPrimary = isPrimary;
        this.givingAccountValue = makeNullValueEqualToZero(givingAccountValue);
        this.givingAccountValue = givingAccountValue;

        this.successor = successor;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public BigDecimal getGivingAccountValue() {
        return givingAccountValue;
    }

    public Successor getSuccessor() {
        return successor;
    }

    private BigDecimal makeNullValueEqualToZero(BigDecimal value) {
        if (null == value) {
            return BigDecimal.ZERO;
        }

        return value;

    }

    @Override
    public String toString() {
        return "Donor{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", isPrimary=" + isPrimary +
                ", givingAccountValue=" + givingAccountValue +
                ", successor=" + successor +
                '}';
    }
}
