package application;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import entities.services.InstallmentService;
import entities.services.PaypalTaxService;
import entities.services.TaxService;
import model.entities.Contract;

public class Program {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub	
		
		Locale.setDefault(Locale.US);
		
		Scanner input = new Scanner(System.in);
		SimpleDateFormat sdf = new SimpleDateFormat("dd/mm/yyyy");
		
		System.out.println("Enter contract data");
		
		System.out.print("Number: ");
		int contractNumber = input.nextInt();
		
		input.nextLine();
		
		System.out.print("Date (dd/mm/yyyy): ");
		Date contractDate = sdf.parse(input.nextLine());		
		
		System.out.print("Contract value: ");
		Double contractValue = input.nextDouble();
		
		System.out.print("Enter number of installments: ");
		Integer numberInstallments = input.nextInt();		
		
		input.nextLine();		
		
		Contract contract = new Contract(contractNumber, contractDate, contractValue);
		
		//INVERSÃO DE CONTROLE/INJEÇÃO DE DEPENDÊNCIA
		TaxService taxService = new PaypalTaxService();
		
		InstallmentService installmentService = new InstallmentService(numberInstallments, contract, taxService);
		
		installmentService.processInstallments();
		
		System.out.println("Installments: ---------------");
		
		//System.out.println(installmentService.getInstallmentItems());
		
		System.out.println(installmentService);		
		
		input.close();	
		
	}

}
