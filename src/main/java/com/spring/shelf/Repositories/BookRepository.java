package com.spring.shelf.Repositories;

import com.spring.shelf.Entities.BookEntity;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Set;

public interface BookRepository extends CrudRepository<BookEntity,Long> {
    @Transactional
    Set<BookEntity> findByUserOwnerAndNameContains(String userOwner, String name);
}
