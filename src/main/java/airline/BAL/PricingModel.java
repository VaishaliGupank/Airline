package airline.BAL;

import java.time.LocalDate;
import java.util.Optional;

public class PricingModel {

    protected Optional<Double> baseFare;
    protected Optional<Integer> totalCapacity;
    protected Optional<LocalDate> departureDate;
    protected Optional<Integer> noOfOccupiedSeats;
    protected Optional<Integer> noOfRequestedSeats;


    public void setBaseFare(Optional<Double> baseFare) {
        this.baseFare = baseFare;
    }

    public void setTotalCapacity(Optional<Integer> totalCapacity) {
        this.totalCapacity = totalCapacity;
    }

    public void setDepartureDate(Optional<LocalDate> departureDate) {
        this.departureDate = departureDate;
    }

    public void setNoOfOccupiedSeats(Optional<Integer> noOfOccupiedSeats) {
        this.noOfOccupiedSeats = noOfOccupiedSeats;
    }

    public void setNoOfRequestedSeats(Optional<Integer> noOfRequestedSeats) {
        this.noOfRequestedSeats = noOfRequestedSeats;
    }


}
