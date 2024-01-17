package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import com.demo.schema.EmployeeRequest;
import com.demo.schema.EmployeeResponse;

public class TempDB {
	  public static List<EmployeeResponse> getEmpFromTempDb() {
	        return new ArrayList<EmployeeResponse>() {
	            {
	                add(EmployeeResponse.newBuilder().setEmpId(1).setEmpName("Anil").setGender("male").setAge(21).build());
	                add(EmployeeResponse.newBuilder().setEmpId(1).setEmpName("Rohit").setGender("male").setAge(23).build());
	                add(EmployeeResponse.newBuilder().setEmpId(1).setEmpName("Harsh").setGender("male").setAge(25).build());
	                add(EmployeeResponse.newBuilder().setEmpId(1).setEmpName("Neha").setGender("female").setAge(21).build());
	                add(EmployeeResponse.newBuilder().setEmpId(1).setEmpName("Shreya").setGender("female").setAge(22).build());
	            }
	        };
	    }
	  
	  public static List<EmployeeRequest> getEmpReqFromTempDb() {
	        return new ArrayList<EmployeeRequest>() {
	            {
	                add(EmployeeRequest.newBuilder().setEmpId(1).setEmpName("Anil").setGender("male").setAge(21).build());
	                add(EmployeeRequest.newBuilder().setEmpId(1).setEmpName("Rohit").setGender("male").setAge(23).build());
	                add(EmployeeRequest.newBuilder().setEmpId(1).setEmpName("Harsh").setGender("male").setAge(25).build());
	                add(EmployeeRequest.newBuilder().setEmpId(1).setEmpName("Neha").setGender("female").setAge(21).build());
	                add(EmployeeRequest.newBuilder().setEmpId(1).setEmpName("Shreya").setGender("female").setAge(22).build());
	            }
	        };
	    }
}
