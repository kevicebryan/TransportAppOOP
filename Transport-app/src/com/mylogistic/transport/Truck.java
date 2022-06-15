package com.mylogistic.transport;

public class Truck implements Transport {
	
	@Override
	public void deliver() {
		System.out.println("Start delivery via Truck");
	}
	
	public String toString(int transportID) {
		// TODO
		return "Truck ID "+ transportID + " from database";
	}

}
