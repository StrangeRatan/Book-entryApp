package com.Ratan.my_fist_Demo.Entity;

import jakarta.persistence.*;
import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.lang.NonNull;

import java.time.LocalDateTime;

@Entity
@Table(name="Book")
public class BookEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long serialNo;

    @NonNull
    private String authorname;

    @NonNull
    private String booktitle;

    private LocalDateTime date;

    public Long getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Long serialNo) {
        this.serialNo = serialNo;
    }

    @NonNull
    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(@NonNull String authorname) {
        this.authorname = authorname;
    }

    @NonNull
    public String getBooktitle() {
        return booktitle;
    }

    public void setBooktitle(@NonNull String booktitle) {
        this.booktitle = booktitle;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
