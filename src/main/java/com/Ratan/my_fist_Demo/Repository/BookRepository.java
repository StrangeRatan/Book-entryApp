package com.Ratan.my_fist_Demo.Repository;

import com.Ratan.my_fist_Demo.Entity.BookEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookEntity,Long> {
}
