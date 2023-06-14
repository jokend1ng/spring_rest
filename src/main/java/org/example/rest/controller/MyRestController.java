package org.example.rest.controller;

import org.example.rest.Service.EmployeeService;
import org.example.rest.entity.Employee;
import org.example.rest.exception_handing.EmployeeIncorectData;
import org.example.rest.exception_handing.NoSuchEmployeeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")

public class MyRestController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/employees")
    public List<Employee> showAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/{id}")
    public Employee getEmployee(@PathVariable int id) {
        Employee employee = employeeService.getEmloyee(id);
        if (employee == null) {
            throw new NoSuchEmployeeException("There is no employee with ID = " + id + " in Database");
        }
        return employee;
    }

    @ExceptionHandler
    public ResponseEntity<EmployeeIncorectData> handleException(NoSuchEmployeeException exception){
        EmployeeIncorectData data =new EmployeeIncorectData();
        data.setInfo(exception.getMessage());
        return  new ResponseEntity<>(data, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler
    public ResponseEntity<EmployeeIncorectData> handleException(Exception exception){
        EmployeeIncorectData data =new EmployeeIncorectData();
        data.setInfo(exception.getMessage());
        return  new ResponseEntity<>(data, HttpStatus.BAD_REQUEST);
    }
    @PostMapping("/employees")
    public Employee addNewEmployee(@RequestBody Employee employee){
                employeeService.saveEmployee(employee);
                return employee;
    }

    @PutMapping("/employees")
    public Employee updateEmployee(@RequestBody Employee employee){
        employeeService.saveEmployee(employee);
        return employee;
    }
    @DeleteMapping("/employees/{id}")
    public String deleteEmployee(@PathVariable int id){
        Employee employee =employeeService.getEmloyee(id);
        if (employee==null){
            throw new NoSuchEmployeeException("There is no Employee with ID =" +id +
                    "in Database");
        }
        employeeService.deleteEmployee(id);
        return "Employee with ID = "+id+ " was deleted";
    }
}
