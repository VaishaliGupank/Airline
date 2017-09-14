package airline.BAL;

import airline.Utility.PricingXMLReader;

import java.time.DayOfWeek;
import java.util.Map;

public class BusinessClassPriceProcessor extends PriceProcessor {
    public BusinessClassPriceProcessor(PricingModel pricingModel,PricingXMLReader pricingXMLReader )
    {
        super(pricingModel,pricingXMLReader);
    }


    @Override
    public double getFare() {

        Map<String,Float> pricingRules = pricingXMLReader.getPricingRulesForBusinessClass();
        double totalFare = 0;
        if(pricingModel.departureDate.isPresent() && pricingModel.baseFare.isPresent() && pricingModel.noOfRequestedSeats.isPresent()) {
            DayOfWeek day =  pricingModel.departureDate.get().getDayOfWeek();
            Float incrementPercentInFare = pricingRules.get(day.name());
            if(incrementPercentInFare != null)
            totalFare = Math.round(pricingModel.baseFare.get() *
                    (incrementPercentInFare / 100 + 1))
                    * pricingModel.noOfRequestedSeats.get();
            else
                totalFare = pricingModel.baseFare.get() * pricingModel.noOfRequestedSeats.get();
        }
        return totalFare;
    }

}
