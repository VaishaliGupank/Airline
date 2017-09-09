package airline.Models;

public class TravelClass {
    public enum TravelType {

        ECONOMY("Economy"),
        FIRST("First Class"),
        BUSINESS("Business Class");

        private String displayName;

        TravelType(String displayName) {
            this.displayName = displayName;
        }

        public String displayName() { return displayName; }

        @Override public String toString() { return displayName; }
    }

    private TravelType travelClass;
    private int noOfSeats;
    private double basePrice;

    public TravelClass(TravelType traveltype, int noOfSeats, double basePrice)
    {
        this.travelClass = traveltype;
        this.noOfSeats = noOfSeats;
        this.basePrice = basePrice;
    }

    public TravelType getTravelClass() {
        return travelClass;
    }

    public int getNoOfSeats() {
        return noOfSeats;
    }

    public double getBasePrice() {
        return basePrice;
    }



}
