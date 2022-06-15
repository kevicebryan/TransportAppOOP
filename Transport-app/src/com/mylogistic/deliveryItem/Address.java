package com.mylogistic.deliveryItem;

public class Address {
	private String name, 
				streetName,
				city;
	
	public String getName() {
		return name;
	}

	public String getStreetName() {
		return streetName;
	}

	public String getCity() {
		return city;
	}

	public Address (String name, String streetName, String city) {
		this.name = name;
		this.streetName = streetName;
		this.city = city;
	}
}
