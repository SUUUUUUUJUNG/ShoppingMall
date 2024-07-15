<%@ page import="com.shoppingmall.domain.dto.ChartDateDTO" %>
<%@ page import="java.util.List" %>
<%--
  Created by IntelliJ IDEA.
  User: SHIN SUJUNG
  Date: 2024-04-13
  Time: 오후 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
    <script src="https://fastly.jsdelivr.net/npm/chart.js"></script>
    <script src="https://fastly.jsdelivr.net/npm/chartjs-adapter-moment"></script>
    <link href="https://fastly.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>
        body, html {
            height: 100%;
            margin: 0;
        }

        .chart-container {
            padding: 20px;
            background-color: #ffffff;
            border-radius: 8px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
        }

        .chart {
            margin-bottom: 40px;
        }

        .header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 20px;
        }

        .header h2 {
            margin: 0;
            color: #333;
        }

        .filter-container {
            margin-bottom: 20px;
        }

        .filters {
            display: flex;
            gap: 20px;
        }

        .export-buttons {
            display: flex;
            gap: 10px;
            margin-left: auto;
        }

        .btn-size {
            font-size: 14px;
            padding: 5px 10px;
        }

        .btn-export {
            color: #fff;
            background-color: #007bff;
            border-color: #007bff;
        }

        .btn-export:hover {
            color: #fff;
            background-color: #0056b3;
            border-color: #004085;
        }

        .bottom-chart {
            max-width: 50%;
            margin: auto;
        }

        .table-custom {
            margin-top: 20px;
            box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
        }

        .header-link {
            margin-bottom: 10px;
            margin-right: 5px;
        }

        .sidebar {
            position: absolute;
            width: 250px;
            height: 100%;
            z-index: 1;
            padding-top: 20px;
        }

        .sidebar .list-group-item {
            border: none;
            background: transparent;
            padding: 15px 20px;
            transition: background-color 0.3s, color 0.3s;
        }

        .sidebar .list-group-item:hover,
        .sidebar .list-group-item:focus {
            background-color: #FFDDEE;
            color: #6d0230;
            cursor: pointer;
        }

        .sidebar .list-group-item.active {
            font-weight: bold;
            color: white;
            background-color: #D66D75;
            text-align: center;
        }

        .sidebar .list-group-item a {
            color: #6d0230;
            text-decoration: none;
        }

        .sidebar .list-group-item a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
<div class="container mt-4">
    <jsp:include page="../common/navbar.jsp" flush="true"></jsp:include>
    <h2>판매량 통계</h2>
    <hr>
    <div class="container mt-4">
        <div class="row">
            <!-- 사이드바 자리-->
            <div class="sidebar">
                <div class="shadow">
                    <ul class="list-group">
                        <li class="list-group-item active" aria-current="true">관리자 페이지</li>
                        <li class="list-group-item"><a href="/admin/goods">상품 관리</a></li>
                        <li class="list-group-item"><a href="/admin/statistics">판매 통계</a></li>
                        <li class="list-group-item">할인 쿠폰(구현예정)</li>
                    </ul>
                </div>
            </div>

            <!-- 메인 컨텐츠 시작 -->
            <div class="col-md-8 offset-md-3 shadow">
                <div class="container">
                    <div class="header">
                        <h2>판매량 통계</h2>
                        <div class="export-buttons ms-auto">
                            <button class="btn btn-size btn-export">PDF 다운로드</button>
                            <button class="btn btn-size btn-export">Excel 다운로드</button>
                        </div>
                    </div>
                    <div class="filter-container">
                        <div class="filters">
                            <input type="date" class="form-control" id="startDate" placeholder="시작 날짜">
                            <input type="date" class="form-control" id="endDate" placeholder="종료 날짜">
                            <select class="form-select" id="categorySelect">
                                <option selected>전체 카테고리</option>
                                <option value="1">카테고리 1</option>
                                <option value="2">카테고리 2</option>
                            </select>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col chart-container">
                            <canvas id="last30DaysSalesChart" class="chart"></canvas>
                        </div>
                        <div class="col chart-container">
                            <canvas id="last12MonthsSalesChart" class="chart"></canvas>
                        </div>
                        <div class="col chart-container bottom-chart">
                            <canvas id="last3YearsSalesChart" class="chart"></canvas>
                        </div>
                    </div>
                </div>
            </div>

            <!-- 메인 컨텐츠 끝 -->
        </div>
    </div>
</div>
<script>
    document.addEventListener('DOMContentLoaded', function () {
        const salesPast30Days = [
            <%
            List<ChartDateDTO> list = (List<ChartDateDTO>) request.getAttribute("salesPast30Days");
            for(int i = 0; i < list.size(); i++) {
                ChartDateDTO item = list.get(i);
                out.print("{x: '" + item.getDateString() + "', y: " + item.getTotalSales() + "}");
                if (i < list.size() - 1) {
                    out.print(", ");
                }
            }
            %>
        ];

        const last12MonthsSales = [
            <%
            List<ChartDateDTO> list2 = (List<ChartDateDTO>) request.getAttribute("monthlySalesPastYear");
            for(int i = 0; i < list2.size(); i++) {
                ChartDateDTO item = list2.get(i);
                out.print("{x: '" + item.getDateString() + "', y: " + item.getTotalSales() + "}");
                if (i < list2.size() - 1) {
                    out.print(", ");
                }
            }
            %>
        ];

        const last3YearsSales = [
            <%
            List<ChartDateDTO> list3 = (List<ChartDateDTO>) request.getAttribute("yearlySalesPastThreeYears");
            for(int i = 0; i < list3.size(); i++) {
                ChartDateDTO item = list3.get(i);
                out.print("{x: '" + item.getDateString() + "', y: " + item.getTotalSales() + "}");
                if (i < list3.size() - 1) {
                    out.print(", ");
                }
            }
            %>
        ];

        // 차트 데이터 형식 변환 로직 추가
        const formatChartData = (salesData) => {
            return salesData.map(data => ({
                x: moment(data.x, 'YYYY-MM-DD').toDate(),
                y: data.y
            }));
        };

        // 최근 30일 판매 데이터 차트
        const last30DaysSalesChartContext = document.getElementById('last30DaysSalesChart').getContext('2d');
        new Chart(last30DaysSalesChartContext, {
            type: 'line',
            data: {
                datasets: [{
                    label: '최근 30일 판매액',
                    data: formatChartData(salesPast30Days),
                    fill: false,
                    borderColor: 'rgb(75, 192, 192)',
                    tension: 0.1
                }]
            },
            options: {
                scales: {
                    x: {
                        type: 'time',
                        time: {
                            unit: 'day'
                        },
                        title: {
                            display: true,
                            text: '기간'
                        }
                    },
                    y: {
                        title: {
                            display: true,
                            text: '판매액'
                        }
                    }
                }
            }
        });

        // 지난 12개월 판매 데이터 차트
        const last12MonthsSalesChartContext = document.getElementById('last12MonthsSalesChart').getContext('2d');
        new Chart(last12MonthsSalesChartContext, {
            type: 'bar',
            data: {
                datasets: [{
                    label: '지난 12개월 판매액',
                    data: formatChartData(last12MonthsSales),
                    backgroundColor: 'rgba(255, 99, 132, 0.2)',
                    borderColor: 'rgba(255, 99, 132, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    x: {
                        type: 'time',
                        time: {
                            unit: 'month',
                            tooltipFormat: 'YYYY-MM'
                        },
                        title: {
                            display: true,
                            text: '기간'
                        }
                    },
                    y: {
                        title: {
                            display: true,
                            text: '판매액'
                        }
                    }
                }
            }
        });

        // 최근 3년 판매 데이터 차트
        const last3YearsSalesChartContext = document.getElementById('last3YearsSalesChart').getContext('2d');
        new Chart(last3YearsSalesChartContext, {
            type: 'bar',
            data: {
                datasets: [{
                    label: '최근 3년 판매액',
                    data: formatChartData(last3YearsSales.map(data => ({
                        ...data,
                        period: `${data.period}-01`
                    }))),
                    backgroundColor: 'rgba(54, 162, 235, 0.2)',
                    borderColor: 'rgba(54, 162, 235, 1)',
                    borderWidth: 1
                }]
            },
            options: {
                scales: {
                    x: {
                        type: 'time',
                        time: {
                            unit: 'year',
                            tooltipFormat: 'YYYY'
                        },
                        title: {
                            display: true,
                            text: '기간'
                        }
                    },
                    y: {
                        title: {
                            display: true,
                            text: '판매액'
                        }
                    }
                }
            }
        });
    });
</script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
