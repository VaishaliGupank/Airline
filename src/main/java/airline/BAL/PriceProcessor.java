package airline.BAL;

import airline.Utility.PricingXMLReader;

public abstract class PriceProcessor implements IPriceProcessor {

    protected PricingModel pricingModel;
    protected PricingXMLReader pricingXMLReader;
    public PriceProcessor(PricingModel pricingModel,PricingXMLReader pricingXMLReader)
    {
        this.pricingModel = pricingModel;
        this.pricingXMLReader = pricingXMLReader;
    }

    public double whatPercentFlightIsFull(int totalCapacity, int noOfBookedSeats)
    {
        double percentFlightFull = (double)  noOfBookedSeats / totalCapacity * 100;
        return Math.round(percentFlightFull);
    }
}
