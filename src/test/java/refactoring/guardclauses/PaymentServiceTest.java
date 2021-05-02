package refactoring.guardclauses;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentServiceTest {

    private static final BigDecimal STANDARD_AMOUNT = BigDecimal.valueOf(1000);
    private static final BigDecimal BUYOUT_AMOUNT = BigDecimal.valueOf(10000);
    private static final BigDecimal SEVERANCE_AMOUNT = BigDecimal.valueOf(1500);

    @Test
    void shouldPayWorkingEmployee() {

        // Given: The employee is working with the company
        final Employee employee = Employee.createWorkingEmployee(1234L, "Angela");

        // When: The company pays the employee
        BigDecimal payment = new PaymentService().payEmployee(employee);

        // Then: The payment should be correct
        assertEquals(STANDARD_AMOUNT, payment);

    }

    @Test
    void shouldNotPayNullEmployee() {

        // Given: The employee has a null value

        // Expect: The exception to be thrown
        Assertions.assertThrows(
                RuntimeException.class, () -> new PaymentService().payEmployee(null)
        );

    }

    @Test
    void shouldPayDeadEmployeeZeroDollars() {

        // Given: The employee is working with the company
        final Employee employee = Employee.createDeadEmployee(2222L, "Bob");

        // When: The company pays the employee
        BigDecimal payment = new PaymentService().payEmployee(employee);

        // Then: The payment should be correct
        assertEquals(BigDecimal.ZERO, payment);

    }

    @Test
    void shouldPayRetiredEmployeeBuyoutAmount() {

        // Given: The employee is working with the company
        final Employee employee = Employee.createRetiredEmployee(3333L, "Claire");

        // When: The company pays the employee
        BigDecimal payment = new PaymentService().payEmployee(employee);

        // Then: The payment should be correct
        assertEquals(BUYOUT_AMOUNT, payment);

    }

    @Test
    void shouldPayTerminatedEmployeeSeverance() {

        // Given: The employee is working with the company
        final Employee employee = Employee.createTerminatedEmployee(4444L, "Dale");

        // When: The company pays the employee
        BigDecimal payment = new PaymentService().payEmployee(employee);

        // Then: The payment should be correct

        assertEquals(SEVERANCE_AMOUNT, payment);

    }

    @Test
    void shouldPayResignedEmployeeStandardAmount() {

        // Given: The employee is working with the company
        final Employee employee = Employee.createResignedEmployee(5555L, "Earl");

        // When: The company pays the employee
        BigDecimal payment = new PaymentService().payEmployee(employee);

        // Then: The payment should be correct
        final BigDecimal SEVERANCE_AMOUNT = BigDecimal.valueOf(1500);
        assertEquals(STANDARD_AMOUNT, payment);

    }

    // -------- Tests for Refactored classes ---------

    @Test
    void shouldPayWorkingEmployeeRefactor() {

        // Given: The employee is working with the company
        final Employee angela = Employee.createWorkingEmployee(1234L, "Angela");

        // When: The company pays the employee
        BigDecimal payment = new PaymentServiceRefactor().payEmployee(angela);

        // Then: The payment should be correct
        assertEquals(STANDARD_AMOUNT, payment);

    }

    @Test
    void shouldNotPayNullEmployeeRefactor() {

        // Given: The employee has a null value

        // Expect: The exception to be thrown
        Assertions.assertThrows(
                RuntimeException.class, () -> new PaymentServiceRefactor().payEmployee(null)
        );

    }

    @Test
    void shouldPayDeadEmployeeZeroDollarsRefactor() {

        // Given: The employee is working with the company
        final Employee angela = Employee.createDeadEmployee(2222L, "Bob");

        // When: The company pays the employee
        BigDecimal payment = new PaymentServiceRefactor().payEmployee(angela);

        // Then: The payment should be correct
        assertEquals(BigDecimal.ZERO, payment);

    }

    @Test
    void shouldPayRetiredEmployeeBuyoutAmountRefactor() {

        // Given: The employee is working with the company
        final Employee employee = Employee.createRetiredEmployee(3333L, "Claire");

        // When: The company pays the employee
        BigDecimal payment = new PaymentServiceRefactor().payEmployee(employee);

        // Then: The payment should be correct
        assertEquals(BUYOUT_AMOUNT, payment);

    }

    @Test
    void shouldPayTerminatedEmployeeSeveranceRefactor() {

        // Given: The employee is working with the company
        final Employee employee = Employee.createTerminatedEmployee(4444L, "Dale");

        // When: The company pays the employee
        BigDecimal payment = new PaymentServiceRefactor().payEmployee(employee);

        // Then: The payment should be correct
        final BigDecimal SEVERANCE_AMOUNT = BigDecimal.valueOf(1500);
        assertEquals(SEVERANCE_AMOUNT, payment);

    }

    @Test
    void shouldPayResignedEmployeeStandardAmountRefactor() {

        // Given: The employee is working with the company
        final Employee employee = Employee.createResignedEmployee(5555L, "Earl");

        // When: The company pays the employee
        BigDecimal payment = new PaymentServiceRefactor().payEmployee(employee);

        // Then: The payment should be correct
        final BigDecimal SEVERANCE_AMOUNT = BigDecimal.valueOf(1500);
        assertEquals(STANDARD_AMOUNT, payment);

    }

}