package com.huiyuanai.mybatisgenerator.service.impl;

import com.huiyuanai.mybatisgenerator.dao.BookMapper;
import com.huiyuanai.mybatisgenerator.entity.Book;
import com.huiyuanai.mybatisgenerator.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @description:
 * @author: xue rui quan
 * @create: 2020-04-15 15:17
 */
@Service
public class BookServiceImpl implements BookService {
    @Resource
    private BookMapper bookMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return bookMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Book record) {
        return bookMapper.insert(record);
    }

    @Override
    public Book selectByPrimaryKey(Integer id) {
        return bookMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Book> selectAll() {
        return bookMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Book record) {
        return bookMapper.updateByPrimaryKey(record);
    }
}
