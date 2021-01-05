package com.book.dao.impl;

import com.book.bean.Book;
import com.book.bean.Page;
import com.book.dao.BaseDao;
import com.book.dao.BookDao;

import java.util.List;

public class BookDaoImpl extends BaseDao<Book> implements BookDao {
    @Override
    public List<Book> getAllBook() {
        String sql = "select id,title,author,price,sales,stock,img_path from books";
        return this.getBeanList(sql);
    }

    @Override
    public void saveBook(Book book) {
        String sql = "insert into books (title,author,price,sales,stock,img_path) value (?,?,?,?,?,?)";
        this.update(sql,book.getTitle(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());
    }

    @Override
    public void delBookById(String id) {
        String sql = "delete from books where id = ?";
        this.update(sql,id);
    }

    @Override
    public Book getBookById(String id) {
        String sql = "select id,title,author,price,sales,stock,img_path from books where id = ?";
        return this.getBean(sql,id);
    }

    @Override
    public void updateBook(Book book) {
        String sql = "update books set title=?,author=?,price=?,sales=?,img_path=? where id=?";
        this.update(sql,book.getTitle(),book.getAuthor(),book.getPrice(),book.getSales(),book.getStock(),book.getImgPath());
    }

    /**
     * 1.查询总记录数
     * @param page
     * @return
     */
    @Override
    public Page<Book> getBookByPage(Page<Book> page) {
        String sql = "select count(1) from books";
        int totalRecord = Integer.parseInt(this.getSingleValue(sql)+"");
        page.setTotalRecord(totalRecord);
        String sql1 = "select id,title,author,price,sales,stock,img_path from books where 1=1 limit ?,?";
        List<Book> books = this.getBeanList(sql1, (page.getPageNo() - 1) * Page.PAGE_SIZE, Page.PAGE_SIZE);
        page.setList(books);
        return page;
    }

    @Override
    public Page<Book> getBookByPageAndPrice(Page<Book> page, String min, String max) {
        String sql = "select count(1) from books where price between ? and ? ";
        int totalRecord = Integer.parseInt(this.getSingleValue(sql,min,max)+"");
        page.setTotalRecord(totalRecord);
        String sql1 = "select id,title,author,price,sales,stock,img_path from books where 1=1 and price between ? and ? limit ?,?";
        List<Book> books = this.getBeanList(sql1, min,max,(page.getPageNo() - 1) * Page.PAGE_SIZE, Page.PAGE_SIZE);
        page.setList(books);
        return page;
    }
}
