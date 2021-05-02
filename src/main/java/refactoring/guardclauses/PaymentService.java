package refactoring.guardclauses;

import java.math.BigDecimal;

public class PaymentService {

  /*
    Code Smell: Nested Conditionals.

    You can identify this smell when you have conditionals
    which make it clear what the normal path of execution should be.

    Code with high number of conditionals have high Cyclomatic complexities.
    It will make it harder to track the flow of the program and potential bugs.

    Why is it a smell?
    1. It's not obvious what the happy path code flow is

    How to fix:
    1. Put each check in it's own guard clause.
        1.1 The guard clauses either return right away or throw an exception.

    Let's take a look at an example below.
     */

    /**
     * Takes an employee and pays them according to their employment status.
     *
     * @param employee the employee to be paid
     * @return the amount they should be paid.
     */
    public BigDecimal payEmployee(Employee employee) {

        BigDecimal payment;

        if (employee == null) { // Standard null check
            throw new RuntimeException("There was no employee supplied");
        } else if (!employee.isAlive()) {
            payment = BigDecimal.ZERO; // Dead people don't get money
        } else { // Must be alive
            if (!employee.isEmployed()) { // Do they still work with us?

                if (employee.isRetired()) { // They retired
                    payment = payCompanyBuyOut();
                } else if (employee.isTerminated()) { // They were fired
                    payment = paySeverance();
                } else { // They resigned
                    payment = payStandardAmount();
                }

            } else { // They still work with us
                payment = payStandardAmount();
            }
        }
        return payment;
    }

    /*
    From the example, if we took out the comments, it will take you some time
    to wrap you mind around what the code is trying to do.
    We want our code's intent to be as clear as possible with minimal effort.
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
