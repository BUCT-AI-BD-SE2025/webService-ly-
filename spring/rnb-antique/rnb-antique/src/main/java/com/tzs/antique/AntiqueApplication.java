package com.tzs.antique;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@EnableCaching
@Transactional
@SpringBootApplication
@MapperScan("com.tzs.antique.*.dao")
public class AntiqueApplication {

    public static void main(String[] args) {
        SpringApplication.run(AntiqueApplication.class, args);
    }

}
