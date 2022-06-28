package com.spring.shelf.Repositories;

import com.spring.shelf.Entities.ShelfEntity;
import org.springframework.data.repository.CrudRepository;

import javax.transaction.Transactional;
import java.util.Set;

public interface ShelfRepository extends CrudRepository<ShelfEntity,Long> {
    @Transactional
    Set<ShelfEntity> findByUserAndShelfNameContains(String user, String shelfName);

    @Transactional
    ShelfEntity findByUserAndShelfName(String user, String shelfName);
}
