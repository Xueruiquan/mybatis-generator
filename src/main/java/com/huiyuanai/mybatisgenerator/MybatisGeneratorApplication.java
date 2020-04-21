package com.huiyuanai.mybatisgenerator;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.huiyuanai.mybatisgenerator.dao")
public class MybatisGeneratorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisGeneratorApplication.class, args);
        System.out.println("MybatisGeneratorApplication 启动成功， ｡:.ﾟヽ(｡◕‿◕｡)ﾉﾟ.:｡+ﾟ");
    }
}
