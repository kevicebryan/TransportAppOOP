package com.mylogistic.factory;

import com.mylogistic.transport.Transport;
import com.mylogistic.transport.Truck;

public class RoadLogistics extends Logistics {

	@Override
	Transport createTransport() {
		return new Truck();
	}

	@Override
	public String getType() {
		// must be the same type as in database table field
		return "Road"; 
	}

}
