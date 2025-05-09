/**
 * Mohammed A. (2025). CIS 530 Server Side Development. Bellevue University, all rights reserved.
 */

package com.bookclub.web;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;
import com.bookclub.service.impl.MongoWishlistDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/api/wishlist", produces = "application/json")
@CrossOrigin (origins = "*")
public class WishlistRestController {

    //Class property with mongowishlist object
    WishlistDao wishlistDao = new MongoWishlistDao();

    //Setter for class property
    @Autowired
    private void setWishlistDao(WishlistDao wishlistDao) {
        this.wishlistDao = wishlistDao;
    }

    /**
     * Get Method to list the wish list items
     * @return list of wish list items from the database
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<WishlistItem> showWishlist(Authentication authentication) {

        String username = authentication.getName();
        return wishlistDao.list(username);
    }

    /**
     * Get Method to find the wish list based on the input id
     * @param id input to fetch the wish list
     * @return wish list item based on id
     */
    @RequestMapping(path = "/{id}", method = RequestMethod.GET)
    public WishlistItem findById (@PathVariable String id){

        return wishlistDao.find(id);
    }
}
