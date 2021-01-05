package com.book.bean;

public class CartItem {

    private Book book;
    private int count;
    private double amout;

    public CartItem() {
    }

    public CartItem(Book book, int count, double amout) {
        this.book = book;
        this.count = count;
        this.amout = amout;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getAmout() {
        return (getCount()*getBook().getPrice());
    }

    public void setAmout(double amout) {
        this.amout = amout;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "book=" + book +
                ", count=" + count +
                ", amout=" + amout +
                '}';
    }
}
