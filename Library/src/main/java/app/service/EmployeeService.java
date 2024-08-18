package app.service;

import app.entity.Employee;
import app.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public String saveEmployee(Employee employee){
        this.employeeRepository.save(employee);
        return (
                "Funcionário " + employee.getName() + " salvo com sucesso!"
        );
    }

    public List<Employee> findAllEmployees(){
        List<Employee> employees = this.employeeRepository.findAll();
        return employees;
    }

    public Employee findEmployeeById(Long id){
        Employee employee = this.employeeRepository.findById(id).get();
        return employee;
    }

    public String updateEmployee(Long id, Employee employee){
        employee.setId(id);
        this.employeeRepository.save(employee);
        return (
                "Funcionário " + employee.getName() + " atualizado com sucesso!"
        );
    }

    public String deleteEmployee(Long id){
        this.employeeRepository.deleteById(id);
        return (
                "Funcionário com id " + id + " deletado com sucesso"
        );
    }
}
