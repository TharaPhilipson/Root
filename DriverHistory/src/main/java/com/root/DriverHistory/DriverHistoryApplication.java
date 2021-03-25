package com.root.DriverHistory;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.root.DriverHistory.Service.DriverServiceImpl;

@SpringBootApplication
public class DriverHistoryApplication {
	
	

	public static void main(String[] args) {
		SpringApplication.run(DriverHistoryApplication.class, args);
		Scanner sc= new Scanner(System.in);   
		System.out.print("Enter the driver and Trip detail.Press Enter to complete input");  
		List<String> input = new ArrayList<String>();
		
		String line = sc.nextLine();
		while (!line.equals("")) {
			 input.add(line);
		    line = sc.nextLine();
		}
		DriverServiceImpl driverService= new DriverServiceImpl();
	    driverService.processInput(input);
		 
	}

}
