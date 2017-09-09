package airline.Repositories;

import airline.Models.Place;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
@Configuration(value = "PlacesRepository")
public class PlaceRepository {

    private List<Place> places = new ArrayList<Place>();
    public  PlaceRepository()
    {
        populatePlaces();
    }

    public List<Place> getPlaces()
    {
        return places;
    }

    public void populatePlaces()
    {
        places.add(new Place("HYD"));
        places.add(new Place("BOM"));
        places.add(new Place("LKN"));

    }
}