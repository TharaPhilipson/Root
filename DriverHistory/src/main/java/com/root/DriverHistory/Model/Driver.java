package com.root.DriverHistory.Model;

import org.joda.time.Duration;

import lombok.Data;
@Data
public class Driver implements Comparable<Driver> {
	String name;
	Duration totalTime;
	long totalTime1;
	Float milesDriven;
	int speed;
	
	
	@Override
	public int compareTo(Driver driver) {
	    return (int)(this.milesDriven -driver.milesDriven);
	}

	

}
