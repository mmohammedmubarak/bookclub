/**
 * Mohammed A. (2025). CIS 530 Server Side Development. Bellevue University, all rights reserved.
 */

package com.bookclub.model;

import java.util.List;

public class Book {
    /**
     * Private fields for the class
     */
    private String isbn;
    private String title;
    private String description;
    private int numOfPages;
    private List<String> authors;

    /**
     * Default Constructor
     */
    public Book() {

    }

    /**
     * Argument Constructor which assigns values to the class private properties
     *
     * @param isbn
     * @param title
     * @param description
     * @param numOfPages
     * @param authors
     */
    public Book(String isbn, String title, String description, int numOfPages, List<String> authors) {
        this.isbn = isbn;
        this.title = title;
        this.description = description;
        this.numOfPages = numOfPages;
        this.authors = authors;
    }

    /**
     * Getter and setter methods for the private fields
     */
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getNumOfPages() {
        return numOfPages;
    }

    public void setNumOfPages(int numOfPages) {
        this.numOfPages = numOfPages;
    }

    public List<String> getAuthors() {
        return authors;
    }

    public void setAuthors(List<String> authors) {
        this.authors = authors;
    }

    /**
     * Overriding toString method to return the class fields
     *
     * @return class fields in the specified format
     */
    @Override
    public String toString() {
        return "Book{" +
                "isbn=" + isbn +
                ", title=" + title +
                ", description=" + description +
                ", numOfPages=" + numOfPages +
                ", authors=" + authors +
                "}";
    }
}
