package com.csi.controller;

import com.csi.exception.RecordNotFoundException;
import com.csi.model.Employee;
import com.csi.service.EmployeeServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
@Slf4j
public class EmployeeController {

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @PostMapping("/signup")
    public ResponseEntity<Employee> signUp(@Valid @RequestBody Employee employee) {
        return new ResponseEntity<>(employeeServiceImpl.signUp(employee), HttpStatus.CREATED);
    }

    @GetMapping("/signin/{empEmailId}/{empPassword}")
    public ResponseEntity<Boolean> signIn(@PathVariable String empEmailId, @PathVariable String empPassword) {
        return ResponseEntity.ok(employeeServiceImpl.signIn(empEmailId, empPassword));
    }

    @GetMapping("/findbyid/{empId}")
    public ResponseEntity<Optional<Employee>> findById(@PathVariable int empId) {
        return ResponseEntity.ok(employeeServiceImpl.findById(empId));
    }

    @GetMapping("/findall")
    public ResponseEntity<List<Employee>> findAll() {
        return ResponseEntity.ok(employeeServiceImpl.findAll());
    }

    @PutMapping("/update/{empId}")
    public ResponseEntity<Employee> update(@PathVariable int empId, @Valid @RequestBody Employee employee) {
        Employee employee1 = employeeServiceImpl.findById(empId).orElseThrow(() -> new RecordNotFoundException("Employee ID does not exist"));

        employee1.setEmpEmailId(employee.getEmpEmailId());
        employee1.setEmpAddress(employee.getEmpAddress());
        employee1.setEmpPassword(employee.getEmpPassword());
        employee1.setEmpContactNo(employee.getEmpContactNo());
        employee1.setEmpUID(employee.getEmpUID());
        employee1.setEmpPancard(employee.getEmpPancard());
        employee1.setEmpDOB(employee.getEmpDOB());
        employee1.setEmpLastName(employee.getEmpLastName());
        employee1.setEmpFirstName(employee.getEmpFirstName());
        employee1.setEmpSalary(employee.getEmpSalary());


        return new ResponseEntity<>(employeeServiceImpl.update(employee1), HttpStatus.CREATED);

    }

    @DeleteMapping("/deletebyid/{empId}")
    public ResponseEntity<String> deleteById(@PathVariable int empId) {

        employeeServiceImpl.deleteById(empId);
        return ResponseEntity.ok("Data Deleted Successfully");
    }
}
