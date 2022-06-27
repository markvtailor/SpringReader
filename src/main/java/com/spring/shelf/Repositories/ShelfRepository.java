package com.spring.shelf.Repositories;

import com.spring.shelf.Entities.ShelfEntity;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;

public interface ShelfRepository extends CrudRepository<ShelfEntity,Long> {
    @Transactional
    ShelfEntity findByUserAndId(String user, long id);
}
