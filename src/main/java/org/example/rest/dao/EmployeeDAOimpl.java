package org.example.rest.dao;


import org.example.rest.entity.Employee;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class EmployeeDAOimpl implements EmployeeDAO {
    @Autowired
    private SessionFactory sessionFactory;


    @Transactional
    public List<Employee> getAllEmployees() {
        Session session = sessionFactory.getCurrentSession();
//        List<Employee> allEmployees=session.createQuery("from Employee ",
//                        Employee.class).getResultList();
        Query<Employee> query = session.createQuery("from Employee ", Employee.class);
        return query.getResultList();
    }

    @Override
    @Transactional
    public void saveEmployee(Employee employee) {
        Session session = sessionFactory.getCurrentSession();

        session.saveOrUpdate(employee);

    }

    @Override
    @Transactional
    public Employee getEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(Employee.class, id);
    }

    @Override
    public void deleteEmployee(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query<Employee> query = session.createQuery("DELETE from Employee " + "where id =:employeeId");
        query.setParameter("employeeId", id);
        query.executeUpdate();
    }
}
