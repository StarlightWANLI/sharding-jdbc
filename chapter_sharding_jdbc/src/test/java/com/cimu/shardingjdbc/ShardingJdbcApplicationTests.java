package com.cimu.shardingjdbc;

import com.cimu.shardingjdbc.entity.User;
import com.cimu.shardingjdbc.mapper.UserDao;
import com.cimu.shardingjdbc.service.UserService;
import com.cimu.shardingjdbc.util.UserIdUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class ShardingJdbcApplicationTests {
	@Autowired
	private UserService userService;

    @Autowired
    UserDao userDao;

	@Test
	public void getById() {
        User user = userService.getById(13L);
        System.out.println(user);
    }

    @Test
    public void findAll() {
        List<User> users = userDao.findAll();
        System.out.println(users);
    }

    @Rollback(false)
    @Test
    public void save() {
	    User user = new User();
     //   user.setId(21L);
        user.setId(UserIdUtil.nextId());
        user.setRealName("1111");
        user.setDelFlag("0");
        userService.save(user);
    }

    @Test
    public void batchSave() {
        List<User> users = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            //   user.setId(21L);
            Long id = UserIdUtil.nextId();
            user.setId(id);
            user.setRealName("测试");
            user.setDelFlag("0");
            users.add(user);
        }
        users.parallelStream().forEach(e->userDao.insert(e));
    }

    @Rollback(false)
    @Test
    public void update() {
        User user = new User();
        user.setId(13L);
        user.setRealName("2222");
        userService.update(user);
    }

}

