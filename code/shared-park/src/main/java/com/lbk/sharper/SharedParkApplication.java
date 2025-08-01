package com.lbk.sharper;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author WJX
 */
@SpringBootApplication
//@MapperScan(value = "com.lbk.sharper.mapper")
public class SharedParkApplication {

    public static void main(String[] args) {
        SpringApplication.run(SharedParkApplication.class, args);
    }

}
