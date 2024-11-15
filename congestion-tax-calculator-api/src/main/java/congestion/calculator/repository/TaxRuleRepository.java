package congestion.calculator.repository;

import congestion.calculator.model.TaxRule;

import java.text.ParseException;
import java.util.List;

public interface TaxRuleRepository {
    List<TaxRule> getTaxRules() throws ParseException;
}
