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
<html>
<head>
    <title>Title</title>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.29.1/moment.min.js"></script>
    <script src="https://fastly.jsdelivr.net/npm/chart.js"></script>
    <script src="https://fastly.jsdelivr.net/npm/chartjs-adapter-moment"></script>
    <link href="https://fastly.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet">
    <style>

        body, html {
            height: 100%; /* 전체 높이를 100%로 설정 */
            margin: 0; /* 기본 마진 제거 */
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
            justify-content: flex-end;
            gap: 10px;
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
    </style>
</head>
<body>
<jsp:include page="../common/navbar.jsp" flush="true"/>
<div class="container mt-4">
    <h2>판매량 통계</h2>
    <hr>

    <jsp:include page="../common/admin-navbar.jsp" flush="true"></jsp:include>
    <div class="container mt-4">
        <div class="row">

            <!-- 메인 컨텐츠 시작 -->
            <div class="col-md-9 shadow">
                <div class="container">
                    <div class="header">
                        <div class="export-buttons">
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
                x: moment(data.x, 'YYYY-MM-DD').toDate(), // 'period'를 날짜 객체로 변환
                y: data.y
            }));
        };

        let formatChartData1 = formatChartData(salesPast30Days);
        console.log(formatChartData1);

        // 최근 30일 판매 데이터 차트
        const last30DaysSalesChartContext = document.getElementById('last30DaysSalesChart').getContext('2d');
        new Chart(last30DaysSalesChartContext, {
            type: 'line', // 라인 차트 사용
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
                        type: 'time', // X 축 타입을 'time'으로 변경
                        time: {
                            unit: 'day' // 데이터 단위를 'day'로 설정
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
            type: 'bar', // 바 차트 사용
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
                        type: 'time', // X 축 타입을 'time'으로 변경
                        time: {
                            unit: 'month', // 데이터 단위를 'month'로 설정
                            tooltipFormat: 'YYYY-MM' // 툴팁에 표시될 날짜 형식
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
            type: 'bar', // 바 차트 사용
            data: {
                datasets: [{
                    label: '최근 3년 판매액',
                    data: formatChartData(last3YearsSales.map(data => ({
                        ...data,
                        period: `${data.period}-01` // 연간 데이터는 연도만 있으므로, 날짜 형식을 맞추기 위해 '-01'을 추가
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
                            unit: 'year', // 데이터 단위를 'year'로 설정
                            tooltipFormat: 'YYYY' // 툴팁에 표시될 날짜 형식
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
