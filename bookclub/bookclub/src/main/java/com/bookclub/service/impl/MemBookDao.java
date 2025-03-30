package com.bookclub.service.impl;

import com.bookclub.model.Book;
import com.bookclub.service.dao.BookDao;

import java.util.ArrayList;
import java.util.List;

public class MemBookDao implements BookDao {

    private List<Book> books;
    public MemBookDao() {
        books = new ArrayList<Book>();
        books.add(new Book("1001","Master of the Game","A multi-generational saga about the Blackwell family, filled with ambition, power struggles, and intrigue",100, List.of("Sidney Sheldon","Author2")));
        books.add(new Book("1002","Tell Me Your Dreams","A psychological thriller exploring multiple personalities and a series of murders, keeping readers on the edge of their seats",100, List.of("Sidney Sheldon")));
        books.add(new Book("1003","Bloodline","A suspenseful story about Elizabeth Roffe, who inherits her family's pharmaceutical empire and uncovers dark secrets while fighting to save the company",100, List.of("Sidney Sheldon")));
        books.add(new Book("1004","Harry Potter and the Philosopher's Stone","Harry Potter, a young boy who discovers he's a wizard and attends Hogwarts School of Witchcraft and Wizardry",100, List.of("J.K. Rowling")));
        books.add(new Book("1005","Harry Potter and the Chamber of Secrets","In Harry's second year at Hogwarts, he encounters danger as the Chamber of Secrets is opened, unleashing a sinister force that threatens the school's students",100, List.of("J.K. Rowling")));
    }
    @Override
    public List<Book> list() {
        return this.books;
    }

    @Override
    public Book find(String key) {
        for (Book book : this.books) {
            if(book.getIsbn().equals(key)){
                return book;
            }
        }
        return new Book();
    }
}
