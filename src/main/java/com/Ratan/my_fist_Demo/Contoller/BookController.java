package com.Ratan.my_fist_Demo.Contoller;

import com.Ratan.my_fist_Demo.DTO.BookDto;
import com.Ratan.my_fist_Demo.DTO.SigupDto;
import com.Ratan.my_fist_Demo.Entity.BookEntity;
import com.Ratan.my_fist_Demo.Entity.UserEntity;
import com.Ratan.my_fist_Demo.Service.BookService;
import com.Ratan.my_fist_Demo.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/BooksEntry")
public class BookController {

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    @GetMapping("/get-allEntry")
    ResponseEntity<?> seeAllBook(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        UserEntity user= userService.findByUsername(username);
        List<BookEntity> all=user.getCollection();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @PostMapping("/insert-Book")
    ResponseEntity<?> getBook(@RequestBody BookDto newBook){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        BookDto book = bookService.getBook(newBook,username);

        return new ResponseEntity<>(book,HttpStatus.CREATED);
    }

    @GetMapping("/get-EntryBySN/{serialNo}")
    ResponseEntity<?> seeBookById(@PathVariable Long serialNo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        UserEntity user=userService.findByUsername(username);
        if(user !=null){
            BookDto bookById = bookService.seeBookById(serialNo);
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
    @PutMapping("/update-BookBySN/{serialNo}")
    ResponseEntity<?> updateBook(@PathVariable Long serialNo, @RequestBody BookDto newBookEntity){

            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String username=authentication.getName();

            BookDto bookDto = bookService.updateBookById(serialNo, newBookEntity,username);
            if(bookDto !=null){
                return new ResponseEntity<>(bookDto,HttpStatus.OK);
            }
            else{
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }



    }
    @DeleteMapping("/delete-BookBySN/{serialNo}")
    ResponseEntity<?> deleteBookById(@PathVariable Long serialNo){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username=authentication.getName();
        bookService.deleteBookById(serialNo,username);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}
