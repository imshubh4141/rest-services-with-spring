package payroll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class EmployeeController {

    @Autowired
    private EmployeeRepository repository;

    @Autowired
    private EmployeeModelAssembler assembler;

    @GetMapping("/employees")
    CollectionModel<EntityModel<Employee>> getAllEmployees(){
        List<EntityModel<Employee>> employees = repository.findAll().stream()
                .map(employee -> assembler.toModel(employee)).toList();

        return CollectionModel.of(employees, linkTo(methodOn(EmployeeController.class).getAllEmployees()).withSelfRel());
    }

    @PostMapping("/employees")
    Employee createEmployee(@RequestBody Employee newEmployee){
        return repository.save(newEmployee);
    }

    @GetMapping("/employees/{id}")
    EntityModel<Employee> getEmployeeById(@PathVariable Long id){
        Employee employee = repository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(id));

        return assembler.toModel(employee);
    }

    @PutMapping("/employees/{id}")
    Employee replaceEmployee(@RequestBody Employee newEmployee, @PathVariable Long id){
        return repository.findById(id)
                .map(employee -> {
                    employee.setName(newEmployee.getName());
                    employee.setRole(newEmployee.getRole());
                    return repository.save(employee);
                })
                .orElseGet(() -> {
                    return repository.save(newEmployee);
                });
    }

    @DeleteMapping("employees/{id}")
    void deleteEmployee(@PathVariable Long id){
        repository.deleteById(id);
    }
}
