package com.Ratan.my_fist_Demo.Service;

import com.Ratan.my_fist_Demo.DTO.BookDto;


import java.util.List;

public interface BookService {
    BookDto getBook(BookDto bookDto, String user);
    List<BookDto> seeAllBook();
    BookDto seeBookById(Long id);
    BookDto updateBookById(Long id, BookDto bookDto, String username);
     void  deleteBookById(Long id, String username);
}
