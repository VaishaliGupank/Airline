package airline.Models;

import java.util.List;
import java.util.Optional;

public class Aeroplane {

    private int aeroplaneId;
    private String modelNumber;
    private int noOfSeats;
    private List<TravelClass> travelClasses;

    public Aeroplane(int aeroplaneId, String modelNumber, List<TravelClass> travelClasses)
    {
        this.aeroplaneId = aeroplaneId;
        this.modelNumber = modelNumber;
        this.travelClasses = travelClasses;
    }

    public List<TravelClass> getTravelClasses() {
        return travelClasses;
    }

    public int getNumberOfSeatsAvailable(TravelClass.TravelType travelType , int noOfSeatsBooked) {
        Optional<TravelClass> travelClass = this.getTravelClasses().stream().
                filter(plane -> plane.getTravelClass().equals(travelType)).findFirst();
        if(travelClass.isPresent())
        {
            return (travelClass.get().getNoOfSeats() - noOfSeatsBooked);
        }

        return 0;
    }

    public double getBasefare(TravelClass.TravelType travelType)
    {
        Optional<TravelClass> travelClass = this.getTravelClasses().stream().
                filter(plane -> plane.getTravelClass().equals(travelType)).findFirst();
        if(travelClass.isPresent())
        {
            return (travelClass.get().getBasePrice());
        }

        return 0;
    }


}

