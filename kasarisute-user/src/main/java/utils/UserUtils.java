package utils;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Date;
import java.util.Random;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class UserUtils {
    public static String pwBCryptEncode(String pw) {
        BCryptPasswordEncoder encoder  = new BCryptPasswordEncoder(16);
        return encoder.encode(pw);
    }

    public static String generateUid(){
        long timestamp = new Date().getTime();
        Random random = new Random();
        int randomNum = random.nextInt(1000);
        return String.valueOf(timestamp) + String.format("%03d", randomNum);
    }

    public static String sha256(String str) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(str.getBytes(StandardCharsets.UTF_8));
            byte[] digest = md.digest();
            return bytesToHexString(digest);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String bytesToHexString(byte[] src) {
        StringBuilder stringBuilder = new StringBuilder("");
        if (src == null || src.length <= 0) {
            return null;
        }
        for (int i = 0; i < src.length; i++) {
            int v = src[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
}
