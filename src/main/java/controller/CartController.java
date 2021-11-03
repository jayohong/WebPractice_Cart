package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartDao;
import entity.Order;
import entity.User;

@WebServlet(value = { "/servlet/cart" })
public class CartController extends HttpServlet {
	private CartDao cartDao = new CartDao();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String type = req.getParameter("type");
		type = (type == null) ? "" : type;
		RequestDispatcher rd = null;
		String rdPath;
		/*
		 * getSession(boolean create)意思是返回当前reqeust中的HttpSession
		 * ，如果当前reqeust中的HttpSession 为null， 当create为true，就创建一个新的Session，否则返回null；
		 * HttpServletRequest.getSession(true) 等同于 HttpServletRequest.getSession(),
		 * HttpServletRequest.getSession(false) 等同于 如果当前Session没有就为null；
		 */
		HttpSession session = req.getSession(false);
		switch (type) {
		case "1":
			rdPath = "/form/cart.jsp";
			rd = req.getRequestDispatcher("/form/cart.jsp");
			req.setAttribute("products", cartDao.queryProducts());
			break;
		case "2":
			rdPath = "/form/record.jsp";
			rd = req.getRequestDispatcher("/form/record.jsp");
			User user = (User) session.getAttribute("user");
			System.out.println("userId: " + user.getId());
			List<Order> orders = cartDao.queryOrdersByUserId(user.getId());

			req.setAttribute("orders", orders);
			req.setAttribute("products", cartDao.queryProducts());
			break;
		default:
			rdPath = "/form/index.jsp";
			rd = req.getRequestDispatcher("/form/index.jsp");
			req.setAttribute("products", cartDao.queryProducts());
		}
		System.out.println("CartController doGet方法進行重導");
		if (rd != null) {
			rd.forward(req, resp);
		}
		System.out.println("重導路徑: "+rdPath);

	}

	// 加入到購物車
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 因為已經有 session user 物件, 所以不需要再判斷是否表單有傳 userId
		String[] data = req.getParameterValues("data");
		if (data != null) {
			for (String d : data) {
				System.out.println(d);
			}
		}

		if (data == null) {
			// 將資料存入到 session 中 (購物車)
			HttpSession session = req.getSession();
			session.setAttribute("data", data);
			resp.sendRedirect(getServletContext().getContextPath() + "/servlet/cart");
		} else {
			HttpSession session = req.getSession();
			System.out.println("將data存入session中");
			session.setAttribute("data", data);
			resp.sendRedirect(getServletContext().getContextPath() + "/servlet/cart?type=1");
		}
	}
	/*
	 * https://ithelp.ithome.com.tw/articles/10185109 跳轉頁面的方法 主要分成兩種:
	 * 
	 * 1.forward(request, response) 2.sendRedirect() 兩種都是servlet支援跳轉頁面的方式
	 * 在這裡簡單整理一下比較:
	 * 
	 * forward(request, response) 屬於server side呼叫
	 * 定義在RequestDispatcher的介面,由request.getRequestDispatcher呼叫
	 * 內部轉址,URL不會顯示程式名稱(可設定成參數) 因是內部轉址,可以透過setAttribute傳遞參數 效率較高 適用於權限管理轉頁時使用
	 * 
	 * sendRedirect() 屬於server side呼叫 定義在HttpServletResponse 直接外部呼叫另一支程式,會顯程式名稱
	 * 定義在HttpServletResponse 效率較低(因為cilent會在request一次) 適用於跳至外部網站或回主畫面使用
	 */

}
