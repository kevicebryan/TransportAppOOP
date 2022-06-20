package com.mylogistic.transport;

public class Truck implements Transport {
	
	private int id;
	
	@Override
	public void deliver(int id) {
		this.id = id;
		System.out.println("Delivery by road using Truck "+this.id);
	}
	
	public String toString(int transportID) {
		// TODO
		return "Truck ID "+ transportID + " from database";
	}

}
