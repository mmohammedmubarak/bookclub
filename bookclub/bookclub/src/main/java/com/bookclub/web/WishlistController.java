/**
 * Mohammed A. (2025). CIS 530 Server Side Development. Bellevue University, all rights reserved.
 */

package com.bookclub.web;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;
import com.bookclub.service.impl.MongoWishlistDao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/wishlist")
public class WishlistController {
    WishlistDao wishlistDao = new MongoWishlistDao();
    @Autowired
    private void setWishlistDao(WishlistDao wishlistDao) {
        this.wishlistDao = wishlistDao;
    }

    /**
     * GET Method to retrieve and show wish list
     * @param model
     * @return wishlist/list view
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showWishlist(Model model){
//        MemWishlistDao dao = new MemWishlistDao();
        List<WishlistItem> wishlist = wishlistDao.list();
        model.addAttribute("wishlist", wishlist);
        return "wishlist/list";
    } // end showWishlist

    /**
     * GET Method to navigate to new wish list form
     * @param model
     * @return wishlist/new view
     */
    @RequestMapping(method = RequestMethod.GET, path = "/new")
    public String wishlistForm(Model model){
        model.addAttribute("wishlistItem", new WishlistItem());
        return "wishlist/new";
    } // end wishlistForm

    /**
     * Method to make post call to add a new Wish list item
     * @param wishlistItem
     * @param bindingResult to validate errors
     * @return wishlist/new view
     */
    @RequestMapping(method = RequestMethod.POST)
    public String addWishlistItem(@Valid WishlistItem wishlistItem, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "wishlist/new";
        }
        else {
            wishlistDao.add(wishlistItem);
            return "redirect:/wishlist";
        }
    } // end addWishlistItem

}
