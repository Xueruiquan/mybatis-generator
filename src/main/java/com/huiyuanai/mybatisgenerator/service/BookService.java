package com.huiyuanai.mybatisgenerator.service;

import com.huiyuanai.mybatisgenerator.entity.Book;

import java.util.List;

/**
 * @description:
 * @author: xue rui quan
 * @create: 2020-04-15 15:17
 */
public interface BookService {

    int deleteByPrimaryKey(Integer id);

    int insert(Book record);

    Book selectByPrimaryKey(Integer id);

    List<Book> selectAll();

    int updateByPrimaryKey(Book record);
}
