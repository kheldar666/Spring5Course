package org.libermundi.spring5webapp.repositories;

import org.libermundi.spring5webapp.model.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book,Long> {
}
