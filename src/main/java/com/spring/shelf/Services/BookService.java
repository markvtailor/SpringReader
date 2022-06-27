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


    public BookEntity uploading(String name, String author, String description, String owner, long shelfId){
        BookEntity book = new BookEntity();
        book.setName(name);
        book.setAuthor(author);
        book.setDescription(description);
        book.setUserOwner(owner);
        book.setShelf(shelfService.getShelf(owner,shelfId));
        return bookRepository.save(book);
    }

    public Set<BookEntity> getBooks(long id){
        return bookRepository.findByShelfId(id);
    }

}
