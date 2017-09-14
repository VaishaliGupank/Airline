package airline.BAL;

import airline.Utility.PricingXMLReader;

import java.util.List;
import java.util.Optional;

public class EconomyClassPriceProcessor extends PriceProcessor  {


    private PricingXMLReader pricingXMLReader  = new PricingXMLReader();
    public EconomyClassPriceProcessor(PricingModel pricingModel,PricingXMLReader pricingXMLReader)
    {
        super(pricingModel,pricingXMLReader);
    }


    @Override
    public double getFare() {
        /* Get the pricing rules for economy class */
        List<PricingRule> pricingRules = pricingXMLReader.getPricingRulesForEconomy();
        double totalFare = 0;
        if(pricingModel.totalCapacity.isPresent() &&
                pricingModel.noOfOccupiedSeats.isPresent() &&
                pricingModel.noOfRequestedSeats.isPresent() &&
                pricingModel.baseFare.isPresent()) {
            double whatPercentFlightIsFullBeforeBooking = whatPercentFlightIsFull(pricingModel.totalCapacity.get(), pricingModel.noOfOccupiedSeats.get());
            double whatPercentFlightIsFullAfterBooking = whatPercentFlightIsFull(pricingModel.totalCapacity.get(),
                    pricingModel.noOfOccupiedSeats.get() + pricingModel.noOfRequestedSeats.get());

            Optional<PricingRule> pricingRuleForBooking = pricingRules.stream().
                    filter(pricingRule -> pricingRule.getMinPercentOfBookedSeats() <= whatPercentFlightIsFullBeforeBooking &&
                            whatPercentFlightIsFullBeforeBooking <= pricingRule.getMaxPercentOfBookedSeats()).findFirst();


            if (pricingRuleForBooking.isPresent()) {
                totalFare = Math.round(pricingModel.baseFare.get() *
                        (pricingRuleForBooking.get().getIncrementPercentInFare() / 100 + 1))
                        * pricingModel.noOfRequestedSeats.get();
            }
        }

        return totalFare;
    }

   /* public static interface IPriceProcessor {
        public double getFare(int noOfBookedSeats, int noOfBookingSeats);
    }*/
}
