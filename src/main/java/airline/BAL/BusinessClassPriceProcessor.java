package airline.BAL;

import java.time.DayOfWeek;
import java.util.Map;
import java.util.Optional;

public class BusinessClassPriceProcessor extends PriceProcessor {
    public BusinessClassPriceProcessor(PricingModel pricingModel)
    {
        super(pricingModel);
    }


    @Override
    public Optional<Double> getFare() {

        if(validator.validate(pricingModel).size() != 0)
            return Optional.empty();
        Map<String,Float> pricingRules = pricingXMLReader.getPricingRulesForBusinessClass();
        double totalFare = 0;
        if(pricingModel.departureDate.isPresent()) {
            DayOfWeek day =  pricingModel.departureDate.get().getDayOfWeek();
            Float incrementPercentInFare = pricingRules.get(day.name());
            totalFare = incrementPercentInFare != null ? Math.round(pricingModel.baseFare *
                    (incrementPercentInFare  + 1))
                    * pricingModel.noOfRequestedSeats : pricingModel.baseFare * pricingModel.noOfRequestedSeats;

        }
        else
            totalFare = pricingModel.baseFare * pricingModel.noOfRequestedSeats;

        return Optional.of(totalFare);
    }

}
