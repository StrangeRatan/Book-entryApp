package com.Ratan.my_fist_Demo.DTO;

import com.Ratan.my_fist_Demo.Entity.UserEntity;

import java.time.LocalDateTime;

public class BookDto {

    private Long serialNo;

    private String authorname;

    private String booktitle;

    private LocalDateTime date;




    public Long getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Long serialNo) {
        this.serialNo = serialNo;
    }

    public String getAuthorname() {
        return authorname;
    }

    public void setAuthorname(String authorname) {
        this.authorname = authorname;
    }

    public String getBooktitle() {
        return booktitle;
    }

    public void setBooktitle(String booktitle) {
        this.booktitle = booktitle;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }


}
