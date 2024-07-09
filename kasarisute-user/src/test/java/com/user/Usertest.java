package com.user;

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
        // BCryptPasswordEncoder encoder  = new BCryptPasswordEncoder(16);
        // String result = encoder.encode("123456");
        // String pwBCryptEncode = UserUtils.pwBCryptEncode("123456");
        // System.err.println(pwBCryptEncode);
    }
}
