package com.book.servlet;

import com.book.bean.Book;
import com.book.bean.Page;
import com.book.service.BookService;
import com.book.service.Impl.BookServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookServlet",urlPatterns = "/BookServlet")
public class BookServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    /**
     * 查询所有的book
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void findAllBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> books = bookService.findAllBook();
        //将数据存放到域中
        request.setAttribute("books",books);
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);

    }
    protected void findAllBookByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pageNo = request.getParameter("pageNo");
        Page<Book> page = bookService.getBookByPage(pageNo);
        request.setAttribute("page",page);
        request.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(request,response);
    }

    /**
     * 添加图书
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
  /*  protected void saveBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String title = request.getParameter("title");
        String price = request.getParameter("price");
        String author = request.getParameter("author");
        String sales = request.getParameter("sales");
        String stock = request.getParameter("stock");
        bookService.saveBook(new Book(null,title,author,Double.parseDouble(price),Integer.parseInt(sales),Integer.parseInt(stock),null));
        response.sendRedirect(request.getContextPath()+"/BookServlet?method=findAllBook");
    }*/

    /**
     * 根据id删除图书
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void delBookById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookId = request.getParameter("id");
        bookService.delBookById(bookId);
        response.sendRedirect(request.getContextPath()+"/BookServlet?method=findAllBook");
    }

    /**
     * 通过id获取book信息
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void getBookById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookId = request.getParameter("id");
        Book book = bookService.getBookById(bookId);
        request.setAttribute("book",book);
        request.getRequestDispatcher("/pages/manager/book_update.jsp").forward(request,response);
    }

    /**
     * 修改book
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    /*protected void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String title = request.getParameter("title");
        String price = request.getParameter("price");
        String author = request.getParameter("author");
        String sales = request.getParameter("sales");
        String stock = request.getParameter("stock");
        bookService.updateBook(new Book(Integer.parseInt(id),title,author,Double.parseDouble(price),Integer.parseInt(sales),Integer.parseInt(stock),null));
        request.getRequestDispatcher("/pages/manager/book_update.jsp").forward(request,response);
    }*/

    /**
     * 增加或修改
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    protected void saveOrUpdateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bookId = request.getParameter("id");
        String title = request.getParameter("title");
        String price = request.getParameter("price");
        String author = request.getParameter("author");
        String sales = request.getParameter("sales");
        String stock = request.getParameter("stock");
        if(bookId !=null && !"".equals(bookId)){
            bookService.updateBook(new Book(Integer.parseInt(bookId),title,author,Double.parseDouble(price),Integer.parseInt(sales),Integer.parseInt(stock),null));
        }else {
            bookService.saveBook(new Book(null,title,author,Double.parseDouble(price),Integer.parseInt(sales),Integer.parseInt(stock),null));
        }
        request.getRequestDispatcher("/pages/manager/book_update.jsp").forward(request,response);
    }



}
