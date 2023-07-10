package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * The interface which defines the operations on Employee entities.
 */
public interface EmployeeService {

    /**
     * The retrieveEmployees service method interface.
     * 
     * @return The list of all employees.
     */
    @Cacheable(value = "employees")
    public List<Employee> retrieveEmployees();

    /**
     * The getEmployee service method interface.
     * 
     * @param employeeId The ID of the employee to be retrieved.
     * @return The employee information with the specified ID.
     */
    @Cacheable(value = "employee", key = "#employeeId")
    public Employee getEmployee(Long employeeId);

    /**
     * The saveEmployee service method interface.
     * 
     * @param employee The employee to be saved.
     */
    @CacheEvict(value = {"employees", "employee"}, allEntries = true)
    public void saveEmployee(Employee employee);

    /**
     * The deleteEmployee service method interface.
     * 
     * @param employeeId The ID of the employee to be deleted.
     */
    @CacheEvict(value = {"employees", "employee"}, allEntries = true)
    public void deleteEmployee(Long employeeId);

    /**
     * The updateEmployee service method interface.
     * 
     * @param employee The updated employee information.
     */
    @CacheEvict(value = {"employees", "employee"}, allEntries = true)
    public void updateEmployee(Employee employee);
}