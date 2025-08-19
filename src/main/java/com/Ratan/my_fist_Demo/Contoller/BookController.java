package com.Ratan.my_fist_Demo.Contoller;

import com.Ratan.my_fist_Demo.Entity.BookEntity;
import com.Ratan.my_fist_Demo.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    ResponseEntity<?> seeAllBook(){
        List<BookEntity> all=bookService.seeAllBook();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @PostMapping
    ResponseEntity<?> getBook(@RequestBody BookEntity newBook){
        BookEntity book = bookService.getBook(newBook);

        return new ResponseEntity<>(book,HttpStatus.CREATED);
    }

    @GetMapping("/{serialNo}")
    ResponseEntity<?> seeBookById(@PathVariable Long serialNo){
        BookEntity bookById = bookService.seeBookById(serialNo);
        if(bookById !=null){
        return  new ResponseEntity<>(bookById,HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @PutMapping("/{serialNo}")
    ResponseEntity<?> updateBook(@PathVariable Long serialNo, @RequestBody BookEntity newBookEntity){
        BookEntity bookEntity = bookService.updateBookById(serialNo, newBookEntity);
        if(bookEntity !=null){
            return new ResponseEntity<>(bookEntity,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{serialNo}")
    ResponseEntity<?> deleteBookById(@PathVariable Long serialNo){
        bookService.deleteBookById(serialNo);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
