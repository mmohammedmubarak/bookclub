/**
 * Mohammed A. (2025). CIS 530 Server Side Development. Bellevue University, all rights reserved.
 */

package com.bookclub.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping
public class HomeController {
    /**
     * Method to handle GET request to the home page
     *
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showHome(Model model) {
        return "index";
    } // end showHome

    /**
     * Method to handle GET request to the about us page
     *
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/about")
    public String showAboutUs(Model model) {
        return "about";
    } // end showAboutUs

    /**
     * Method to handle GET request to the contact us page
     *
     * @param model
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path = "/contact")
    public String showContactUs(Model model) {
        return "contact";
    } // end showContactUs
}
