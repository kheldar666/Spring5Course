package org.libermundi.spring5webapp.repositories;

import org.libermundi.spring5webapp.model.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthorRepository extends CrudRepository<Author, Long> {
}
