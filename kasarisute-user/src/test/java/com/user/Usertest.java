package com.user;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kasarisute.KasarisuteUserApplication;
import com.kasarisute.domain.User;
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
        // User user = new User();
        // UserPw userPw = new UserPw();
        // userPw.setPassword("123445");
        // userPw.setUid("1720530751409229");
        
        // user.setUid("1720530751409229");
        // user.setMail("qianxiao0762@outlook.com");
        // user.setUserName("QianXiao");

        // user.setUserPw(userPw);

        // userRepository.save(user);

        User byUid = userRepository.findByUid(Long.parseLong("1720536827163256"));

        // Long id = byUid.getId();
        // 1720530751409229	2024-07-09 13:12:31.451587	qianxiao0762@outlook.com	5	USER	100	2024-07-09 13:12:31.538811	QianXiao	1
    }
}
