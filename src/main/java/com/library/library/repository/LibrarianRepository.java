package com.library.library.repository;

import com.library.library.models.Librarian;
import org.springframework.data.repository.CrudRepository;

public interface LibrarianRepository extends CrudRepository<Librarian, Long> {
}
