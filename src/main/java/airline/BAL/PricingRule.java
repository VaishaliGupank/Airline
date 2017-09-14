package airline.BAL;

import airline.Models.TravelClass;

public class PricingRule {

    private TravelClass.TravelType travelType;
    private float minPercentOfBookedSeats;
    private float maxPercentOfBookedSeats;
    private float incrementPercentInFare;

    public PricingRule(TravelClass.TravelType travelType,
                       float minPercentOfBookedSeats,
                       float maxPercentOfBookedSeats,
                       float incrementPercentInFare )
    {
        this.travelType = travelType;
        this.minPercentOfBookedSeats = minPercentOfBookedSeats;
        this.maxPercentOfBookedSeats = maxPercentOfBookedSeats;
        this.incrementPercentInFare = incrementPercentInFare;
    }

    public TravelClass.TravelType getTravelType() {
        return travelType;
    }

    public float getIncrementPercentInFare() {
        return incrementPercentInFare;
    }
    public float getMinPercentOfBookedSeats() {
        return minPercentOfBookedSeats;
    }
    public float getMaxPercentOfBookedSeats() {
        return maxPercentOfBookedSeats;
    }


}
