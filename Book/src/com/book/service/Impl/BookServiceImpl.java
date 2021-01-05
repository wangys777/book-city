package com.book.service.Impl;

import com.book.bean.Book;
import com.book.bean.Page;
import com.book.dao.BookDao;
import com.book.dao.impl.BookDaoImpl;
import com.book.service.BookService;

import java.util.List;

public class BookServiceImpl implements BookService {
    private BookDao bookDao = new BookDaoImpl();
    @Override
    public List<Book> findAllBook() {
        return bookDao.getAllBook();
    }

    @Override
    public void saveBook(Book book) {
        bookDao.saveBook(book);
    }

    @Override
    public void delBookById(String id) {
        bookDao.delBookById(id);
    }

    @Override
    public Book getBookById(String id) {
        return bookDao.getBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Page<Book> getBookByPage(String pageNo) {
        Page<Book> page = new Page<>();
        if(pageNo==null && "".equals(pageNo)){
            page.setPageNo(1);
        }else {
            page.setPageNo(Integer.parseInt(pageNo));
        }
        return bookDao.getBookByPage(page);
    }

    @Override
    public Page<Book> getBookByPageAndPrice(String pageNo, String min, String max) {
        Page<Book> page = new Page<>();
        if(pageNo==null && "".equals(pageNo)){
            page.setPageNo(1);
        }else {
            page.setPageNo(Integer.parseInt(pageNo));
        }
      /*  double min2=0,max2 = Double.MAX_VALUE;
        try {
            min2 = Double.parseDouble(min);
            max2= Double.parseDouble(max);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }*/
        return bookDao.getBookByPageAndPrice(page,min,max);
    }

}
