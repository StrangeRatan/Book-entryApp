package com.Ratan.my_fist_Demo.Service.impl;

import com.Ratan.my_fist_Demo.Entity.BookEntity;
import com.Ratan.my_fist_Demo.Entity.UserEntity;
import com.Ratan.my_fist_Demo.Repository.BookRepository;
import com.Ratan.my_fist_Demo.Repository.UserRepository;
import com.Ratan.my_fist_Demo.Service.BookService;
import com.Ratan.my_fist_Demo.Service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Override
    @Transactional
    public BookEntity getBook(BookEntity bookEntity, String username) {

        UserEntity user = userService.findByUsername(username);
        bookEntity.setDate(LocalDateTime.now());
        bookEntity.setUser(user);
        BookEntity saved = bookRepository.save(bookEntity);
        user.getCollection().add(saved);
        return saved;
    }

    @Override
    public List<BookEntity> seeAllBook() {
        List<BookEntity> list=new ArrayList<>();
        list.addAll(bookRepository.findAll());
        return list;
    }

    @Override
    public BookEntity seeBookById(Long id) {
        Optional<BookEntity> byId = bookRepository.findById(id);
        if(byId.isPresent()){
            return byId.get();

        }
        else{
            return null;
        }
    }

    @Override
    public BookEntity updateBookById(Long id, BookEntity newBook, String username) {
        UserEntity user = userService.findByUsername(username);
        if(user !=null){
            Optional<BookEntity> oldbook = bookRepository.findById(id);
            if(oldbook.isPresent()){
                BookEntity lestestBook=oldbook.get();
                lestestBook.setBooktitle(newBook.getBooktitle());
                lestestBook.setAuthorname(newBook.getAuthorname());
                return bookRepository.save(lestestBook);
            }
            return null;
        }

        return null;
    }

    @Override
    public void deleteBookById(Long id, String username) {
        UserEntity user = userService.findByUsername(username);
        if(user !=null){
            Optional<BookEntity> book = bookRepository.findById(id);
            if(book.isPresent()){
                BookEntity bookEntity=book.get();
                if(bookEntity.getUser().getUsername().equals(username)){
                    user.getCollection().remove(bookEntity);
                    bookRepository.delete(bookEntity);
                    userService.save(user);
                }

                }
            }

        }

}
