package net.javaguides.springbootbackend.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import net.javaguides.springbootbackend.exception.ResourceNotFoundException;
import net.javaguides.springbootbackend.model.Employee;
import net.javaguides.springbootbackend.repository.EmployeeRepository;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
//@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/api/v1/")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	

	@GetMapping("/employees")
	public List<Employee> getAllEmployees(){
		return employeeRepository.findAll();
	}
	

	@PostMapping("/employees")
	public Employee createEmployee(@RequestBody Employee employee) {
		return employeeRepository.save(employee);
	}
	
	@GetMapping("/employees/{id}")
	public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
		Employee employee= employeeRepository.findById(id)
				.orElseThrow(()-> 
				new ResourceNotFoundException
				("Resource not found"));
		return ResponseEntity.ok(employee);
	}
	
	
	
//	@CrossOrigin(origins = "http://localhost:8080")
	@PutMapping("/employees/{id}")
	public ResponseEntity<Employee> updateEmployees(@PathVariable Long id ,@RequestBody Employee employeeDetails){
		
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(()-> 
				new ResourceNotFoundException
				("Resource not found:" +id ));
		
		
		
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmailId(employeeDetails.getEmailId());
		
		Employee updatedEmployee = employeeRepository.save(employee);
		
		return ResponseEntity.ok(updatedEmployee);
	}
	
	
	// delete rest api's
	
	@DeleteMapping("/employees/{id}")
	public ResponseEntity<Map<String,Boolean>> deleteEmployees(@PathVariable long id){
		
		Employee employee = employeeRepository.findById(id)
				.orElseThrow(()-> 
				new ResourceNotFoundException
				("Resource not found:" +id ));
		
		employeeRepository.delete(employee);
		Map<String,Boolean> response = new HashMap<>();
		response.put("deleted", true);
		return ResponseEntity.ok(response);
		
	}
	
	
	
}
