package util;

public final class Queries {

    private Queries() {
    }

    public static final String FIND_ALL_EMPLOYEES = "SELECT * FROM employees;";
    public static final String FIND_ALL_OFFICES = "SELECT * FROM offices;";

    public static final String FIND_EMPLOYEE_BY_ID = "SELECT * FROM employees WHERE employeeNumber = ?;";

    public static final String FIND_OFFICE_BY_ID = "SELECT * FROM offices WHERE officeCode = ?;";

    public static final String FIND_CUSTOMER_BY_ID = "SELECT c.customerNumber, c.customerName, c.contactLastName, c.contactFirstName, c.phone, c.addressLine1, c.addressLine2, c.city, c.state, c.postalCode, c.country, c.salesRepEmployeeNumber, c.creditLimit, o.orderNumber , o.orderDate, o.requiredDate, o.shippedDate, o.status, o.comments, o.customerNumber \n" +
            "FROM customers c \n" +
            "LEFT JOIN orders o ON c.customerNumber = o.customerNumber \n" +
            "WHERE c.customerNumber = ?\n;";

    public static final String CHECK_IF_EMPLOYEE_EXISTS = "SELECT COUNT(*) FROM employees WHERE employeeNumber = ?;";

    public static final String CHECK_IF_OFFICE_EXISTS = "SELECT COUNT(*) FROM offices WHERE officeCode = ?;";

    public static final String INSERT_INTO_OFFICE = "INSERT INTO offices (officeCode, city, phone, addressLine1, addressLine2, state, country, postalCode, territory) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static final String FIND_ALL_ORDERS = "SELECT * FROM orders;";

    public static final String FIND_ALL_CUSTOMERS = "SELECT c.customerNumber, c.customerName, c.contactLastName, c.contactFirstName, c.phone, c.addressLine1, c.addressLine2, c.city, c.state, c.postalCode, c.country, c.salesRepEmployeeNumber, c.creditLimit, o.orderNumber, o.orderDate, o.requiredDate, o.shippedDate, o.status, o.comments, o.customerNumber as order_customer_number\n" +
            "FROM customers c\n" +
            "LEFT JOIN orders o ON c.customerNumber = o.customerNumber\n;";

    public static final String FIND_ALL_ORDERS_BY_CUSTOMER_ID = "SELECT * FROM orders where customerNumber = ?;";

    public static final String FIND_ORDER_BY_ID = "SELECT * FROM orders WHERE orderNumber = ?;";

    public static final String CHECK_IF_ORDER_EXISTS = "SELECT COUNT(*) FROM orders WHERE orderNumber = ?;";

    // Insert a new customer
    public static final String INSERT_CUSTOMER = "INSERT INTO customers "
            + "(customerNumber, customerName, contactLastName, " +
            "contactFirstName, phone, addressLine1, addressLine2, city, state," +
            " postalCode, country, salesRepEmployeeNumber, creditLimit) "
            + "VALUES (?,?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    // Update an existing customer
    public static final String UPDATE_CUSTOMER = "UPDATE customers SET "
            + "customerName = ?, contactLastName = ?, contactFirstName = ?, phone = ?, addressLine1 = ?, addressLine2 = ?, city = ?, state = ?, postalCode = ?, country = ?, salesRepEmployeeNumber = ?, creditLimit = ? "
            + "WHERE customerNumber = ?";

    // Insert a new order
    public static final String INSERT_ORDER = "INSERT INTO orders "
            + "(orderNumber, orderDate, requiredDate, shippedDate, status, comments, customerNumber) "
            + "VALUES (?,?, ?, ?, ?, ?, ?)";

    // Update an existing order
    public static final String UPDATE_ORDER = "UPDATE orders SET "
            + "orderDate = ?, requiredDate = ?, shippedDate = ?, status = ?, comments = ?, customerNumber = ? "
            + "WHERE orderNumber = ?";


}
