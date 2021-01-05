package com.book.servlet;

import com.book.bean.User;
import com.book.service.Impl.UserServiceImpl;
import com.book.service.UserService;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@WebServlet(name = "UserServlet",urlPatterns = "/UserServlet")
public class UserServlet extends BaseServlet {

    UserService userService = new UserServiceImpl();

    /**
     * 登录
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User u = userService.login(new User(null, username, password, null));
        if(u !=null){
            session.setAttribute("name",u);
            response.sendRedirect(request.getContextPath()+"/pages/user/login_success.jsp");
        }else {
            request.setAttribute("msg", "用户名或密码有误，请重新输入！！！");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        }
    }

    /**
     * 注册
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void regist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        //验证码是否正确
        HttpSession session = request.getSession();
        Object code = session.getAttribute("KAPTCHA_SESSION_KEY");
        String code1 = request.getParameter("code");
        if(code !=null && code.toString().equals(code1)) {
         //验证码正确
            session.removeAttribute("KAPTCHA_SESSION_KEY");
            boolean b = userService.checkUserName(username);
            if (b) {
                request.setAttribute("msg", "用户名已存在");
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            } else {
                int num = userService.saveUser(new User(null, username, password, email));
                if (num > 0) {
                    response.sendRedirect(request.getContextPath() + "/pages/user/regist_success.jsp");
                } else {
                    request.setAttribute("msg", "注册失败");
                    request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
                }
            }
        }else {
            //验证码有误
            request.setAttribute("msg", "验证码有误，请重新输入");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
        }
    }

    /**
     * 注销
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //将session域中的数据移除
        HttpSession session = request.getSession();
        session.removeAttribute("user");
        //将session失效
       // session.invalidate();
        response.sendRedirect(request.getContextPath()+"/index.jsp");
    }
}
