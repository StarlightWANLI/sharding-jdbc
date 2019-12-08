package com.wanli.shardingjdbc;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.wanli.shardingjdbc.entity.Order;
import com.wanli.shardingjdbc.entity.OrderItem;
import com.wanli.shardingjdbc.entity.User;
import com.wanli.shardingjdbc.repository.OrderMapper;
import com.wanli.shardingjdbc.repository.UserMapper;
import com.wanli.shardingjdbc.repository.mybatis.MybatisAddressRepository;
import com.wanli.shardingjdbc.repository.mybatis.MybatisOrderItemRepository;
import com.wanli.shardingjdbc.repository.mybatis.MybatisOrderRepository;
import com.wanli.shardingjdbc.service.impl.OrderServiceImpl;
import com.wanli.shardingjdbc.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@SpringBootTest
public class ShardingJdbcApplicationTests {

	@Autowired
	private OrderServiceImpl orderServiceImpl;

    @Autowired
    private UserServiceImpl userService;


    @Resource
    private MybatisOrderRepository mybatisOrderRepository;

    @Resource
    private MybatisOrderItemRepository mybatisOrderItemRepository;

    @Resource
    private MybatisAddressRepository mybatisAddressRepository;

    @Resource
    UserMapper userMapper;

    @Resource
    OrderMapper orderMapper;

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


    @Test
    public void initUser() throws SQLException {
        userService.initEnvironment();
    }

    @Test
    public void userTestProcessSuccess() throws SQLException {
        userService.processSuccess();
    }


    /**
     * 通用Mapper测试
     * @throws SQLException
     */
    @Test
    public void mapperTest() throws SQLException {
        List<User> users = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            User user = new User();
            user.setUserId(i);
            user.setUserName("test_mybatis_" + i);
            user.setPwd("pwd_mybatis_" + i);
            users.add(user);
        }
        userMapper.insertList(users);
    }

    /**
     * 通用Mapper测试
     * @throws SQLException
     */
    @Test
    public void findUserTest() throws SQLException {
        PageHelper.startPage(2,3);
        List<User> users = userMapper.selectAll();
        System.out.println(JSON.toJSONString(users));
    }


    /**
     * 通用Mapper测试
     * @throws SQLException
     */
    @Test
    public void findOrderTest() throws SQLException {
        PageHelper.startPage(2,3);
        List<Order> orders = orderMapper.selectAll();
        System.out.println(JSON.toJSONString(orders));
    }

    /**
     * 通用Mapper批量插入
     * @throws SQLException
     */
    @Test
    public void batchInsertOrder() throws SQLException {
        List<Order> orders = new ArrayList<>();
        Random random = new Random();
        for (int i = 1; i <= 10; i++) {
            //根据userid确认分库
            int userId = random.nextInt(10);

            //根据orderid确定分表
            Order order = new Order();
            order.setUserId(userId);
            order.setAddressId(i);
            order.setStatus("Mapper_insert");
            orders.add(order);
        }
        //批量插入，也会自动根据雪花算法生成id
        orderMapper.insertList(orders);
    }


    /**
     * 对订单表，排序后分页
     * @throws SQLException
     */
    @Test
    public void orderPage() throws SQLException {
        PageHelper.startPage(1,3);
        Example example = new Example(Order.class);
        example.orderBy("orderId");
       // example.createCriteria().andEqualTo("userId",9);
        List<Order> orders =  orderMapper.selectByExample(example);
        orders.stream().forEach(e->System.out.println(e.toString()));
    }


}

