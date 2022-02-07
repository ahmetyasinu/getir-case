package com.getir.readingisgood.customer.controller;

import com.getir.readingisgood.customer.controller.dto.AddCustomerDTO;
import com.getir.readingisgood.customer.entity.Customer;
import com.getir.readingisgood.customer.service.CustomerService;
import com.getir.readingisgood.order.entity.Order;
import com.getir.readingisgood.order.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/customer")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;
    private final OrderService orderService;

    @PostMapping(value = "/add")
    public ResponseEntity<Customer> add(@RequestBody @Valid AddCustomerDTO addCustomerDTO) {
        Customer customer = customerService.add(addCustomerDTO.getDomainObject());
        return ResponseEntity.ok(customer);
    }

    @GetMapping(value = "/getAllOrdersByCustomerId")
    public ResponseEntity<Page<Order>> getAllOrdersByCustomerId(@RequestParam String id) {
        Page<Order> orderPage = orderService.getAllOrdersPageableByCustomerId(id, PageRequest.of(0, 30));
        return ResponseEntity.ok(orderPage);
    }
}
