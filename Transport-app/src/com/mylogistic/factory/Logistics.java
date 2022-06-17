package com.mylogistic.factory;

import com.mylogistic.transport.Transport;

import transaction.Transaction;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.mylogistic.database.DBConnection;
import com.mylogistic.deliveryItem.Package;

public abstract class Logistics {
	public void planDelivery(Package p) {
		if (getTransportAvailability()) {
			// get the TransportID of the available Transport
			int transportID = getTransportIDAvailable();
			//  insert the transaction..
			Transaction.insertTransactionDatabase(Integer.parseInt(p.getPackageID()), transportID);
			//  update the transport object status to Shipping
			toggleTransportStatus(transportID, false);
			
			
			Transport t = createTransport();
			System.out.println("Plan Delivery "+ p +" via " + t.toString(transportID) + " Logistic.");
			t.deliver();
			
		} else {
			System.out.println("Sorry, we're currently unavailable to transport your package.");
			// TODO
			System.out.println("The closest available date for this " + getType() + " is at ???");
		}
	}
	


	abstract Transport createTransport();
	
	public abstract String getType();
	
	private boolean getTransportAvailability() {
		DBConnection db = DBConnection.getInstance();
		System.out.println(">>>>>>>>>>>>>>");
		System.out.println(getType());
		ResultSet rs = db.execute("SELECT count(id) as availableTransport "
				+ "FROM transport WHERE type = '" + getType() + "' "
				+ "AND status = 'Available' "); 
		// Shipping or Maintenance means it's not available
		try {
			rs.next();
			return rs.getInt("availableTransport") > 0;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	

	
	private int getTransportIDAvailable()
	{
		DBConnection db = DBConnection.getInstance();
		
		ResultSet rs = db.execute("SELECT id as availableTransport "
				+ "FROM transport WHERE type = '" + getType() + "' "
				+ "AND status = 'Available' "); 
		// Shipping or Maintenance means it's not available
		try {
			rs.next();
			int id =  rs.getInt("availableTransport");
			System.out.println("Avaialble "+ getType() + " is "+ id);
			return id;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1;
	}
	
//	Change from Available to BUSY, from BUYS to Available
	public static void toggleTransportStatus(int transportID, boolean complete) {
		DBConnection db = DBConnection.getInstance();
		String query = "";
		if(complete) {
			 query = String.format("UPDATE `transport` SET `status`='Available' WHERE id = %d", transportID);
		}else {
			 query = String.format("UPDATE `transport` SET `status`='Shipping' WHERE id = %d", transportID);
		}

		try {
			System.out.println(query);
			db.executeUpdate(query);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
}
