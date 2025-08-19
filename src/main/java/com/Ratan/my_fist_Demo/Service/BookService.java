package com.Ratan.my_fist_Demo.Service;

import com.Ratan.my_fist_Demo.Entity.BookEntity;

import java.util.List;

public interface BookService {
    BookEntity getBook(BookEntity bookEntity);
    List<BookEntity> seeAllBook();
    BookEntity seeBookById(Long id);
    BookEntity updateBookById(Long id,BookEntity bookEntity);
     void  deleteBookById(Long id);
}
