package airline.BAL;
import airline.Utility.PricingXMLReader;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

public class FirstClassPriceProcessor extends PriceProcessor {

    public FirstClassPriceProcessor(PricingModel pricingModel, PricingXMLReader pricingXMLReader)
    {
        super(pricingModel,pricingXMLReader);
    }


    @Override
    public double getFare() {
        /* Get the pricing rules for first class */
        float incrementPercentInFare = pricingXMLReader.getPercentIncrementInFareFirstClass();
        double totalFare = 0;

        if(pricingModel.departureDate.isPresent()) {
            Optional<Integer> noOfDaysBeforeBookingStarts =
                    pricingXMLReader.getDaysWhenBookingIsAllowedBeforeDepartureDate("First");
            if (noOfDaysBeforeBookingStarts.isPresent()) {
                LocalDate openingDateOfBookingFlight = pricingModel.departureDate.get().
                        minusDays(noOfDaysBeforeBookingStarts.get());
                long daysBetweenDateOfTravelAndOpeningDateOfBookingFlight = ChronoUnit.DAYS.between(openingDateOfBookingFlight,
                        LocalDate.now());
                totalFare = Math.round(pricingModel.baseFare *
                        Math.pow(incrementPercentInFare / 100 + 1, daysBetweenDateOfTravelAndOpeningDateOfBookingFlight))
                        * pricingModel.noOfRequestedSeats;
            }
        }
        else
            totalFare = pricingModel.baseFare * pricingModel.noOfRequestedSeats;
        return totalFare;
    }

}
