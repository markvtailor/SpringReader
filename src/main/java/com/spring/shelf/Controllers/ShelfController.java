package com.spring.shelf.Controllers;

import com.spring.shelf.Entities.ShelfEntity;
import com.spring.shelf.Services.ShelfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/shelves")
public class ShelfController {

    @Autowired
    ShelfService shelfService;

    @PostMapping("/create")
    public ResponseEntity createShelf(@RequestParam String username, @RequestParam String shelfName){
        try {
            shelfService.createShelf(username,shelfName);
            return ResponseEntity.ok("Создана новая полка!");
        } catch (Exception e){
            return  ResponseEntity.badRequest().body("Ошибка при создании полки: " + e.getMessage());
        }

    }

    @GetMapping("/get")
    public ResponseEntity getShelf(@RequestParam String username, @RequestParam long id){
        try {
            return ResponseEntity.ok(shelfService.getShelf(username, id));
        } catch (Exception e){
            return ResponseEntity.badRequest().body("Не удалось загрузить полку" + e.getMessage());
        }
    }
}
