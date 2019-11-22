package ua.nure.chekhunov.practice7.controller;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ua.nure.chekhunov.practice7.constants.XMLConstants;
import ua.nure.chekhunov.practice7.constants.XMLTariffConstants;
import ua.nure.chekhunov.practice7.tariff.CallPrice;
import ua.nure.chekhunov.practice7.tariff.Parameters;
import ua.nure.chekhunov.practice7.tariff.Tariff;
import ua.nure.chekhunov.practice7.util.Util;

/**
 * 
 * @author Chekhunov.
 *
 */
public class DOMController {
	// path to req. XML file
	private String xmlFilePath;
	private Document xmlDocument;
	// container for tariff objects
	private List<Tariff> tariffs;
	private Tariff tariff;

	public DOMController(String xmlFilePath) {
		this.xmlFilePath = xmlFilePath;
		tariffs = new ArrayList<>();
	}

	/**
	 * @return - tariff list
	 */
	public List<Tariff> getTariffs() {
		return tariffs;
	}

	/**
	 * 
	 * @param validate
	 * @param validationPath
	 * @throws SAXException
	 * @throws IOException
	 * @throws ParserConfigurationException
	 *             Parse XML for Tariffs objects executing
	 * @see tariff
	 */
	public void parseXML(boolean validate) {
		DocumentBuilderFactory dFactory = DocumentBuilderFactory.newInstance();
		dFactory.setNamespaceAware(true);
		if (validate) {
			try {
				dFactory.setFeature("http://xml.org/sax/features/validation", true);
				dFactory.setFeature("http://apache.org/xml/features/validation/schema", true);
			} catch (ParserConfigurationException e) {
				System.err.println(e.getLocalizedMessage());
			}
		}
		try {
			xmlDocument = dFactory.newDocumentBuilder().parse(xmlFilePath);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			System.err.println(e.getLocalizedMessage());
		}
		NodeList operators = xmlDocument.getElementsByTagName("tariff");
		for (int i = 0; i < operators.getLength(); i++) {
			NodeList operator = operators.item(i).getChildNodes();
			parse(operator);
			tariffs.add(tariff);

		}

	}
 
	/**
	 * @param operator
	 *                 - Parse each node and convert to Tariff
	 */
	private void parse(NodeList operator) {
		tariff = new Tariff();
		Node node;
		for (int i = 0; i < operator.getLength(); i++) {
			node = operator.item(i);
			if (node.getNodeName().equals(XMLTariffConstants.TARIFF_NAME)) {
				tariff.setTariffName(node.getTextContent());
			} else if (node.getNodeName().equals(XMLTariffConstants.OPERATOR_NAME)) {
				tariff.setOperatorName(node.getTextContent());
			} else if (node.getNodeName().equals(XMLTariffConstants.PAYROLL)) {
				tariff.setPayroll(Double.parseDouble(node.getTextContent()));
			} else if (node.getNodeName().equals(XMLTariffConstants.CALL_PRICE)) {
				tariff.setCallPrices(getCallPrice(node.getChildNodes()));
			} else if (node.getNodeName().equals(XMLTariffConstants.SMS_PRICE)) {
				tariff.setSmsPrice(Double.parseDouble(node.getTextContent()));
			} else if (node.getNodeName().equals(XMLTariffConstants.PARAMETERS)) {
				tariff.setParameters(getParameters(node.getChildNodes()));
			}
		}
	}
 
	/**
	 * Parse NodeList and convert it to CallPrice
	 * @param nodeList
	 * @return
	 */
	private static CallPrice getCallPrice(NodeList nodeList) {
		CallPrice callPrice = new CallPrice();
		Node node;
		for (int i = 0; i < nodeList.getLength(); i++) {
			node = nodeList.item(i);
			if (node.getNodeName().equals(XMLTariffConstants.WITHIN_NETWORK_CALL_PRICE)) {
				callPrice.setWithinNetworkCallPrice(Double.parseDouble(node.getTextContent()));
			} else if (node.getNodeName().equals(XMLTariffConstants.OUT_OF_NETWORK_CALL)) {
				callPrice.setOutOfNetworkCallPrice(Double.parseDouble(node.getTextContent()));
			} else if (node.getNodeName().equals(XMLTariffConstants.LAND_LINE_NUM_CALL_PRICE)) {
				callPrice.setLandLineNumCallPrice(Double.parseDouble(node.getTextContent()));
			}
		}
		return callPrice;
	}
 
	/**
	 * Parse NodeList and convert if to Parameters
	 * @param nodeList
	 * @return
	 */
	private static Parameters getParameters(NodeList nodeList) {
		Parameters parameters = new Parameters();
		Node node;
		for (int i = 0; i < nodeList.getLength(); i++) {
			node = nodeList.item(i);
			// checking for arguments
			if (node.getNodeName().equals(XMLTariffConstants.LOVELY_NUMBER_EXISTANCE)) {
				// getting tag's value
				parameters.setLovelyNumberExitance(Integer.parseInt(node.getTextContent()));
			} else if (node.getNodeName().equals(XMLTariffConstants.CONNECTION_PRICE)) {
				parameters.setConnectingPrice(Double.parseDouble(node.getTextContent()));
			} else if (node.getNodeName().equals(XMLTariffConstants.TARIFFICATION)) {
				parameters.setTariffication(Integer.parseInt(node.getTextContent()));
			}
		}
		return parameters;
	}

	/**
	 * @return
	 * @throws ParserConfigurationException
	 */
	private Document createDocument() throws ParserConfigurationException {
		// creating DocBuilderFactory
		DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
		builderFactory.setNamespaceAware(true);
		// initialize document builder
		DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
		// creating document object for future XML
		Document document = documentBuilder.newDocument();
		// creating root tag for XML
		Element root = document.createElement(XMLTariffConstants.TARIFFS);
		//исправление
		root.setAttribute("xmlns:tns", "http://www.example.org/input");
		root.setAttribute("xmlns:xsi", "http://www.w3.org/2001/XMLSchema-instance");
		root.setAttribute("xsi:schemaLocation", "http://www.example.org/input input.xsd");
		document.appendChild(root);
		Element element;
		// filling the document
		for (Tariff tariffObj : tariffs) {
			Element tariffElement = document.createElement(XMLTariffConstants.TARIFF);
			// splitting tariff values
			List<Object> listedTariff = Util.tariffToList(tariffObj);
			List<Object> listedCallPrice = listedTariff.subList(4, 7);
			List<Object> listedParameters = listedTariff.subList(7, listedTariff.size());
			for (int i = 0; i < 4; i++) {
				// adding tags and values to document
				element = document.createElement(XMLTariffConstants.TARIFF_ARRAY[i]);
				element.setTextContent(listedTariff.get(i).toString());
				tariffElement.appendChild(element);
			}
			// adding CallPrice
			tariffElement.appendChild(getCallPrice(document, listedCallPrice));
			// adding Parameters
			tariffElement.appendChild(getParameters(document, listedParameters));
			root.appendChild(tariffElement);
		}
		return document;

	}

	/**
	 * @param document
	 * @param callPrice
	 * @return
	 */
	private static Element getCallPrice(Document document, List<Object> callPrice) {
		// creating root for inner elements
		Element callPriceElement = document.createElement(XMLTariffConstants.CALL_PRICE);
		Element childElement;
		// filling element
		for (int i = 1; i <= XMLTariffConstants.CALL_PRICE_ARRAY.length; i++) {
			childElement = document.createElement(XMLTariffConstants.CALL_PRICE_ARRAY[i - 1]);
			childElement.setTextContent(callPrice.get(i - 1).toString());
			callPriceElement.appendChild(childElement);
		}
		// return filled node
		return callPriceElement;
	}

	/**
	 * @param document
	 * @param parameters
	 * @return
	 */
	private static Element getParameters(Document document, List<Object> parameters) {
		Element parametersElement = document.createElement(XMLTariffConstants.PARAMETERS);
		Element childElement;
		for (int i = 1; i <= XMLTariffConstants.PARAMETERS_ARRAY.length; i++) {
			childElement = document.createElement(XMLTariffConstants.PARAMETERS_ARRAY[i - 1]);
			childElement.setTextContent(parameters.get(i - 1).toString());
			parametersElement.appendChild(childElement);
		}
		return parametersElement;
	}

	/**
	 * @throws TransformerException
	 * @throws ParserConfigurationException
	 */
	public void saveToXML() throws TransformerException, ParserConfigurationException {

		StreamResult result = new StreamResult(new File(XMLConstants.DOM_RESULT));

		TransformerFactory tf = TransformerFactory.newInstance();
		javax.xml.transform.Transformer t = tf.newTransformer();
		t.setOutputProperty(OutputKeys.INDENT, "yes");

		t.transform(new DOMSource(createDocument()), result);
	}

	/**
	 * @param args
	 * @throws TransformerException
	 * @throws ParserConfigurationException
	 */
	public static void main(String[] args) throws TransformerException, ParserConfigurationException {
		DOMController parser = new DOMController("input.xml");
		parser.parseXML(true);
		parser.saveToXML();
	}
}
