package com.getir.readingisgood.customer.entity;

import com.getir.readingisgood.general.entity.AbstractAuditedEntity;
import com.getir.readingisgood.order.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
public class Customer extends AbstractAuditedEntity {

    private String firstName;
    private String lastName;
    private String email;
    private String phoneNumber;
    private String address;

    public String getFullName() {
        StringBuilder stringBuilder = new StringBuilder(firstName);
        stringBuilder.append(" ");
        stringBuilder.append(lastName);
        return stringBuilder.toString();
    }

}
