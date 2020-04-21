package com.huiyuanai.mybatisgenerator.controller;

import com.huiyuanai.mybatisgenerator.entity.Book;
import com.huiyuanai.mybatisgenerator.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @description:
 * @author: xue rui quan
 * @create: 2020-04-15 16:54
 */
@RestController
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping(value = "/selectAllBook")
    public List<Book> selectAll() {
        return bookService.selectAll();
    }

    @PostMapping(value = "/insertBook")
    public int insert(@RequestBody Book record) {
        return bookService.insert(record);
    }
}
