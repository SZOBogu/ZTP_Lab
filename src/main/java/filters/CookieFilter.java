//package filters;
//
//import com.google.gson.Gson;
//import java.io.IOException;
//import java.util.Base64;
//import javax.servlet.Filter;
//import javax.servlet.FilterChain;
//import javax.servlet.FilterConfig;
//import javax.servlet.ServletException;
//import javax.servlet.ServletRequest;
//import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.Cookie;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import pojos.User;
//
///**
// *
// * @author root
// */
//    @WebFilter(filterName = "LoginFilter", urlPatterns = {"/dashboard","/dashboard*"})
//    public class CookieFilter implements Filter {
//        @Override
//        public void init(FilterConfig filterConfig) throws ServletException {
//        }
//
//        @Override
//        public void doFilter(ServletRequest request, ServletResponse
//        response, FilterChain chain) throws IOException,ServletException {
//
//            HttpServletRequest req = (HttpServletRequest) request;
//            try {
//                Object userObj = req.getSession().getAttribute("user");
//                if(userObj == null) {
//                    System.out.println("Unauthorized user");
//                    throw new Exception("Unauthorized user");
//                }
//                User user = (User) userObj;
//                System.out.println(user);
//                if(!checkForUserIdCookie(req.getCookies(), user)) {
//                throw new Exception("No proper cookie");
//                }
//                chain.doFilter(request, response);
//            }
//            catch (Exception ex) {
//                Gson gson = new Gson();
//                response.setContentType("application/json;charset=UTF-8");
//
//                ((HttpServletResponse) response).setStatus(401);
//                gson.toJson(response, response.getWriter());
//            }
//        }
//
//        private boolean checkForUserIdCookie(Cookie[] cookies, User user) {
//            for (Cookie cookie : cookies) {
//                if ("userId".equals(cookie.getName())) {
//                    return new String(Base64.getDecoder()
//                    .decode(cookie.getValue().getBytes()))
//                    .equals(user.getName());
//                }
//            }
//            return false;
//        }
//
//        @Override
//        public void destroy() {
//        }
//}
