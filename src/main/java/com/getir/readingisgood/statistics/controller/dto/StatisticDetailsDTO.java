package com.getir.readingisgood.statistics.controller.dto;

import com.getir.readingisgood.statistics.controller.pojo.StatisticDetail;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatisticDetailsDTO {

    private String month;
    private Integer totalOrderCount;
    private Integer totalBookCount;
    private Double totalPurchasedAmount;

    public StatisticDetailsDTO(StatisticDetail statisticDetail) {
        this.month = statisticDetail.getMonth();
        this.totalOrderCount = statisticDetail.getTotalOrderCount();
        this.totalBookCount = statisticDetail.getTotalBookCount();
        this.totalPurchasedAmount = statisticDetail.getTotalPurchasedAmount();
    }
}
