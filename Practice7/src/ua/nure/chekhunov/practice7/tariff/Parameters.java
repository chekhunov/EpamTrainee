package ua.nure.chekhunov.practice7.tariff;

import java.util.Arrays;
import java.util.List;

/**
 * @author chekhunov.
 *
 */
public class Parameters implements Comparable<Parameters> {
	private static final List<Integer> TARIFFICATION_EXITANCE = Arrays.asList(12, 60);
	private int tariffication;
	private int lovelyNumberExitance;
	private double connectingPrice;

	/**
	 * Constructor.
	 */
	public Parameters() {
	}

	public Parameters(int tariffication, int lovelyNumberExistance, double connectingPrice) {
		this.tariffication = validateTariffication(tariffication);
		this.lovelyNumberExitance = lovelyNumberExistance;
		this.connectingPrice = connectingPrice;
	}

	private static int validateTariffication(int enterTariffication) {
		if (TARIFFICATION_EXITANCE.contains(enterTariffication)) {
			return enterTariffication;
		}
		return 0;
	}

	public void setConnectingPrice(double connectingPrice) {
		this.connectingPrice = connectingPrice;
	}

	public void setLovelyNumberExitance(int lovelyNumberExitance) {
		this.lovelyNumberExitance = lovelyNumberExitance;
	}

	public void setTariffication(int tariffication) {
		this.tariffication = tariffication;
	}

	public double getConnectingPrice() {
		return connectingPrice;
	}

	public int getLovelyNumberExitance() {
		return lovelyNumberExitance;
	}

	public int getTariffication() {
		return tariffication;
	}

	@Override
	public String toString() {
		return "Parameters:\n\tTariffication: " + tariffication + "\n\tConnecting price: " + connectingPrice
				+ "\n\tLovely number existance: " + lovelyNumberExitance;
	}

	@Override
	public int compareTo(Parameters o) {
		int result = Integer.compare(tariffication, o.tariffication);
		if (result != 0) {
			return result;
		}
		result = Integer.compare(lovelyNumberExitance, o.lovelyNumberExitance);
		if (result != 0) {
			return result;
		}
		result = Double.compare(connectingPrice, o.connectingPrice);
		if (result != 0) {
			return result;
		}
		return 0;
	}
}
