package congestion.calculator.service;

import congestion.calculator.model.TaxRule;
import congestion.calculator.model.Vehicle;
import congestion.calculator.repository.TaxRuleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class CongestionTaxCalculatorImpl implements CongestionTaxCalculatorService {

    private final int maxFee = 60;
    private final TaxRuleRepository taxRuleRepository;

    @Autowired
    public CongestionTaxCalculatorImpl(TaxRuleRepository taxRuleRepository) {
        this.taxRuleRepository = taxRuleRepository;
    }

    @Override
    public int getTax(Vehicle vehicle, LocalDateTime[] datesTime) throws ParseException {
        LocalDateTime intervalStart = datesTime[0];
        int totalFee = 0;

        for (int i = 0; i < datesTime.length ; i++) {
            LocalDateTime dateTime = datesTime[i];
            int currentFee = getTollFee(dateTime, vehicle);
            int tempFee = getTollFee(intervalStart, vehicle);

            Duration duration = Duration.between(intervalStart, dateTime);
            long minutes = duration.toMinutes();

            if (minutes <= 60) {
                if (totalFee > 0) {
                    totalFee = totalFee - tempFee;
                }
                if (currentFee >= tempFee) {
                    tempFee = currentFee;
                }
                totalFee = totalFee + tempFee;
            } else {
                totalFee = totalFee + currentFee;
                intervalStart = dateTime;
            }
        }

        if (totalFee > maxFee) totalFee = maxFee;
        return totalFee;
    }

    public int getTollFee(LocalDateTime dateTime, Vehicle vehicle) throws ParseException {
        if (isTollFreeDate(dateTime.toLocalDate()) || vehicle.IsTollFreeVehicle()) return 0;

        List<TaxRule> taxRules = taxRuleRepository.getTaxRules();

        LocalDate date = dateTime.toLocalDate();

        for (TaxRule taxRule : taxRules) {
            LocalDateTime startDateTime = LocalDateTime.of(date, taxRule.getStartTime());
            LocalDateTime endDateTime = LocalDateTime.of(date, taxRule.getEndTime());

            if (endDateTime.isBefore(startDateTime)) {
                startDateTime = LocalDateTime.of(date.minusDays(1), taxRule.getStartTime());
            }

            if (dateTime.isEqual(startDateTime) || dateTime.isEqual(endDateTime) || (dateTime.isAfter(startDateTime) && dateTime.isBefore(endDateTime))) {
                return taxRule.getAmount();
            }
        }
        return 0;
    }

    public Boolean isTollFreeDate(LocalDate date)
    {
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfWeek().getValue();
        int dayOfMonth = date.getDayOfMonth();

        if (day == 6 || day == 7) return true;  // Saturday or Sunday

        if (year == 2013)
        {
            if ((month == 1 && dayOfMonth == 1) ||
                    (month == 3 && (dayOfMonth == 28 || dayOfMonth == 29)) ||
                    (month == 4 && (dayOfMonth == 1 || dayOfMonth == 30)) ||
                    (month == 5 && (dayOfMonth == 1 || dayOfMonth == 8 || dayOfMonth == 9)) ||
                    (month == 6 && (dayOfMonth == 5 || dayOfMonth == 6 || dayOfMonth == 21)) ||
                    (month == 7) ||
                    (month == 11 && dayOfMonth == 1) ||
                    (month == 12 && (dayOfMonth == 24 || dayOfMonth == 25 || dayOfMonth == 26 || dayOfMonth == 31)))
            {
                return true;
            }
        }
        return false;
    }

}
