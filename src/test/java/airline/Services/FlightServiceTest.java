package airline.Services;

import airline.MockFlightInformationRepository;
import airline.MockSearchCriteriaRepository;
import airline.Models.FlightInformation;
import airline.Models.FlightSearchCriteria;
import airline.Repositories.FlightInformationRepository;
import airline.Repositories.PlaceRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
@SpringBootTest
@RunWith(SpringRunner.class)
public class FlightServiceTest {

    @Autowired
    private FlightService flightService;

    private MockFlightInformationRepository mockFlightInfoRepo = new MockFlightInformationRepository();
    private MockSearchCriteriaRepository mockSearchCriteriaRepository = new MockSearchCriteriaRepository();

    @MockBean
    @Qualifier("FlightsRepository")
    private FlightInformationRepository flightInfoRepo;
    @MockBean
    @Qualifier("PlacesRepository")
    private PlaceRepository placeRepository;

    private List<FlightInformation> listOfMockFlights = mockFlightInfoRepo.getMockFlightInformationList();
    private Map<String, FlightSearchCriteria> listOfSearchCriteria;


    @Before
    public void setUp() throws Exception {
        listOfSearchCriteria = mockSearchCriteriaRepository.getTestData();
    }

    @Test
    public void testSearchFlightsWithSourceAndDestination() throws Exception {

        Mockito.when(flightInfoRepo.getFlightInformation()).thenReturn(listOfMockFlights);
        List<FlightInformation> flightInfoList = flightService.searchFlightsWithSourceAndDestination("LKN", "BOM");
        assertEquals(2, flightInfoList.size());
    }

    @Test
    public void testSearchFlightsWithSourceAndDestinationWithNullDepartureDateAndDefaultNumberOfPassengers() throws Exception {

        Mockito.when(flightInfoRepo.getFlightInformation()).thenReturn(listOfMockFlights);
        List<FlightInformation> flightInfoList = flightService.searchFlights(listOfSearchCriteria.
                get("testSearchFlightsWithSourceAndDestinationWithNullDepartureDateAndDefaultNumberOfPassengers"));
        assertEquals(1, flightInfoList.size());
    }

    @Test
    public void testSearchFlightsWithSourceAndDestinationWithNullDepartureDateAndNumberOfPassengers() throws Exception {

        Mockito.when(flightInfoRepo.getFlightInformation()).thenReturn(listOfMockFlights);
        List<FlightInformation> flightInfoList = flightService.searchFlights(
                listOfSearchCriteria.
                        get("testSearchFlightsWithSourceAndDestinationWithNullDepartureDateAndNumberOfPassengers"));
        assertEquals(0, flightInfoList.size());
    }


    @Test
    public void testSearchFlightsWithSourceAndDestinationWithDepartureDateAndNumberOfPassengers() throws Exception {

        Mockito.when(flightInfoRepo.getFlightInformation()).thenReturn(listOfMockFlights);
        FlightSearchCriteria searchCriteria =  listOfSearchCriteria.
                get("testSearchFlightsWithSourceAndDestinationWithDepartureDateAndNumberOfPassengers");
        searchCriteria.setDepartureDate(LocalDate.now().toString());
        List<FlightInformation> flightInfoList = flightService.searchFlights(searchCriteria);
        assertEquals(0, flightInfoList.size());
    }

    @Test
    public void testSearchFlightsWithSourceAndDestinationWithDepartureDateAndDefaultNumberOfPassengers() throws Exception {

        Mockito.when(flightInfoRepo.getFlightInformation()).thenReturn(listOfMockFlights);
        FlightSearchCriteria searchCriteria =  listOfSearchCriteria.
                get("testSearchFlightsWithSourceAndDestinationWithDepartureDateAndDefaultNumberOfPassengers");
        searchCriteria.setDepartureDate(LocalDate.now().toString());
        List<FlightInformation> flightInfoList = flightService.searchFlights(searchCriteria);
        assertEquals(0, flightInfoList.size());
    }

    @Test
    public void testSearchFlightsWithSourceAndDestinationWithDepartureDateAndNumberOfPassengersBusinessClass() throws Exception {

        Mockito.when(flightInfoRepo.getFlightInformation()).thenReturn(listOfMockFlights);
        FlightSearchCriteria searchCriteria =  listOfSearchCriteria.
                get("testSearchFlightsWithSourceAndDestinationWithDepartureDateAndNumberOfPassengersBusinessClass");
        searchCriteria.setDepartureDate(LocalDate.now().toString());
        List<FlightInformation> flightInfoList = flightService.searchFlights(searchCriteria);
        assertEquals(0, flightInfoList.size());
    }

    @Test
    public void testSearchFlightsWithSourceAndDestinationWithDepartureDateAndDefaultNumberOfPassengersBusinessClass() throws Exception {

        Mockito.when(flightInfoRepo.getFlightInformation()).thenReturn(listOfMockFlights);
        FlightSearchCriteria searchCriteria =  listOfSearchCriteria.
                get("testSearchFlightsWithSourceAndDestinationWithDepartureDateAndDefaultNumberOfPassengersBusinessClass");
        searchCriteria.setDepartureDate(LocalDate.now().toString());
        List<FlightInformation> flightInfoList = flightService.searchFlights(searchCriteria);
        assertEquals(0, flightInfoList.size());
    }

    @Test
    public void testSearchFlightsWithSourceAndDestinationWithNullDepartureDateAndNumberOfPassengersBusinessClass() throws Exception {

        Mockito.when(flightInfoRepo.getFlightInformation()).thenReturn(listOfMockFlights);
        List<FlightInformation> flightInfoList = flightService.searchFlights(listOfSearchCriteria.
                get("testSearchFlightsWithSourceAndDestinationWithNullDepartureDateAndNumberOfPassengersBusinessClass"));
        assertEquals(1, flightInfoList.size());
    }
}