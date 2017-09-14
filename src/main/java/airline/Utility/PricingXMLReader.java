package airline.Utility;

import airline.BAL.PricingRule;
import airline.Models.TravelClass;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.*;

@Component("Utility Class")
public class PricingXMLReader {

    private Document pricingRulesXMLDocument;
    public PricingXMLReader()
    {
        LoadPricingRulesXML();
    }

    public void LoadPricingRulesXML() {
        try {
            File pricingRulesXmlFile = new File("PricingRules.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            pricingRulesXMLDocument = dBuilder.parse(pricingRulesXmlFile);
            pricingRulesXMLDocument.getDocumentElement().normalize();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Optional<Element> getPriceRule(String priceRuleName)
    {
        NodeList nList = pricingRulesXMLDocument.getElementsByTagName("PricingRule");
        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) nNode;
                if (eElement.getAttribute("name").equals(priceRuleName)) {
                    return Optional.of(eElement);
            }
            }
        }
        return Optional.empty();
     }

     public Optional<Integer> getDaysWhenBookingIsAllowedBeforeDepartureDate(String classType) {
         Element getPriceRule;
         int getDaysWhenBookingIsAllowedBeforeDepartureDate = 0;
         List<PricingRule> pricingRules = new ArrayList<PricingRule>();
         if (getPriceRule("OpenDaysBefore").isPresent()) {
             getPriceRule = getPriceRule("OpenDaysBefore").get();
             NodeList childNodes = getPriceRule.getElementsByTagName("TravelClass");
             for (int i = 0; i < childNodes.getLength(); i++) {
                 Node nNode1List = childNodes.item(i);
                 if (nNode1List.getNodeType() == Node.ELEMENT_NODE) {
                     Element eElement1 = (Element) nNode1List;
                     if (eElement1.getAttribute("name").equals(classType))
                         if(!eElement1.getTextContent().isEmpty())
                         getDaysWhenBookingIsAllowedBeforeDepartureDate = Integer.parseInt(
                                 eElement1.getTextContent());
                 }
             }
         }
         return Optional.of(getDaysWhenBookingIsAllowedBeforeDepartureDate);
     }

        public List<PricingRule> getPricingRulesForEconomy()
        {
            Element getPriceRule;
            List<PricingRule> pricingRules = new ArrayList<PricingRule>();
            if(getPriceRule("FareBasedOnAvailabilityOfSeats").isPresent())
            {
                 getPriceRule =  getPriceRule("FareBasedOnAvailabilityOfSeats").get();
                 NodeList childNodes = getPriceRule.getElementsByTagName("Rule");
                 for (int i = 0; i < childNodes.getLength(); i++) {
                    Node nNode1List = childNodes.item(i);
                  if (nNode1List.getNodeType() == Node.ELEMENT_NODE) {
                      Element eElement1 = (Element) nNode1List;
                    TravelClass.TravelType travelType = TravelClass.TravelType.ECONOMY;
                    float minPercentOfBookedSeats = Float.parseFloat(
                            eElement1.getElementsByTagName("minPercentOfBookedSeats").item(0).getTextContent());
                    float maxPercentOfBookedSeats = Float.parseFloat(
                            eElement1.getElementsByTagName("maxPercentOfBookedSeats").item(0).getTextContent());
                    float incrementPercentInFare = Float.parseFloat(
                            eElement1.getElementsByTagName("incrementPercentInFare").item(0).getTextContent());
                    PricingRule rule = new PricingRule(travelType, minPercentOfBookedSeats, maxPercentOfBookedSeats, incrementPercentInFare);
                    pricingRules.add(rule);

                  }
               }
            }
         return pricingRules;
        }

    public Map<String, Float> getPricingRulesForBusinessClass()
    {
        Map<String, Float> daysIncrementInFare = new HashMap<>();
        Element getPriceRule;
        if(getPriceRule("HikedFareOnDays").isPresent())
        {
            getPriceRule = getPriceRule("HikedFareOnDays").get();
            NodeList childNodes = getPriceRule.getElementsByTagName("TravelClass");
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node nNode1List = childNodes.item(i);
                if (nNode1List.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement1 = (Element) nNode1List;
                    if (eElement1.getAttribute("name").equals("Business")) {
                        NodeList daysNode = eElement1.getElementsByTagName("day");
                        for (int j = 0; j < daysNode.getLength(); j++) {
                            Element dayElement = (Element) daysNode.item(j);
                            Float incrementPercentInFare = Float.parseFloat(
                                    eElement1.getElementsByTagName("incrementPercentInFare").
                                            item(0).getTextContent());
                            String day = dayElement.getAttribute("name");
                            daysIncrementInFare.put(day, incrementPercentInFare);

                        }
                    }
                }
            }
        }
        return daysIncrementInFare;
    }


    public float getPercentIncrementInFareFirstClass()
    {
        float incrementPercentInFare = 0;
        Element getPriceRule;
        if(getPriceRule("EveryDayIncrementInFareAfterOpeningDay").isPresent())
        {
            getPriceRule = getPriceRule("EveryDayIncrementInFareAfterOpeningDay").get();
            NodeList childNodes = getPriceRule.getElementsByTagName("TravelClass");
            for (int i = 0; i < childNodes.getLength(); i++) {
                Node nNode1List = childNodes.item(i);
                        if (nNode1List.getNodeType() == Node.ELEMENT_NODE) {
                            Element eElement1 = (Element) nNode1List;
                            if(eElement1.getAttribute("name").equals("First")) {
                                 incrementPercentInFare = Float.parseFloat(
                                        eElement1.getElementsByTagName("incrementPercentInFare").
                                                item(0).getTextContent());

                            }
                        }
                    }
                }
        return incrementPercentInFare;
    }
}




