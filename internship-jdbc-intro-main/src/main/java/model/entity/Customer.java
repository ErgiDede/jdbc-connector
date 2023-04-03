package model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    private Integer id;
    private String customerName;
    private String contactLastName;
    private String contactFirstName;
    private String phone;
    private String addressLine1;
    private String addressLine2;
    private String city;
    private String state;
    private String postalCode;
    private String country;
    private int salesRepEmployeeNumber;
    private Double creditLimit;

    private List<Order> orders;

    public Customer(int id, String customerName, String contactLastName, String contactFirstName, String phone,
                    String addressLine1, String addressLine2, String city, String state, String postalCode, String country,
                    int salesRepEmployeeNumber, double creditLimit) {
        this.id = id;
        this.customerName = customerName;
        this.contactLastName = contactLastName;
        this.contactFirstName = contactFirstName;
        this.phone = phone;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.state = state;
        this.postalCode = postalCode;
        this.country = country;
        this.salesRepEmployeeNumber = salesRepEmployeeNumber;
        this.creditLimit = creditLimit;
        this.orders = new ArrayList<>();
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Customer{id=").append(id);
        sb.append(", customerName='").append(customerName).append('\'');
        sb.append(", contactLastName='").append(contactLastName).append('\'');
        sb.append(", contactFirstName='").append(contactFirstName).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", addressLine1='").append(addressLine1).append('\'');
        sb.append(", addressLine2='").append(addressLine2).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", state='").append(state).append('\'');
        sb.append(", postalCode='").append(postalCode).append('\'');
        sb.append(", country='").append(country).append('\'');
        sb.append(", salesRepEmployeeNumber=").append(salesRepEmployeeNumber);
        sb.append(", creditLimit=").append(creditLimit);
        sb.append(", orders=").append(orders);
        sb.append('}');
        return sb.toString();
    }


}
