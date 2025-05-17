/**
 * Mohammed A. (2025). CIS 530 Server Side Development. Bellevue University, all rights reserved.
 */

package com.bookclub.service.impl;

import com.bookclub.model.Book;
import com.bookclub.service.dao.BookDao;
import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;

public class RestBookDao implements BookDao {

//    private List<Book> books;
    public RestBookDao() {
    }

    /**
     * Method to return the list of all books
     * @return list of books
     */
    @Override
    public List<Book> list(String key) {
        //Hardocoded ISBN IDs for testing
//        String isbnString = "ISBN:9780593099322,9780261102361,9780261102378,9780590302715,9780316769532";
//        Object doc = getBooksDoc(isbnString);

        Object doc = getBooksDoc(key); //Using the ISBN key from the database

        List<Book> books = new ArrayList<Book>();

        //Reading values from the JSON response
        List<String> titles = JsonPath.read(doc, "$..title");
        List<String> isbns = JsonPath.read(doc, "$..bib_key");
        List<String> infoUrls = JsonPath.read(doc, "$..info_url");

        for (int index = 0; index < titles.size(); index++) {
            books.add(new Book(isbns.get(index), titles.get(index), infoUrls.get(index)));
        }

        return books;
    }  //end list


    /**
     * Method to find a book based on the input ISBN key passed
     * @param key
     * @return Book object based on the ISBN Key passed
     */
    @Override
    public Book find(String key) {
        Object doc = getBooksDoc(key);

        //Reading values from the JSON response
        List<String> isbns = JsonPath.read(doc, "$..bib_key");
        List<String> titles = JsonPath.read(doc, "$..title");
        List<String> subtitle = JsonPath.read(doc, "$..details.subtitle");
        List<String> infoUrls = JsonPath.read(doc, "$..info_url");
        List<Integer> pages = JsonPath.read(doc, "$..details.number_of_pages");

        //Assigning default values when no values exists
        String isbn = isbns.size() > 0 ? isbns.get(0) : "N/A";
        String title = titles.size() > 0 ? titles.get(0) : "N/A";
        String desc = subtitle.size() > 0 ? subtitle.get(0) : "N/A";
        String infoUrl = infoUrls.size() > 0 ? infoUrls.get(0) : "N/A";
        int numOfPages = pages.size() > 0 ? pages.get(0) : 0;

        //Creating new book object based on retrieved values
        Book book = new Book(isbn, title, desc, infoUrl, numOfPages);

        return book;
    }

    /**
     * Method to invoke open library API and return the response
     * @param isbnString
     * @return response from the open library API
     */
    public  Object getBooksDoc(String isbnString){
        String openLibraryAPIUrl = "https://openlibrary.org/api/books";
        RestTemplate rest = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(openLibraryAPIUrl)
                .queryParam("bibkeys", isbnString)
                .queryParam("format", "json")
                .queryParam("jscmd", "details");

        HttpEntity<?> entity = new HttpEntity<>(headers);

        HttpEntity<String> response = rest.exchange(
                builder.toUriString(),
                HttpMethod.GET,
                entity,
                String.class);

        String jsonBooklist = response.getBody();

        return Configuration.defaultConfiguration().jsonProvider().parse(jsonBooklist);

    }
}
