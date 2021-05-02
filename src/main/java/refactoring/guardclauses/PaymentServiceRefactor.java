package refactoring.guardclauses;

import java.math.BigDecimal;

public class PaymentServiceRefactor {

    /**
     * Takes an employee and pays them according to their employment status.
     *
     * @param employee the employee to be paid
     * @return the amount they should be paid.
     */
    public BigDecimal payEmployee(Employee employee) {

        // Guard Clause 1:
        // We can't do anything without an employee
        // Fail early and fast.
        if (employee == null) {
            throw new RuntimeException("There was no employee supplied");
        }

        // Guard Clause 2:
        // Dead people don't get money
        if (!employee.isAlive()) {
            return BigDecimal.ZERO;
        }

        // Guard Clause 3:
        if (employee.isRetired()) {
            return payCompanyBuyOut();
        }

        // Guard Clause 4:
        if (employee.isTerminated()) {
            return paySeverance();
        }

        // The common execution path
        return payStandardAmount();

    }

    /*
    After our example, the intent is clearer, and we've reduced the
    cyclomatic complexity. Another benefit is that we removed
    the payment variable that was being mutated.
     */

    private BigDecimal paySeverance() {
        return BigDecimal.valueOf(1500);
    }

    private BigDecimal payCompanyBuyOut() {
        return BigDecimal.valueOf(10000);
    }

    private BigDecimal payStandardAmount() {
        return BigDecimal.valueOf(1000);
    }

}
