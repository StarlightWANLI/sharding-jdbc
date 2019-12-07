package com.wanli.shardingjdbc;

import cn.hutool.core.util.IdUtil;
import com.wanli.shardingjdbc.entity.Order;
import com.wanli.shardingjdbc.entity.OrderItem;
import com.wanli.shardingjdbc.repository.mybatis.MybatisAddressRepository;
import com.wanli.shardingjdbc.repository.mybatis.MybatisOrderItemRepository;
import com.wanli.shardingjdbc.repository.mybatis.MybatisOrderRepository;
import com.wanli.shardingjdbc.service.impl.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
public class ShardingJdbcApplicationTests {

	@Autowired
	private OrderServiceImpl orderServiceImpl;


    @Resource
    private MybatisOrderRepository mybatisOrderRepository;

    @Resource
    private MybatisOrderItemRepository mybatisOrderItemRepository;

    @Resource
    private MybatisAddressRepository mybatisAddressRepository;



    @Test
    public void init() throws SQLException {
        orderServiceImpl.initEnvironment();
    }

    @Test
    public void processSuccess() throws SQLException {
        orderServiceImpl.processSuccess();
    }


    @Test
    public void insertTest() throws SQLException {
        List<Order> orders = new ArrayList<>();
        List<OrderItem> orderItems = new ArrayList<>();
        Random random = new Random();

        for (int i = 1; i <= 10; i++) {
            //根据userid确认分库
            int userId = random.nextInt(10);

            //根据orderid确定分表
            Order order = new Order();
            order.setUserId(userId);
            order.setAddressId(i);
            order.setStatus("INSERT_TEST");
            orders.add(order);
            mybatisOrderRepository.insert(order);

            OrderItem item = new OrderItem();
            item.setOrderId(order.getOrderId());
            item.setUserId(userId);
            item.setStatus("INSERT_TEST");
            mybatisOrderItemRepository.insert(item);
        }
    }



}

