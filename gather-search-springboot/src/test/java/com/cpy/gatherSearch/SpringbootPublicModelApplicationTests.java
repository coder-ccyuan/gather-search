package com.cpy.gatherSearch;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.cpy.gatherSearch.model.entity.User;
import com.cpy.gatherSearch.service.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
class SpringbootPublicModelApplicationTests {
@Resource
    UserService userService;
    @Test
    void contextLoads() {
        QueryWrapper<User> qw = new QueryWrapper<>();
        List<User> list = userService.list(qw);
        for (User user : list) {
            System.out.println(user);
        }
    }

}
