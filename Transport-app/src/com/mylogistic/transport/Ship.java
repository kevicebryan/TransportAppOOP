package com.mylogistic.transport;

public class Ship implements Transport {
	
	@Override
	public void deliver() {
		// TODO implement a facade pattern that this method calls to 
		// another complex operations held by other class, you can made it up. 
		System.out.println("Delivery by sea.....");
	}
	
	public String toString(int transportID) {
		// TODO
		return "Ship ID "+transportID + " from database";
	}

}
