import model.entity.Customer;
import model.entity.Employee;
import model.entity.Office;
import model.entity.Order;
import repository.impl.CustomerRepository;
import repository.impl.EmployeeRepository;
import repository.impl.OfficeRepository;
import repository.impl.OrderRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        //employee();

        //office();

/*
        System.out.println();
        System.out.println("customer");
        System.out.println();
        CustomerRepository customerRepository = new CustomerRepository();

        List<Customer> customers = customerRepository.findAll();
        for (Customer customer : customers) {
            printCustomerDetails(customer);
        }
*/


        //Customer customer = customerRepository.findById(103);

        // printCustomerDetails(customer);



        /*System.out.println();
        System.out.println("Orders");
        System.out.println();

        OrderRepository orderRepository = new OrderRepository();

        List<Order> orders = orderRepository.findAllByCusmoerId(103);
        for (Order order:orders){
            System.out.println(order);
        }*/

        //createCustomer();
        CustomerRepository customerRepository = new CustomerRepository();
        Customer customer = customerRepository.findById(486);
        System.out.println(customer);
        customer.setCountry("Albania");
        customer.setPhone("555-555-1212");
        int rowsAffected = customerRepository.update(customer);
        System.out.println("Rows affected by update: " + rowsAffected);
    }

    private static void createCustomer() {
        CustomerRepository customerRepository = new CustomerRepository();
        OrderRepository orderRepository = new OrderRepository();


        Customer customer = new Customer();
        customer.setId(11112);
        customer.setCustomerName("John Smith");
        customer.setContactLastName("Smith");
        customer.setContactFirstName("John");
        customer.setPhone("1234567890");
        customer.setAddressLine1("123 Main St");
        customer.setCity("Los Angeles");
        customer.setState("CA");
        customer.setPostalCode("90001");
        customer.setCountry("USA");
        customer.setSalesRepEmployeeNumber(1002);
        customer.setCreditLimit(50000.0);

        Order order1 = new Order();
        order1.setId(1);
        order1.setOrderDate(LocalDate.of(2023, 3, 31));
        order1.setRequiredDate(LocalDate.of(2023, 4, 7));
        order1.setShippedDate(LocalDate.of(2023, 4, 1));
        order1.setStatus("Shipped");
        order1.setComments("Order shipped on time");
        order1.setCustomerNumber(11111);

        Order order2 = new Order();
        order2.setId(2);
        order2.setOrderDate(LocalDate.of(2023, 4, 1));
        order2.setRequiredDate(LocalDate.of(2023, 4, 8));
        order2.setStatus("In Process");
        order2.setComments("Order processing");
        order2.setCustomerNumber(11111);

        Order order3 = orderRepository.findById(10100);

        List<Order> orders = new ArrayList<>();

        orders.add(order1);
        orders.add(order2);
        orders.add(order3);
        customer.setOrders(orders);

        boolean result = customerRepository.save(customer);

        if (result) {
            System.out.println("Customer saved successfully.");
        } else {
            System.out.println("Customer save failed.");
        }
    }


    private static void office() {
        System.out.println();
        System.out.println("Office");
        ;
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

        Office updatedOffice = officeRepository.findById("8");
        updatedOffice.setCity("Korca");
        int numRecordsOfficeUpdated = officeRepository.update(updatedOffice);
        System.out.println(numRecordsOfficeUpdated + " record(s) updated successfully!");
    }

    private static void employee() {
        System.out.println();
        System.out.println("Employee");
        System.out.println();

        EmployeeRepository employeeRepository = new EmployeeRepository();
        List<Employee> employees = employeeRepository.findAll();

        for (Employee employee : employees) {
            System.out.println(employee);
        }

        Employee employee = employeeRepository.findById(1002);
        System.out.println(employee);

        System.out.println(employeeRepository.exists(1002));

        /*Employee addEmployee = new Employee(125,"Ergi","Dede","ED007","ergi.dede@gmail.com","1",null,"Software Developer");
        employeeRepository.save(addEmployee);*/

        Employee updatedEmployee = employeeRepository.findById(1002);
        updatedEmployee.setReportsTo(1621);
        int numRecordsUpdated = employeeRepository.update(updatedEmployee);
        System.out.println(numRecordsUpdated + " record(s) updated successfully!");
    }

    private static void printCustomerDetails(Customer customer) {
        System.out.println("Customer Details:");
        System.out.println("ID: " + customer.getId());
        System.out.println("Name: " + customer.getContactFirstName() + " " + customer.getContactLastName());
        System.out.println("Phone: " + customer.getPhone());
        System.out.println("Address1: " + customer.getAddressLine1());
        System.out.println("Address2: " + customer.getAddressLine2());
        System.out.println("City: " + customer.getCity());
        System.out.println("State: " + customer.getState());
        System.out.println("Postal Code: " + customer.getPostalCode());
        System.out.println("Country: " + customer.getCountry());
        System.out.println("Sales Rep Employee Number: " + customer.getSalesRepEmployeeNumber());
        System.out.println("Credit Limit: " + customer.getCreditLimit());

        System.out.println("Orders:");
        for (Order order : customer.getOrders()) {
            System.out.println("  Order ID: " + order.getId());
            System.out.println("  Order Date: " + order.getOrderDate());
            System.out.println("  Required Date: " + order.getRequiredDate());
            System.out.println("  Shipped Date: " + order.getShippedDate());
            System.out.println("  Status: " + order.getStatus());
            System.out.println("  Comments: " + order.getComments());
            System.out.println();
        }
    }

}
