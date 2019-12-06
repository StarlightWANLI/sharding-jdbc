package com.cimu.shardingjdbc;


import com.cimu.shardingjdbc.entity.User;
import com.cimu.shardingjdbc.util.UserIdUtil;
import org.junit.jupiter.api.Test;

/**
 * @program: rwtcpt
 * @description: 基础测试
 * @author: wanli
 * @create: 2019-10-22 10:14
 **/
public class SimpeleTest {

    @Test
    public  void get(){
        for (int i = 0; i < 100; i++) {
            Long id = UserIdUtil.nextId();
            System.out.println("用户id:" + id);
        }
    }


}
