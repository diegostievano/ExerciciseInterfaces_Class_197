package entities.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import model.entities.Contract;
import model.entities.Installment;

public class InstallmentService {
	private Integer numberOfInstallments;
	private Contract contract;	
	private Installment installment;
	private TaxService taxService;	
	
	public InstallmentService() {
		
	}
	
	public InstallmentService(Integer numberOfInstallments, Contract contract, TaxService taxService) throws ParseException {
		this.numberOfInstallments = numberOfInstallments;
		this.contract = contract;
		this.taxService = taxService;
	}	
	
	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public Integer getNumberOfInstallments() {
		return numberOfInstallments;
	}

	public void setNumberOfInstallments(Integer numberOfInstallments) {
		this.numberOfInstallments = numberOfInstallments;
	}	

	public void processInstallments() throws ParseException {
		
		Double amountValue = contract.getTotalValue() / 3;		
		
		for(int i = 1; i <= numberOfInstallments; i++) {			
			Double auxAmount = taxService.monthValue(amountValue, i);
			
			Date dueDate = addMonth(contract.getDate(), i);
			
			installment = new Installment(dueDate, auxAmount);
			
			contract.getInstallments().add(installment);
			
			//installmentItems.add(installment);
		}	
	}
	
	private Date addMonth(Date date, int i) {
		/*CONFORME EXEMPLO DA AULA ISSO NÃO FUNCIONA*/
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);		
		cal.add(Calendar.MONTH, 4);		
		return cal.getTime();
	}
	
	@Override
	public String toString() {
		
		StringBuilder sb = new StringBuilder();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		
		for(Installment list : contract.getInstallments()) {
			//sb.append(sdf.format(list.getDueDate()) + " - " + list.getAmount());
			sb.append(sdf.format(list.getDueDate()) + " - " + String.format("%.2f", list.getAmount()) + "\n");
		}		
		
		return sb.toString();
	}
	
	

}
