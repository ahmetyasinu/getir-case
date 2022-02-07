package com.getir.readingisgood.statistics.controller;

import com.getir.readingisgood.statistics.controller.dto.StatisticDetailsDTO;
import com.getir.readingisgood.statistics.controller.pojo.StatisticDetail;
import com.getir.readingisgood.statistics.service.StatisticsService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/statistics")
@AllArgsConstructor
public class StatisticsController {

    private final StatisticsService statisticsService;

    @GetMapping(value = "/getMonthlyStatistics")
    public ResponseEntity<List <StatisticDetailsDTO>> getMonthlyStatistics(@RequestParam String customerId) {
        List<StatisticDetail> statisticDetailList = statisticsService.getMonthlyStatistics(customerId);
        List<StatisticDetailsDTO> statisticDetailsDTOList = statisticDetailList.stream().map(StatisticDetailsDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok(statisticDetailsDTOList);
    }
}
