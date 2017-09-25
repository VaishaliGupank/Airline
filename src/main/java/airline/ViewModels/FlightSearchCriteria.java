package airline.ViewModels;

import airline.Models.TravelClass;
import org.hibernate.validator.constraints.NotEmpty;

import java.time.LocalDate;
import java.util.Optional;

public class FlightSearchCriteria {

    @NotEmpty(message = "Please enter your source.")
    private String source;

    @NotEmpty(message = "Please enter your destination.")
    private String destination;

    private Optional<Integer> noOfPassengers;

    private String departureDate;

    @NotEmpty(message = "Please enter your travel class.")
    private String travelClass;

   public String getTravelClass() {
        return travelClass;
    }

    public void setTravelClass(String travelClass) {
        this.travelClass = travelClass;

    }

    public String getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(String departureDate) {
        this.departureDate = departureDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public Optional<Integer> getNoOfPassengers() {
        return noOfPassengers;
    }

    public void setNoOfPassengers(Optional<Integer> noOfPassengers) {
        this.noOfPassengers =  noOfPassengers;
    }

    public TravelClass.TravelType getParsedTravelClass(){
        for(TravelClass.TravelType e : TravelClass.TravelType.values())
        {
            if(this.travelClass.equals(e.displayName()))
                return e;
        }
        return null;
    }


    public Optional<LocalDate> getParsedDate()
    {
        if(this.departureDate.isEmpty() || this.departureDate == null) {
            return null;
        }
        else
            return Optional.of(LocalDate.parse(departureDate));
    }

}
