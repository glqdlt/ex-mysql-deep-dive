package com.glqdlt.ex.exmysqldeepdive;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@SpringBootTest
public class CustomerServiceTest {

    @Autowired
    private CustomerService customerService;


    @Test
    void name() throws InterruptedException {
        ExecutorService pool = Executors.newFixedThreadPool(3);
        pool.submit(() -> {
            customerService.updateWithXlock(5);
        });

        Thread.sleep(500);
        pool.submit(() -> {
            customerService.updateWith(5);
        });

        pool.awaitTermination(1, TimeUnit.MINUTES);

    }
}