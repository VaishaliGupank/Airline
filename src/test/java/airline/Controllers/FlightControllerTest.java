package airline.Controllers;

import airline.MockFlightInformationRepository;
import airline.Services.FlightService;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(value = FlightController.class)
public class FlightControllerTest {
    @Autowired
    private MockMvc mockMVC;

    @MockBean
    private FlightService flightService;

    private MockFlightInformationRepository mockFlightInfoRepo = new MockFlightInformationRepository();



    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void searchFlights() throws Exception {

       Mockito.when(flightService.searchFlights(Mockito.anyObject())).
                thenReturn(mockFlightInfoRepo.getMockFlightInformationList());
      //  mockMVC.perform(MockMvcRequestBuilders.post("/searchFlights").accept(MediaType.TEXT_HTML))
        //       .andExpect(view().name("flightSearch"));

    }

}