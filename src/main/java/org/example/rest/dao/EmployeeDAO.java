package org.example.rest.dao;



import org.example.rest.entity.Employee;

import java.util.List;

public interface EmployeeDAO {
    public List<Employee> getAllEmployees();


   public void saveEmployee(Employee employee);

   public Employee getEmployee(int id);

    public void deleteEmployee(int id);
}
