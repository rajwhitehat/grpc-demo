package com.example.demo;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Descriptors.FieldDescriptor;

@RestController
public class EmpController {

	@Autowired
	EmployeeGRPCService empService;
	
	@GetMapping("/employee/{id}")
	public Map<Descriptors.FieldDescriptor, Object> getEmployee(@PathVariable Integer id){
		return empService.getEmployeeId(id);
}
	
	@GetMapping("/employee/gender/{gender}")
	public List<Map<Descriptors.FieldDescriptor, Object>> getEmployee(@PathVariable String gender) throws InterruptedException{
	
		return empService.getEmployeeByGender(gender);
}
	
	
	@GetMapping("/employee/oldest")
	public Map<String, Map<FieldDescriptor, Object>> getOldestEmployee() throws InterruptedException{
	
		return empService.getOldestEmployee();
}
}