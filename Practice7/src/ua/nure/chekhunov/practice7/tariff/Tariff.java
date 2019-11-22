package ua.nure.chekhunov.practice7.tariff;

/**
 * @author chekhunov.
 *
 */
public class Tariff implements Comparable<Tariff> {
	private String tariffName;
	private String operatorName;
	private double payroll;
	private CallPrice callPrices;
	private double smsPrice;
	private Parameters parameters;

	public Tariff(String tariffName, String operatorName, double payroll) {
		this.tariffName = tariffName;
		this.operatorName = operatorName;
		this.payroll = payroll;
	}

	/**
	 * Constructor.
	 */
	public Tariff() {
	}

	public void setOperatorName(String operatorName) {
		this.operatorName = operatorName;
	}

	public void setPayroll(double payroll) {
		this.payroll = payroll;
	}

	public void setTariffName(String tariffName) {
		this.tariffName = tariffName;
	}

	public void setCallPrices(CallPrice callPrice) {
		this.callPrices = callPrice;
	}

	public void setSmsPrice(double smsPrice) {
		this.smsPrice = smsPrice;
	}

	public String getOperatorName() {
		return operatorName;
	}

	public String getTariffName() {
		return tariffName;
	}

	public double getPayroll() {
		return payroll;
	}

	public CallPrice getCallPrices() {
		return callPrices;
	}

	public double getSmsPrice() {
		return smsPrice;
	}

	public void setParameters(Parameters parameters) {
		this.parameters = parameters;
	}

	public Parameters getParameters() {
		return parameters;
	}

	@Override
	public int compareTo(Tariff o) {
		int result = operatorName.compareTo(o.operatorName);
		if (result != 0) {
			return result;
		}
		result = o.operatorName.compareTo(operatorName);
		if (result != 0) {
			return result;
		}
		result = tariffName.compareTo(o.tariffName);
		if (result != 0) {
			return result;
		}
		result = o.tariffName.compareTo(tariffName);
		if (result != 0) {
			return result;
		}
		result = Double.compare(payroll, o.payroll);
		if (result != 0) {
			return result;
		}
		result = Double.compare(smsPrice, o.smsPrice);
		if (result != 0) {
			return result;
		}
		result = callPrices.compareTo(o.callPrices);
		if (result != 0) {
			return result;
		}
		result = parameters.compareTo(o.parameters);
		if (result != 0) {
			return result;
		}
		return 0;
	}

	@Override
	public String toString() {
		return "-------------------------\n" + "Tariff name: " + tariffName + "\nOperator name: " + operatorName
				+ "\nPayroll: " + payroll + "\n" + String.valueOf(callPrices) + "\nSms price: " + smsPrice + "\n"
				+ String.valueOf(parameters);
	}
}
