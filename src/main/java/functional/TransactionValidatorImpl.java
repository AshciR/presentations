package functional;

import java.util.List;

public class TransactionValidatorImpl {

    public static boolean areAllTransactionsValid(List<Transaction> transactions) {

        for (Transaction transaction : transactions) {
            if (transaction.getFlag() != Flag.NONE) {
                return false;
            }
        }

        return true;
    }

    public static boolean areAllTransactionsValidV2(List<Transaction> transactions) {

        for (Transaction transaction : transactions) {
            if (!(transaction.getFlag() == Flag.NONE || transaction.getFlag() == Flag.GREEN)) {
                return false;
            }
        }

        return true;

    }

}
