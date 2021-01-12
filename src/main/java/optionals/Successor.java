package optionals;

import java.math.BigDecimal;
import java.util.Objects;

class Successor {

    private String firstName;
    private String lastName;
    private BigDecimal netWorth;

    public Successor(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Successor(String firstName, String lastName, BigDecimal netWorth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.netWorth = netWorth;
    }


    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public BigDecimal getNetWorth() {
        return netWorth;
    }

    @Override
    public String toString() {
        return "Successor{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Successor successor = (Successor) o;
        return Objects.equals(firstName, successor.firstName) &&
                Objects.equals(lastName, successor.lastName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName);
    }
}
