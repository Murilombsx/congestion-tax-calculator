package congestion.calculator.repository;

import congestion.calculator.model.TaxRule;
import org.springframework.stereotype.Repository;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Repository
public class TaxRuleRepositoryInMemoryImpl implements TaxRuleRepository {

    private static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm");

    public List<TaxRule> getTaxRules() throws ParseException {
        LocalTime t11 = LocalTime.parse("06:00");
        LocalTime t12 = LocalTime.parse("06:29");
        LocalTime t21 = LocalTime.parse("06:30");
        LocalTime t22 = LocalTime.parse("06:59");
        LocalTime t31 = LocalTime.parse("07:00");
        LocalTime t32 = LocalTime.parse("07:59");
        LocalTime t41 = LocalTime.parse("08:00");
        LocalTime t42 = LocalTime.parse("08:29");
        LocalTime t51 = LocalTime.parse("08:30");
        LocalTime t52 = LocalTime.parse("14:59");
        LocalTime t61 = LocalTime.parse("15:00");
        LocalTime t62 = LocalTime.parse("15:29");
        LocalTime t71 = LocalTime.parse("15:30");
        LocalTime t72 = LocalTime.parse("16:59");
        LocalTime t81 = LocalTime.parse("17:00");
        LocalTime t82 = LocalTime.parse("17:59");
        LocalTime t91 = LocalTime.parse("18:00");
        LocalTime t92 = LocalTime.parse("18:29");
        LocalTime t101 = LocalTime.parse("18:30");
        LocalTime t102 = LocalTime.parse("05:59");

        return Arrays.asList(
                new TaxRule(t11, t12, 8),
                new TaxRule(t21, t22, 13),
                new TaxRule(t31, t32, 18),
                new TaxRule(t41, t42, 13),
                new TaxRule(t51, t52, 8),
                new TaxRule(t61, t62, 13),
                new TaxRule(t71, t72, 18),
                new TaxRule(t81, t82, 13),
                new TaxRule(t91, t92, 8),
                new TaxRule(t101, t102, 0)
        );
    }
}
