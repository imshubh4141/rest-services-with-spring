package payroll;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class Employee {

    private @Id
    @GeneratedValue Long id;
    private String firstName;
    private String lastName;
    private String role;

    Employee(){}

    Employee(String firstName, String lastName, String role){
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setName(String name){
        String[] parts = name.split(" ");
        this.firstName = parts[0];
        this.lastName = parts[1];
    }

    public String getName(){
        return this.firstName + " " + this.lastName;
    }

    @Override
    public boolean equals(Object o){
        if(this == o){
            return true;
        }
        if(!(o instanceof Employee)){
            return false;
        }
        Employee employee = (Employee) o;
        return Objects.equals(this.id, employee.id)
                && Objects.equals(this.firstName, employee.firstName)
                && Objects.equals(this.lastName, employee.lastName)
                && Objects.equals(this.role, employee.role);
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.id, this.firstName, this.lastName, this.role);
    }

    @Override
    public String toString(){
        return "Employee{" + "id=" + this.id + ", name='" + this.firstName + " " + this.lastName + '\'' + ", role='" + this.role + '\'' + '}';
    }

}
