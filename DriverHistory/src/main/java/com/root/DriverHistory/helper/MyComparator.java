package com.root.DriverHistory.helper;

import java.util.Comparator;

import com.root.DriverHistory.Model.Driver;

public class MyComparator implements Comparator<Driver> {

	  @Override
	  public int compare(Driver d1, Driver d2) {
	    return d2.getMilesDriven().compareTo(d1.getMilesDriven());
	  }
	}