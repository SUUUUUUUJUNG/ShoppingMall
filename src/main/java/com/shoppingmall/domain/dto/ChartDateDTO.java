package com.shoppingmall.domain.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
public class ChartDateDTO {
    private String dateString; // 날짜를 'YYYY-MM-DD' 형식의 문자열로 관리
    private BigDecimal totalSales; // 해당 날짜의 총 판매액
}
