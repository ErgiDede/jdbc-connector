package main;

import model.entity.Customer;
import model.entity.Employee;
import model.entity.Office;
import repository.impl.CustomerRepository;
import repository.impl.EmployeeRepository;
import repository.impl.OfficeRepository;

import java.util.List;

public class Main {

    public static void main(String[] args) {
        EmployeeRepository employeeRepository = new EmployeeRepository();
        List<Employee> employees = employeeRepository.findAll();

        for (Employee employee : employees) {
            System.out.println(employee);
        }

        Customer employee = employeeRepository.findById(1002);
        System.out.println(employee);

        System.out.println(employeeRepository.exists(1002));

        /*Employee addEmployee = new Employee(125,"Ergi","Dede","ED007","ergi.dede@gmail.com","1",null,"Software Developer");
        employeeRepository.save(addEmployee);*/

        Employee updatedEmployee = new Employee(1002, "Murphy", "Diane", "x5800", "dmurphy@classicmodelcars.com", "1", 125, "President");
        updatedEmployee.setReportsTo(1621);
        int numRecordsUpdated = employeeRepository.update(updatedEmployee);
        System.out.println(numRecordsUpdated + " record(s) updated successfully!");

        System.out.println("");
        System.out.println("Office");;
        System.out.println();

        OfficeRepository officeRepository = new OfficeRepository();
        List<Office> offices = officeRepository.findAll();

        for (Office office : offices) {
            System.out.println(office);
        }

        Office office = officeRepository.findById("1");
        System.out.println(office);

        System.out.println(officeRepository.exists("1"));

        /*Office addOffice = new Office("8", "Tirana","0675699734","Rruga Qemal Stafa", "Rruga e Durrsit", "Shqiperi", "Tirana Country", "1015", "Pazar");
        officeRepository.save(addOffice);*/

        Office updatedOffice =  officeRepository.findById("8");
        updatedOffice.setCity("Korca");
        int numRecordsOfficeUpdated = officeRepository.update(updatedOffice);
        System.out.println(numRecordsOfficeUpdated + " record(s) updated successfully!");

        Employee employee1 = new Employee();
        System.out.println(employee1);

        System.out.println(
        );

        System.out.println("customer");

        System.out.println();
        CustomerRepository customerRepository = new CustomerRepository();

        Customer customer = customerRepository.findById(103);

        System.out.println(customer);
    }


}
