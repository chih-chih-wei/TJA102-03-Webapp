<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%@ c:formatDate value="${productVO.statusUpdateTime}" pattern="yyyy-MM-dd HH:mm:ss"%> --%>
<%@ page import="com.babymate.product.model.*"%>

<% 
	//見com.emp.controller.EmpServlet.java第238行存入req的empVO物件 (此為輸入格式有錯誤時的empVO物件)
	ProductVO productVO = (ProductVO) request.getAttribute("productVO");

	//預設時間處理（避免 null）
	java.sql.Timestamp status_update_time = null;
	if (productVO != null && productVO.getStatus_update_time() != null) {
	   status_update_time = productVO.getStatus_update_time();
	} else {
	   status_update_time = new java.sql.Timestamp(System.currentTimeMillis());
	}
	
	// 若要帶 update_time（通常 DB 自動生成，不一定顯示在表單）
	java.sql.Timestamp updateTime = null;
	if (productVO != null && productVO.getUpdate_time() != null) {
	   updateTime = productVO.getUpdate_time();
	} else {
	   updateTime = new java.sql.Timestamp(System.currentTimeMillis());
	}
%>

<html>
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
<title>商品資料新增</title>

<style>
  table#table-1 {
	background-color: #CCCCFF;
    border: 2px solid black;
    text-align: center;
  }
  table#table-1 h4 {
    color: red;
    display: block;
    margin-bottom: 1px;
  }
  h4 {
    color: blue;
    display: inline;
  }

  table {
	width: 450px;
	background-color: white;
	margin-top: 1px;
	margin-bottom: 1px;
  }
  table, th, td {
    border: 0px solid #CCCCFF;
  }
  th, td {
    padding: 1px;
  }
  .xdsoft_datetimepicker .xdsoft_datepicker {
           width:  300px;   /* width:  300px; */
  }
  .xdsoft_datetimepicker .xdsoft_timepicker .xdsoft_time_box {
           height: 151px;   /* height:  151px; */
  }
  b{
  	color: red;
  }
</style>

</head>
<body bgcolor='white'>

<table id="table-1">
	<tr>
		<td>
			 <h3>商品資料新增</h3></td><td>
			 <h4><a href="<%= request.getContextPath() %>/back-end/product/select_page.jsp">
			 	<img src="<%= request.getContextPath() %>/resources/images/mother.png" width="100" height="100" border="0">回首頁</a>
			 </h4>
		</td>
	</tr>
</table>

<h3>新增商品:</h3>

<%-- 錯誤表列 --%>
<c:if test="${not empty errorMsgs}">
	<font style="color:red">請修正以下錯誤:</font>
	<ul>
		<c:forEach var="message" items="${errorMsgs}">
			<li style="color:red">${message}</li>
		</c:forEach>
	</ul>
</c:if>

<FORM METHOD="post" ACTION="<%=request.getContextPath()%>/product/product.do" name="form1">
<div>
<table>
	<tr>
		<td>商品編號:<b>*</b></td>
		<td><input type="TEXT" name="product_no" value="<%= (productVO==null)? "B0021" : productVO.getProduct_no()%>" size="45"/></td>
	</tr>
	<tr>
		<td>商品名稱:<b>*</b></td>
		<td><input type="TEXT" name="product_name"   value="<%= (productVO==null)? "電動玩具車" : productVO.getProduct_name()%>" size="45"/></td>
	</tr>
<%-- 	<jsp:useBean id="categorySvc" scope="page" class="com.babymate.category.model.CategoryService" /> --%>
	<tr>
		<td>商品類別:<b>*</b></td>
		<td>
		<select  size="1" name="category_id" style="width:314px">
			<option value="1" <%= (productVO != null && productVO.getCategory_id() != null && productVO.getCategory_id() == 1) ? "selected" : "" %>> 1：奶粉類</option>
			<option value="2" <%= (productVO != null && productVO.getCategory_id() != null && productVO.getCategory_id() == 2) ? "selected" : "" %>> 2：母嬰日常用品類</option>
			<option value="3" <%= (productVO != null && productVO.getCategory_id() != null && productVO.getCategory_id() == 3) ? "selected" : "" %>> 3：桌椅類</option>
		</select>
		<%-- <td><select size="1" name="category_id">
			<c:forEach var="categoryVO" items="${caegorySvc.all}">
				<option value="${categoryVO.category_id}" ${(productVO.category_id==category_idVO.category_id)? 'selected':'' } >${categoryVO.category_name}
			</c:forEach>
		</select></td> --%>
		</td>
	</tr>
	<tr>
		<td>價格:<b>*</b></td>
		<td><input type="TEXT" name="price"   value="<%= (productVO==null)? "3580" : productVO.getPrice()%>" size="45"/></td>
	</tr>
	<tr>
		<td>狀態<b>*</b></td>
		<td>
			<select  size="1" name="status" style="width:314px">
				<option value="1">1:上架</option>
				<option value="0">0:下架</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>狀態異動時間:<b>*</b></td>
		<td><input type="TEXT" name="status_update_time" id="status_update_time"  value="<%= status_update_time %>" size="45"/></td>
	</tr>
	<tr>
		<td>圖示:</td>
		<td><input type="FILE" name="product_icon"   value="<%= (productVO==null)? "" : productVO.getProduct_icon()%>" size="45"/></td>
	</tr>
	<tr>
		<td>特色說明:</td>
		<td><input type="TEXT" name="feature_desc"   value="<%= (productVO==null)? "適用於15公斤以下幼兒玩具，請大人在旁陪同" : productVO.getFeature_desc()%>" size="45"/></td>
	</tr>
	<tr>
		<td>規格說明:</td>
		<td><input type="TEXT" name="spec_desc"   value="<%= (productVO==null)? "測試資料" : productVO.getSpec_desc()%>" size="45"/></td>
	</tr>
	<tr>
		<td>注意事項:</td>
		<td><input type="TEXT" name="note"   value="<%= (productVO==null)? "小孩玩遊戲時，需大人全程陪同" : productVO.getNote()%>" size="45"/></td>
	</tr>
	<tr>
		<td>備註:</td>
		<td><input type="TEXT" name="remark"   value="<%= (productVO==null)? "1" : productVO.getRemark()%>" size="45"/></td>
	</tr>

	

</table>
</div>
<br>
<input type="hidden" name="action" value="insert">
<input type="submit" value="送出新增">
</FORM>



<!-- =========================================以下為 datetimepicker 之相關設定========================================== -->

<%-- <% 
	

  java.sql.Timestamp status_update_time = null;
  try {
	    status_update_time = productVO.getStatus_update_time();
   } catch (Exception e) {
	    status_update_time = new java.sql.Timestamp(System.currentTimeMillis());
   } 
%> --%>
<%-- <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.css" /> --%>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.js"></script>
<script src="<%=request.getContextPath()%>/datetimepicker/jquery.datetimepicker.full.js"></script>


<script>
        $.datetimepicker.setLocale('zh');
        $('#status_update_time').datetimepicker({
	       theme: '',              //theme: 'dark',
	       timepicker:true,       //異動時間包涵時分秒
	       step: 30,                //step: 60 (這是timepicker的預設間隔60分鐘)
	       format:'Y-m-d H:i:s',         //日期+時間格式,
		   value: '<%=status_update_time%>', // JSP從ProductVO帶入的值
           
		   <%-- $.datetimepicker.setLocale('zh');
	        $('#update_time').datetimepicker({
		       theme: '',              //theme: 'dark',
		       timepicker:true,       //異動時間包涵時分秒
		       step: 30,                //step: 60 (這是timepicker的預設間隔60分鐘)
		       format:'Y-m-d H:i:s',         //日期+時間格式,
			   value: '<%=update_time%>', // JSP從ProductVO帶入的值 --%>
        });
        
</script>
</body>
</html>