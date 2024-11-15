package congestion.calculator.service;

import congestion.calculator.model.Vehicle;

import java.text.ParseException;
import java.time.LocalDateTime;

public interface CongestionTaxCalculatorService {
    int getTax(Vehicle vehicle, LocalDateTime[] dates) throws ParseException;
}
