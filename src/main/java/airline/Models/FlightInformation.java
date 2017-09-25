package airline.Models;

import airline.BAL.*;
import airline.Utility.PricingXMLReader;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.Period;
import java.util.Map;
import java.util.Optional;

public class FlightInformation {
    private String source;
    private String destination;
    private String flightNumber;
    private Aeroplane aeroplane;
    private LocalDate departureDate;
    private Map<TravelClass.TravelType, Integer> noOfOccupiedSeatsPerClass;

    @Autowired
    private PricingXMLReader pricingXml = new PricingXMLReader();


    public FlightInformation() {

    }

    public FlightInformation(String source, String destination, String flightName, LocalDate departureDate,
                             Map<TravelClass.TravelType, Integer> noOfOccupiedSeatsPerClass) {
        this.source = source;
        this.destination = destination;
        this.flightNumber = flightName;
        this.departureDate = departureDate;
        this.noOfOccupiedSeatsPerClass = noOfOccupiedSeatsPerClass;
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

    public boolean validateDepartureDate(Optional<LocalDate> departureDate, TravelClass.TravelType travelType)
    {
        boolean validateDepartureDate = false;
        if(Optional.ofNullable(departureDate).equals(Optional.empty())) {
            if(this.departureDate.isEqual(LocalDate.now()) || this.departureDate.isAfter(LocalDate.now()))
                validateDepartureDate = true;
        }
        else {
            if(this.departureDate.equals(departureDate.get()))
                validateDepartureDate = true;
        }
        boolean validateDepartureDateforClasses = validateDepartureDateforClasses(travelType);
        return validateDepartureDate && validateDepartureDateforClasses;
    }

    private boolean validateDepartureDateforClasses(TravelClass.TravelType travelType)
    {
        int noOfDaysBeforeBookingStarts = pricingXml.
                getDaysWhenBookingIsAllowedBeforeDepartureDate(travelType.displayName()).get();
        switch (travelType)
        {
            case ECONOMY:
                return true;
            case BUSINESS:
            case FIRST:
                if(Period.between(this.departureDate, LocalDate.now()).getDays() <=
                        noOfDaysBeforeBookingStarts);
                    return true;

        }
        return false;

    }

    public boolean validateNumberOfAvailableSeats(TravelClass.TravelType travelType, int noOfRequestedSeats)
    {

        Integer noOfOccupiedSeats = 0;
        switch(travelType)
        {
            case BUSINESS:
                noOfOccupiedSeats = this.noOfOccupiedSeatsPerClass.get(TravelClass.TravelType.BUSINESS);
                break;
            case FIRST:
                noOfOccupiedSeats = this.noOfOccupiedSeatsPerClass.get(TravelClass.TravelType.FIRST);
                break;
            case ECONOMY:
                noOfOccupiedSeats = this.noOfOccupiedSeatsPerClass.get(TravelClass.TravelType.ECONOMY);
                break;
        }
        return (this.aeroplane.getNumberOfSeatsAvailable(travelType) -
                (noOfOccupiedSeats + noOfRequestedSeats) > 0 ? true : false);
    }

    public boolean validateSourceAndDestination(String source, String destination)
    {
        if(this.source.equals(source) && this.destination.equals(destination))
            return true;
        return false;
    }

    public double getFare(TravelClass.TravelType travelType,
                          int noOfRequestedSeats)
    {
        PriceProcessorFactory priceProcessorFactory = new PriceProcessorFactory();
        double fare = 0;
        PricingModel pricingModel = new PricingModel();
        pricingModel.setBaseFare(this.aeroplane.getBasefare(travelType));
        pricingModel.setNoOfRequestedSeats(noOfRequestedSeats);
        pricingModel.setDepartureDate(Optional.of(this.departureDate));
        pricingModel.setTotalCapacity(Optional.of(this.aeroplane.getNumberOfSeatsAvailable(travelType)));
        pricingModel.setNoOfOccupiedSeats(Optional.of(this.noOfOccupiedSeatsPerClass.get(travelType)));
        IPriceProcessor processor = priceProcessorFactory.getPriceProcessor(travelType,pricingModel);
        if(processor.getFare().isPresent())
            fare = processor.getFare().get();
        return fare;
    }
}