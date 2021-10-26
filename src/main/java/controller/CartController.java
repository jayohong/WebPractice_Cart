package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(value = {"/servlet/cart"})
public class CartController extends HttpServlet{

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}
	
	//加入到購物車
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//因為已經有 session user 物件, 所以不需要再判斷是否表單有傳 userId
		String[] data =req.getParameterValues("data");
		//將資料存入到 session 中 (購物車)
		HttpSession session = req.getSession();
		session.setAttribute("data", data);
		resp.sendRedirect(getServletContext().getContextPath()+"/servlet/cart?type=1");
		
	}
	/*
	https://ithelp.ithome.com.tw/articles/10185109
	跳轉頁面的方法
	主要分成兩種:

		1.forward(request, response)
		2.sendRedirect()
		兩種都是servlet支援跳轉頁面的方式
		在這裡簡單整理一下比較:

		forward(request, response)
		屬於server side呼叫
		定義在RequestDispatcher的介面,由request.getRequestDispatcher呼叫
		內部轉址,URL不會顯示程式名稱(可設定成參數)
		因是內部轉址,可以透過setAttribute傳遞參數
		效率較高
		適用於權限管理轉頁時使用
		
		sendRedirect()
		屬於server side呼叫
		定義在HttpServletResponse
		直接外部呼叫另一支程式,會顯程式名稱
		定義在HttpServletResponse
		效率較低(因為cilent會在request一次)
		適用於跳至外部網站或回主畫面使用
	*/
	
}