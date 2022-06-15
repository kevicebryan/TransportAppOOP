package transaction;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.Date;
import java.util.Vector;

import com.mylogistic.database.DBConnection;

public class Transaction {
	private int shippingID, packageID, transportID;
	private String cDate, eta, arrivalDate;
	
	
	
	public Transaction( int packageID, int transportID) {
		super();
		this.shippingID = generateID();
		this.packageID = packageID;
		this.transportID = transportID;
		this.cDate = LocalDate.now().toString();
		//Anggapan paket akan sampai dalam 10 hari dari transaksi dimulai
		this.eta = LocalDate.now().plusDays(10).toString();
		this.arrivalDate = "";

		printTransactionDetail();
	}
	
	public Transaction(int transID, int shippingID2, int packageID2, String cDate2, String eta2, String arrival) {
		// TODO Auto-generated constructor stub
		this.transportID= transID;
		this.shippingID = shippingID2;
		this.packageID = packageID2;
		this.cDate = cDate2;
		this.eta = eta2;
		this.arrivalDate = arrival;
	}

	private void printTransactionDetail() {
		// TODO Auto-generated method stub
		System.out.println("");
		
	}

	private int generateID() {
		int id = (int) Math.floor(Math.random()*100);
		return id;
	}
	
	public static void insertTransactionDatabase( int packageID, int transportID) {
		Transaction trans = new Transaction( packageID, transportID);
		DBConnection db = DBConnection.getInstance();
		try {
			String query = String.format("INSERT transaction VALUES ('%d','%d','%d','%s','%s',DEFAULT)", 
					trans.getShippingID(), 
					trans.getPackageID(), 
					trans.getTransportID(), 
					trans.getcDate(), 
					trans.getEta()
					);
			db.executeUpdate(query);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("FAILED TO INSERT NEW TRANSACTION!");
		}
	}
	
	public static void updateArrival(int shippingID) {
		DBConnection db = DBConnection.getInstance();

		try {
			String query = String.format("UPDATE `transaction` SET `arrival_date`='%s' "
					+ "WHERE shipping_id = '%d'", LocalDate.now().toString(), shippingID);
//			System.out.println(query);
			db.executeUpdate(query);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("FAILED TO UPDATE ARRIVAL");
		}
	}
	
	public static Vector<Transaction> viewTrans() {
		Vector<Transaction> trans = new Vector<>();
		DBConnection db = DBConnection.getInstance();
		try {
			String query = "SELECT * FROM `transaction` WHERE arrival_date IS NULL;";
			ResultSet rs = db.execute(query);
			System.out.println("\n\nShippingID, TransportID, PackageID, CreatedDate, ETA, Arrival");
			while(rs.next()) {
				String arrivalStr = "-";
//				System.out.println("TESTT");
				int transID = rs.getInt("transport_id");
				int shippingID = rs.getInt("shipping_id");
//				System.out.println("TESTSTT");
				int packageID = Integer.parseInt(rs.getString("package_id")) ;
//				System.out.println("TESTSTT");
				String cDate = rs.getDate("created_date").toString();
				String eta = rs.getDate("eta").toString();
				Date arrival = rs.getDate("arrival_date");
				if(arrival != null) {
					 arrivalStr = rs.getDate("arrival_date").toString();
				}
				System.out.println("=---========================================================---=");
				System.out.printf("%5d, %5d, %5d, %10s, %10s, %10s\n", shippingID, 
						transID, packageID,
						cDate, eta, arrivalStr);
				
				Transaction transaction = new Transaction(transID, shippingID, packageID,
						cDate, eta, arrivalStr);
				trans.add(transaction);
			} return trans;
		} catch (Exception e) {
			// TODO: handle exception
		}
		System.out.println("\n");
		return trans;
	}
	
	
	public int getShippingID() {
		return shippingID;
	}
	public void setShippingID(int shippingID) {
		this.shippingID = shippingID;
	}
	public int getPackageID() {
		return packageID;
	}
	public void setPackageID(int packageID) {
		this.packageID = packageID;
	}
	public int getTransportID() {
		return transportID;
	}
	public void setTransportID(int transportID) {
		this.transportID = transportID;
	}
	public String getcDate() {
		return cDate;
	}
	public void setcDate(String cDate) {
		this.cDate = cDate;
	}
	public String getEta() {
		return eta;
	}
	public void setEta(String eta) {
		this.eta = eta;
	}
	public String getArrivalDate() {
		return arrivalDate;
	}
	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = arrivalDate;
	}
	
	
}
