package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Optional;

/**
 * The class which provides the Implementation of the EmployeeService Interface.
 */
@Service
public class EmployeeServiceImpl implements EmployeeService{

    @Autowired
    private EmployeeRepository employeeRepository;

    /**
     * Sets the EmployeeRepository used by the service.
     * 
     * @param employeeRepository The EmployeeRepository implementation.
     */
    public void setEmployeeRepository(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

   /**
    * The retrieveEmployees service method implementation. 
    *
    * @return The list of all employees. 
    */
    @Cacheable("employees")
    public List<Employee> retrieveEmployees() {
        List<Employee> employees = employeeRepository.findAll();
        return employees;
    }

    /**
     * The getEmployee service method implementation.
     * 
     * @param employeeId The ID of the employee to be retrieved.
     * @return The employee information with the specified ID.
     */
    @Cacheable("employee")
    public Employee getEmployee(Long employeeId) {
        Optional<Employee> optEmp = employeeRepository.findById(employeeId);
        return optEmp.get();
    }

    /**
     * The saveEmployee service method implementation.
     * 
     * @param employee The employee to be saved.
     */
    @CacheEvict(value = {"employees", "employee"}, allEntries = true)
    public void saveEmployee(Employee employee){
        employeeRepository.save(employee);
    }

    /**
     * The deleteEmployee service method implementation.
     * 
     * @param employeeId The ID of the employee to be deleted.
     */
    @CacheEvict(value = {"employees", "employee"}, allEntries = true)
    public void deleteEmployee(Long employeeId){
        employeeRepository.deleteById(employeeId);
    }

    /**
     * The updateEmployee service method implementation.
     * 
     * @param employee The updated employee information.
     */
    @CacheEvict(value = {"employees", "employee"}, allEntries = true)
    public void updateEmployee(Employee employee) {
        employeeRepository.save(employee);
    }
}