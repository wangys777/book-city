package com.book.bean;

import java.util.*;

public class Cart {

    private Map<String,CartItem> map = new LinkedHashMap<>();
    private int totalCount;
    private double totalAmount;
    /**
     * 获取购物项集合
     */
    public List<CartItem> getCartItems(){
        Collection<CartItem> values = map.values();
        return new ArrayList<>(values);
    }
    /**
     * 添加Book到购物车
     */
    public void addBookToCart(Book book){
        CartItem cartItem = map.get(book.getId() + "");
        if(cartItem == null){
            //没有买过
            cartItem = new CartItem();
            cartItem.setBook(book);
            cartItem.setCount(1);
            map.put(book.getId()+"",cartItem);
        }else {
            //买过，将数量加一
            int c = cartItem.getCount()+1;
            cartItem.setAmout(c);
        }
    }
}
