package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.CartDao;


@WebServlet(value="/servlet/cart/submit")
public class CartSubmitController extends HttpServlet{
	private CartDao cartDao = new CartDao();

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		HttpSession session =req.getSession(false);
		if(session.getAttribute("user")==null) {
			resp.getWriter().print("Submit Fail");
			return;
		}
	}
	
}
