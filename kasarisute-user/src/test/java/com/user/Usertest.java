package com.user;

import javax.crypto.SecretKey;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.kasarisute.KasarisuteUserApplication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@SpringBootTest(classes = KasarisuteUserApplication.class)
public class Usertest {
    // @Autowired
    // private UserRepository userRepository;
    // @Autowired
    // private UserIdRepository userIdRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void testMain() {
        // String encode = passwordEncoder.encode("111");

        // String ecodeString = "$2a$10$OKJe2xb.iPcNWV1q67DF4ebJILw32yrg1lJ8qZwPwRwI2MK9.ZT7u";
        // String ecodeString =  "$2a$10$OKJe2xb.iPcNWV1q67DF4ebJILw32yrg1lJ8qZwPwRwI2MK9.ZT7u";
        String ecodeString = "$2a$10$5ExgcBYauIhKsAYCMBgum.hJsB6zlKS4fYH70Iyyy2zUQbZj/kA/.";

        // $2a$16$oL0cOk6az5t.CBfrnT9UYeRs5udC.rhtvXV8FtYXI1OfOI0hHXqMm
        boolean matches = passwordEncoder.matches("111", ecodeString);
        System.out.println(matches);
    }

    @Test
    public void userRepositoryTest() {
        SecretKey key = Jwts.SIG.HS256.key().build();

        // String string = key.;
        String str = Jwts.builder()
                .subject("123456")
                .signWith(key)
                .compact();

        System.out.println("JJWT:++++++" + str);

        try {
            Claims payload = Jwts.parser().verifyWith(key).build().parseSignedClaims(str).getPayload();
            System.err.println(payload);
        } catch (Exception e) {
        }
        // Jwts.parser()
    }
}
