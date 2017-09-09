package airline.Models;

import airline.MockFlightInformationRepository;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.Assert.assertEquals;

@SpringBootTest
public class FlightInformationTest {


    private MockFlightInformationRepository mockFlightInfoRepo = new MockFlightInformationRepository();

    @Test
    public void getNumberOfSeatsAvailable() throws Exception {
        FlightInformation flightInfo = mockFlightInfoRepo.getMockFlightInformationList().get(0);
        boolean isAvailableSeats = flightInfo.validateNumberOfAvailableSeats(TravelClass.TravelType.ECONOMY,1);
        assertEquals(true,isAvailableSeats);
    }

}