package org.libermundi.recipe.repositories;

import org.libermundi.recipe.domain.Notes;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotesRepository extends CrudRepository<Notes,Long> {

}
