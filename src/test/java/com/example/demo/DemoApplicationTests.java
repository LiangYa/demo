package com.example.demo;

import com.example.demo.com.example.demo.entity.Test12;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoApplicationTests {

    @Test
    public void contextLoads() {
        Test12  test12 = new Test12();
        System.out.print("*****************");
        System.out.print(test12.getCount());
        System.out.print(test12.getName());
    }

}
