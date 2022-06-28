package com.spring.shelf.Controllers;

import com.spring.shelf.Services.BookService;
import org.apache.coyote.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    BookService bookService;


    @PostMapping("/upload")
    public ResponseEntity uploadBook(@RequestParam String name, @RequestParam String author, @RequestParam String description, @RequestParam String owner, @RequestParam String shelfName){
        try {
            System.out.println(name);
            System.out.println(shelfName);
            bookService.uploading(name,author,description,owner,shelfName);
            return ResponseEntity.ok("Книга " + name + " загружена на полку " + shelfName);
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Ошибка при загрузке: "+e.getMessage());
        }
    }

    @GetMapping("/book")
    public ResponseEntity getBooksFromShelf(@RequestParam String username, @RequestParam String name){
        try {
            return ResponseEntity.ok(bookService.getBook(username, name));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Не удалось загрузить книги с полки: " + e.getMessage());
        }
    }
}
