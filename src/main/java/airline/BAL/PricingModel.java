package airline.BAL;

import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.Optional;

public class PricingModel {

    @NotEmpty
    protected Double baseFare;
    @NotEmpty
    protected Integer noOfRequestedSeats;
    protected Optional<Integer> totalCapacity;
    protected Optional<LocalDate> departureDate;
    protected Optional<Integer> noOfOccupiedSeats;
    public void setBaseFare(Double baseFare) {
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

    public void setNoOfRequestedSeats(Integer noOfRequestedSeats) {
        this.noOfRequestedSeats = noOfRequestedSeats;
    }


}
