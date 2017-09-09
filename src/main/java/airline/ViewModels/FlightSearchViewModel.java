package airline.ViewModels;

public class FlightSearchViewModel {
    String source;
    String destination;
    String flightNumber;
    double fare;

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public double getFare() {
        return fare;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public void setFlightNumber(String flightNumber) {
        this.flightNumber = flightNumber;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

}
