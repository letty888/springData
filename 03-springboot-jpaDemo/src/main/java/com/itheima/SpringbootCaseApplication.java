package com.itheima;

import com.itheima.utils.IdWorker;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * @author zhang
 * @version 1.0
 * @date 2020/4/19 10:05
 */
@SpringBootApplication
public class SpringbootCaseApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootCaseApplication.class, args);
    }

    @Bean
    public IdWorker idWorker() {
        return new IdWorker();
    }
}
