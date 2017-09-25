package airline.BAL;

import airline.Models.TravelClass;

public class PriceProcessorFactory {

    public IPriceProcessor getPriceProcessor(TravelClass.TravelType travelType, PricingModel pricingModel){
        if(travelType == null){
            return null;
        }
        switch (travelType)
        {
            case ECONOMY:
                return new EconomyClassPriceProcessor(pricingModel);
            case FIRST:
                return new FirstClassPriceProcessor(pricingModel);
            case BUSINESS:
                return new BusinessClassPriceProcessor(pricingModel);

        }
        return null;
    }
}
