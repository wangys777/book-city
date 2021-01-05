package com.book.servlet;

import com.book.bean.User;
import com.book.dao.UserDao;
import com.book.dao.impl.UserDaoImpl;
import com.book.service.Impl.UserServiceImpl;
import com.book.service.UserService;

import java.io.IOException;

@javax.servlet.annotation.WebServlet(name = "LoginServlet",urlPatterns ="/LoginServlet")
public class LoginServlet extends javax.servlet.http.HttpServlet {

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
     doGet(request,response);
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        //UserDao userDao = new UserDaoImpl();
        UserService userService = new UserServiceImpl();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User u = userService.login(new User(null, username, password, null));
        if(u !=null){
            response.sendRedirect(request.getContextPath()+"/pages/user/login_success.jsp");
        }else {
            request.setAttribute("msg", "用户名或密码有误，请重新输入！！！");
            request.getRequestDispatcher("/pages/user/login.jsp").forward(request, response);
        }
        }

}
