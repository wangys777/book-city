package com.book.service;

import com.book.bean.Book;
import com.book.bean.Page;

import java.util.List;

public interface BookService {
    /**
     * 查询所有book信息
     */
    public List<Book> findAllBook();

    /**
     * 保存book
     * @param book
     */
    void saveBook(Book book);

    /**
     * 根据id删除图书
     * @param id
     */
    void delBookById(String id);

    /**
     * 通过id获取book信息
     * @param id
     * @return
     */
    Book getBookById(String id);

    /**
     * 修改book信息
     * @param book
     */
    void updateBook(Book book);

    /**
     * 分页查询Book
     * @param pageNo
     * @return
     */
    Page<Book> getBookByPage(String pageNo);


    Page<Book> getBookByPageAndPrice(String pageNo,String min,String max);
}
