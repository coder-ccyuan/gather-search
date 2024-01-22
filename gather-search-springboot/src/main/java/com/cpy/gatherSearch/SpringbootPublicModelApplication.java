package com.cpy.gatherSearch;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@MapperScan("com.cpy.gatherSearch.dao.mapper")
public class SpringbootPublicModelApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootPublicModelApplication.class, args);
    }

}
