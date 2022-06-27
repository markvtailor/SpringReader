package com.spring.shelf.Services;

import com.spring.shelf.Entities.ShelfEntity;
import com.spring.shelf.Repositories.ShelfRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

@Service
public class ShelfService {
    @Autowired
    ShelfRepository shelfRepository;

    public ShelfEntity createShelf(String username,String name){
        ShelfEntity shelf = new ShelfEntity(username, name);
        return shelfRepository.save(shelf);
    }

    public ShelfEntity getShelf(String username, long id){
        return shelfRepository.findByUserAndId(username, id);
    }
}
