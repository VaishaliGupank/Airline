package airline.Repositories;

import airline.BAL.PricingRule;
import airline.Models.TravelClass;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Configuration(value = "PriceRuleRepository")
public class PricingRulesRepository {

    private List<PricingRule> priceRules = new ArrayList<PricingRule>();
    public  PricingRulesRepository()
    {
        populatePriceRules();
    }

    public List<PricingRule> getPriceRules()
    {
        return priceRules;
    }

    public void populatePriceRules()
    {
        priceRules.add(new PricingRule(TravelClass.TravelType.ECONOMY,0, 40, 0));
        priceRules.add(new PricingRule(TravelClass.TravelType.ECONOMY,41, 90, 30));
        priceRules.add(new PricingRule(TravelClass.TravelType.ECONOMY,91, 100,60));

    }
    public List<PricingRule> getPricingRules(TravelClass.TravelType travelType)
    {
        return getPriceRules().stream().
                filter(x -> x.getTravelType().equals(travelType)).collect(Collectors.toList());

    }
}