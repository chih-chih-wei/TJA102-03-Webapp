<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.babymate.product.model.*"%>

<%
ProductVO productVO = (ProductVO) request.getAttribute("productVO"); // 67-79行要宣告
%>

<html>
<head>
<title>所有商品清單</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/product.css">
<style>
</style>
</head>
<body>
	<div class="card mb-4">
		<div class="card-header border-0">
			<table id="table-1">
				<tr>
					<td>
						<h2 class="card-title">商品資料</h2>
						<h4 style="text-align: left;">
							<a href="<%= request.getContextPath() %>/back-end/product/select_page.jsp">
							回首頁
							<img src="<%=request.getContextPath()%>/resources/images/back-icon.png"
								 width="30" height="30" border="0">
							</a>
						</h4>
					</td>
				</tr>
			</table>

			<div class="card-tools">
				<a href="#" class="btn btn-tool btn-sm"> <i
					class="bi bi-download"></i></a> <a href="#" class="btn btn-tool btn-sm">
					<i class="bi bi-list"></i>
				</a>
			</div>
		</div>

		<div class="card-body table-responsive p-0">

			<table class="table table-striped align-middle">
				<thead>
					<tr>
						<th>商品ID</th>
						<th>商品編號</th>
						<th>商品名稱</th>
						<th>商品類別ID</th>
						<th>價格</th>
						<th>狀態</th>
						<th>狀態異動時間</th>
						<th>圖示</th>
						<th>特色說明</th>
						<th>規格說明</th>
						<th>注意事項</th>
						<th>備註</th>
						<th>更新時間</th>
					</tr>
				</thead>

				<tbody>
					<tr>
						<td><%=productVO.getProduct_id()%></td> 
						<td><%=productVO.getProduct_no()%></td>
						<td><%=productVO.getProduct_name()%></td>
						<td><%=productVO.getCategory_id()%></td>
						<td><%=productVO.getPrice()%></td>
						<td><%=productVO.getStatus()%></td>
						<td><%=productVO.getStatus_update_time()%></td>
						<td><%=productVO.getProduct_icon()%></td>
						<td><%=productVO.getFeature_desc()%></td>
						<td><%=productVO.getSpec_desc()%></td>
						<td><%=productVO.getNote()%></td>
						<td><%=productVO.getRemark()%></td>
						<td><%=productVO.getUpdate_time()%></td>
					</tr>
				</tbody>
			</table>

		</div>

	</div>
</body>
</html>