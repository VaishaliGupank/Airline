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

        if(pricingModel.departureDate.isPresent() &&
                pricingModel.baseFare.isPresent() &&
                pricingModel.noOfRequestedSeats.isPresent())
        {
            LocalDate openingDateOfBookingFlight = pricingModel.departureDate.get().minusDays(10);
            long daysBetweenDateOfTravelAndOpeningDateOfBookingFlight = ChronoUnit.DAYS.between(openingDateOfBookingFlight,
                    LocalDate.now());
            totalFare = Math.round(pricingModel.baseFare.get() *
                    Math.pow(incrementPercentInFare /100 + 1, daysBetweenDateOfTravelAndOpeningDateOfBookingFlight))
                    * pricingModel.noOfRequestedSeats.get();
        }
        return totalFare;
    }

}
