package com.book.dao;

import com.book.bean.Book;
import com.book.bean.Page;

import java.util.List;

public interface BookDao {

    /**
     * 查询所有book信息
     */
    public List<Book> getAllBook();

    /**
     * 保存book信息
     */
    public void saveBook(Book book);

    /**
     * 删除图书
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
     * 分页book
     * @param page
     * @return
     */
   Page<Book> getBookByPage(Page<Book> page);


   Page<Book> getBookByPageAndPrice(Page<Book> page,String min,String max);
}
