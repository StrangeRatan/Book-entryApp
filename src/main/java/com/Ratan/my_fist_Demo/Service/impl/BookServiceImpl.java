package com.Ratan.my_fist_Demo.Service.impl;

import com.Ratan.my_fist_Demo.Entity.BookEntity;
import com.Ratan.my_fist_Demo.Entity.UserEntity;
import com.Ratan.my_fist_Demo.Repository.BookRepository;
import com.Ratan.my_fist_Demo.Repository.UserRepository;
import com.Ratan.my_fist_Demo.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    @Override
    public BookEntity getBook(BookEntity bookEntity) {

        BookEntity newbook=new BookEntity();
        newbook.setAuthorname(bookEntity.getAuthorname());
        newbook.setBooktitle(bookEntity.getBooktitle());
        newbook.setDate(LocalDateTime.now());
        BookEntity save = bookRepository.save(newbook);
        return save;

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
    public BookEntity updateBookById(Long id,BookEntity newBook) {
        Optional<BookEntity> oldbook = bookRepository.findById(id);
        if(oldbook.isPresent()){
            BookEntity lestestBook=oldbook.get();
            lestestBook.setBooktitle(newBook.getBooktitle());
            lestestBook.setAuthorname(newBook.getAuthorname());
            return bookRepository.save(lestestBook);
        }
        return null;
    }

    @Override
    public void deleteBookById(Long id) {
     bookRepository.deleteById(id);
    }
}
