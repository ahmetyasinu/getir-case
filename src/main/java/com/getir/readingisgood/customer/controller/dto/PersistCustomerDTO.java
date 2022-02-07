package com.getir.readingisgood.customer.controller.dto;

import com.getir.readingisgood.customer.entity.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
public class PersistCustomerDTO {

    @NotNull(message = "Name cannot be empty")
    @Size(min = 2, max = 100,
            message = "Name must be at least of 2 letters and not more then 100 letters")
    private String firstName;

    @NotNull(message = "Last name cannot be empty")
    @Size(min = 2, max = 100,
            message = "Last Name must be at least of 2 letters and not more then 100 letters")
    private String lastName;

    @NotEmpty(message = "Email cannot be empty")
    @Email
    private String email;

    private String phoneNumber;

    @NotNull(message = "Last name cannot be empty")
    @Size(min = 10, max = 100,
            message = "Address must be at least of 10 letters and not more then 100 letters")
    private String address;

    public Customer getDomainObject() {
        return new Customer(firstName, lastName, email, phoneNumber, address);
    }
}
