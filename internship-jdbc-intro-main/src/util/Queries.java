package util;

public final class Queries {

    private Queries() {}

    public static final String FIND_ALL_EMPLOYEES = "SELECT * FROM employees;";
    public static final String FIND_ALL_OFFICES = "SELECT * FROM offices;";

    public static final String FIND_EMPLOYEE_BY_ID = "SELECT * FROM employees WHERE employeeNumber = ?;";

    public static final String FIND_OFFICE_BY_ID = "SELECT * FROM offices WHERE officeCode = ?;";

    public static final String CHECK_IF_EMPLOYEE_EXISTS= "SELECT COUNT(*) FROM employees WHERE employeeNumber = ?;";

    public static final String CHECK_IF_OFFICE_EXISTS = "SELECT COUNT(*) FROM offices WHERE officeCode = ?;";

    public static final String INSERT_INTO_OFFICE = "INSERT INTO offices (officeCode, city, phone, addressLine1, addressLine2, state, country, postalCode, territory) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";



}
