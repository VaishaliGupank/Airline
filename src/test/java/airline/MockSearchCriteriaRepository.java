package airline;
import airline.Models.FlightSearchCriteria;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MockSearchCriteriaRepository {

    public MockSearchCriteriaRepository()
    {
        readTestData();
    }

    private Map<String,FlightSearchCriteria> testData = new HashMap<String,FlightSearchCriteria>();

    public Map<String, FlightSearchCriteria> getTestData() {
        return testData;
    }


    public void readTestData() {
        try {
            File fXmlFile = new File("SearchCriteria.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();
            NodeList nList = doc.getElementsByTagName("TestCase");
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;
                    FlightSearchCriteria flightSearchCriteria = new FlightSearchCriteria();
                    flightSearchCriteria.setSource(eElement.getElementsByTagName("source").item(0).getTextContent());
                    flightSearchCriteria.setDestination(eElement.getElementsByTagName("destination").item(0).getTextContent());
                    Element node = (Element) eElement.getElementsByTagName("noOfPassengers").item(0);
                    if(node == null || node.getTextContent().equals("") || node.getTextContent().equals("0")) {
                        Optional<Integer> noOfPassengers = Optional.ofNullable(null);
                        flightSearchCriteria.setNoOfPassengers(noOfPassengers);
                    }
                    else
                    flightSearchCriteria.setNoOfPassengers(Optional.of
                            (Integer.parseInt(eElement.getElementsByTagName("noOfPassengers").item(0).getTextContent())));
                    flightSearchCriteria.setDepartureDate(eElement.getElementsByTagName("departureDate").item(0).getTextContent());
                    flightSearchCriteria.setTravelClass(eElement.getElementsByTagName("travelClass").item(0).getTextContent());
                    testData.put(((Element) nNode).getAttribute("name"),flightSearchCriteria);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
