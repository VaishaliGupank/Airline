package airline.BAL;

import airline.Utility.PricingXMLReader;

import javax.validation.Validation;
import javax.validation.ValidatorFactory;

public abstract class PriceProcessor implements IPriceProcessor {

    protected PricingModel pricingModel;
    protected PricingXMLReader pricingXMLReader;
    protected static javax.validation.Validator validator;
    public PriceProcessor(PricingModel pricingModel,PricingXMLReader pricingXMLReader)
    {
        this.pricingModel = pricingModel;
        this.pricingXMLReader = pricingXMLReader;
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    public double whatPercentFlightIsFull(int totalCapacity, int noOfBookedSeats)
    {
        double percentFlightFull = (double)  noOfBookedSeats / totalCapacity * 100;
        return Math.round(percentFlightFull);
    }
}
