package functional;

import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class TransactionValidatorFunctionalImpl {

    public static boolean areAllTransactionsAreValid(List<Transaction> transactions) {
        return transactions
                .stream()
                .allMatch(transaction -> transaction.getFlag() == Flag.NONE);

    }

    public static boolean areAllTransactionsAreValidV2(List<Transaction> transactions) {

        return transactions
                .stream()
                .filter(transaction1 -> transaction1.getFlag() != Flag.NONE)
                .allMatch(transaction -> transaction.getFlag() == Flag.GREEN);

    }

    public static boolean allTransactionsAreValidV3(List<Transaction> transactions, Predicate<Transaction> criteria) {

        return transactions
                .stream()
                .allMatch(criteria);

    }

    //////////////////////////////////////////////////////////////////////

    public static Transaction calculateAmount(Transaction payCheck) {

        Transaction thousandAdded = add1000DollarsToTransactionAmount(payCheck);
        return doubleTransactionAmount(thousandAdded);
    }

    public static Transaction calculateAmountV2(Transaction payCheck) {

        Transaction doubled = doubleTransactionAmount(payCheck);
        return add1000DollarsToTransactionAmount(doubled);
    }

    public static Transaction calculateAmountV3(Transaction payCheck, Function<Double, Double> bonusCalculation) {

        // .apply() is how Java says do the calculation
        // bonus = f(payCheck)
        final Double bonus = bonusCalculation.apply(payCheck.getAmount());

        return new Transaction(
                payCheck.getId(),
                bonus,
                payCheck.getRecipient(),
                payCheck.getFlag()
        );

    }

    public static Transaction add1000DollarsToTransactionAmount(Transaction payCheck) {

        return new Transaction(
                payCheck.getId(),
                payCheck.getAmount() + 1000.00,
                payCheck.getRecipient(),
                payCheck.getFlag()
        );

    }

    public static Transaction doubleTransactionAmount(Transaction payCheck) {

        return new Transaction(
                payCheck.getId(),
                payCheck.getAmount() * 2.0,
                payCheck.getRecipient(),
                payCheck.getFlag()
        );

    }

}
