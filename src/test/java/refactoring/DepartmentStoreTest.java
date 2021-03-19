package refactoring;

import org.junit.jupiter.api.Test;
import refactoring.switchstatements.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static refactoring.switchstatements.DepartmentStoreFunctionalRefactor.*;

public class DepartmentStoreTest {

    @Test
    void applyDiscount() {

        // Given: We have a some payments
        Payment cash = new Payment(100.00, PaymentType.CASH);
        Payment credit = new Payment(100.00, PaymentType.CREDIT);
        Payment debit = new Payment(100.00, PaymentType.DEBIT);

        // When: We apply the discounts
        Double cashDiscount = new DepartmentStore().applyDiscount(cash);
        Double creditDiscount = new DepartmentStore().applyDiscount(credit);
        Double debitDiscount = new DepartmentStore().applyDiscount(debit);

        // Then: The discounts should be applied correctly
        assertEquals(70.0, cashDiscount);
        assertEquals(80.0, creditDiscount);
        assertEquals(90.0, debitDiscount);
    }

    @Test
    void applyDiscountRefactor() {

        // Given: We have a some payments
        PaymentRefactor cash = new Cash(100.00);
        PaymentRefactor credit = new Credit(100.00);
        PaymentRefactor debit = new Debit(100.00);

        // When: We apply the discounts
        Double cashDiscount = new DepartmentStoreRefactor().applyDiscount(cash);
        Double creditDiscount = new DepartmentStoreRefactor().applyDiscount(credit);
        Double debitDiscount = new DepartmentStoreRefactor().applyDiscount(debit);

        // Then: The discounts should be applied correctly
        assertEquals(70.0, cashDiscount);
        assertEquals(80.0, creditDiscount);
        assertEquals(90.0, debitDiscount);

    }

    @Test
    void applyDiscountFunctionalRefactor() {

        // Given: We have a some payments
        Payment payment = new Payment(100.00);

        // When: We apply the discounts
        Double cashDiscount = DepartmentStoreFunctionalRefactor.applyDiscount(payment, CASH_DISCOUNT);
        Double creditDiscount = DepartmentStoreFunctionalRefactor.applyDiscount(payment, CREDIT_DISCOUNT);
        Double debitDiscount = DepartmentStoreFunctionalRefactor.applyDiscount(payment, DEBIT_DISCOUNT);

        // An advantage of this is that you can pass in a new calculation without the overhead of creating a new class
        Double halfOffDiscount = DepartmentStoreFunctionalRefactor.applyDiscount(payment, p -> p.getValue() * 0.5);

        // Then: The discounts should be applied correctly
        assertEquals(70.0, cashDiscount);
        assertEquals(80.0, creditDiscount);
        assertEquals(90.0, debitDiscount);
        assertEquals(50.0, halfOffDiscount);

    }

}
