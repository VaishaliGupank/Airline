package airline.Services;

import airline.Models.FlightInformation;
import airline.Models.FlightSearchCriteria;
import airline.Models.Place;
import airline.Models.TravelClass;
import airline.Repositories.FlightInformationRepository;
import airline.Repositories.PlaceRepository;
import airline.ViewModels.FlightSearchViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class FlightService {

    @Autowired @Qualifier("FlightsRepository")
    private FlightInformationRepository flightsInformationRepository;
    @Autowired @Qualifier("PlacesRepository")
    private PlaceRepository placesRepository;

    public FlightService() {

    }


    public List<FlightInformation> getFlightInformation() {
        return flightsInformationRepository.getFlightInformation();
    }

    public List<Place> getPlaces() {
        return placesRepository.getPlaces();

    }


    public List<TravelClass.TravelType> getTravelClasses()
    {
        return Arrays.asList(TravelClass.TravelType.values());
    }


    public List<FlightInformation> searchFlightsWithSourceAndDestination(String source, String destination) {
        return flightsInformationRepository.getFlightInformation().stream()
                .filter(flight -> flight.validateSourceAndDestination(source,destination) == true)
                .collect(Collectors.toList());
    }

    public List<FlightInformation> searchFlights(FlightSearchCriteria searchCriteria)
    {
        List<FlightSearchViewModel> flistSearchViewModelList = new ArrayList<FlightSearchViewModel>();
        List<FlightInformation> getFlights;
        getFlights = searchFlightsWithSourceAndDestination(searchCriteria.getSource(),
                searchCriteria.getDestination()).stream()
                    .filter(flight -> flight.validateDepartureDate(searchCriteria.getParsedDate(),
                            searchCriteria.getParsedTravelClass()) == true)
                    .filter(flight ->
                            flight.validateNumberOfAvailableSeats(searchCriteria.getParsedTravelClass(),
                                    searchCriteria.getNoOfPassengers().orElse(1)) == true)
                    .collect(Collectors.toList());
        return getFlights;
    }




}



