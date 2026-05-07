package com.example.demo.lprod.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LprodServiceTest {
    @Autowired
    LprodService service;

    @Test
    void test() {
        service.readLprodList().forEach(System.out::println);
    }
}
