package entities.services;

public class PaypalTaxService implements TaxService {
	private Double tax = 0.02;
	private Double monthTax = 0.01;
	@Override
	public double monthValue(double amount, int months) {
		Double auxAmount = amount * monthTax * months;
		auxAmount += amount;
		auxAmount += (auxAmount * tax);
		
		return auxAmount;
		
	}
	

}
