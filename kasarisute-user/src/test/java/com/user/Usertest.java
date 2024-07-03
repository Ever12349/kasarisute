package com.user;

import java.time.Instant;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kasarisute.KasarisuteUserApplication;
import com.kasarisute.repositories.UserIdRepository;
import com.kasarisute.repositories.UserRepository;

@SpringBootTest(classes = KasarisuteUserApplication.class)
public class Usertest {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private UserIdRepository userIdRepository;

    @Test
    public void userRepositoryTest() {

        Instant date = Instant.now();
        System.out.println("当前时间:" + date);
        // Page<User> all = userRepository.findAll(PageRequest.of(0, 50));
        // List<User> content = all.getContent();
        // System.out.println("全部内容:" + content);
        // int size = all.getSize();
        // System.out.println("size:" + size);
        // int totalPages = all.getTotalPages();
        // System.out.println("totaoPages:" + totalPages);
        // long totalElements = all.getTotalElements();
        // System.out.println("totalElements:" + totalElements);
    }
}
