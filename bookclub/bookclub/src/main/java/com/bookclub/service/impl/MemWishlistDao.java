package com.bookclub.service.impl;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;

import java.util.ArrayList;
import java.util.List;

public class MemWishlistDao implements WishlistDao {
    private List<WishlistItem> wishlist;
    public MemWishlistDao() {
        wishlist = new ArrayList<WishlistItem>();
        wishlist.add(new WishlistItem("9780446520805","The Notebook "));
        wishlist.add(new WishlistItem("9780446693806","A Walk to Remember"));
        wishlist.add(new WishlistItem("9780446528054","Dear John"));
    }
    @Override
    public List<WishlistItem> list() {
        return this.wishlist;
    }

    @Override
    public WishlistItem find(String key) {
        for (WishlistItem item : wishlist) {
            if(item.getIsbn().equals(key)) {
                return item;
            }
        }
        return new WishlistItem();
    }
}
