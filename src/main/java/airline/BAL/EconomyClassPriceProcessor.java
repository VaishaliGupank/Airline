package airline.BAL;

import airline.Utility.PricingXMLReader;

import java.util.List;
import java.util.Optional;

public class EconomyClassPriceProcessor extends PriceProcessor  {


    private PricingXMLReader pricingXMLReader  = new PricingXMLReader();
    public EconomyClassPriceProcessor(PricingModel pricingModel)
    {
        super(pricingModel);
    }


    @Override
    public Optional<Double> getFare() {

        if(validator.validate(pricingModel).size() != 0)
            return Optional.empty();
        /* Get the pricing rules for economy class */
        List<PricingRule> pricingRules = pricingXMLReader.getPricingRulesForEconomy();
        double totalFare = 0;
        if(pricingModel.totalCapacity.isPresent() &&
                pricingModel.noOfOccupiedSeats.isPresent()) {
            double whatPercentFlightIsFullBeforeBooking = whatPercentFlightIsFull(pricingModel.totalCapacity.get(), pricingModel.noOfOccupiedSeats.get());
            double whatPercentFlightIsFullAfterBooking = whatPercentFlightIsFull(pricingModel.totalCapacity.get(),
                    pricingModel.noOfOccupiedSeats.get() + pricingModel.noOfRequestedSeats);

            Optional<PricingRule> pricingRuleForBooking = pricingRules.stream().
                    filter(pricingRule -> pricingRule.getMinPercentOfBookedSeats() <= whatPercentFlightIsFullBeforeBooking &&
                            whatPercentFlightIsFullBeforeBooking <= pricingRule.getMaxPercentOfBookedSeats()).findFirst();


            if (pricingRuleForBooking.isPresent()) {
                totalFare = Math.round(pricingModel.baseFare *
                        (pricingRuleForBooking.get().getIncrementPercentInFare()  + 1))
                        * pricingModel.noOfRequestedSeats;
            }
        }
        else
            totalFare = pricingModel.baseFare * pricingModel.noOfRequestedSeats;

        return Optional.of(totalFare);
    }

   /* public static interface IPriceProcessor {
        public double getFare(int noOfBookedSeats, int noOfBookingSeats);
    }*/
}
