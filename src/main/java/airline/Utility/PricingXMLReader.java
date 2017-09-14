package airline.Utility;

import airline.Models.PricingRule;
import airline.Models.TravelClass;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XmlReader {

    private Document pricingRulesXMLDocument;
    public XmlReader()
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

        public List<PricingRule> getPricingRulesForEconomy()
        {
            List<PricingRule> pricingRules = new ArrayList<PricingRule>();
            NodeList nList = pricingRulesXMLDocument.getElementsByTagName("PricingRule");
            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node nNode = nList.item(temp);
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {

                    Element eElement = (Element) nNode;

                    if (eElement.getAttribute("name").equals("FareBasedOnAvailabilityOfSeats")) {

                        NodeList childNodes = eElement.getElementsByTagName("Rule");
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
                }
            }
            return pricingRules;
        }
}



