package com.root.DriverHistory.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.Interval;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.root.DriverHistory.Model.Driver;
import com.root.DriverHistory.helper.MyComparator;

public class DriverServiceImpl {
	public void processInput(List<String> inputs) {
		Map<String,Driver> driverMap = new HashMap<String,Driver>();
		DateTimeFormatter formatter = DateTimeFormat.forPattern("HH:mm");
		for(String input:inputs) {
			if(input.split(" ")[0].equalsIgnoreCase("Driver")) {
				Driver driver = new Driver();
				driver.setName(input.split(" ")[1]);
				driver.setMilesDriven(0f);
				driverMap.put(input.split(" ")[1], driver);
			
			}
			if(input.split(" ")[0].equalsIgnoreCase("Trip")) {
				String name = input.split(" ")[1];
				String startTime = input.split(" ")[2];
				String stopTime  = input.split(" ")[3];
				Float milesDriven   = Float.parseFloat(input.split(" ")[4]);
				DateTime start = formatter.parseDateTime(startTime);
			    DateTime end = formatter.parseDateTime(stopTime);
			    Duration duration = new Interval(start, end).toDuration();
			    if((milesDriven * 60 )/duration.getStandardMinutes() < 100 && (milesDriven * 60 )/duration.getStandardMinutes()>5 ) {
					if(driverMap.containsKey(name)) {
						Driver driverObj = driverMap.get(name);
						Float existingMiles = driverObj.getMilesDriven();
						driverObj.setMilesDriven(existingMiles+milesDriven);
						Duration existingDuration = driverMap.get(name).getTotalTime();
						
						if(existingDuration==null) {
							driverMap.get(name).setTotalTime(duration);
							driverMap.get(name).setTotalTime1(duration.getStandardMinutes());
						}else {
							driverMap.get(name).setTotalTime(existingDuration.plus(duration));
							driverMap.get(name).setTotalTime1(existingDuration.plus(duration).getStandardMinutes());
						}
						
						
					}
				
			    }
			}
			
			
		}
		for (String key : driverMap.keySet()) {
			int speed = Math.round(driverMap.get(key).getMilesDriven()* 60/(driverMap.get(key).getTotalTime1()));
			driverMap.get(key).setSpeed(speed);
		}
		printOutput(driverMap);
		
	}
	
	public void printOutput(Map<String, Driver> driverMap) {
		Collection<Driver> drivers = driverMap.values();
	    List<Driver> list = new ArrayList<>(drivers);
	    Collections.sort(list, new MyComparator());
	    for (Iterator<Driver> it = list.iterator(); it.hasNext();) {
	    	Driver stdn = (Driver) it.next();
	    	
	    	String a = " @ " + stdn.getSpeed() +"mph";
	    	String b ="";
	    	
	    	System.out.print(stdn.getName() + " : " + Math.round(stdn.getMilesDriven()) + " miles");
	    	System.out.print(stdn.getSpeed()!=0? a:b);
	    	System.out.println();
	        
	      }
	}

}
