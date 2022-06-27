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
    public ResponseEntity uploadBook(@RequestParam String name, @RequestParam String author, @RequestParam String description, @RequestParam String owner, @RequestParam long shelfId){
        try {
            System.out.println(name);
            System.out.println(shelfId);
            bookService.uploading(name,author,description,owner,shelfId);
            return ResponseEntity.ok("Книга загружена!");
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Ошибка при загрузке: "+e.getMessage());
        }
    }

    @GetMapping("/shelf/{id}")
    public ResponseEntity getBooksFromShelf(@PathVariable long id){
        try {
            return ResponseEntity.ok(bookService.getBooks(id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Не удалось загрузить книги с полки: " + e.getMessage());
        }
    }
}
