package com.getir.readingisgood.order.controller;

import com.getir.readingisgood.order.controller.dto.AddOrderDTO;
import com.getir.readingisgood.order.entity.Order;
import com.getir.readingisgood.order.service.OrderService;
import lombok.AllArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/order")
@AllArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping(value = "/add")
    public ResponseEntity<Order> add(@RequestBody @Valid AddOrderDTO addOrderDTO) {
        Order order = orderService.add(addOrderDTO.getDomainObject());
        return ResponseEntity.ok(order);
    }

    @GetMapping(value = "/getById")
    public ResponseEntity<Order> getById(@RequestParam String id) {
        Order order = orderService.getById(id);
        return ResponseEntity.ok(order);
    }

    @GetMapping(value = "/getByDateInterval")
    public ResponseEntity<List<Order>> getByDateInterval(@RequestParam("startDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime startDate,
                                                         @RequestParam("endDate") @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endDate) {
        List<Order> orderList = orderService.getByDateInterval(startDate, endDate);
        return ResponseEntity.ok(orderList);
    }

}
