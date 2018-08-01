<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
<!-- <link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.min.css"> -->
<script type="text/javascript" src="assets/js/core/app.js"></script>
<script type="text/javascript" src="assets/js/highstock/6.1.0/highstock.js"></script>
<script type="text/javascript" src="assets/js/highstock/6.1.0/exporting.js"></script>
<script type="text/javascript" src="assets/js/highstock/6.1.0/highcharts-zh_CN.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.min.js"></script>
<script type="text/javascript" src="https://cdn.datatables.net/rowgroup/1.0.3/js/dataTables.rowGroup.min.js"></script>
<style type="text/css">
table.dataTable thead th, table.dataTable tfoot th {
    font-weight: bold;
}
.dataTables_info{
 padding-left: 8px;
}

.td_right{
 text-align: right;
}

@media (max-width: 768px){
	.content {
		 padding: 8px 8px; 
	}
}

</style>
</head>
<body>
	<div class="content">
		 <div class="row">
			<div class="col-lg-8">
			    <div class="panel panel-flat">
				    <div id="container" style="max-width: 1060px;"></div>  
			    </div>
				<!-- Marketing campaigns -->
				<div class="panel panel-flat">
					<div class="panel-heading">
						<h6 class="panel-title">交易记录</h6>
					</div>
					<c:if test="${not empty report}">
					<div class="table-responsive">
						<table class="table table-xlg text-nowrap">
							<tbody>
								<tr>
									<td class="col-md-3">
										<div class="media-left media-middle">
											<a href="#" class="btn border-indigo-400 text-indigo-400 btn-flat btn-rounded btn-xs btn-icon"><i class="icon-cash3"></i></a>
										</div>

										<div class="media-left">
											<c:if test="${report.totalNetProfit>=0 }">
												<h5 class="text-semibold no-margin">$${report.balance} <small class="text-success text-size-base"><i class="icon-arrow-up12"></i> (+${report.totalNetProfit })</small></h5>
											</c:if>
											<c:if test="${report.totalNetProfit<=0 }">
												<h5 class="text-semibold no-margin">$${report.balance} <small class="text-success text-size-base"><i class="icon-arrow-down12"></i> (-${report.totalNetProfit })</small></h5>
											</c:if>
											<span class="text-muted"><span class="status-mark border-success position-left"></span> 账户余额</span>
										</div>
									</td>

									<td class="col-md-3">
										<div class="media-left media-middle">
											<a href="#" class="btn border-indigo-400 text-indigo-400 btn-flat btn-rounded btn-xs btn-icon"><i class="icon-pie-chart3"></i></a>
										</div>

										<div class="media-left">
											<h5 class="text-semibold no-margin">
												${report.totalTrades } <small class="display-block no-margin">交易数量</small>
											</h5>
										</div>
									</td>

									<td class="col-md-3">
										<div class="media-left media-middle">
											<a href="#" class="btn border-indigo-400 text-indigo-400 btn-flat btn-rounded btn-xs btn-icon"><i class=" icon-pie-chart4"></i></a>
										</div>

										<div class="media-left">
											<h5 class="text-semibold no-margin">
												${report.longPositions } <small class="display-block no-margin">买单数量</small>
											</h5>
										</div>
									</td>
									<td class="col-md-3">
										<div class="media-left media-middle">
											<a href="#" class="btn border-indigo-400 text-indigo-400 btn-flat btn-rounded btn-xs btn-icon"><i class=" icon-pie-chart5"></i></a>
										</div>

										<div class="media-left">
											<h5 class="text-semibold no-margin">
												${report.shortPositions } <small class="display-block no-margin">卖单数量</small>
											</h5>
										</div>
									</td>
								</tr>
							</tbody>
						</table>	
					</div>
					</c:if>
					
					<div class="table-responsive">
						<table id="datatables_order_open"  class="table text-nowrap" style="width:100%;border-top: 1px solid #ddd;" align="center"  >
					        <thead>
					           	    <tr>
										<th>入场时间</th>
										<th>类型</th>
										<th>手数</th>
										<th>交易品种</th>
										<th>入场价格</th>
										<th>出场时间</th>
										<th>出场价格</th>
										<th>库存费</th>
										<th>获利</th>
										<th>状态</th>
									</tr>
					        </thead>
					    </table>
						<table id="datatables_order_close"  class="table text-nowrap" style="border-top: 1px solid #ddd;" align="center"  >
					        <thead>
					           	    <tr>
										<th>入场时间</th>
										<th>类型</th>
										<th>手数</th>
										<th>交易品种</th>
										<th>入场价格</th>
										<th>出场时间</th>
										<th>出场价格</th>
										<th>库存费</th>
										<th>获利</th>
										<th>状态</th>
									</tr>
					        </thead>
					    </table>
					</div>
				</div>
				<!-- /marketing campaigns -->

				<!-- Support tickets -->
				
				<!-- /support tickets -->


				<!-- Latest posts -->
				
				<!-- /latest posts -->

			</div>
			<div class="col-lg-4">
				<div class="panel panel-flat">
					<div class="panel-heading" style="border-bottom:solid 1px #ddd;">
						<h6 class="panel-title">外汇行情</h6>
					</div>
					<div class="table-responsive">
						<table id="datatables_market"  class="table text-nowrap">
					        <thead>
					           	    <tr>
										<th>币种</th>
										<th>现价</th>
										<th>涨跌幅</th>
									</tr>
					        </thead>
					    </table>
					</div>
				</div>
			</div>
			<!-- Footer -->
			<div class="footer text-muted">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				&copy; 2018 <a href="${company.website }" target="_blank">${company.name }</a>
			</div>
			<!-- /footer -->
		</div>
	</div>
			
<script type="text/javascript">
$(function(){
	initChart();
	initOrderClose();
	initOrderOpen();
	initMarket();
});

function initChart(){
	var chartTitle="获利";
	$.getJSON("/user/chart",function (data) {
	    if(data.code !== 1) {
	        alert('读数据失败！');
	        return false;
	    }
	    data = data.data;
	    Highcharts.stockChart('container', {
	        title: {
	            text: chartTitle
	        },
	        scrollbar : {
                enabled : false
            },
	        plotOptions: {
	            series: {
	                showInLegend: true
	            }
	        },
	        tooltip: {
           	    split: false,
	            shared: true,
	            valueDecimals: 2,
	            formatter: function () {
	                return Highcharts.dateFormat('%Y-%m-%d %H:%M:%S', this.x)+ '<br/> ' 
	                +'<b>' + "获利: " + '</b> $' + Highcharts.numberFormat(this.y, 2)
	                
	            }
	        },
	        credits:{  
	            enabled:false  
	        },
	        exporting:{  
	        	enabled:true //用来设置是否显示‘打印’,'导出'等功能按钮，不设置时默认为显示  
	        },  
	        series: [{
	            id: 'totalNetProfit',
	            name: chartTitle,
	            data: data,
	            threshold : null,
                tooltip : {
                    valueDecimals : 2
                },
                lineWidth: 2
	        }],
	    });
	});
}


function initOrderClose(){
	var url="/user/order/close";
	$('#datatables_order_close').DataTable( {
        searching:false,
        ordering: false,
        ajax: {
            url: url
        },
        columns: [
            { data: "openTime" },
            { data: "type",render:typeRender},
            { data: "size",className:"td_right"},
            { data: "item"},
            { data: "openPrice",className:"td_right"},
            { data: "closeTime",className:"td_right"},
            { data: "closePrice",className:"td_right"},
            { data: "swap",className:"td_right"},
            { data: "profit",className:"td_right"},
            { data: "state"}
           /*  { data: "state",render:stateRender}  */
        ],
        info: true,
        paging: true,
        serverSide: true,
        pagingType: "full_numbers", 
	    lengthMenu:[10,20,50,100],
	    lengthChange: false,
	    language: {
	    	processing:   "处理中...",
	    	lengthMenu:   "显示 _MENU_ 项结果",
	    	zeroRecords:  "暂无历史交易记录",
	    	info:         "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
	    	infoEmpty:    "显示第 0 至 0 项结果，共 0 项",
	    	infoFiltered: "(由 _MAX_ 项结果过滤)",
	    	infoPostFix:  "",
	    	search:       "搜索：",
	    	url:          "",
	    	emptyTable:     "暂无历史交易记录",
	    	loadingRecords: "载入中...",
	    	infoThousands:  ",",
	    	paginate: {
	    		"sFirst":    "首页",
	    		"sPrevious": "上页",
	    		"sNext":     "下页",
	    		"sLast":     "末页"
	    	},
	    	aria: {
	    		"sSortAscending":  ": 以升序排列此列",
	    		"sSortDescending": ": 以降序排列此列"
	    	}
	    }
    } );
}

function initOrderOpen(){
	var url="/user/order/open";
	$('#datatables_order_open').DataTable( {
        searching:false,
        ordering: false,
        ajax: {
            url: url
        },
        columns: [
            { data: "openTime" },
            { data: "type",render:typeRender},
            { data: "size",className:"td_right"},
            { data: "item"},
            { data: "openPrice",className:"td_right"},
            { data: "closeTime",className:"td_right"},
            { data: "closePrice",className:"td_right"},
            { data: "swap",className:"td_right"},
            { data: "profit",className:"td_right"},
            { data: "state"}
        ],
        info: false,
        paging: false,
        serverSide: true,
        pagingType: "full_numbers", 
	    lengthMenu:[10,20,50,100],
	    lengthChange: false,
	    language: {
	    	processing:   "处理中...",
	    	lengthMenu:   "显示 _MENU_ 项结果",
	    	zeroRecords:  "当前无持仓记录",
	    	info:         "显示第 _START_ 至 _END_ 项结果，共 _TOTAL_ 项",
	    	infoEmpty:    "显示第 0 至 0 项结果，共 0 项",
	    	infoFiltered: "(由 _MAX_ 项结果过滤)",
	    	infoPostFix:  "",
	    	search:       "搜索：",
	    	url:          "",
	    	emptyTable:     "当前无持仓记录",
	    	loadingRecords: "载入中...",
	    	infoThousands:  ",",
	    	paginate: {
	    		"sFirst":    "首页",
	    		"sPrevious": "上页",
	    		"sNext":     "下页",
	    		"sLast":     "末页"
	    	},
	    	aria: {
	    		"sSortAscending":  ": 以升序排列此列",
	    		"sSortDescending": ": 以降序排列此列"
	    	}
	    }
    } );
}

function initMarket(){
	var url='http://webforex.hermes.hexun.com/forex/quotelist?code=FOREXUSDX,FOREXUSDCNY,FOREXEURUSD,FOREXUSDJPY,FOREXGBPUSD,FOREXUSDCAD,FOREXUSDCHF,FOREXAUDUSD,FOREXGBPJPY,FOREXXAUUSD&column=Name,Price,UpdownRate,PriceWeight';
	 $.ajax({
	        url: url,
	        dataType: 'jsonp',
	        jsonp: 'callback',
	        success: function (json) {
	        	var marketData = json.Data[0];
	            for (var i = 0; i < marketData.length; i++) {
	            	marketData[i][1]=marketData[i][1]/marketData[i][3];
	            	marketData[i][2]=marketData[i][2]/100+"%";
	            }
	            $('#datatables_market').DataTable( {
	         		searching:false,
	         		ordering: false,
	             	info:false,
	         		paging: false,
	         		lengthChange: false,
	         		data:marketData
	             } );
	        }
	    })
}



window.typeRender=function(data, type, row, meta) {
    if (data ==-1)
		return "balance";
	else if (data ==0)
		return "buy";
	else if (data ==1)
		return "sell";
	else if (data ==2)
		return "buy limit";
	else if (data ==3)
		return "sell limit";
	else if (data ==4)
		return "buy stop";
	else if (data ==5)
		return "sell stop";
	else if (data ==6 && row.profit>0)
		return "deposit";
	else if (data ==6 && row.profit<0)
		return "withdraw";
	else (data == "" || data == undefined)
		return "unknow";
};

window.stateRender=function(data, type, row, meta) {
 if(row.type==-1) 
	 return "";
 else  if(data=='close')
	 return '<span class="label bg-danger">'+data+'</span>';
 else
	 return '<span class="label bg-blue">'+data+'</span>'; 
};

</script>
			
</body>
</html>