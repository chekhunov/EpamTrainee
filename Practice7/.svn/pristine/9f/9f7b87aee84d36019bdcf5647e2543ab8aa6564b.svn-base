package ua.nure.chekhunov.practice7.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

import ua.nure.chekhunov.practice7.constants.XMLConstants;
import ua.nure.chekhunov.practice7.constants.XMLTariffConstants;
import ua.nure.chekhunov.practice7.tariff.CallPrice;
import ua.nure.chekhunov.practice7.tariff.Parameters;
import ua.nure.chekhunov.practice7.tariff.Tariff;
import ua.nure.chekhunov.practice7.util.Util;

/**
 * @author chekhunov.
 *
 */
public class SAXController extends DefaultHandler {

	private static class ParserBooleanContainer {
		private static boolean tariffName;
		private static boolean operatorName;
		private static boolean payroll;
		private static boolean callPrice;
		private static boolean inNetworkCall;
		private static boolean outNetworkCall;
		private static boolean lanadLineCall;
		private static boolean smsPrice;
		private static boolean parameters;
		private static boolean lovelyNumberExistance;
		private static boolean tariffication;
		private static boolean connectingPrice;
	}

	private String xmlFilePath;
	// container
	private List<Tariff> tariffs;
	private Tariff tariff;
	// parameters for tariff object
	private Parameters parameters;
	// CallPrice for tariff object
	private CallPrice callPrice;

	public SAXController(String xmlFlePath) {
		this.xmlFilePath = xmlFlePath;
		tariffs = new ArrayList<>();
	}

	/**
	 * @param validate
	 */
	public void parse(boolean validate) {
		// obtaining factory
		SAXParserFactory factory = SAXParserFactory.newInstance();
		factory.setNamespaceAware(true);

		if (validate) {
			factory.setValidating(true);
			try {
				factory.setFeature(XMLConstants.FEATURE_TURN_VALIDATION_ON, true);
				factory.setFeature(XMLConstants.FEATURE_TURN_SCHEMA_VALIDATION_ON, true);
			} catch (SAXNotRecognizedException | SAXNotSupportedException | ParserConfigurationException e) {
				e.printStackTrace();
			}
		}
		javax.xml.parsers.SAXParser parser;
		try {
			// obtaining parser
			parser = factory.newSAXParser();
			parser.parse(xmlFilePath, this);
		} catch (SAXException | IOException | ParserConfigurationException e) {
			System.err.println(e.getLocalizedMessage());
		}
	}

	@Override
	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if (qName.equals(XMLTariffConstants.TARIFF)) {
			tariff = new Tariff();
		} else if (qName.equals(XMLTariffConstants.TARIFF_NAME)) {
			ParserBooleanContainer.tariffName = true;
		} else if (qName.equals(XMLTariffConstants.OPERATOR_NAME)) {
			ParserBooleanContainer.operatorName = true;
		} else if (qName.equals(XMLTariffConstants.PAYROLL)) {
			ParserBooleanContainer.payroll = true;
		} else if (qName.equals(XMLTariffConstants.CALL_PRICE)) {
			callPrice = new CallPrice();
			ParserBooleanContainer.callPrice = true;
		} else if (qName.equals(XMLTariffConstants.WITHIN_NETWORK_CALL_PRICE)) {
			ParserBooleanContainer.inNetworkCall = true;
		} else if (qName.equals(XMLTariffConstants.OUT_OF_NETWORK_CALL)) {
			ParserBooleanContainer.outNetworkCall = true;
		} else if (qName.equals(XMLTariffConstants.LAND_LINE_NUM_CALL_PRICE)) {
			ParserBooleanContainer.lanadLineCall = true;
		} else if (qName.equals(XMLTariffConstants.SMS_PRICE)) {
			ParserBooleanContainer.smsPrice = true;
		} else if (qName.equals(XMLTariffConstants.PARAMETERS)) {
			parameters = new Parameters();
			ParserBooleanContainer.parameters = true;
		} else if (qName.equals(XMLTariffConstants.LOVELY_NUMBER_EXISTANCE)) {
			ParserBooleanContainer.lovelyNumberExistance = true;
		} else if (qName.equals(XMLTariffConstants.TARIFFICATION)) {
			ParserBooleanContainer.tariffication = true;
		} else if (qName.equals(XMLTariffConstants.CONNECTION_PRICE)) {
			ParserBooleanContainer.connectingPrice = true;
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) throws SAXException {
		if (qName.equals(XMLTariffConstants.TARIFF)) {
			if (!Objects.isNull(tariff)) {
				tariffs.add(tariff);
			}

		} else if (qName.equals(XMLTariffConstants.TARIFF_NAME)) {
			ParserBooleanContainer.tariffName = false;
		} else if (qName.equals(XMLTariffConstants.OPERATOR_NAME)) {
			ParserBooleanContainer.operatorName = false;
		} else if (qName.equals(XMLTariffConstants.PAYROLL)) {
			ParserBooleanContainer.payroll = false;
		} else if (qName.equals(XMLTariffConstants.CALL_PRICE)) {
			tariff.setCallPrices(callPrice);
			ParserBooleanContainer.callPrice = false;
		} else if (qName.equals(XMLTariffConstants.WITHIN_NETWORK_CALL_PRICE)) {
			ParserBooleanContainer.inNetworkCall = false;
		} else if (qName.equals(XMLTariffConstants.OUT_OF_NETWORK_CALL)) {
			ParserBooleanContainer.outNetworkCall = false;
		} else if (qName.equals(XMLTariffConstants.LAND_LINE_NUM_CALL_PRICE)) {
			ParserBooleanContainer.lanadLineCall = false;
		} else if (qName.equals(XMLTariffConstants.SMS_PRICE)) {
			ParserBooleanContainer.smsPrice = false;
		} else if (qName.equals(XMLTariffConstants.PARAMETERS)) {
			tariff.setParameters(parameters);
			ParserBooleanContainer.parameters = false;
		} else if (qName.equals(XMLTariffConstants.LOVELY_NUMBER_EXISTANCE)) {
			ParserBooleanContainer.lovelyNumberExistance = false;
		} else if (qName.equals(XMLTariffConstants.TARIFFICATION)) {
			ParserBooleanContainer.tariffication = false;
		} else if (qName.equals(XMLTariffConstants.CONNECTION_PRICE)) {
			ParserBooleanContainer.connectingPrice = false;
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) throws SAXException {
		String value = new String(ch, start, length).trim();
		if (!value.isEmpty()) {
			if (ParserBooleanContainer.tariffName) {
				tariff.setTariffName(value);
			} else if (ParserBooleanContainer.operatorName) {
				tariff.setOperatorName(value);
			} else if (ParserBooleanContainer.payroll) {
				tariff.setPayroll(Double.parseDouble(value));
			} else if (ParserBooleanContainer.callPrice) {
				if (!setCallPrice(value)) {
					callPrice = new CallPrice();
				}
			} else if (ParserBooleanContainer.smsPrice) {
				tariff.setSmsPrice(Double.parseDouble(value));
			} else if (ParserBooleanContainer.parameters) {
				if (!setParameters(value)) {
					parameters = new Parameters();
				}
			}
		}
	}

	private boolean setParameters(String s) {

		if (ParserBooleanContainer.lovelyNumberExistance) {
			parameters.setLovelyNumberExitance(Integer.parseInt(s));
			return true;
		} else if (ParserBooleanContainer.tariffication) {
			parameters.setTariffication(Integer.parseInt(s));
			return true;
		} else if (ParserBooleanContainer.connectingPrice) {
			parameters.setConnectingPrice(Double.parseDouble(s));
			return true;
		}
		return false;
	}

	private boolean setCallPrice(String s) {
		if (ParserBooleanContainer.inNetworkCall) {
			callPrice.setWithinNetworkCallPrice(Double.parseDouble(s));
			return true;
		} else if (ParserBooleanContainer.outNetworkCall) {
			callPrice.setOutOfNetworkCallPrice(Double.parseDouble(s));
			return true;
		} else if (ParserBooleanContainer.lanadLineCall) {
			callPrice.setLandLineNumCallPrice(Double.parseDouble(s));
			return true;
		}
		return false;
	}

	@Override
	public void error(SAXParseException e) throws SAXException {
		super.error(e);
	}

	public void writeXML() throws JAXBException, FileNotFoundException {
		tariffs.sort(new Comparator<Tariff>() {
			@Override
			public int compare(Tariff o1, Tariff o2) {
				return o2.compareTo(o1);
			}
		});
		try {
			Util.writeSaxStax(tariffs, XMLConstants.SAX_RESULT);
		} catch (IOException | XMLStreamException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 * @throws FileNotFoundException
	 * @throws JAXBException
	 */
	public static void main(String[] args) throws FileNotFoundException, JAXBException {
		SAXController saxParser = new SAXController("input.xml");
		saxParser.parse(true);
		saxParser.writeXML();
	}
}
