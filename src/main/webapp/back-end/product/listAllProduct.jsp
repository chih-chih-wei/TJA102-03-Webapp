<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page import="java.util.*"%>
<%@ page import="com.babymate.product.model.*"%>

<%
ProductService productSvc = new ProductService();
List<ProductVO> list = productSvc.getAll();
pageContext.setAttribute("list", list);
%>

<html>
<head>
<title>所有商品清單</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/product.css">
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
						<h4 style="text-align:left;">
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
			<%@ include file="page1.file"%>
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
						<th>修改</th>
						<th>刪除</th>
					</tr>
				</thead>

				<tbody>
					<c:forEach var="productVO" items="${list}" begin="<%=pageIndex%>"
						end="<%=pageIndex+rowsPerPage-1%>">
						<tr>
							<td>${productVO.product_id}</td>
							<td>${productVO.product_no}</td>
							<td>${productVO.product_name}</td>
							<td>${productVO.category_id}</td>
							<td>${productVO.price}</td>
							<td>${productVO.status}</td>
							<td>${productVO.status_update_time}</td>
							<td>${productVO.product_icon}</td>
							<td>${productVO.feature_desc}</td>
							<td>${productVO.spec_desc}</td>
							<td>${productVO.note}</td>
							<td>${productVO.remark}</td>
							<td>${productVO.update_time}</td>
							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/product/product.do"
									style="margin-bottom: 0px;">
									<input type="submit" value="修改"> <input type="hidden"
										name="product_id" value="${productVO.product_id}"> <input
										type="hidden" name="action" value="getOne_For_Update">
								</FORM>
							</td>
							<td>
								<FORM METHOD="post"
									ACTION="<%=request.getContextPath()%>/product/product.do"
									style="margin-bottom: 0px;">
									<input type="submit" value="刪除"> <input type="hidden"
										name="product_id" value="${productVO.product_id}"> <input
										type="hidden" name="action" value="delete">
								</FORM>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<%@ include file="page2.file"%>
		</div>

	</div>
</body>
</html>