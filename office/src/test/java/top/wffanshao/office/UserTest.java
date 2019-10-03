package top.wffanshao.office;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import top.wffanshao.office.pojo.OfficeDbUser;
import top.wffanshao.office.service.UserService;

import java.util.HashMap;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserTest {

    @Autowired
    private UserService userService;

    @Test
    public void testLogin() {

        OfficeDbUser user = new OfficeDbUser();
        user.setUserName("winfans");
        user.setUserPasswd("12345678");
        userService.register(user);
    }
}
