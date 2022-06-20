package com.mylogistic.transport;

public class Ship implements Transport {
	
	private int id;
	
	@Override
	public void deliver(int id) {
		// TODO implement a facade pattern that this method calls to 
		// another complex operations held by other class, you can made it up. 
		this.id = id;
		System.out.println("Delivery by sea using Ship "+this.id);
	}
	
	public String toString(int transportID) {
		// TODO
		return "Ship ID "+transportID + " from database";
	}

}
