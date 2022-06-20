package com.mylogistic.transport;

public interface Transport {
	
	void deliver(int id);

	String toString(int transportID);
}
