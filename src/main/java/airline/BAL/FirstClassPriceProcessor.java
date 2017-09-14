package airline.BAL;
import airline.Utility.PricingXMLReader;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class FirstClassPriceProcessor extends PriceProcessor {

    public FirstClassPriceProcessor(PricingModel pricingModel, PricingXMLReader pricingXMLReader)
    {
        super(pricingModel,pricingXMLReader);
    }


    @Override
    public double getFare() {
        /* Get the pricing rules for economy class */
        float incrementPercentInFare = pricingXMLReader.getPricingRulesForFirstClass();
        double totalFare = 0;

        if(pricingModel.departureDate.isPresent())
        {
            LocalDate openingDateOfBookingFlight = pricingModel.departureDate.get().minusDays(10);
            long daysBetweenDateOfTravelAndOpeningDateOfBookingFlight = ChronoUnit.DAYS.between(openingDateOfBookingFlight,
                    LocalDate.now());
            totalFare = Math.round(pricingModel.baseFare *
                    Math.pow(incrementPercentInFare /100 + 1, daysBetweenDateOfTravelAndOpeningDateOfBookingFlight))
                    * pricingModel.noOfRequestedSeats;
        }
        else
            totalFare = pricingModel.baseFare * pricingModel.noOfRequestedSeats;
        return totalFare;
    }

}
