package congestion.calculator.service;

import congestion.calculator.model.TaxRule;
import congestion.calculator.model.Vehicle;
import congestion.calculator.repository.TaxRuleRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class CongestionTaxCalculatorImplTest {

    @Mock
    private TaxRuleRepository taxRuleRepository;

    @Mock
    private Vehicle vehicle;

    @InjectMocks
    private CongestionTaxCalculatorImpl congestionTaxCalculator;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        congestionTaxCalculator = new CongestionTaxCalculatorImpl(taxRuleRepository);
    }

    @Test
    void testGetTax() throws ParseException {
        List<TaxRule> taxRules = new ArrayList<>();
        TaxRule taxRule1 = new TaxRule(
                LocalTime.parse("07:00"),
                LocalTime.parse("07:59"),
                18);
        TaxRule taxRule2 = new TaxRule(
                LocalTime.parse("15:00"),
                LocalTime.parse("15:29"),
                13);
        taxRules.add(taxRule1);
        taxRules.add(taxRule2);
        when(taxRuleRepository.getTaxRules()).thenReturn(taxRules);

        when(vehicle.IsTollFreeVehicle()).thenReturn(false);

        LocalDateTime[] dates = new LocalDateTime[2];
        dates[0] = LocalDateTime.of(2013, 10, 10, 7, 0);
        dates[1] = LocalDateTime.of(2013, 10, 10, 15, 28);

        int tax = congestionTaxCalculator.getTax(vehicle, dates);

        assertEquals(31, tax);
    }

    @Test
    public void testGetTollFee() throws ParseException {
        Vehicle vehicle = Mockito.mock(Vehicle.class);
        when(vehicle.IsTollFreeVehicle()).thenReturn(false);

        List<TaxRule> taxRules = new ArrayList<>();
        TaxRule taxRule = new TaxRule(
                LocalTime.parse("15:00"),
                LocalTime.parse("15:29"),
                13);
        taxRules.add(taxRule);

        when(taxRuleRepository.getTaxRules()).thenReturn(taxRules);

        LocalDateTime date = LocalDateTime.of(2013, 10, 10, 15, 28);
        int tollFee = congestionTaxCalculator.getTollFee(date, vehicle);

        assertEquals(13, tollFee);
    }

    @Test
    public void testIsTollFreeDate() throws ParseException {
        // Saturday
        LocalDate tollFreeDateWeekend = LocalDateTime.of(2013, 10, 12, 0, 0).toLocalDate();
        Boolean isTollFreeWeekend = congestionTaxCalculator.isTollFreeDate(tollFreeDateWeekend);
        assertTrue(isTollFreeWeekend);

        // Thursday
        LocalDate nonTollFreeDateWeekday = LocalDateTime.of(2013, 10, 17, 0, 0).toLocalDate();
        Boolean isNotTollFree = congestionTaxCalculator.isTollFreeDate(nonTollFreeDateWeekday);
        assertFalse(isNotTollFree);

        // Holiday
        LocalDate tollFreeDateHoliday = LocalDateTime.of(2013, 3, 29, 0, 0).toLocalDate();
        Boolean isTollFreeHoliday = congestionTaxCalculator.isTollFreeDate(tollFreeDateHoliday);
        assertTrue(isTollFreeHoliday);
    }
}
