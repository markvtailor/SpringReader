package com.spring.shelf.Services;

import com.spring.shelf.Entities.ShelfEntity;
import com.spring.shelf.Repositories.ShelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class ShelfService {
    @Autowired
    ShelfRepository shelfRepository;

    public void createShelf(String username, String name){
        ShelfEntity shelf = new ShelfEntity(username, name);
        shelfRepository.save(shelf);
    }

    public Set<ShelfEntity> findShelves(String username, String shelfName){
        return shelfRepository.findByUserAndShelfNameContains(username, shelfName );
    }

    public ShelfEntity getShelf(String username, String shelfName){
        return shelfRepository.findByUserAndShelfName(username, shelfName);
    }

}
