package com.mylogistic.deliveryItem;

public class Package {
	private String packageID;
	
	public String getPackageID() {
		return packageID;
	}

	private double weight, width, height, depth;
	private Address toAddress, fromAddress;
	
	public Package(String ID) {
		packageID = ID;
	}
	
	public String toString() {
		return "Package "+packageID;
	}
}
