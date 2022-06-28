package com.spring.shelf.Services;

import com.spring.shelf.Entities.BookEntity;
import com.spring.shelf.Repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    @Autowired
    ShelfService shelfService;


    public BookEntity uploading(String name, String author, String description, String owner, String shelfName){
        BookEntity book = new BookEntity();
        book.setName(name);
        book.setAuthor(author);
        book.setDescription(description);
        book.setUserOwner(owner);
        book.setInternalName(owner+"_"+name);
        book.setShelf(shelfService.getShelf(owner,shelfName));
        return bookRepository.save(book);
    }

    public Set<BookEntity> getBook(String username, String name){
        return bookRepository.findByUserOwnerAndNameContains(username, name);
    }

}
