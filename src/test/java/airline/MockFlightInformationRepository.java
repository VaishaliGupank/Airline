package airline;

import airline.Models.Aeroplane;
import airline.Models.FlightInformation;
import airline.Models.TravelClass;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MockFlightInformationRepository  {

    private List<FlightInformation> mockFlightInfoRepo = new ArrayList<FlightInformation>();

    public MockFlightInformationRepository()
    {
        populateFlightInformation();
    }

    public List<FlightInformation> getMockFlightInformationList()
    {
        return this.mockFlightInfoRepo;
    }

    public void populateFlightInformation()
    {

        HashMap<TravelClass.TravelType, Integer> noofOccupiedSeats = new HashMap<TravelClass.TravelType,Integer>();
        noofOccupiedSeats.put(TravelClass.TravelType.ECONOMY,12);
        noofOccupiedSeats.put(TravelClass.TravelType.FIRST,0);
        noofOccupiedSeats.put(TravelClass.TravelType.BUSINESS,0);
        mockFlightInfoRepo.add(new FlightInformation("HYD","BOM","Vapasi-123",
                LocalDate.of(2017, Month.SEPTEMBER, 14),noofOccupiedSeats));
        mockFlightInfoRepo.get(0).setAeroplane(populateAeroplanes(0));
        mockFlightInfoRepo.add(new FlightInformation("BOM","HYD","Vapasi-456",
                LocalDate.of(2017, Month.AUGUST, 21),noofOccupiedSeats));
        mockFlightInfoRepo.get(1).setAeroplane(populateAeroplanes(1));
        mockFlightInfoRepo.add(new FlightInformation("LKN","BOM","Vapasi-987",
                LocalDate.of(2017, Month.SEPTEMBER, 19),noofOccupiedSeats));
        mockFlightInfoRepo.get(2).setAeroplane(populateAeroplanes(2));
        mockFlightInfoRepo.add(new FlightInformation("LKN","BOM","Vapasi-100",
                LocalDate.of(2017, Month.SEPTEMBER, 5),noofOccupiedSeats));
        mockFlightInfoRepo.get(3).setAeroplane(populateAeroplanes(3));

    }

    public Aeroplane populateAeroplanes(int i)
    {
        List<Aeroplane> aeroplanes = new ArrayList<Aeroplane>();
        aeroplanes.add(new Aeroplane(1, "Boeing 777-200LR(77L)", getTravelClassDataForBoing()));
        aeroplanes.add(new Aeroplane(2, "Airbus A319" , getTravelClassDataFor319()));
        aeroplanes.add(new Aeroplane(3, "Airbus A321", getTravelClassDatafor321()));
        aeroplanes.add(new Aeroplane(4, "Boeing 777-200LR(77L)", getTravelClassDataForBoing()));
        return aeroplanes.get(i);
    }

    public List<TravelClass> getTravelClassDataForBoing()
    {
        List<TravelClass> travelClassList =  new ArrayList<TravelClass>();
        TravelClass classInfoForEconomy = new TravelClass(TravelClass.TravelType.ECONOMY, 195,6000);
        TravelClass classInfoForBusiness = new TravelClass(TravelClass.TravelType.BUSINESS, 35,13000);
        TravelClass classInfoForFirst = new TravelClass(TravelClass.TravelType.FIRST, 8,20000);
        travelClassList.add(classInfoForEconomy);
        travelClassList.add(classInfoForBusiness);
        travelClassList.add(classInfoForFirst);
        return  travelClassList;
    }


    public List<TravelClass> getTravelClassDataFor319()
    {
        List<TravelClass> travelClassList =  new ArrayList<TravelClass>();
        TravelClass classInfoForEconomy = new TravelClass(TravelClass.TravelType.ECONOMY, 144,4000);
        TravelClass classInfoForBusiness = new TravelClass(TravelClass.TravelType.BUSINESS, 0,0);
        TravelClass classInfoForFirst = new TravelClass(TravelClass.TravelType.FIRST, 0,0);
        travelClassList.add(classInfoForEconomy);
        travelClassList.add(classInfoForBusiness);
        travelClassList.add(classInfoForFirst);
        return  travelClassList;
    }

    public List<TravelClass> getTravelClassDatafor321()
    {
        List<TravelClass> travelClassList =  new ArrayList<TravelClass>();
        TravelClass classInfoForEconomy = new TravelClass(TravelClass.TravelType.ECONOMY, 25,5000);
        TravelClass classInfoForBusiness = new TravelClass(TravelClass.TravelType.BUSINESS, 20,10000);
        TravelClass classInfoForFirst = new TravelClass(TravelClass.TravelType.FIRST, 0,0);
        travelClassList.add(classInfoForEconomy);
        travelClassList.add(classInfoForBusiness);
        travelClassList.add(classInfoForFirst);
        return  travelClassList;
    }

}
