/**
 * Mohammed A. (2025). CIS 530 Server Side Development. Bellevue University, all rights reserved.
 */

package com.bookclub.web;

import com.bookclub.model.WishlistItem;
import com.bookclub.service.dao.WishlistDao;
import com.bookclub.service.impl.MongoWishlistDao;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
//     * @param model
     * @return wishlist/list view
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showWishlist(){
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
    public String addWishlistItem(@Valid WishlistItem wishlistItem, BindingResult bindingResult, Authentication authentication){
        wishlistItem.setUsername(authentication.getName());
        if(bindingResult.hasErrors()){
            return "wishlist/new";
        }
        else {
            wishlistDao.add(wishlistItem);
            return "redirect:/wishlist";
        }
    } // end addWishlistItem

    /**
     * Method to show wish list item
     * @param id
     * @param model
     * @return view page
     */
    @RequestMapping(method = RequestMethod.GET, path = "/{id}")
    public String showWishlistItem(@PathVariable String id, Model model){
        WishlistItem wishlistItem = wishlistDao.find(id);
        model.addAttribute("wishlistItem", wishlistItem);
        return "wishlist/view";
    } // end showWishlistItem

    /**
     * Method to update wish list item
     * @param wishlistItem
     * @param bindingResult
     * @param authentication
     * @return view page on successful update and redirect to wishlist if any error
     */
    @RequestMapping(method = RequestMethod.POST, path = "/update")
    public String updateWishlistItem(@Valid WishlistItem wishlistItem, BindingResult bindingResult, Authentication authentication){
        wishlistItem.setUsername(authentication.getName());
        if(bindingResult.hasErrors()){
            return "wishlist/view";
        }

            wishlistDao.update(wishlistItem);
            return "redirect:/wishlist";

    } // end updateWishlistItem

    /**
     * Method to remove wish list item
     * @param id
     * @return wishlist page
     */
    @RequestMapping(method = RequestMethod.GET, path = "/remove/{id}")
    public String removeWishlistItem(@PathVariable String id){
        wishlistDao.remove(id);
        return "redirect:/wishlist";
    } // end showWishlistItem

}
