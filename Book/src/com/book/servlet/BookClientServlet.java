package com.book.servlet;

import com.book.bean.Book;
import com.book.bean.Page;
import com.book.service.BookService;
import com.book.service.Impl.BookServiceImpl;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "BookClientServlet",urlPatterns = "/BookClientServlet")
public class BookClientServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();
    protected void getAllBookByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNo = request.getParameter("pageNo");
        Page<Book> page = bookService.getBookByPage(pageNo);
        request.setAttribute("page",page);
        request.getRequestDispatcher("/pages/client/book_client.jsp").forward(request,response);
    }
    protected void getBookByPageAndPrice(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNo = request.getParameter("pageNo");
        String min = request.getParameter("min");
        String max = request.getParameter("max");
        Page<Book> page = bookService.getBookByPageAndPrice(pageNo,min,max);
        request.setAttribute("page",page);
        request.getRequestDispatcher("/pages/client/book_client.jsp").forward(request,response);
    }
}
