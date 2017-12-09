package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import beans.User;

/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter(urlPatterns = {"/userpage", "/adminpage", "/user_front_page.html", "/admin_front_page.html"
						, "/userreservationpage", "/adminreservationpage", "/user_reservation_page.html"
						, "/admin_reservation_page.html"})
public class LoginFilter implements Filter {
	/**
	 * @see Filter#destroy()
	 */
    @Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
    @Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		httpResponse.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); 
		httpResponse.setHeader("Pragma", "no-cache"); 
		httpResponse.setDateHeader("Expires", 0); 
		
		// pass the request along the filter chain
		String link = "index.html?error=";
		
		String path = httpRequest.getServletPath();
		
		int id = -1;
		String position = null;
		
<<<<<<< HEAD
		try {
		id = (Integer)httpRequest.getSession().getAttribute("id");
		position = (String)httpRequest.getSession().getAttribute("position");
		}
		catch(Exception e) {}
		
		if (id == -1) {
=======
		if (httpRequest.getSession(false) == null) {
>>>>>>> development
			httpResponse.sendRedirect(link + "RA");
		}
		else {		
			id = (Integer)httpRequest.getSession().getAttribute("id");
			position = (String)httpRequest.getSession().getAttribute("position");
			
			if(position.equals("admin") && !path.contains("admin")) {
				httpResponse.sendRedirect("adminpage");
			}
			else if((position.equals("student") || position.equals("faculty")) && !path.contains("user")) {
				httpResponse.sendRedirect("userpage");
			}
			else
				chain.doFilter(request, response);
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
    @Override
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
