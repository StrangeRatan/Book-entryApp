package com.Ratan.my_fist_Demo.Service.impl;

import com.Ratan.my_fist_Demo.DTO.BookDto;
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
    public BookDto getBook(BookDto bookDto, String username) {
        UserEntity user = userService.findByUsername(username);

        BookEntity bookEntity=new BookEntity();
        bookEntity.setDate(LocalDateTime.now());
        bookEntity.setUser(user);
        bookEntity.setSerialNo(bookDto.getSerialNo());
        bookEntity.setAuthorname(bookDto.getAuthorname());
        bookEntity.setBooktitle(bookDto.getBooktitle());
        BookEntity saved = bookRepository.save(bookEntity);
        user.getCollection().add(saved);
        BookDto entry=new BookDto();
        entry.setSerialNo(saved.getSerialNo());
        entry.setAuthorname(saved.getAuthorname());
        entry.setBooktitle(saved.getBooktitle());
        entry.setDate(saved.getDate());

        return entry;
    }

    @Override
    public List<BookDto> seeAllBook() {
        List<BookDto> list=new ArrayList<>();
        List<BookEntity>  bookEntities=new ArrayList<>();
        bookEntities.addAll(bookRepository.findAll());
        for(BookEntity bookEntity : bookEntities){
            BookDto bookDto=new BookDto();
            bookDto.setSerialNo(bookEntity.getSerialNo());
            bookDto.setAuthorname(bookEntity.getAuthorname());
            bookDto.setBooktitle(bookEntity.getBooktitle());
            bookDto.setDate(bookEntity.getDate());
            list.add(bookDto);

        }
        return list;
    }

    @Override
    public BookDto seeBookById(Long id) {
        Optional<BookEntity> byId = bookRepository.findById(id);

        if(byId.isPresent()){
            BookEntity bookEntity=byId.get();
            BookDto bookDto=new BookDto();
            bookDto.setSerialNo(bookEntity.getSerialNo());
            bookDto.setAuthorname(bookEntity.getAuthorname());
            bookDto.setBooktitle(bookEntity.getBooktitle());
            bookDto.setDate(bookEntity.getDate());
           return bookDto;

        }
        else{
            return null;
        }
    }

    @Override
    public BookDto updateBookById(Long id, BookDto newBook, String username) {
        UserEntity user = userService.findByUsername(username);
        if(user !=null){
            Optional<BookEntity> oldbook = bookRepository.findById(id);
            if(oldbook.isPresent()){

                BookEntity lestestBook=oldbook.get();
                lestestBook.setBooktitle(newBook.getBooktitle());
                lestestBook.setAuthorname(newBook.getAuthorname());
                BookEntity saved = bookRepository.save(lestestBook);
                BookDto bookDto=new BookDto();
                bookDto.setSerialNo(id);
                bookDto.setAuthorname(saved.getAuthorname());
                bookDto.setBooktitle(saved.getBooktitle());
                bookDto.setDate(saved.getDate());

                return bookDto;
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
