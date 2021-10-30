package filter;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebFilter(value= {"/form/index.jsp"})
public class PathRedirectFilter extends HttpFilter{

	@Override
	protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
			throws IOException, ServletException {

//		內導不改網址
//		RequestDispatcher rd = req.getRequestDispatcher("/servlet/cart");
//		rd.forward(req, res);
		
		//外導改網址
		res.sendRedirect(getServletContext().getContextPath()+"/servlet/cart");
	}
	
	

}
