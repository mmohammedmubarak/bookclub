/**
 * Mohammed A. (2025). CIS 530 Server Side Development. Bellevue University, all rights reserved.
 */

package com.bookclub.web;

import com.bookclub.model.Book;
import com.bookclub.model.BookOfTheMonth;
import com.bookclub.service.dao.BookOfTheMonthDao;
import com.bookclub.service.impl.MongoBookOfTheMonthDao;
import com.bookclub.service.impl.RestBookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping
public class HomeController {

    BookOfTheMonthDao bookOfTheMonthDao = new MongoBookOfTheMonthDao();

    @Autowired
    public void setBookOfTheMonthDao(BookOfTheMonthDao bookOfTheMonthDao) {
        this.bookOfTheMonthDao = bookOfTheMonthDao;
    }


    /**
     * Method to handle GET request to the home page
     *
     * @param model
     * @return home page
     */
    @RequestMapping(method = RequestMethod.GET)
    public String showHome(Model model) {
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int calMonth = cal.get(Calendar.MONTH) + 1;

        RestBookDao bookDao = new RestBookDao();
        List<BookOfTheMonth> monthlyBooks = bookOfTheMonthDao.list(Integer.toString(calMonth));

        StringBuilder isbnBuilder = new StringBuilder();
        isbnBuilder.append("ISBN:");

        for (BookOfTheMonth monthlyBook : monthlyBooks) {
            isbnBuilder.append(monthlyBook.getIsbn()).append(",");
        }

        String isbnString = isbnBuilder.toString().substring(0, isbnBuilder.toString().length() - 1);

        List<Book> books = bookDao.list(isbnString);

        model.addAttribute("books", books);

        return "index";
    } // end showHome

    /**
     * Method to Handle GET request using id and open view book page
     * @param id ISBN id of the book
     * @param model
     * @return view book page
     */
    @RequestMapping(method = RequestMethod.GET,value = "/{id}")
    public String getMonthlyBook(@PathVariable("id") String id, Model model) {
        RestBookDao restBookDao = new RestBookDao();
        Book book = restBookDao.find(id);
        System.out.println(book.toString());
        model.addAttribute("book",book);
        return  "monthly-books/view";
    } // end getMonthlyBook

    /**
     * Method to handle GET request to the about us page
     *
     * @param model
     * @return about page
     */
    @RequestMapping(method = RequestMethod.GET, path = "/about")
    public String showAboutUs(Model model) {
        return "about";
    } // end showAboutUs

    /**
     * Method to handle GET request to the contact us page
     *
     * @param model
     * @return contact page
     */
    @RequestMapping(method = RequestMethod.GET, path = "/contact")
    public String showContactUs(Model model) {
        return "contact";
    } // end showContactUs
}
