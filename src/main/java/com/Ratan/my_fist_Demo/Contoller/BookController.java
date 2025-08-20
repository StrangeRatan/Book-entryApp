package com.Ratan.my_fist_Demo.Contoller;

import com.Ratan.my_fist_Demo.DTO.SigupDto;
import com.Ratan.my_fist_Demo.Entity.BookEntity;
import com.Ratan.my_fist_Demo.Entity.UserEntity;
import com.Ratan.my_fist_Demo.Service.BookService;
import com.Ratan.my_fist_Demo.Service.UserService;
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

    @Autowired
    private UserService userService;

    @GetMapping("/{username}")
    ResponseEntity<?> seeAllBook(@PathVariable String username){
        UserEntity user= userService.findByUsername(username);
        List<BookEntity> all=user.getCollection();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @PostMapping("/{username}")
    ResponseEntity<?> getBook(@RequestBody BookEntity newBook,@PathVariable String username){

        BookEntity book = bookService.getBook(newBook,username);

        return new ResponseEntity<>(book,HttpStatus.CREATED);
    }

    @GetMapping("/{username}/{serialNo}")
    ResponseEntity<?> seeBookById(@PathVariable Long serialNo,@PathVariable String username){
        UserEntity user=userService.findByUsername(username);
        if(user !=null){
            BookEntity bookById = bookService.seeBookById(serialNo);
            if(bookById !=null){
                return  new ResponseEntity<>(bookById,HttpStatus.OK);
            }
            else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        }
        else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }


    }
    @PutMapping("/{username}/{serialNo}")
    ResponseEntity<?> updateBook(@PathVariable Long serialNo, @PathVariable String username, @RequestBody BookEntity newBookEntity){

        BookEntity bookEntity = bookService.updateBookById(serialNo, newBookEntity,username);
        if(bookEntity !=null){
            return new ResponseEntity<>(bookEntity,HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @DeleteMapping("/{username}/{serialNo}")
    ResponseEntity<?> deleteBookById(@PathVariable Long serialNo,@PathVariable String username){
        bookService.deleteBookById(serialNo,username);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
