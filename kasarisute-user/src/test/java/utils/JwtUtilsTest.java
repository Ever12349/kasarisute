package utils;

import java.util.HashMap;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.kasarisute.KasarisuteUserApplication;
import com.kasarisute.utils.JwtUtils;

import io.jsonwebtoken.Claims;

@SpringBootTest(classes = KasarisuteUserApplication.class)
public class JwtUtilsTest {

    @Autowired
    private JwtUtils jwtUtils;

    @Test
    void testGenerateToken() {
        String token = jwtUtils.generateToken(new HashMap<>(){{
            put("userName", "123");
            put("uid", "456");
            put("userCode", "789");
        }});

        System.err.println(token);

        Claims claimsByToken = jwtUtils.getClaimsByToken(token);

        System.out.println(claimsByToken.get("uid"));
        
    }
}
