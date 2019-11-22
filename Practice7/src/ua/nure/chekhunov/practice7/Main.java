package ua.nure.chekhunov.practice7;

import ua.nure.chekhunov.practice7.controller.DOMController;
import ua.nure.chekhunov.practice7.controller.SAXController;
import ua.nure.chekhunov.practice7.controller.STAXController;

/**
 * @author chekhunov
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {

		// DOM parser
		DOMController domController = new DOMController(args[0]);
		domController.parseXML(true);
		domController.saveToXML();

		// STAX parser
		STAXController stAXController = new STAXController(args[0]);
		stAXController.parse();
		stAXController.writeXML();

		// Sax parser
		SAXController saxController = new SAXController(args[0]);
		saxController.parse(true);
		saxController.writeXML();

	}
}