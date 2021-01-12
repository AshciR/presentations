package optionals;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

/**
 * Domain object to represent a grant.
 */
public class Grant implements Serializable {

    private BigDecimal amount;
    private Optional<LocalDate> scheduleDate; // Will throw NotSerializableException

    Grant(BigDecimal amount, Optional<LocalDate> scheduleDate) {
        this.amount = amount;
        this.scheduleDate = scheduleDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    // Jackson will serialize this into
    // "scheduleDate":{"present":true}}
    // We'd want the actual date
    public Optional<LocalDate> getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Optional<LocalDate> scheduleDate) {
        this.scheduleDate = scheduleDate;
    }
}
