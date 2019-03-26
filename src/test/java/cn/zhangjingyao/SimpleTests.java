package cn.zhangjingyao;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class SimpleTests {
    @Test
    public void createPassword(){
        System.out.print(new BCryptPasswordEncoder().encode("admin"));
    }
}
