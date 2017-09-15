package airline.BAL;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Optional;

public class PricingModel {


    @NotNull
    protected Double baseFare;
    @Min(1)
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
