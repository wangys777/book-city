package com.book.servlet;

import com.book.bean.User;
import com.book.dao.UserDao;
import com.book.dao.impl.UserDaoImpl;
import com.book.service.Impl.UserServiceImpl;
import com.book.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "RegistServlet")
public class RegistServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      doGet(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset:UTF-8");
        //UserDao userDao = new UserDaoImpl();
        UserService userService = new UserServiceImpl();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        boolean b = userService.checkUserName(username);
        if(b){
            request.setAttribute("msg","用户名已存在");
            request.getRequestDispatcher("/pages/user/regist.jsp").forward(request,response);
        }else {
            int num = userService.saveUser(new User(null, username, password, email));
            if(num >0){
             response.sendRedirect(request.getContextPath()+"/pages/user/regist_success.jsp");
            }else {
                request.setAttribute("msg", "注册失败");
                request.getRequestDispatcher("/pages/user/regist.jsp").forward(request, response);
            }
        }
    }
}
