package com.csi.service;

import com.csi.model.Employee;
import com.csi.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class EmployeeServiceImpl {

    @Autowired
    private EmployeeRepository employeeRepositoryImpl;

    public Employee signUp(Employee employee) {
        return employeeRepositoryImpl.save(employee);
    }

    public boolean signIn(String empEmailId, String empPassword) {
        boolean flag = false;

        Employee employee = employeeRepositoryImpl.findByEmpEmailIdAndEmpPassword(empEmailId, empPassword);


        if (employee != null && employee.getEmpEmailId().equals(empEmailId) && employee.getEmpPassword().equals(empPassword)) {
            flag = true;
        }

        return flag;

    }

    @Cacheable(value = "empId")
    public Optional<Employee> findById(int empId) {
        log.info("#####Trying to fetch data from db..." + empId);
        return employeeRepositoryImpl.findById(empId);
    }

    public List<Employee> findAll() {
        return employeeRepositoryImpl.findAll();
    }

    public Employee update(Employee employee) {
        return employeeRepositoryImpl.save(employee);
    }

    public void deleteById(int empId) {
        employeeRepositoryImpl.deleteById(empId);
    }


}
