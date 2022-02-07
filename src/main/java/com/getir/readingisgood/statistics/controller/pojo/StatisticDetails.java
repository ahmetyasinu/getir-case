package com.getir.readingisgood.statistics.controller.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class StatisticDetails {

    private String month;
    private Integer totalOrderCount;
    private Integer totalBookCount;
    private Double totalPurchasedAmount;

}
