package airline.Controllers;

import airline.Models.FlightInformation;
import airline.Models.FlightSearchCriteria;
import airline.Services.FlightService;
import airline.ViewModels.FlightSearchViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by vaishali on 8/8/17.
 */
@Controller
public class FlightController {

    @Autowired
    private FlightService flightService;
    public FlightController()
    {

    }

    @RequestMapping("/airline")
    public String populateCitiesList(Model model)
    {
        populateDefaultModelAttributes(model);
        model.addAttribute( "flightSearchCriteria", new FlightSearchCriteria());
        return "flightSearch";
    }

    @RequestMapping(value = "/searchFlights" , method = RequestMethod.POST)
    public String searchFlights(@Valid @ModelAttribute(value="flightSearchCriteria") FlightSearchCriteria flightSearchCriteria,
                                BindingResult result,
                                        Model model)
    {
        if(!result.hasErrors())
        {
            List<FlightSearchViewModel> flistSearchViewModelList = new ArrayList<FlightSearchViewModel>();
            List<FlightInformation> listOfFlights = flightService.searchFlights(flightSearchCriteria);
            if(listOfFlights.size() > 0)
            {
                for (FlightInformation flight: listOfFlights)
                {
                    FlightSearchViewModel flightSearchViewModel = new FlightSearchViewModel();
                    flightSearchViewModel.setSource(flight.getSource());
                    flightSearchViewModel.setDestination(flight.getDestination());
                    flightSearchViewModel.setFlightNumber(flight.getFlightNumber());
                    flightSearchViewModel.setFare((flight.getFare(flightSearchCriteria.getParsedTravelClass(),
                            flightSearchCriteria.getNoOfPassengers().orElse(1))));
                    flistSearchViewModelList.add(flightSearchViewModel);

                }

            }
            model.addAttribute("flights", flistSearchViewModelList);
        }

        populateDefaultModelAttributes(model);
        return "flightSearch";
    }

    public void populateDefaultModelAttributes(Model model)
    {
        model.addAttribute("cities", flightService.getPlaces());
        model.addAttribute("travelClasses",flightService.getTravelClasses());

    }


}
