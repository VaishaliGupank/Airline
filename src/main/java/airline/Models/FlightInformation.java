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

        double fare = 0;
        IPriceProcessor processor;
        PricingModel pricingModel;
        switch(travelType)
        {
            case BUSINESS:
                pricingModel = new PricingModel();
                pricingModel.setBaseFare(aeroplane.getBasefare(TravelClass.TravelType.BUSINESS));
                pricingModel.setNoOfRequestedSeats(noOfRequestedSeats);
                pricingModel.setDepartureDate(Optional.of(this.departureDate));
                processor = new BusinessClassPriceProcessor(pricingModel,pricingXml);
                if(processor.getFare().isPresent())
                fare = processor.getFare().get();
                break;
            case FIRST:
                pricingModel = new PricingModel();
                pricingModel.setBaseFare(aeroplane.getBasefare(TravelClass.TravelType.FIRST));
                pricingModel.setNoOfRequestedSeats(noOfRequestedSeats);
                pricingModel.setDepartureDate(Optional.of(this.departureDate));
                processor = new FirstClassPriceProcessor(pricingModel,pricingXml);
                if(processor.getFare().isPresent())
                    fare = processor.getFare().get();
                break;
            case ECONOMY:
                pricingModel = new PricingModel();
                pricingModel.setBaseFare(aeroplane.getBasefare(TravelClass.TravelType.ECONOMY));
                pricingModel.setTotalCapacity(Optional.of(aeroplane.getNumberOfSeatsAvailable(TravelClass.TravelType.ECONOMY)));
                pricingModel.setNoOfOccupiedSeats(Optional.of(this.noOfOccupiedSeatsPerClass.get(TravelClass.TravelType.ECONOMY)));
                pricingModel.setNoOfRequestedSeats(noOfRequestedSeats);
                processor = new EconomyClassPriceProcessor(pricingModel,pricingXml);
                if(processor.getFare().isPresent())
                    fare = processor.getFare().get();
                break;

        }

        return fare;
    }







}