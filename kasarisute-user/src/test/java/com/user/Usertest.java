package com.user;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.kasarisute.KasarisuteUserApplication;
// import com.kasarisute.dao.UserIdDao;
import com.kasarisute.domain.UserId;
import com.kasarisute.repositories.UserIdRepository;

@SpringBootTest(classes = KasarisuteUserApplication.class)
public class Usertest {
    @Autowired
    private UserIdRepository userIdRepository;

    @Test
    public void userRepositoryTest() {
        Page<UserId> all = userIdRepository.findAll(PageRequest.of(0, 50));
        // List<UserId> content = all.getContent();
        // System.out.println("全部内容:" + content);
        // int size = all.getSize();
        // System.out.println("size:" + size);
        // int totalPages = all.getTotalPages();
        // System.out.println("totaoPages:" + totalPages);
        // long totalElements = all.getTotalElements();
        // System.out.println("totalElements:" + totalElements);
    }
}
