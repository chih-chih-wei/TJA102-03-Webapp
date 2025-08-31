package com.babymate.product.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import com.babymate.product.model.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// doGet / doPost 請求進入點
public class ProductServlet extends HttpServlet {
//	覆寫 doGet:把 Get 請求轉交給 doPost 處理
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doPost(req, res);
	}

//  覆寫 doPost:把表單/動作丟進這裡處理
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		避免中文亂碼
		req.setCharacterEncoding("UTF-8");
//		向 select_page.jsp 取出 FORM標籤內的 name="action"
		String action = req.getParameter("action");

		/* =============== 查詢 =============== */
//		在 select_page.jsp FORM標籤內的 value="getOne_For_Display"
		if ("getOne_For_Display".equals(action)) {

			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/* =============== 接收請求參數(錯誤處理) =============== */
			String str = req.getParameter("product_id");
			if (str == null || str.trim().length() == 0) {
				errorMsgs.add("請輸入商品編號");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			Integer product_id = null;
			try {
				product_id = Integer.valueOf(str);
			} catch (Exception e) {
				errorMsgs.add("輸入商品類別格式錯誤");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			/* =============== 開始查詢資料 =============== */
			ProductService productSvc = new ProductService();
			ProductVO productVO = productSvc.getOneProduct(product_id);
			if (productVO == null) {
				errorMsgs.add("查無資料");
			}
			if (!errorMsgs.isEmpty()) {
				RequestDispatcher failureView = req.getRequestDispatcher("/back-end/product/select_page.jsp");
				failureView.forward(req, res);
				return;
			}

			/* =============== 查詢完成，準備轉交 =============== */
			req.setAttribute("productVO", productVO);
			String url = "/back-end/product/listOneProduct.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		/* =============== 新增 =============== */
		if ("insert".equals(action)) { // 來自addEmp.jsp請求
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);

			/* =============== 接收請求參數(錯誤處理) =============== */
			String product_no = req.getParameter("product_no");
			if (product_no == null || product_no.trim().length() == 0) {
				errorMsgs.add("商品編號：請勿空白");
			} else if (!product_no.trim().matches("^B\\d{4}$")) {
				errorMsgs.add("商品編號格式需為B加四位數字(如B0001)");
			}

			String product_name = req.getParameter("product_name").trim();
			if (product_name == null || product_name.trim().length() == 0) {
				errorMsgs.add("商品名稱請勿空白");
			}

			Integer category_id = null;
			String category_idstr = req.getParameter("category_id");
			if(category_idstr == null || category_idstr.trim().length() == 0) {
				errorMsgs.add("商品類別請勿空白");
			}else {
				try {
					category_id = Integer.valueOf(req.getParameter("category_id").trim());
				} catch (Exception e) {
					errorMsgs.add("商品類別必填，請選擇正確的類別");
				}
			}

			Double price = null;
			String pricestr = req.getParameter("price");
			if (pricestr == null || pricestr.trim().length() == 0) {
				errorMsgs.add("價格請勿空白");
			} else {
				try {
					price = Double.valueOf(req.getParameter("price").trim());
				} catch (Exception e) {
					errorMsgs.add("價格需為數字");
				}
			}

			Integer status = null;
			try {
				status = Integer.valueOf(req.getParameter("status")); // 0/1
			} catch (Exception e) {
				errorMsgs.add("狀態必填，請選擇 0 或 1");
			}

			java.sql.Timestamp status_update_time;
			try {
				String ts = req.getParameter("status_update_time");
				if (ts == null || ts.trim().isEmpty())
					throw new IllegalArgumentException();
				status_update_time = Timestamp.valueOf(ts.trim()); // 需 'yyyy-MM-dd HH:mm:ss'
			} catch (IllegalArgumentException e) {
				status_update_time = new java.sql.Timestamp(System.currentTimeMillis());
				errorMsgs.add("請輸入正確的狀態異動時間 (yyyy-MM-dd HH:mm:ss)");
			}

			byte[] product_icon = null;
			String feature_desc = req.getParameter("feature_desc");
			String spec_desc = req.getParameter("spec_desc");
			String note = req.getParameter("note");
			String remark = req.getParameter("remark");

			ProductVO productVO = new ProductVO();
			productVO.setProduct_no(product_no);
			productVO.setProduct_name(product_name);
			productVO.setCategory_id(category_id);
			productVO.setPrice(price);
			productVO.setStatus(status);
			productVO.setStatus_update_time(status_update_time);
			productVO.setProduct_icon(product_icon);
			productVO.setFeature_desc(feature_desc);
			productVO.setSpec_desc(spec_desc);
			productVO.setNote(note);
			productVO.setRemark(remark);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("productVO", productVO);
				RequestDispatcher failureVIew = req.getRequestDispatcher("/back-end/product/addProduct.jsp");
				failureVIew.forward(req, res);
				return;
			}
			/* =============== 開始新增資料 =============== */
			ProductService productSvc = new ProductService();
			productVO = productSvc.addProduct(product_no, product_name, category_id, price, status, status_update_time,
					product_icon, feature_desc, spec_desc, note, remark);

			/* =============== 新增完成，準備轉交 =============== */
			String url = "/back-end/product/listAllProduct.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}

		/* =============== 刪除 =============== */
		if ("delete".equals(action)) { // listAllProduct.jsp
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/* =============== 接收請求參數 =============== */
			Integer product_id = Integer.valueOf(req.getParameter("product_id"));
			
			/* =============== 接收請求參數 =============== */
			ProductService productSvc = new ProductService();
			productSvc.deleteProduct(product_id);
			
			/* =============== 刪除完成，準備轉交 =============== */
			String url = "/back-end/product/listAllProduct.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		}
		
		/* =============== 修改 =============== */
		if("getOne_For_Update".equals(action)) { // listAllProduct.jsp要修改商品
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/* =============== 接收請求參數 =============== */
			Integer product_id = Integer.valueOf(req.getParameter("product_id"));
			
			/* =============== 開始查詢資料 =============== */
			ProductService productSvc = new ProductService();
			ProductVO productVO = productSvc.getOneProduct(product_id);

			/* =============== 查詢完成，準備轉交 =============== */
			req.setAttribute("productVO", productVO);
			String url = "/back-end/product/update_product_input.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
		
		}
		
		
		
		if("update".equals(action)) { // update_product_input.jsp
			List<String> errorMsgs = new LinkedList<String>();
			req.setAttribute("errorMsgs", errorMsgs);
			
			/* =============== 接收請求參數 =============== */
			Integer product_id = Integer.valueOf(req.getParameter("product_id").trim());
			
			String product_no = req.getParameter("product_no");
			if (product_no == null || product_no.trim().length() == 0) {
				errorMsgs.add("商品編號：請勿空白");
			} else if (!product_no.trim().matches("^B\\d{4}$")) {
				errorMsgs.add("商品編號格式需為B加四位數字(如B0001)");
			}

			String product_name = req.getParameter("product_name").trim();
			if (product_name == null || product_name.trim().length() == 0) {
				errorMsgs.add("商品名稱請勿空白");
			}

			Integer category_id = null;
			String category_idstr = req.getParameter("category_id");
			if(category_idstr == null || category_idstr.trim().length() == 0) {
				errorMsgs.add("商品類別請勿空白");
			}else {
				try {
					category_id = Integer.valueOf(req.getParameter("category_id").trim());
				} catch (Exception e) {
					errorMsgs.add("商品類別必填，請選擇正確的類別");
				}
			}
			

			Double price = null;
			String pricestr = req.getParameter("price");
			if (pricestr == null || pricestr.trim().length() == 0) {
				errorMsgs.add("價格請勿空白");
			} else {
				try {
					price = Double.valueOf(req.getParameter("price").trim());
				} catch (Exception e) {
					errorMsgs.add("價格需為數字");
				}
			}

			Integer status = null;
			try {
				status = Integer.valueOf(req.getParameter("status")); // 0/1
			} catch (Exception e) {
				errorMsgs.add("狀態必填，請選擇 0 或 1");
			}

			java.sql.Timestamp status_update_time;
			try {
				String ts = req.getParameter("status_update_time");
				if (ts == null || ts.trim().isEmpty())
					throw new IllegalArgumentException();
				status_update_time = Timestamp.valueOf(ts.trim()); // 需 'yyyy-MM-dd HH:mm:ss'
			} catch (IllegalArgumentException e) {
				status_update_time = new java.sql.Timestamp(System.currentTimeMillis());
				errorMsgs.add("請輸入正確的狀態異動時間 (yyyy-MM-dd HH:mm:ss)");
			}

			byte[] product_icon = null;
			String feature_desc = req.getParameter("feature_desc");
			String spec_desc = req.getParameter("spec_desc");
			String note = req.getParameter("note");
			String remark = req.getParameter("remark");

			ProductVO productVO = new ProductVO();
			productVO.setProduct_no(product_no);
			productVO.setProduct_name(product_name);
			productVO.setCategory_id(category_id);
			productVO.setPrice(price);
			productVO.setStatus(status);
			productVO.setStatus_update_time(status_update_time);
			productVO.setProduct_icon(product_icon);
			productVO.setFeature_desc(feature_desc);
			productVO.setSpec_desc(spec_desc);
			productVO.setNote(note);
			productVO.setRemark(remark);

			if (!errorMsgs.isEmpty()) {
				req.setAttribute("productVO", productVO);
				RequestDispatcher failureVIew = req.getRequestDispatcher("/back-end/product/update_product_input.jsp");
				failureVIew.forward(req, res);
				return;
			} 
			
			/* =============== 開始修改資料 =============== */
			ProductService productSvc = new ProductService();
			productVO = productSvc.updateProduct(product_id, product_no, product_name, category_id, price, status, status_update_time,
					product_icon, feature_desc, spec_desc, note, remark);

			/* =============== 新增完成，準備轉交 =============== */
			req.setAttribute("productVO", productVO);
			String url = "/back-end/product/listOneProduct.jsp";
			RequestDispatcher successView = req.getRequestDispatcher(url);
			successView.forward(req, res);
			
		}

	}
}
