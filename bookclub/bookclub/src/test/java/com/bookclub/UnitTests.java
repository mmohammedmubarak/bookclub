package com.bookclub;

import com.bookclub.model.Book;
import com.bookclub.model.BookOfTheMonth;
import com.bookclub.service.impl.MongoBookOfTheMonthDao;
import com.bookclub.service.impl.RestBookDao;
import com.bookclub.web.HomeController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.ui.Model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class UnitTests {
    @Mock
    private RestBookDao restBookDao;

    @Mock
    private Model model;

    @InjectMocks
    private HomeController homeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Test to validate the web method Get Monthly Book
     * Mocks a book object and asserts the view name and verify the find method
     */
    @Test
    void testGetMonthlyBook() {
        String bookId = "123";
        Book mockBook = new Book("123", "Java 101", "John Blake");
        when(restBookDao.find(bookId)).thenReturn(mockBook);
        String viewName = homeController.getMonthlyBook(bookId, model);
        assertEquals("monthly-books/view", viewName);
        verify(model).addAttribute("book", mockBook);
        verify(restBookDao).find(bookId);
    }

    /**
     * Test to validate the contact us method in the home controller
     * Asserts if the right view name is returned
     */
    @Test
    void testShowContactUs() {
        String viewName = homeController.showContactUs(model);
        assertEquals("contact", viewName);
    }

    @Mock
    private MongoTemplate mongoTemplate;

    @InjectMocks
    private MongoBookOfTheMonthDao bookOfTheMonthDao;

    /**
     * Test to validate add book method
     * Verifies the save method by passing the mock book object
     */
    @Test
    void testAddBookOfTheMonth() {
        BookOfTheMonth book = new BookOfTheMonth();
        bookOfTheMonthDao.add(book);
        verify(mongoTemplate, times(1)).save(book);
    }

    /**
     * Test to validate remove book method
     * Verifies the remove method by passing mock book id
     */
    @Test
    void testRemoveBookOfTheMonth() {
        String bookId = "1";
        Query query = new Query().addCriteria(org.springframework.data.mongodb.core.query.Criteria.where("id").is(bookId));
        boolean result = bookOfTheMonthDao.remove(bookId);
        verify(mongoTemplate, times(1)).remove(query, BookOfTheMonth.class);
        assertTrue(result);
    }


}
