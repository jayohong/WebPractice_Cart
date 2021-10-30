package filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDao;
import entity.User;

@WebFilter(value = {"/servlet/cart","/servlet/cart/submit"})
public class LoginFilter extends HttpFilter{
	private UserDao userDao = new UserDao();

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {		
		//是否有 user 的 session 物件資料
		HttpSession session =req.getSession();
		
		
		if(session.getAttribute("user")==null) {
			//是否有帶入登錄資訊
			String name = req.getParameter("name");
			String password = req.getParameter("password");
			
			if(name!=null && password!=null) {
				User user = userDao.loginCheck(name, password);
				if(user !=null) {//login sucess
					session.setAttribute("user", user);
					chain.doFilter(req, res);
					System.out.println("3333");
					return;
				}
			}
			RequestDispatcher rd = req.getRequestDispatcher("/form/login.jsp");
			rd.forward(req, res);
			
		}else {//by pass
			chain.doFilter(req, res);
			
		}
	}
	/*
	https://ithelp.ithome.com.tw/articles/10029611
	Session 的使用方法和介紹
	可以不使用form來達到傳值的效果
	在session沒有消失前都可以在同一個web裡存活
	一般使用在判斷使用者帳號權限,或共享資料

	1.設定值
	session.setAttribute("name", name);
	2.取值
	Object name = session.getAttribute("name");
	*/
	

}
