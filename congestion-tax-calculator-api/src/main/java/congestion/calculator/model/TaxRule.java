package congestion.calculator.model;

import java.time.LocalTime;

public class TaxRule {

    private LocalTime startTime;
    private LocalTime endTime;
    private int amount;

    public TaxRule(LocalTime startTime, LocalTime endTime, int amount) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.amount = amount;
    }

    // Getters
    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public int getAmount() {
        return amount;
    }
}
