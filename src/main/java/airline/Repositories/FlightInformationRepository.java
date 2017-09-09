package airline.Repositories;

import airline.Models.Aeroplane;
import airline.Models.FlightInformation;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
@Repository
@Configuration(value = "FlightsRepository")
public class FlightInformationRepository {

    private List<FlightInformation> flightInformationList = new ArrayList<FlightInformation>();

    public  FlightInformationRepository()
    {
        populateFlightInformation();
    }

    public List<FlightInformation> getFlightInformation()
    {
        return flightInformationList;
    }

    public void populateFlightInformation()
    {
        int i= 0;
        flightInformationList.add(new FlightInformation("HYD","BOM","Vapasi-123",
                LocalDate.of(2017, Month.SEPTEMBER, 14)));
        flightInformationList.add(new FlightInformation("BOM","HYD","Vapasi-456",
                LocalDate.of(2017, Month.AUGUST, 21)));
        flightInformationList.add(new FlightInformation("LKN","BOM","Vapasi-987",
                LocalDate.of(2017, Month.SEPTEMBER, 19)));
        flightInformationList.add(new FlightInformation("LKN","BOM","Vapasi-100",
                LocalDate.of(2017, Month.SEPTEMBER, 5)));
        AeroplaneRepository aeroplaneRepository = new AeroplaneRepository();
        List<Aeroplane> aeroplanes =  aeroplaneRepository.getAeroplanes();
        for(FlightInformation flight : flightInformationList)
        {
            flight.setAeroplane(aeroplanes.get(i));
            i++;
        }

    }

}