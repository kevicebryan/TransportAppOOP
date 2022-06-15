package com.mylogistic.factory;

import com.mylogistic.transport.Ship;
import com.mylogistic.transport.Transport;

public class SeaLogistics extends Logistics {

	@Override
	Transport createTransport() {
		return new Ship();
	}

	@Override
	public String getType() {
		return "Sea";
	}

}
