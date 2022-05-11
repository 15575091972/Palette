package com.outstandingteam.palette;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.outstandingteam.palette.mapper")
public class PaletteApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaletteApplication.class, args);
    }

}
