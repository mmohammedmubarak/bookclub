/**
 * Mohammed A. (2025). CIS 530 Server Side Development. Bellevue University, all rights reserved.
 */

package com.bookclub.service;

import java.util.List;

public interface GenericDao<E, K> {
    List<E> list(K key); // Return list of objects of type E

    E find(K key); // Return object of type E by type K value
}
