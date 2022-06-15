package com.mylogistic.app;

import java.util.*;

import com.mylogistic.deliveryItem.Package;
import com.mylogistic.factory.Logistics;
import com.mylogistic.factory.RoadLogistics;
import com.mylogistic.factory.SeaLogistics;

import transaction.Transaction;



public class Program {

	private static final Map<Integer, Logistics> LOGISTIC_FACTORIES = new HashMap<>();
	private static Vector<Transaction> transactions = new Vector<Transaction>();
	
	static Scanner in = new Scanner(System.in);
	
	static { 
		LOGISTIC_FACTORIES.put(1, new RoadLogistics());
		LOGISTIC_FACTORIES.put(2, new SeaLogistics());
	}; 
	
	static String getShippingOptions() {
		ArrayList<String> optionString = new ArrayList<>();
				
		Iterator<Map.Entry<Integer, Logistics>> it = LOGISTIC_FACTORIES.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, Logistics> e = it.next();
			optionString.add( e.getKey() + ". " +  e.getValue().getType() );
		}
		
		return Arrays.toString(optionString.toArray());
	}
	
	public static void main(String[] args) {
		
		// Restructure the code so that the program can handle several operations such as
		// 1) create new shipping transaction V
		// 2) completes a shipping transaction 		
		menu();
		
	}
	
	private static void menu() {
		int choice = -1;
		do {
			printMenu();
			choice = in.nextInt();in.nextLine();
			switch (choice) {
			case 1:
				createTrans();
				break;
			case 2:
				completeTrans();
				break;
			default:
				break;
			}
		} while (choice != 3);
	}
	
	private static void printMenu() {
		System.out.println("====================================");
		System.out.println("1. Create new Shipping Transaction");
		System.out.println("2. Complete a shipping transaction");
		System.out.println("3. EXIT");
		System.out.println("====================================");
		System.out.print("Choose what you want to do: ");
	}
	
	private static void createTrans() {
		
		System.out.print("Create new delivery package, insert the delivery code:");
		Package deliveryPackage = new Package(in.next());
		
		String shippingOptions = getShippingOptions();
		System.out.print("Choose shipping "+ shippingOptions +" : ");
		
		try {
			Logistics logisticOpr = LOGISTIC_FACTORIES.get(in.nextInt());
			if (logisticOpr == null) {
				throw new Exception("logistic option not supported yet.");
			}
			
			logisticOpr.planDelivery(deliveryPackage);
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
		}
		
	}
	private static void completeTrans() {
		
//		System.out.println("Complete delivery, insert the devilery code:");
//		Package deliveryPackage = new Package(in.next());
		
		try {
			transactions = Transaction.viewTrans();
			if( transactions.size() == 0) {
				System.out.println("No Transactions Yet\n");
				return;
			}
			
			Transaction trans = getTransaction();
			
			System.out.println("Transaction "+trans.getShippingID() + " has been completed");
			Logistics.toggleTransportStatus(trans.getTransportID(), true);
			System.out.println(trans.getShippingID());
			Transaction.updateArrival(trans.getShippingID());
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("Complete Transaction FAILED!");
		}

	}
	
	private static Transaction getTransaction () {
		Transaction trans;
		int idx = -1;
		do {
			System.out.print("Input Shipment ID to Complete: ");
			idx = in.nextInt();
		} while (validShipID(idx) == null);
		return validShipID(idx);
	}
	
	private static Transaction validShipID(int idx) {
		// TODO Auto-generated method stub
		for(int i = 0; i < transactions.size(); i++) {
			if(idx == transactions.get(i).getShippingID()) return transactions.get(i);
		}
		return null;
	}

	private static void viewTrans() {
		System.out.println("Transaction List");
		System.out.println("===============================");
		
	}

}
