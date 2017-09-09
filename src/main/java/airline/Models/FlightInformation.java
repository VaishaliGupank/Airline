package airline.Models;

import java.time.LocalDate;
import java.util.Optional;

public class FlightInformation {
    private String source;
    private String destination;
    private String flightNumber;
    private Aeroplane aeroplane;
    private LocalDate departureDate;
    private int noOfSeatsBookedEconomy;
    private int noOfSeatsBookedFirst;
    private int noOfSeatsBookedBusiness;

    public FlightInformation() {

    }

    public FlightInformation(String source, String destination, String flightName, LocalDate departureDate) {
        this.source = source;
        this.destination = destination;
        this.flightNumber = flightName;
        this.departureDate = departureDate;
    }


    public void setAeroplane(Aeroplane aeroplane) {
        this.aeroplane = aeroplane;
    }

    public String getSource() {
        return this.source;
    }

    public String getDestination() {
        return this.destination;
    }

    public String getFlightNumber() {
        return this.flightNumber;
    }

    public Aeroplane getAeroplane() {
        return aeroplane;
    }






    public boolean validateDepartureDate(Optional<LocalDate> departureDate)
    {
        if(Optional.ofNullable(departureDate).equals(Optional.empty()))
        {
            if(this.departureDate.isEqual(LocalDate.now()) || this.departureDate.isAfter(LocalDate.now()))
                return true;

        }
        else
        {
            if(this.departureDate.equals(departureDate.get()))
                return true;
        }

        return false;
    }

    public boolean validateNumberOfAvailableSeats(TravelClass.TravelType travelType, int noOfBookingSeats)
    {
        int availableSeats = 0;
        switch(travelType)
        {
            case BUSINESS:
                availableSeats = this.aeroplane.getNumberOfSeatsAvailable(travelType,noOfSeatsBookedBusiness) - noOfBookingSeats;
            case FIRST:
                availableSeats = this.aeroplane.getNumberOfSeatsAvailable(travelType,noOfSeatsBookedFirst) - noOfBookingSeats;
            case ECONOMY:
                availableSeats = this.aeroplane.getNumberOfSeatsAvailable(travelType,noOfSeatsBookedEconomy) - noOfBookingSeats;
        }
        return (availableSeats >= 0 ? true : false);
    }

    public boolean validateSourceAndDestination(String source, String destination)
    {
        if(this.source.equals(source) && this.destination.equals(destination))
            return true;
        return false;
    }





}