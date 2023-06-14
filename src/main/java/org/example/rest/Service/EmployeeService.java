package org.example.rest.Service;



import org.example.rest.entity.Employee;

import java.util.List;

public interface EmployeeService {
    public List<Employee> getAllEmployees();
    public void saveEmployee(Employee employee);
    public Employee getEmloyee(int id);
    public void deleteEmployee(int id);
}
