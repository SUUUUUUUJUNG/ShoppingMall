package com.shoppingmall.web.controller.order;

import com.shoppingmall.domain.dto.ChartDateDTO;
import com.shoppingmall.domain.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping("/statistics")
public class StatisticsController {

    private final OrderService orderService;

    @GetMapping
    public String statistics(Model model) {
        List<ChartDateDTO> salesPast30Days = orderService.selectSalesPast30Days();
        List<ChartDateDTO> monthlySalesPastYear = orderService.selectMonthlySalesPastYear();
        List<ChartDateDTO> yearlySalesPastThreeYears = orderService.selectYearlySalesPastThreeYears();
        model.addAttribute("salesPast30Days", salesPast30Days);
        model.addAttribute("monthlySalesPastYear", monthlySalesPastYear);
        model.addAttribute("yearlySalesPastThreeYears", yearlySalesPastThreeYears);
        return "admin-page/statistics";
    }
}
